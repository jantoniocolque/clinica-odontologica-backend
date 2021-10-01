package com.jantonio.clinica.controllers;

import java.time.LocalDate;
import java.util.List;
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

import com.jantonio.clinica.models.Turno;
import com.jantonio.clinica.models.dto.TurnoDTO;
import com.jantonio.clinica.services.TurnoService;

@RestController
@RequestMapping("api/turnos")
@CrossOrigin("*")
public class TurnoController {
	@Autowired
	private TurnoService turnoService;
	
	@GetMapping
	public ResponseEntity<List<Turno>> listar(){
		return ResponseEntity.ok(turnoService.listar());
	}
	
	@PostMapping
	public ResponseEntity<Turno> guardar(@RequestBody TurnoDTO turnodto){
		return ResponseEntity.ok(turnoService.crear(turnodto));
	}
	@GetMapping("/{id}")
	public ResponseEntity<Turno> buscar(@PathVariable Long id){
		Turno turno = turnoService.buscar(id).orElse(null);
		if(turno == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(turno);
	}
	@PutMapping
	public ResponseEntity<Turno> actualizar(@RequestBody TurnoDTO turnodto) {
		if ( turnodto.getId() != null && turnoService.buscar(turnodto.getId()).isPresent()) {
            return ResponseEntity.ok(turnoService.editar(turnodto));
        }
       return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@GetMapping("/semana")
	public ResponseEntity<List<Turno>> turnosSemanaProxima(@RequestParam("queryFecha") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate fecha){
        return ResponseEntity.ok(turnoService.turnosEntreFechas(fecha, fecha.plusDays(7)));
	}
	
	@GetMapping("/fecha")
	public ResponseEntity<List<Turno>> turnosPorFecha(@RequestParam("queryFecha") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate fecha){
		System.out.println(fecha);
		return ResponseEntity.ok(turnoService.turnoPorFecha(fecha));
	}
	
	
	@GetMapping("/odontologo/{id}")
    public ResponseEntity<List<Turno>> listarPorOdontologo(@PathVariable Long id){
    	return ResponseEntity.ok(turnoService.buscarPorOdontologo(id));
    }
	
	@GetMapping("/paciente/{id}")
    public ResponseEntity<List<Turno>> listarPorPaciente(@PathVariable Long id){
    	return ResponseEntity.ok(turnoService.buscarPorPaciente(id));
    }
	
	@GetMapping("/odontologo")
    public ResponseEntity<List<Turno>> listarPorNombreOdontologo(@RequestParam("queryNombre") String nombre){
    	return ResponseEntity.ok(turnoService.buscarPorNombreOdontologo(nombre));
    }
	
	@GetMapping("/paciente")
    public ResponseEntity<List<Turno>> listarPorNombrePaciente(@RequestParam("queryNombre") String nombre){
    	return ResponseEntity.ok(turnoService.buscarPorNombrePaciente(nombre));
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminar(@PathVariable Long id){
		turnoService.eliminar(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
	}
	
}
