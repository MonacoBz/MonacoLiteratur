package com.app.monacolibrary.configuration;

import com.app.monacolibrary.repository.AutorRepository;
import com.app.monacolibrary.repository.LibroRepository;
import com.app.monacolibrary.ui.Ui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Autowired
    LibroRepository repositoryLibro;

    @Autowired
    AutorRepository repositoryAutor;

    @Bean
    public Ui ui (){
        return new Ui(repositoryLibro,repositoryAutor);
    }
}
