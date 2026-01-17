package com.app.monacolibrary.service;

import com.app.monacolibrary.dto.AutorDto;
import com.app.monacolibrary.dto.AutorSimpleDto;
import com.app.monacolibrary.dto.LibroDto;
import com.app.monacolibrary.dto.LibroSimpleDto;
import com.app.monacolibrary.models.Autor;
import com.app.monacolibrary.models.Libro;

import java.util.List;

public class Mapper {

    public LibroDto libroToDto(Libro libro){
        var autor = autorToSimpleDto(libro.getAutor());
        return new LibroDto(libro.getTitulo(),autor,libro.getIdioma(),libro.getDescargas());
    }

    private LibroSimpleDto libroToSimpleDto(Libro libro){
        return new LibroSimpleDto(libro.getTitulo(),libro.getIdioma(), libro.getDescargas());
    }

    public AutorDto autorToDto(Autor autor){
        var libros = autor.getLibros().stream()
                .map(this::libroToSimpleDto)
                .toList();
        return new AutorDto(autor.getNombre(),autor.getNacimiento(),autor.getMuerte(),libros);

    }

    private AutorSimpleDto autorToSimpleDto(Autor autor){
        return new AutorSimpleDto(autor.getNombre(),autor.getNacimiento(),autor.getMuerte());
    }
}
