package com.taller_1.programacion_3.Servicio;

import com.taller_1.programacion_3.Entidad.Reserva;
import com.taller_1.programacion_3.Repositorio.ReservaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaServicio {

    @Autowired
    private ReservaRepositorio reservaRepositorio;

    public List<Reserva> listarReservas() {
        return reservaRepositorio.findAll();
    }

    public Optional<Reserva> obtenerReservaPorId(Long id) {
        return reservaRepositorio.findById(id);
    }

    public Reserva guardarReserva(Reserva reserva) {
        if (reserva.getFechaReserva() == null) {
            reserva.setFechaReserva(LocalDateTime.now());
        }
        return reservaRepositorio.save(reserva);
    }

    public void eliminarReserva(Long id) {
        reservaRepositorio.deleteById(id);
    }

    public List<Reserva> obtenerReservasPorCliente(Long clienteId) {
        return reservaRepositorio.findByClienteId(clienteId);
    }

    public List<Reserva> obtenerReservasPorPelicula(Long peliculaId) {
        return reservaRepositorio.findByPeliculaId(peliculaId);
    }
}
