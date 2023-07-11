package com.dh.final_project_backend;

import com.dh.final_project_backend.entity.*;
import com.dh.final_project_backend.service.OdontologoService;
import com.dh.final_project_backend.service.PacienteService;
import com.dh.final_project_backend.service.TurnoService;
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
import java.time.LocalTime;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionTurnosTest {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private MockMvc mockMvc;

    public void cargarDataSet() {
        Domicilio domicilio = new Domicilio("Septima", 123, "cund", "col", 123);
        Paciente p = pacienteService.guardar(new PacienteDTO("Andres", "Lopez", domicilio, 123, LocalDate.now()));
        this.odontologoService.guardar(new OdontologoDTO("Santiago", "Paz", "3455647"));
        turnoService.guardar(new TurnoDTO(pacienteService.guardar(pacienteService.buscar(1L)),odontologoService.guardar(odontologoService.buscar(1L)),LocalDate.now(), LocalTime.now()));

    }
    @Test
    public void listarTurnos() throws Exception {
        this.cargarDataSet();
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/final/turnos/get/all").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }
}
