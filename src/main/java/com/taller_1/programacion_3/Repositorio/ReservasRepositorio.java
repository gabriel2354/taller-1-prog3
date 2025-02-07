package com.taller_1.programacion_3.Repositorio;

import com.taller_1.programacion_3.Entidad.ReservasPeliculas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservasRepositorio extends JpaRepository<ReservasPeliculas, Long> {
    List<ReservasPeliculas> findByCliente_Id(Long clienteId);
}
