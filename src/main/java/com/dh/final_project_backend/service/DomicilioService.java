package com.dh.final_project_backend.service;

import com.dh.final_project_backend.entity.Domicilio;
import com.dh.final_project_backend.entity.DomicilioDTO;
import com.dh.final_project_backend.repository.IDomicilioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DomicilioService implements IDomicilioService{

    @Autowired
    IDomicilioRepository domicilioRepository;

    @Autowired
    ObjectMapper mapper;

    @Override
    public Domicilio guardar(DomicilioDTO domicilioDTO){
        Domicilio domicilio = mapper.convertValue(domicilioDTO, Domicilio.class);
        return domicilioRepository.save(domicilio);
    }

    @Override
    public DomicilioDTO buscar(Long id){
        Optional<Domicilio> domicilio = domicilioRepository.findById(id);
        DomicilioDTO domicilioDTO = null;
        if(domicilio.isPresent()){
            domicilioDTO = mapper.convertValue(domicilio.get(), DomicilioDTO.class);
        }
        return domicilioDTO;
    }

    @Override
    public Domicilio actualizar(DomicilioDTO domicilioDTO){
        Domicilio domicilio = mapper.convertValue(domicilioDTO, Domicilio.class);
        return domicilioRepository.save(domicilio);
    }

    @Override
    public void eliminar(Long id){
        domicilioRepository.deleteById(id);
    }

    @Override
    public Set<DomicilioDTO> buscarTodos(){
        List<Domicilio> domicilios = domicilioRepository.findAll();
        Set<DomicilioDTO> domiciliosDTO = new HashSet<>();
        for(Domicilio domicilio : domicilios){
            domiciliosDTO.add(mapper.convertValue(domicilio, DomicilioDTO.class));
        }
        return domiciliosDTO;
    }

}
