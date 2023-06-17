package com.dh.final_project_backend.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {

    private Long id;
    private String usuario;
    private String password;
    private String rol;

}
