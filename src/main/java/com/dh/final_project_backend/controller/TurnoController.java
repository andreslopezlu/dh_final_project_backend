package com.dh.final_project_backend.controller;

import com.dh.final_project_backend.entity.Turno;
import com.dh.final_project_backend.entity.TurnoDTO;
import com.dh.final_project_backend.exceptions.GlobalExceptionHandler;
import com.dh.final_project_backend.service.ITurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/final/turnos")
public class TurnoController {

    @Autowired
    ITurnoService turnoService;

    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

    @PostMapping("/post")
    public ResponseEntity<Turno> guardar(@RequestBody TurnoDTO turnoDTO){
        logger.info("Creando turno//" + "pacienteId:" + turnoDTO.getPaciente().getId() + ";" + "odontologoId:" +turnoDTO.getOdontologo().getId() + ";" + "fecha:" + turnoDTO.getFecha() + ";" + "hora:" + turnoDTO.getHora());
        return ResponseEntity.ok(turnoService.guardar(turnoDTO));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TurnoDTO> buscar(@PathVariable Long id){
        logger.info("Encontrando turno//" + "id:" + id);
        return ResponseEntity.ok(turnoService.buscar(id));
    }

    @PutMapping("/put")
    public ResponseEntity<Turno> actualizar(@RequestBody TurnoDTO turnoDTO){
        logger.info("Actualizando turno//" + "id:" + turnoDTO.getId() + ";" + "pacienteId:" + turnoDTO.getPaciente().getId() + ";" + "odontologoId:" +turnoDTO.getOdontologo().getId() + ";" + "fecha:" + turnoDTO.getFecha() + ";" + "hora:" + turnoDTO.getHora());
        return ResponseEntity.ok(turnoService.actualizar(turnoDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        turnoService.eliminar(id);
        logger.info("Eliminando turno//" + "id:" + id);
        return ResponseEntity.ok("{\"response\": \"ok\"}");
    }

    @GetMapping("/get/all")
    public ResponseEntity<Set<TurnoDTO>> buscarTodos(){
        logger.info("Mostrando turnos//");
        return ResponseEntity.ok(turnoService.buscarTodos());
    }

}
