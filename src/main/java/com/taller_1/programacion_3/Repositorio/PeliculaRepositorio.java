package com.taller_1.programacion_3.Repositorio;

import com.taller_1.programacion_3.Entidad.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepositorio extends JpaRepository<Pelicula, Integer> {
}
