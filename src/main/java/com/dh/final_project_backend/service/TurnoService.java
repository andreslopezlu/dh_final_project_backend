package com.dh.final_project_backend.service;

import com.dh.final_project_backend.entity.Turno;
import com.dh.final_project_backend.entity.TurnoDTO;
import com.dh.final_project_backend.repository.TurnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TurnoService implements ITurnoService{

    @Autowired
    TurnoRepository turnoRepository;

    @Autowired
    ObjectMapper mapper;

    @Override
    public Turno guardar(TurnoDTO turnoDTO){
        Turno turno = mapper.convertValue(turnoDTO, Turno.class);
        return turnoRepository.save(turno);
    }

    @Override
    public TurnoDTO buscar(Long id){
        Optional<Turno> turno = turnoRepository.findById(id);
        TurnoDTO turnoDTO = null;
        if(turno.isPresent()){
            turnoDTO = mapper.convertValue(turno.get(), TurnoDTO.class);
        }
        return turnoDTO;
    }

    @Override
    public Turno actualizar(TurnoDTO turnoDTO){
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        Turno turno = mapper.convertValue(turnoDTO, Turno.class);
        return turnoRepository.save(turno);
    }

    @Override
    public void eliminar(Long id){
        turnoRepository.deleteById(id);
    }

    @Override
    public Set<TurnoDTO> buscarTodos(){
        List<Turno> turnos = turnoRepository.findAll();
        Set<TurnoDTO> turnosDTO = new HashSet<>();
        for(Turno turno : turnos){
            turnosDTO.add(mapper.convertValue(turno, TurnoDTO.class));
        }
        return turnosDTO;
    }

}
