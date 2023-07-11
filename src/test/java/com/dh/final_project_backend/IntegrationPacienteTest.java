package com.dh.final_project_backend;


import com.dh.final_project_backend.entity.Domicilio;
import com.dh.final_project_backend.entity.Paciente;
import com.dh.final_project_backend.entity.PacienteDTO;
import com.dh.final_project_backend.service.DomicilioService;
import com.dh.final_project_backend.service.PacienteService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegrationPacienteTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private DomicilioService domicilioService;

    public void cargarDataSet() {
        Domicilio domicilio = new Domicilio("Septima", 123, "cund", "col", 123);
        Paciente p = pacienteService.guardar(new PacienteDTO("Andres", "Lopez", domicilio, 123, LocalDate.now()));
    }
    @Test
    public void listarPacientes() throws Exception {
        this.cargarDataSet();
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/{id}", 1).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }
}
