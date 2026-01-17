package com.app.monacolibrary.repository;


import com.app.monacolibrary.models.LENGUAJE;
import com.app.monacolibrary.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface LibroRepository extends JpaRepository<Libro, Long>{


    List<Libro> findByTituloContainingIgnoreCase(String titulo);

    List<Libro> findByIdioma(LENGUAJE idioma);
}
