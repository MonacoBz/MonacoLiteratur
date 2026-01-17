package com.app.monacolibrary.repository;

import com.app.monacolibrary.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor,Long> {

    Optional<Autor> findByNombreIgnoreCase(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.nacimiento <= :year AND (a.muerte IS NULL OR a.muerte >= :year)")
    List<Autor> buscaDeterminadoAnio(Integer year);
}
