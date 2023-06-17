package com.dh.final_project_backend.controller;

import com.dh.final_project_backend.entity.Turno;
import com.dh.final_project_backend.entity.TurnoDTO;
import com.dh.final_project_backend.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/final/turnos")
public class TurnoController {

    @Autowired
    ITurnoService turnoService;

    @PostMapping("/post")
    public ResponseEntity<Turno> guardar(@RequestBody TurnoDTO turnoDTO){
        return ResponseEntity.ok(turnoService.guardar(turnoDTO));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TurnoDTO> buscar(@PathVariable Long id){
        return ResponseEntity.ok(turnoService.buscar(id));
    }

    @PutMapping("/put")
    public ResponseEntity<Turno> actualizar(@RequestBody TurnoDTO turnoDTO){
        return ResponseEntity.ok(turnoService.actualizar(turnoDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        turnoService.eliminar(id);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/get/all")
    public ResponseEntity<Set<TurnoDTO>> buscarTodos(){
        return ResponseEntity.ok(turnoService.buscarTodos());
    }

}
