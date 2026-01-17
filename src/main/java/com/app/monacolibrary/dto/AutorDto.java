package com.app.monacolibrary.dto;

import com.app.monacolibrary.models.Libro;

import java.util.List;

public record AutorDto(
        String nombre,
        Integer nacimiento,
        Integer muerte,
        List<LibroSimpleDto> libros



) {
    @Override
    public String toString() {
        List<String> titulos = libros.stream()
                .map(LibroSimpleDto::titulo)
                .toList();

        return """
           ---------------
           Autor: %s
           Nacimiento: %s
           Muerte: %s
           Libros: %s
           ---------------
           """.formatted(nombre, nacimiento, muerte, titulos);
    }
}
