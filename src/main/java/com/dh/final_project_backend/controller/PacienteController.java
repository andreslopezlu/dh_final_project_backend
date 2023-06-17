package com.dh.final_project_backend.controller;

import com.dh.final_project_backend.entity.Paciente;
import com.dh.final_project_backend.entity.PacienteDTO;
import com.dh.final_project_backend.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/final/pacientes")
public class PacienteController {

    @Autowired
    IPacienteService pacienteService;

    @PostMapping("/post")
    public ResponseEntity<Paciente> guardar(@RequestBody PacienteDTO pacienteDTO){
        return ResponseEntity.ok(pacienteService.guardar(pacienteDTO));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PacienteDTO> buscar(@PathVariable Long id){
        return ResponseEntity.ok(pacienteService.buscar(id));
    }

    @PutMapping("/put")
    public ResponseEntity<Paciente> actualizar(@RequestBody PacienteDTO pacienteDTO){
        return ResponseEntity.ok(pacienteService.actualizar(pacienteDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        pacienteService.eliminar(id);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/get/all")
    public ResponseEntity<Set<PacienteDTO>> buscarTodos(){
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

}
