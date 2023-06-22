package com.dh.final_project_backend.controller;

import com.dh.final_project_backend.entity.Domicilio;
import com.dh.final_project_backend.entity.DomicilioDTO;
import com.dh.final_project_backend.service.IDomicilioService;
import com.dh.final_project_backend.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/final/domicilios")
public class DomicilioController {

    @Autowired
    IDomicilioService domicilioService;

    @Autowired
    PacienteService pacienteService;

    @PostMapping("/post")
    public ResponseEntity<Domicilio> guardar(@RequestBody DomicilioDTO domicilioDTO){
        return ResponseEntity.ok(domicilioService.guardar(domicilioDTO));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<DomicilioDTO> buscar(@PathVariable Long id){
        return ResponseEntity.ok(domicilioService.buscar(id));
    }

    @PutMapping("/put")
    public ResponseEntity<Domicilio> actualizar(@RequestBody DomicilioDTO domicilioDTO){
        return ResponseEntity.ok(domicilioService.actualizar(domicilioDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        domicilioService.eliminar(id);
        return ResponseEntity.ok("{\"response\": \"ok\"}");
    }

    @GetMapping("/get/all")
    public ResponseEntity<Set<DomicilioDTO>> buscarTodos(){
        return ResponseEntity.ok(domicilioService.buscarTodos());
    }
}
