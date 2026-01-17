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

    @ElementCollection(fetch = FetchType.EAGER)
    private List<LENGUAJE> idiomas;

    private Long descargas;

    public Libro(){}

    public Libro(DatosLibro datos){
        this.titulo = datos.titulo();
        this.autor = new Autor(datos.autores().get(0));
        this.descargas = datos.descargas();
        this.idiomas = datos.idiomas();
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

    public List<LENGUAJE> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<LENGUAJE> idiomas) {
        this.idiomas = idiomas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return """
                -----LIBRO-----
                Título: %s
                Autor: %s
                Idioma: %s
                Descargas: %d
                ----------------
                """.formatted(titulo,autor.getNombre(),idiomas.get(0).toString(),descargas);
    }
}
