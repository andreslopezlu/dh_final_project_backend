package com.dh.final_project_backend.service;

import com.dh.final_project_backend.entity.Odontologo;
import com.dh.final_project_backend.entity.OdontologoDTO;
import com.dh.final_project_backend.repository.IOdontologoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OdontologoService implements IOdontologoService{

    @Autowired
    IOdontologoRepository odontologoRepository;

    @Autowired
    ObjectMapper mapper;

    @Override
    public Odontologo guardar(OdontologoDTO odontologoDTO){
        Odontologo odontologo = mapper.convertValue(odontologoDTO, Odontologo.class);
        return odontologoRepository.save(odontologo);
    }

    @Override
    public OdontologoDTO buscar(Long id){
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        OdontologoDTO odontologoDTO = null;
        if(odontologo.isPresent()){
            odontologoDTO = mapper.convertValue(odontologo, OdontologoDTO.class);
        }
        return odontologoDTO;
    }

    @Override
    public Odontologo actualizar(OdontologoDTO odontologoDTO){
        Odontologo odontologo = mapper.convertValue(odontologoDTO, Odontologo.class);
        return odontologoRepository.save(odontologo);
    }

    @Override
    public void eliminar(Long id){
        odontologoRepository.deleteById(id);
    }

    @Override
    public Set<OdontologoDTO> buscarTodos(){
        List<Odontologo> odontologos = odontologoRepository.findAll();
        Set<OdontologoDTO> odontologosDTOS = new HashSet<>();
        for(Odontologo odontologo : odontologos){
            odontologosDTOS.add(mapper.convertValue(odontologo, OdontologoDTO.class));
        }
        return odontologosDTOS;
    }

}
