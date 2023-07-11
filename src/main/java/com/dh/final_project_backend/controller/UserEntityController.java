package com.dh.final_project_backend.controller;

import com.dh.final_project_backend.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/final/usuarios")
public class UserEntityController {

    @Autowired
    UserSecurityService usuarioService;

    @GetMapping("/get")
    public ResponseEntity<UserDetails> buscarEmail(@RequestBody String email){
        return ResponseEntity.ok(usuarioService.loadUserByUsername(email));
    }

}
