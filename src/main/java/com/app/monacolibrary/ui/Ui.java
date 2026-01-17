package com.app.monacolibrary.ui;

import com.app.monacolibrary.dto.AutorDto;
import com.app.monacolibrary.dto.LibroDto;
import com.app.monacolibrary.models.Autor;
import com.app.monacolibrary.models.LENGUAJE;
import com.app.monacolibrary.models.Libro;
import com.app.monacolibrary.repository.AutorRepository;
import com.app.monacolibrary.repository.LibroRepository;
import com.app.monacolibrary.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Ui {
    private final Scanner sc = new Scanner(System.in);
    private final IAutor<AutorDto> autorService;
    private final ILibro<LibroDto> libroService;

    public Ui(
            IAutor<AutorDto> autorService,
            ILibro<LibroDto> libroService
    ){
        this.autorService = autorService;
        this.libroService = libroService;
    }


    public void menu(){
        String inicio = """
                Bienvenido a Monaco Library, elija una opción:
                1)Buscar un libro por titulo
                2)Listar libros registrados
                3)Listar autores registrados
                4)Listar autores vivos en un determinado año
                5)Listar libros por idioma
                0)Salir de la aplicación
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
        String menuIdioma = """
                Ingresa el idioma
                1)Ingles -> en
                2)Español -> es
                3)Frances -> fr
                4)DU -> du
                """;
        System.out.println(menuIdioma);
        String idioma = sc.nextLine();
        while(true){
            try{
                libroService.librosPorIdioma(LENGUAJE.toString(idioma)).forEach(System.out::println);
                break;
            }catch (Exception e){
                System.out.println("Opcion no identificada, ingresa una opción valida");
                idioma = sc.nextLine();
            }
        }
    }

    private void listarAutoresVivos() {
        System.out.println("Ingresa año:");
        String year = sc.nextLine();
        while(true){
            try{
                autorService.autoresVivos(Integer.parseInt(year)).forEach(System.out::println);
                break;
            }catch (NumberFormatException e){
                System.out.println("Ingresa un año por favor");
                year = sc.nextLine();
            }
        }
    }

    private void listarAutoresRegistrados() {
        autorService.obtenTodo().forEach(System.out::println);
    }

    private void listarLibrosRegistrados() {
        System.out.println("Libros registrados:");
        libroService.obtenTodo().forEach(System.out::println);
    }

    private void buscarLibroPorNombre(){
        System.out.println("Introduce el nombre del libro a buscar");
        String nombre = sc.nextLine();
        libroService.buscarLibro(nombre).forEach(System.out::println);

    }



}
