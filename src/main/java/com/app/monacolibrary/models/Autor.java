package com.app.monacolibrary.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private Integer nacimiento;

    private Integer muerte;

    @OneToMany(mappedBy = "autor",fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

    public Autor(){}

    public Autor(DatosAutor datos){
        this.nombre = datos.nombre();
        this.nacimiento = datos.nacimiento();
        this.muerte = datos.muerte();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMuerte() {
        return muerte;
    }

    public void setMuerte(Integer muerte) {
        this.muerte = muerte;
    }

    public Integer getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Integer nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public void agregarLibro(Libro libro){
        this.libros.add(libro);
        libro.setAutor(this);
    }

    @Override
    public String toString() {
        List<String> titulos = getLibros().stream()
                .map(Libro::getTitulo)
                .toList();

        return """
           Autor: %s
           Nacimiento: %s
           Muerte: %s
           Libros: %s
           """.formatted(nombre, nacimiento, muerte, titulos);
    }
}
