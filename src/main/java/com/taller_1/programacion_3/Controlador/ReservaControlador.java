package com.taller_1.programacion_3.Controlador;

import com.taller_1.programacion_3.Entidad.Clientes;
import com.taller_1.programacion_3.Entidad.Reserva;
import com.taller_1.programacion_3.Entidad.Pelicula;
import com.taller_1.programacion_3.Servicio.ReservaServicio;
import com.taller_1.programacion_3.Servicio.ClienteServicio;
import com.taller_1.programacion_3.Servicio.PeliculaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/reservas")
public class ReservaControlador {

    @Autowired
    private ReservaServicio reservaServicio;

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private PeliculaServicio peliculaServicio;

    @GetMapping
    public String listarReservas(Model model) {
        List<Reserva> reservas = reservaServicio.listarReservas();
        model.addAttribute("reservas", reservas);
        return "Listas/listaReserva";
    }

    @GetMapping("/formulario")
    public String mostrarFormularioReserva(Model model) {
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("clientes", clienteServicio.listarTodosLosClientes());
        model.addAttribute("peliculas", peliculaServicio.listarPeliculas());
        return "pages/formularioReserva";
    }

    @PostMapping("/guardar")
    public String guardarReserva(@ModelAttribute Reserva reserva) {
        reservaServicio.guardarReserva(reserva);
        return "redirect:/reservas";
    }

    @GetMapping("/editar/{id}")
    public String editarReserva(@PathVariable Long id, Model model) {
        Optional<Reserva> reserva = reservaServicio.obtenerReservaPorId(id);
        if (reserva.isPresent()) {
            model.addAttribute("reserva", reserva.get());
            model.addAttribute("clientes", clienteServicio.listarTodosLosClientes());
            model.addAttribute("peliculas", peliculaServicio.listarPeliculas());
            return "pages/formularioReserva";
        }
        return "redirect:/reservas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarReserva(@PathVariable Long id) {
        reservaServicio.eliminarReserva(id);
        return "redirect:/reservas";
    }
}
