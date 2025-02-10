
package com.taller_1.programacion_3.Repositorio;

import com.taller_1.programacion_3.Entidad.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepositorio extends JpaRepository<Reserva, Long> {

    List<Reserva> findByClienteId(Long clienteId);

    List<Reserva> findByPeliculaId(Long peliculaId);
}
