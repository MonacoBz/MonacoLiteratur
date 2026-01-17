package com.app.monacolibrary.models;

import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private Integer nacimiento;

    private Integer muerte;

    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;

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

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
