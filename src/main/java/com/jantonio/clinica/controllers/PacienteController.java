package com.jantonio.clinica.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jantonio.clinica.models.dto.PacienteDTO;
import com.jantonio.clinica.services.PacienteService;

@RestController
@RequestMapping("api/pacientes")
@CrossOrigin("*")
public class PacienteController {
	private static final Logger logger = Logger.getLogger(OdontologoController.class);
	
	@Autowired
    ObjectMapper mapper;
	
	@Autowired
	private PacienteService pacienteService;

	@PostMapping()
    public ResponseEntity<PacienteDTO> guardar(@RequestBody PacienteDTO pacientedto) {
		PacienteDTO response = pacienteService.crear(pacientedto);
    	logger.info("Se guardo el paciente "+pacientedto);
    	return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> buscar(@PathVariable Long id) throws Exception {
    	PacienteDTO paciente = pacienteService.buscar(id).orElse(null);
        if(paciente == null) {
        	logger.warn("No se encontro un paciente con el id: "+id);
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        logger.info("Devolvemos el paciente "+ paciente);
        return ResponseEntity.ok(paciente);
    }

    @PutMapping()
    public ResponseEntity<PacienteDTO> actualizar(@RequestBody PacienteDTO pacientedto) throws Exception {
        if ( pacientedto.getId() != null && pacienteService.buscar(pacientedto.getId()).isPresent()) {
        	if(pacienteService.editar(pacientedto) == null)
        	 logger.info("Se actualizo con exito, nuevo paciente "+pacientedto);
            return ResponseEntity.ok(pacienteService.editar(pacientedto));
        }
       logger.warn("No existe el odontologo con id: "+ pacientedto.getId());
       return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) throws Exception {
    	Optional<PacienteDTO> o = pacienteService.buscar(id);
        if (o.isPresent()) {
        	pacienteService.eliminar(id);
            logger.info("Se elimino con exito al paciente "+o.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        }
        logger.warn("No existe el paciente con id: "+id);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    
    @GetMapping
    public ResponseEntity<List<PacienteDTO>> listar(){
    	List<PacienteDTO> pacientes= pacienteService.listar();
		logger.info("Devolvemos una lista de "+pacientes.size()+" pacientes");
		return ResponseEntity.ok(pacientes);
    }
    
    @GetMapping("/fechaAlta")
    public ResponseEntity<List<PacienteDTO>> listarPorFechaAlta(@RequestParam("queryFecha") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate fecha){
    	return ResponseEntity.ok(pacienteService.buscarPorFechaAlta(fecha));
    }
    
    @GetMapping("/tratamiento")
    public ResponseEntity<List<PacienteDTO>> listarSinFechaAlta(){
    	return ResponseEntity.ok(pacienteService.buscarPorFechaAlta(null));
    }
}
