package com.dh.final_project_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name="Odontologos")
@Getter
@Setter
@ToString
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id", nullable=false)
    private Long id;

    private String nombre;

    private String apellido;

    private String matricula;

    @OneToMany(mappedBy = "odontologo", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Turno> turnos = new HashSet<>();

    public Odontologo() {
    }

//    public Odontologo(String nombre, String apellido, String matricula, Set<Turno> turnos) {
//        this.nombre = nombre;
//        this.apellido = apellido;
//        this.matricula = matricula;
//        this.turnos = turnos;
//    }
//
//    public Odontologo(Long id, String nombre, String apellido, String matricula, Set<Turno> turnos) {
//        this.id = id;
//        this.nombre = nombre;
//        this.apellido = apellido;
//        this.matricula = matricula;
//        this.turnos = turnos;
//    }
}
