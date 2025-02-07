package com.taller_1.programacion_3.Repositorio;

import com.taller_1.programacion_3.Entidad.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientesRepositorio extends JpaRepository<Clientes, Long> {
    List<Clientes> findByNombreContainingIgnoreCase(String nombre);
}
