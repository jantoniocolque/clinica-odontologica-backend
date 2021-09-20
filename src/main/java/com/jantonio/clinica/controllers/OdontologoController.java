package com.jantonio.clinica.controllers;

import org.apache.log4j.Logger;
import java.util.List;
import java.util.Optional;

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

import com.jantonio.clinica.models.Odontologo;
import com.jantonio.clinica.models.Paciente;
import com.jantonio.clinica.services.OdontologoService;

@RestController
@RequestMapping("api/odontologos")
@CrossOrigin("*")
public class OdontologoController {
	private static final Logger logger = Logger.getLogger(OdontologoController.class);
	@Autowired
	private OdontologoService odontologoService;

    @PostMapping()
    public ResponseEntity<Odontologo> guardar(@RequestBody Odontologo odontologo) {
    	Odontologo response = odontologoService.crear(odontologo);
    	logger.info("Se guardo el odontologo "+odontologo);
    	return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscar(@PathVariable Long id) {
        Odontologo odontologo = odontologoService.buscar(id).orElse(null);
        if(odontologo == null) {
        	logger.warn("No se encontro un odontologo con el id: "+id);
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        logger.info("Devolvemos el odontologo "+ odontologo);
        return ResponseEntity.ok(odontologo);
    }

    @PutMapping()
    public ResponseEntity<Odontologo> actualizar(@RequestBody Odontologo odontologo) {
        if ( odontologo.getId() != null && odontologoService.buscar(odontologo.getId()).isPresent()) {
        	if(odontologoService.editar(odontologo) == null)
        	 logger.info("Se actualizo con exito, nuevo odontologo "+odontologo);
            return ResponseEntity.ok(odontologoService.editar(odontologo));
        }
       logger.warn("No existe el odontologo con id: "+ odontologo.getId());
       return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
    	Optional<Odontologo> o = odontologoService.buscar(id);
        if (o.isPresent()) {
            odontologoService.eliminar(id);
            logger.info("Se elimino con exito al odontologo "+o.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        }
        logger.warn("No existe el odontologo con id: "+id);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @GetMapping
    public ResponseEntity<List<Odontologo>> listar(){
    	List<Odontologo> odontologos= odontologoService.listar();
		logger.info("Devolvemos una lista de "+odontologos.size()+" odontologos");
		return ResponseEntity.ok(odontologos);
    }
    
    /*@PostMapping("/paciente")
    public ResponseEntity<Odontologo> guardarPaciente(@RequestBody Paciente paciente){
    	Odontologo o = paciente.getOdontologo();
    	o.addPaciente(paciente);
    	return ResponseEntity.ok(o);
    }*/
}
