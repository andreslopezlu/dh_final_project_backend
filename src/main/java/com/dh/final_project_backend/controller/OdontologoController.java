package com.dh.final_project_backend.controller;

import com.dh.final_project_backend.entity.Odontologo;
import com.dh.final_project_backend.entity.OdontologoDTO;
import com.dh.final_project_backend.exceptions.GlobalExceptionHandler;
import com.dh.final_project_backend.service.IOdontologoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/final/odontologos")
public class OdontologoController {

    @Autowired
    IOdontologoService odontologoService;

    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @PostMapping("/post")
    public ResponseEntity<Odontologo> guardar(@RequestBody OdontologoDTO odontologoDTO){
        logger.info("Creando odontologo//" + "nombre:" +odontologoDTO.getNombre() + ";" + "apellido:" +odontologoDTO.getApellido() + ";" + "matricula:" +odontologoDTO.getMatricula());
        return ResponseEntity.ok(odontologoService.guardar(odontologoDTO));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<OdontologoDTO> buscar(@PathVariable Long id){
        logger.info("Encontrando odontologo//" + "id:" + id);
        return ResponseEntity.ok(odontologoService.buscar(id));
    }

    @PutMapping("/put")
    public ResponseEntity<Odontologo> actualizar(@RequestBody OdontologoDTO odontologoDTO){
        logger.info("Actualizando odontologo//" + "id:" +odontologoDTO.getId() + ";" + "nombre:" + odontologoDTO.getNombre() + ";" + "apellido:" +odontologoDTO.getApellido() + ";" + "matricula:" + odontologoDTO.getMatricula());
        return ResponseEntity.ok(odontologoService.actualizar(odontologoDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        odontologoService.eliminar(id);
        logger.info("Eliminando odontologo//" + "id:" + id);
        return ResponseEntity.ok("{\"response\": \"ok\"}");
    }

    @GetMapping("/get/all")
    public ResponseEntity<Set<OdontologoDTO>> buscarTodos(){
        logger.info("Mostrando odontologos//");
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }
}
