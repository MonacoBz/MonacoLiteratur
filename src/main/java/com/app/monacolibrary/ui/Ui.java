package com.app.monacolibrary.ui;

import com.app.monacolibrary.models.Autor;
import com.app.monacolibrary.models.Libro;
import com.app.monacolibrary.repository.LibroRepository;
import com.app.monacolibrary.service.ApiRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Ui {

    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private final Scanner sc = new Scanner(System.in);
    private final ApiRequest API = new ApiRequest();


    private final LibroRepository repository;

    public Ui(LibroRepository repository){
        this.repository = repository;
    }

    public void menu(){
        String inicio = """
                Bienvenido a Monaco Library, elija una opción:
                1)Buscar un libro por titulo
                2)Listar libros registrados
                3)Listar autores registrados
                4)Listar autores vivos en un determinado año
                5)Listar libros por idioma
                """;
        int opcion = -1;

        while(opcion != 0){
            System.out.println(inicio);
            try {
                String input = sc.nextLine();
                opcion = Integer.parseInt(input);

                switch (opcion) {
                    case 1 -> buscarLibroPorNombre();
                    case 2 -> listarLibrosRegistrados();
                    case 3 -> listarAutoresRegistrados();
                    case 4 -> listarAutoresVivos();
                    case 5 -> listarLibrosIdioma();
                    case 0 -> System.out.println("Cerrando la aplicación...");
                    default -> System.out.println("Opción inválida");
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor ingrese un número válido.");
            }
        }
    }

    private void listarLibrosIdioma() {
    }

    private void listarAutoresVivos() {
    }

    private void listarAutoresRegistrados() {
        
    }

    private void listarLibrosRegistrados() {
        System.out.println("Libros registrados:");
        repository.findAll().forEach(System.out::println);
    }

    private void buscarLibroPorNombre(){
        System.out.println("Introduce el nombre del libro a buscar");
        String nombre = sc.nextLine();
        List<Libro> libros = repository.findByTituloContainingIgnoreCase(nombre);
        if(!libros.isEmpty()){
            System.out.println("Libros encontrados:");
            libros.forEach(System.out::println);
        }
        else buscarEnApi(nombre);

    }
    private void buscarEnApi(String nombre){
        try {
            var datos = API.realizaSolicitud(URL_BASE + nombre.replace(" ","%20"));
            var libro = new Libro(datos);
            repository.save(libro);
            System.out.println(libro);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


}
