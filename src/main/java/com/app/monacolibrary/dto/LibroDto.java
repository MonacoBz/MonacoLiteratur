package com.app.monacolibrary.dto;

import com.app.monacolibrary.models.LENGUAJE;

public record LibroDto(
        String titulo,
        AutorSimpleDto autor,
        LENGUAJE idioma,
        Long descargas


) {
    @Override
    public String toString() {
        return """
                -----LIBRO-----
                Título: %s
                Autor: %s
                Idioma: %s
                Descargas: %d
                ----------------
                """.formatted(titulo,autor.nombre(),idioma.toString(),descargas);
    }
}
