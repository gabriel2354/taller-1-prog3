package com.taller_1.programacion_3.Controlador;

import com.taller_1.programacion_3.Entidad.ReservasPeliculas;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ReservasControlador {

    private final List<String> peliculas = List.of("Avatar", "Inception", "Matrix");
    private final List<String> funciones = List.of("10:00 AM", "2:00 PM", "6:00 PM");

    @GetMapping("/formularioReservas")
    public String mostrarFormularioReservas(Model model) {
        ReservasPeliculas reserva = new ReservasPeliculas();
        model.addAttribute("boleto", reserva);
        model.addAttribute("peliculas", peliculas);
        model.addAttribute("funciones", funciones);
        return "pages/formularioReservas";
    }

    @PostMapping("/formularioReservas")
    public String procesarReserva(@Valid @ModelAttribute("boleto") ReservasPeliculas reserva,
                                  BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("peliculas", peliculas);
            model.addAttribute("funciones", funciones);
            return "pages/formularioReservas";
        }

        System.out.println("Reserva procesada con éxito: " + reserva);
        return "redirect:/home";
    }
}
