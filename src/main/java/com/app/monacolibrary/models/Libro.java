package com.app.monacolibrary.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue
    private Long id;

    private String titulo;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @Enumerated(EnumType.STRING)
    private LENGUAJE idioma;

    private Long descargas;

    public Libro(){}

    public Libro(DatosLibro datos){
        this.titulo = datos.titulo();
        this.autor = new Autor(datos.autores().get(0));
        this.descargas = datos.descargas();
        this.idioma = datos.idiomas().get(0);
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Long getDescargas() {
        return descargas;
    }

    public void setDescargas(Long descargargas) {
        this.descargas = descargargas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LENGUAJE getIdioma() {
        return idioma;
    }

    public void setIdioma(LENGUAJE idioma) {
        this.idioma = idioma;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
