package com.taller_1.programacion_3.Controlador;

import com.taller_1.programacion_3.Entidad.ReservasPeliculas;
import com.taller_1.programacion_3.Servicio.ReservasServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reservas") // Ruta base para vistas relacionadas con reservas
public class ReservasControlador {

    @Autowired
    private ReservasServicio reservasServicio;

    // Mostrar el formulario de reservas
    @GetMapping("/formulario")
    public String mostrarFormularioReservas(Model model) {
        model.addAttribute("reserva", new ReservasPeliculas());
        return "pages/formularioReservas"; // Ruta a la plantilla HTML
    }

    // Guardar una nueva reserva desde el formulario
    @PostMapping("/guardar")
    public String guardarReserva(@Valid @ModelAttribute("reserva") ReservasPeliculas reserva, BindingResult result, Model model) {
        // Verifica si hay errores en la validación
        if (result.hasErrors()) {
            // Devuelve el formulario con los errores mostrados
            return "pages/formularioReservas";
        }

        // Guarda la reserva si no hay errores
        reservasServicio.crearReserva(reserva);
        return "redirect:/reservas/lista"; // Redirige a la lista de reservas
    }


    // Mostrar lista de reservas
    @GetMapping("/lista")
    public String listarReservas(Model model) {
        model.addAttribute("reservas", reservasServicio.obtenerTodasLasReservas());
        return "pages/listaReservas"; // Ruta a la plantilla HTML
    }
}
