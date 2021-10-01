package com.jantonio.clinica;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.jantonio.clinica.controllers.PacienteController;
import com.jantonio.clinica.models.*;
import com.jantonio.clinica.models.dto.PacienteDTO;
import com.jantonio.clinica.services.PacienteService;



@RunWith(SpringRunner.class)
@SpringBootTest
@WebMvcTest(PacienteController.class)
@AutoConfigureMockMvc(addFilters = false)
public class IntegrationPacienteTest {
	  @Autowired
	  private MockMvc mockMvc;
	  @Autowired
	  private PacienteService pacienteService;
	  
	  public void cargarDataSet() {
	        Domicilio domicilio = new Domicilio("Av Santa fe",444, "CABA", "Buenos Aires");
	        PacienteDTO pacientedto = new PacienteDTO();
	        pacientedto.setNombre("Alex");
	        pacientedto.setCalle(domicilio.getCalle());
	        pacientedto.setNumero(domicilio.getNumero());
	        pacientedto.setLocalidad(domicilio.getLocalidad());
	        pacientedto.setProvincia(domicilio.getProvincia());
	        pacienteService.crear(pacientedto);
	    }
	  
	  @Test
	    public void listarPaciente() throws Exception {
	        this.cargarDataSet();
	        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/api/pacientes/{id}", 1).accept(MediaType.APPLICATION_JSON))
	                .andDo(MockMvcResultHandlers.print())
	                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
	
	        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
	    }
	  
	  @Test
	    public void lsitarPacientes() throws Exception {
	        this.cargarDataSet();
	        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/api/pacientes").accept(MediaType.APPLICATION_JSON))
	                .andDo(MockMvcResultHandlers.print())
	                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

	        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
	    }
}
