package com.dh.final_project_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="Turnos")
@Getter
@Setter
@ToString
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id", nullable=false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name="odontologo_id", nullable = false)
    private Odontologo odontologo;

    private LocalDate fecha;

    private LocalTime hora;

    public Turno() {
    }

//    public Turno(Paciente paciente, Odontologo odontologo, LocalDate fecha, LocalTime hora) {
//        this.paciente = paciente;
//        this.odontologo = odontologo;
//        this.fecha = fecha;
//        this.hora = hora;
//    }
//
//    public Turno(Long id, Paciente paciente, Odontologo odontologo, LocalDate fecha, LocalTime hora) {
//        this.id = id;
//        this.paciente = paciente;
//        this.odontologo = odontologo;
//        this.fecha = fecha;
//        this.hora = hora;
//    }
}
