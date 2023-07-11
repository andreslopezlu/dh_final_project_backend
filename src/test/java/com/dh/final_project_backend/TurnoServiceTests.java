package com.dh.final_project_backend;

import com.dh.final_project_backend.entity.*;
import com.dh.final_project_backend.service.OdontologoService;
import com.dh.final_project_backend.service.PacienteService;
import com.dh.final_project_backend.service.TurnoService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class TurnoServiceTests {

    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;

    public void cargarDataSet() {
        Domicilio domicilio = new Domicilio("Septima", 123, "cund", "col", 123);
        Paciente p = pacienteService.guardar(new PacienteDTO("Andres", "Lopez", domicilio, 123, LocalDate.now()));
        this.odontologoService.guardar(new OdontologoDTO("Santiago", "Paz", "3455647"));
    }
    @Test
    public void altaTurnoTest(){
        this.cargarDataSet();
        turnoService.guardar(new TurnoDTO(pacienteService.guardar(pacienteService.buscar(1L)),odontologoService.guardar(odontologoService.buscar(1L)),LocalDate.now(), LocalTime.now()));
        Assert.assertNotNull(turnoService.buscar(1L));
    }
    @Test
    public void buscarTurnoTest(){
        Assert.assertNotNull(turnoService.buscar(1L));
    }
    @Test
    public void eliminarTurnoTest(){
        turnoService.eliminar(1L);
        Assert.assertFalse(turnoService.buscar(1L) != null);
    }
}
