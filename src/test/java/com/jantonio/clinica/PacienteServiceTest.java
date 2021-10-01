package com.jantonio.clinica;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import com.jantonio.clinica.models.Domicilio;
import com.jantonio.clinica.models.dto.PacienteDTO;
import com.jantonio.clinica.services.PacienteService;

@SpringBootTest
public class PacienteServiceTest {
	@Autowired
	private PacienteService pacienteService;
	public void cargarDataSet() {
		LocalDate fechaAlta = LocalDate.of(2020,10,10);
        Domicilio domicilio = new Domicilio("Av Santa fe", 444, "CABA", "Buenos Aires");
        PacienteDTO pacientedto = new PacienteDTO();
        pacientedto.setNombre("Alex");
        pacientedto.setFechaAlta(fechaAlta);
        pacientedto.setCalle(domicilio.getCalle());
        pacientedto.setNumero(domicilio.getNumero());
        pacientedto.setLocalidad(domicilio.getLocalidad());
        pacientedto.setProvincia(domicilio.getProvincia());
        pacienteService.crear(pacientedto);
    }
	/*Probar cada test por separado dado que el metodo de cargar un paciente se encuentra en cada test*/
	@Test
    public void testGetListPacientes(){
		this.cargarDataSet();
		List<PacienteDTO> pacientes = pacienteService.listar();
		assertTrue(pacientes.size() == 1);
	}
	
	@Test
	public void testGetPaciente() throws Exception {
		this.cargarDataSet();
		Optional<PacienteDTO> paciente = pacienteService.buscar(1L);
		assertNotNull(paciente.get());
	}
	@Test
	public void testSavePaciente() throws Exception {
		this.cargarDataSet();
		Optional<PacienteDTO> paciente = pacienteService.buscar(1L);
		assertTrue(paciente.get().getNombre() == "Alex");
	}
	
	@Test
	public void testDeletePaciente() throws Exception {
		this.cargarDataSet();
		pacienteService.eliminar(1L);
		
		assertThrows(Exception.class, () -> {
			   pacienteService.buscar(1L);
		});
	}
	
	@Test
	public void testEditPaciente()  throws Exception {
		this.cargarDataSet();
		PacienteDTO pacienteOriginal = pacienteService.buscar(1L).get();
		PacienteDTO pacienteAEditar = pacienteService.buscar(1L).get();
		pacienteAEditar.setNombre("Joel");
		PacienteDTO pacienteEditado = pacienteService.editar(pacienteAEditar);
		
		assertFalse( pacienteOriginal.getNombre().equalsIgnoreCase(pacienteEditado.getNombre()));
	}
	
	@Test
	public void testGetPacienteByDate() {
		this.cargarDataSet();
		LocalDate fechaAlta = LocalDate.of(2020,10,10);
		List<PacienteDTO> pacienteBaseDatos = pacienteService.buscarPorFechaAlta(fechaAlta);
		
		assertNotNull(pacienteBaseDatos);
		assertTrue(pacienteBaseDatos.size() ==1);
	}
}
