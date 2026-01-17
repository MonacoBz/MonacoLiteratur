package com.app.monacolibrary.dto;

import com.app.monacolibrary.models.LENGUAJE;

public record LibroSimpleDto(
        String titulo,
        LENGUAJE idioma,
        Long descargas
) {

}
