package com.dh.final_project_backend.controller;

import com.dh.final_project_backend.entity.Turno;
import com.dh.final_project_backend.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/final/turnos")
public class TurnoController {

    @Autowired
    TurnoService turnoService;

    @PostMapping("/post")
    public ResponseEntity<Turno> guardar(@RequestBody Turno turno){
        return ResponseEntity.ok(turnoService.guardar(turno));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Turno> buscar(@PathVariable Integer id){
        return ResponseEntity.ok(turnoService.buscar(id));
    }

    @PutMapping("/put")
    public ResponseEntity<Turno> actualizar(@RequestBody Turno turno){
        ResponseEntity<Turno> response = null;
        if(turno.getId() != null){
            response = ResponseEntity.ok(turnoService.actualizar(turno));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        ResponseEntity<String> response = null;
        if(turnoService.buscar(id).getId() != null){
            turnoService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Turno>> buscarTodos(){
        return ResponseEntity.ok(turnoService.buscarTodos());
    }
}
