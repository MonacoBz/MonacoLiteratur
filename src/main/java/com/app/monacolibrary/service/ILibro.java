package com.app.monacolibrary.service;


import com.app.monacolibrary.models.LENGUAJE;

import java.util.List;

public interface ILibro<T> extends Service<T>{

    List<T> buscarLibro(String titulo);

    List<T> librosPorIdioma(LENGUAJE lenguaje);
}
