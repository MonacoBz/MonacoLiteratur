package com.app.monacolibrary.ui;

import com.app.monacolibrary.service.ApiRequest;

import java.util.Scanner;

public class Ui {

    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private final Scanner sc = new Scanner(System.in);
    private final ApiRequest API = new ApiRequest();

    public void menu(){
        String inicio = """
                Bienvenido a Monaco Library, elija una opción:
                1)Buscar un libro por titulo
                2)Listar libros registrados
                3)Listar autores registrados
                4)Listar autores vivos en un determinado año
                5)Listar libros por idioma
                """;
        System.out.println(inicio);
        int opcion = sc.nextInt();

        while(opcion != 0){
            sc.nextLine();
            switch (opcion){
                case 1 -> buscarLibroPorNombre();
                case 2 -> listarLibrosRegistrados();
                case 3 -> listarAutoresRegistrados();
                case 4 -> listarAutoresVivos();
                case 5 -> listarLibrosIdioma();
                
            }
            opcion = sc.nextInt();
            System.out.println(inicio);
        }
    }

    private void listarLibrosIdioma() {
    }

    private void listarAutoresVivos() {
    }

    private void listarAutoresRegistrados() {
        
    }

    private void listarLibrosRegistrados() {
        
    }

    private void buscarLibroPorNombre(){
        System.out.println("Introduce el nombre del libro a buscar");
        String nombre = sc.nextLine();
        try {
            var libro = API.realizaSolicitud(URL_BASE + nombre.replace(" ","%20"));
            System.out.println(libro);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
