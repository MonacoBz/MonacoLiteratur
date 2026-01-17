package com.app.monacolibrary.service;

import com.app.monacolibrary.dto.AutorDto;
import com.app.monacolibrary.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AutorService implements IAutor<AutorDto>{

    AutorRepository repository;
    Mapper mapper;

    public AutorService(
            AutorRepository repository,
            Mapper mapper
    ){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<AutorDto> autoresVivos(Integer anio) {
        return repository.buscaDeterminadoAnio(anio).stream()
                .map(mapper::autorToDto)
                .toList();
    }

    @Override
    public List<AutorDto> obtenTodo() {
        return repository.findAll().stream()
                .map(mapper::autorToDto)
                .toList();
    }
}
