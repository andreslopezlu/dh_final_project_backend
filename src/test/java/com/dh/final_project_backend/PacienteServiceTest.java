package com.dh.final_project_backend;


import com.dh.final_project_backend.entity.Domicilio;
import com.dh.final_project_backend.entity.Paciente;
import com.dh.final_project_backend.entity.PacienteDTO;
import com.dh.final_project_backend.service.PacienteService;
import org.junit.Assert;


import org.junit.FixMethodOrder;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;


    public void cargarDataSet() {
        Domicilio domicilio = new Domicilio("Septima", 123, "cund", "col", 123);
        Paciente p = pacienteService.guardar(new PacienteDTO("Andres", "Lopez", domicilio, 123, LocalDate.now()));
        Domicilio domicilio1 = new Domicilio("Novena", 123, "boy", "col", 456);
        Paciente p1 = pacienteService.guardar(new PacienteDTO("Natalia", "Valbuena", domicilio1, 456, LocalDate.now()));

    }

    @Test
    public void agregarYBuscarPacienteTest() {
        this.cargarDataSet();
        Domicilio domicilio2 = new Domicilio("Decima", 789, "ant", "col", 789);
        Paciente p2 = pacienteService.guardar(new PacienteDTO("Angela", "Suarez", domicilio2, 456, LocalDate.now()));

        Assert.assertNotNull(pacienteService.buscar(p2.getId()));
    }

    @Test
    public void eliminarPacienteTest() {
        pacienteService.eliminar(3L);
        Assert.assertTrue(pacienteService.buscar(3L)==null);

    }

    @Test
    public void traerTodos() {
        Set<PacienteDTO> pacientes = pacienteService.buscarTodos();
        Assert.assertTrue(!pacientes.isEmpty());
        Assert.assertTrue(pacientes.size() == 2);
        System.out.println(pacientes);
    }


}
