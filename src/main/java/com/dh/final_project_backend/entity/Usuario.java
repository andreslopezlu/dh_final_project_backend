package com.dh.final_project_backend.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Usuarios")
@Getter
@Setter
@ToString
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Usuario {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @Column(name="id", nullable = false)
    private Long id;

    private String usuario;

    private String password;

    private String rol;

    public Usuario() {
    }

//    public Usuario(String usuario, String password, String rol) {
//        this.usuario = usuario;
//        this.password = password;
//        this.rol = rol;
//    }
//
//    public Usuario(Long id, String usuario, String password, String rol) {
//        this.id = id;
//        this.usuario = usuario;
//        this.password = password;
//        this.rol = rol;
//    }
}
