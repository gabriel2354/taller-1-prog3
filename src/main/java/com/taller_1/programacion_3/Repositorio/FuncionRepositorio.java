package com.taller_1.programacion_3.Repositorio;

import com.taller_1.programacion_3.Entidad.Funciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Jpa21Utils;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FuncionRepositorio extends JpaRepository<Funciones, Long> {
    List<Funciones> findByHorarioBetween(LocalDateTime inicio, LocalDateTime fin);

}
