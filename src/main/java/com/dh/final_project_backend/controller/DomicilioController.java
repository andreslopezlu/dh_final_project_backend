package com.dh.final_project_backend.controller;

import com.dh.final_project_backend.entity.Domicilio;
import com.dh.final_project_backend.service.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/final/domicilios")
public class DomicilioController {

    @Autowired
    DomicilioService domicilioService;

    @PostMapping("/post")
    public ResponseEntity<Domicilio> guardar(@RequestBody Domicilio domicilio){
        return ResponseEntity.ok(domicilioService.guardar(domicilio));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Domicilio> buscar(@PathVariable Integer id){
        return ResponseEntity.ok(domicilioService.buscar(id));
    }

    @PutMapping("/put")
    public ResponseEntity<Domicilio> actualizar(@RequestBody Domicilio domicilio){
        ResponseEntity<Domicilio> response = null;
        if(domicilio.getId() != null){
            response = ResponseEntity.ok(domicilioService.actualizar(domicilio));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        ResponseEntity<String> response = null;
        if(domicilioService.buscar(id).getId() != null){
            domicilioService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Domicilio>> buscarTodos(){
        return ResponseEntity.ok(domicilioService.buscarTodos());
    }
}
