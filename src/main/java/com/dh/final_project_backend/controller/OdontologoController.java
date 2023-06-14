package com.dh.final_project_backend.controller;

import com.dh.final_project_backend.entity.Odontologo;
import com.dh.final_project_backend.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("final/odontologos")
public class OdontologoController {

    @Autowired
    OdontologoService odontologoService;

    @PostMapping("/post")
    public ResponseEntity<Odontologo> guardar(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardar(odontologo));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Odontologo> buscar(@PathVariable Integer id){
        return ResponseEntity.ok(odontologoService.buscar(id));
    }

    @PutMapping("/put")
    public ResponseEntity<Odontologo> actualizar(@RequestBody Odontologo odontologo){
        ResponseEntity<Odontologo> response = null;
        if(odontologo.getId() != null){
            response = ResponseEntity.ok(odontologoService.actualizar(odontologo));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        ResponseEntity<String> response = null;
        if(odontologoService.buscar(id).getId() != null){
            odontologoService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Odontologo>> buscarTodos(){
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }

}
