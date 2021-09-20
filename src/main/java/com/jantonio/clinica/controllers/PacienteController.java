package com.jantonio.clinica.controllers;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jantonio.clinica.models.Paciente;
import com.jantonio.clinica.services.PacienteService;

@RestController
@RequestMapping("api/pacientes")
@CrossOrigin("*")
public class PacienteController {
	private static final Logger logger = Logger.getLogger(OdontologoController.class);
	@Autowired
	private PacienteService pacienteService;

	@PostMapping()
    public ResponseEntity<Paciente> guardar(@RequestBody Paciente paciente) {
		Paciente response = pacienteService.crear(paciente);
    	logger.info("Se guardo el paciente "+paciente);
    	return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscar(@PathVariable Long id) {
    	Paciente paciente = pacienteService.buscar(id).orElse(null);
        if(paciente == null) {
        	logger.warn("No se encontro un paciente con el id: "+id);
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        logger.info("Devolvemos el paciente "+ paciente);
        return ResponseEntity.ok(paciente);
    }

    @PutMapping()
    public ResponseEntity<Paciente> actualizar(@RequestBody Paciente paciente) {
        if ( paciente.getId() != null && pacienteService.buscar(paciente.getId()).isPresent()) {
        	if(pacienteService.editar(paciente) == null)
        	 logger.info("Se actualizo con exito, nuevo paciente "+paciente);
            return ResponseEntity.ok(pacienteService.editar(paciente));
        }
       logger.warn("No existe el odontologo con id: "+ paciente.getId());
       return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
    	Optional<Paciente> o = pacienteService.buscar(id);
        if (o.isPresent()) {
        	pacienteService.eliminar(id);
            logger.info("Se elimino con exito al paciente "+o.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        }
        logger.warn("No existe el paciente con id: "+id);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @GetMapping
    public ResponseEntity<List<Paciente>> listar(){
    	List<Paciente> pacientes= pacienteService.listar();
		logger.info("Devolvemos una lista de "+pacientes.size()+" pacientes");
		return ResponseEntity.ok(pacientes);
    }
}
