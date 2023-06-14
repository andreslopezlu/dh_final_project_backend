package com.dh.final_project_backend.controller;

import com.dh.final_project_backend.entity.Paciente;
import com.dh.final_project_backend.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/final/pacientes")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    @PostMapping("/post")
    public ResponseEntity<Paciente> guardar(@RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.guardar(paciente));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Paciente> buscar(@PathVariable Integer id){
        return ResponseEntity.ok(pacienteService.buscar(id));
    }

    @PutMapping("/put")
    public ResponseEntity<Paciente> actualizar(@RequestBody Paciente paciente){
        ResponseEntity<Paciente> response = null;
        if(paciente.getId() != null){
            response = ResponseEntity.ok(pacienteService.actualizar(paciente));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        ResponseEntity<String> response = null;
        if(pacienteService.buscar(id).getId() != null){
            pacienteService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Paciente>> buscarTodos(){
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }
}
