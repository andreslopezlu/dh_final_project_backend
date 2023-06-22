package com.dh.final_project_backend.controller;

import com.dh.final_project_backend.entity.Odontologo;
import com.dh.final_project_backend.entity.OdontologoDTO;
import com.dh.final_project_backend.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/final/odontologos")
public class OdontologoController {

    @Autowired
    IOdontologoService odontologoService;

    @PostMapping("/post")
    public ResponseEntity<Odontologo> guardar(@RequestBody OdontologoDTO odontologoDTO){
        return ResponseEntity.ok(odontologoService.guardar(odontologoDTO));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<OdontologoDTO> buscar(@PathVariable Long id){
        return ResponseEntity.ok(odontologoService.buscar(id));
    }

    @PutMapping("/put")
    public ResponseEntity<Odontologo> actualizar(@RequestBody OdontologoDTO odontologoDTO){
        return ResponseEntity.ok(odontologoService.actualizar(odontologoDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        odontologoService.eliminar(id);
        return ResponseEntity.ok("{\"response\": \"ok\"}");
    }

    @GetMapping("/get/all")
    public ResponseEntity<Set<OdontologoDTO>> buscarTodos(){
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }
}
