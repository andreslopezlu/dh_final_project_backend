package com.dh.final_project_backend;

import com.dh.final_project_backend.entity.Odontologo;
import com.dh.final_project_backend.entity.OdontologoDTO;
import com.dh.final_project_backend.service.OdontologoService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class OdontologoServiceTests {
    @Autowired
    private OdontologoService odontologoService;


    public void cargarDataSet() {
        this.odontologoService.guardar(new OdontologoDTO("Andres", "Lopez", "123456"));


    }

    @Test
    public void agregarOdontologo() {
        this.cargarDataSet();
        Odontologo odontologo = odontologoService.guardar(new OdontologoDTO("Natalia", "Valbuena", "123456"));
        Assert.assertTrue(odontologo.getId() != null);

    }

    @Test
    public void eliminarOdontologoTest() {
        odontologoService.eliminar(1L);
        Assert.assertTrue(odontologoService.buscar(1L) == null);

    }

    @Test
    public void traerTodos() {
        Set<OdontologoDTO> odontologos = odontologoService.buscarTodos();
        Assert.assertTrue(!odontologos.isEmpty());
        Assert.assertTrue(odontologos.size() == 1);
        System.out.println(odontologos);
    }

}
