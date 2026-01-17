package com.app.monacolibrary.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue
    private Long id;

    private String titulo;

    @OneToMany(mappedBy = "libro", fetch = FetchType.EAGER)
    private List<Autor> autores;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<LENGUAJE> idiomas;

    private Long descargas;

    public Libro(){}

    public Libro(DatosLibro datos){
        this.titulo = datos.titulo();
        this.autores = datos.autores().stream().map(a -> new Autor(a)).toList();
        this.descargas = datos.descargas();
        this.idiomas = datos.idiomas();
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
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
                """.formatted(getTitulo(),getAutores().get(0).getNombre(),getIdiomas().get(0).toString(),getDescargas());
    }
}
