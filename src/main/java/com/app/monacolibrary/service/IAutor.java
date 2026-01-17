package com.app.monacolibrary.service;

import java.util.List;

public interface IAutor<T> extends Service<T>{

    List<T> autoresVivos(Integer anio);
}
