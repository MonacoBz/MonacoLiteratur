package com.app.monacolibrary.service;

import com.app.monacolibrary.dto.LibroDto;
import com.app.monacolibrary.models.LENGUAJE;
import com.app.monacolibrary.models.Libro;
import com.app.monacolibrary.repository.AutorRepository;
import com.app.monacolibrary.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService implements ILibro<LibroDto> {

    private final LibroRepository repositoryLibro;
    private final AutorRepository repositoryAutor;
    private final Mapper mapper;
    private final ApiRequest api;
    private final String URL_BASE = "https://gutendex.com/books/?search=";

    public LibroService(
            LibroRepository repositoryLibro,
            AutorRepository repositoryAutor,
            Mapper mapper,
            ApiRequest api
    ){
        this.repositoryLibro = repositoryLibro;
        this.repositoryAutor = repositoryAutor;
        this.mapper = mapper;
        this.api = api;
    }

    @Override
    public List<LibroDto> buscarLibro(String titulo) {
        List<Libro> libros = repositoryLibro.findByTituloContainingIgnoreCase(titulo);
        if(!libros.isEmpty())return libros.stream()
                .map(mapper::libroToDto)
                .toList();
        else return List.of(mapper.libroToDto(buscarEnApi(titulo)));

    }

    private Libro buscarEnApi(String nombre){
        Libro libro = null;
        try {
            var datos = api.realizaSolicitud(URL_BASE + nombre.replace(" ","%20"));
            libro = new Libro(datos);
            var autor = repositoryAutor.findByNombreIgnoreCase(libro.getAutor().getNombre())
                    .orElse(libro.getAutor());
            if(autor.getId() == null) autor = repositoryAutor.save(autor);

            autor.agregarLibro(libro);

            repositoryLibro.save(libro);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return libro;
    }

    @Override
    public List<LibroDto> librosPorIdioma(LENGUAJE lenguaje) {
        return repositoryLibro.findByIdioma(lenguaje).stream()
                .map(mapper::libroToDto)
                .toList();
    }

    @Override
    public List<LibroDto> obtenTodo() {
        return repositoryLibro.findAll().stream()
                .map(mapper::libroToDto)
                .toList();
    }
}
