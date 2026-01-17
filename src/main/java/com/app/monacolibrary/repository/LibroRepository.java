package com.app.monacolibrary.repository;

import com.app.monacolibrary.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface LibroRepository extends JpaRepository<Libro, Long>{


    List<Libro> findByTituloContainingIgnoreCase(String titulo);
}
