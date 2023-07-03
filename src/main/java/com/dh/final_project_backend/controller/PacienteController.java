package com.dh.final_project_backend.controller;

import com.dh.final_project_backend.entity.Paciente;
import com.dh.final_project_backend.entity.PacienteDTO;
import com.dh.final_project_backend.exceptions.GlobalExceptionHandler;
import com.dh.final_project_backend.service.IPacienteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/final/pacientes")
public class PacienteController {

    @Autowired
    IPacienteService pacienteService;

    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @PostMapping("/post")
    public ResponseEntity<Paciente> guardar(@RequestBody PacienteDTO pacienteDTO){
        logger.info("Creando paciente//" + "nombre:" + pacienteDTO.getNombre() + ";" + "apellido:" +pacienteDTO.getApellido() + ";" + "dni:" + pacienteDTO.getDni());
        return ResponseEntity.ok(pacienteService.guardar(pacienteDTO));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PacienteDTO> buscar(@PathVariable Long id){
        logger.info("Encontrando paciente//" + "id:" + id);
        return ResponseEntity.ok(pacienteService.buscar(id));
    }

    @PutMapping("/put")
    public ResponseEntity<Paciente> actualizar(@RequestBody PacienteDTO pacienteDTO){
        logger.info("Actualizando paciente//" + "id:" +pacienteDTO.getId() + ";" + "nombre:" +pacienteDTO.getNombre() + ";" + "apellido:" +pacienteDTO.getApellido() + ";" + "dni:" +pacienteDTO.getDni());
        return ResponseEntity.ok(pacienteService.actualizar(pacienteDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        pacienteService.eliminar(id);
        logger.info("Eliminando paciente//" + "id:" + id);
        return ResponseEntity.ok("{\"response\": \"ok\"}");
    }

    @GetMapping("/get/all")
    public ResponseEntity<Set<PacienteDTO>> buscarTodos(){
        logger.info("Mostrando pacientes//");
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

}
