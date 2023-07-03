package com.dh.final_project_backend.controller;

import com.dh.final_project_backend.entity.Usuario;
import com.dh.final_project_backend.entity.UsuarioDTO;
import com.dh.final_project_backend.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/final/usuarios")
public class UsuarioController {

    @Autowired
    IUsuarioService usuarioService;

    @PostMapping("/post")
    public ResponseEntity<Usuario> guardar(@RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(usuarioService.guardar(usuarioDTO));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UsuarioDTO> buscar(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.buscar(id));
    }

    @PutMapping("/put")
    public ResponseEntity<Usuario> actualizar(@RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(usuarioService.actualizar(usuarioDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.ok("{\"response\": \"ok\"}");
    }

    @GetMapping("/get/all")
    public ResponseEntity<Set<UsuarioDTO>> buscarTodos(){
        return ResponseEntity.ok(usuarioService.buscarTodos());
    }

}
