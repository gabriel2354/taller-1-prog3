package com.taller_1.programacion_3.Servicio;

import com.taller_1.programacion_3.Entidad.Clientes;
import com.taller_1.programacion_3.Entidad.ReservasPeliculas;
import com.taller_1.programacion_3.Repositorio.ClientesRepositorio;
import com.taller_1.programacion_3.Repositorio.ReservasRepositorio;
import com.taller_1.programacion_3.Repositorio.ReservasRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservasServicio {




    @Autowired
    private ReservasRepositorio reservasRepositorio;

    public Optional<ReservasPeliculas> obtenerReservaPorId(Long id) {
        return reservasRepositorio.findById(id);
    }

    @Autowired
    private ClientesRepositorio clientesRepositorio;

    public List<Clientes> obtenerTodosLosClientes() {
        return clientesRepositorio.findAll(); // Método proporcionado por JpaRepository
    }
    @Autowired
    private ReservasRepositorio ReservasRepositorio; // Corregido: Ahora inyectamos el repositorio correcto

    // Obtener todas las reservas
    public List<ReservasPeliculas> obtenerTodasLasReservas() {
        return reservasRepositorio.findAll();
    }

    // Obtener reservas por cliente
    public List<ReservasPeliculas> obtenerReservasPorCliente(Long clienteId) {
        return reservasRepositorio.findByCliente_Id(clienteId);
    }

    // Crear una nueva reserva
    public ReservasPeliculas crearReserva(ReservasPeliculas nuevaReserva) {
        return reservasRepositorio.save(nuevaReserva);
    }

    // Actualizar una reserva existente
    public ReservasPeliculas actualizarReserva(Long id, ReservasPeliculas detallesReserva) {
        // Buscar la reserva por ID
        Optional<ReservasPeliculas> reservaExistenteOpt = reservasRepositorio.findById(id);
        if (reservaExistenteOpt.isPresent()) {
            ReservasPeliculas reservaExistente = reservaExistenteOpt.get();
            // Actualizar los detalles de la reserva
            reservaExistente.setPelicula(detallesReserva.getPelicula());
            reservaExistente.setHorario(detallesReserva.getHorario());
            reservaExistente.setAsiento(detallesReserva.getAsiento());
            reservaExistente.setMontoPagado(detallesReserva.getMontoPagado());
            reservaExistente.setCliente(detallesReserva.getCliente());
            return reservasRepositorio.save(reservaExistente);
        } else {
            throw new RuntimeException("Reserva no encontrada con ID: " + id);
        }
    }

    // Eliminar una reserva por ID
    public void eliminarReserva(Long id) {
        if (reservasRepositorio.existsById(id)) {
            reservasRepositorio.deleteById(id);
        } else {
            throw new RuntimeException("Reserva no encontrada con ID: " + id);
        }
    }
}
