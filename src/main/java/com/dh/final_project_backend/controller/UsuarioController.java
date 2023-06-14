package com.dh.final_project_backend.controller;

import com.dh.final_project_backend.entity.Usuario;
import com.dh.final_project_backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/final/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/post")
    public ResponseEntity<Usuario> guardar(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.guardar(usuario));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Usuario> buscar(@PathVariable Integer id){
        return ResponseEntity.ok(usuarioService.buscar(id));
    }

    @PutMapping("/put")
    public ResponseEntity<Usuario> actualizar(@RequestBody Usuario usuario){
        ResponseEntity<Usuario> response = null;
        if(usuario.getId() != null){
            response = ResponseEntity.ok(usuarioService.actualizar(usuario));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        ResponseEntity<String> response = null;
        if(usuarioService.buscar(id).getId() != null){
            usuarioService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Usuario>> buscarTodos(){
        return ResponseEntity.ok(usuarioService.buscarTodos());
    }


}
