package com.taller_1.programacion_3.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControlador {

    @GetMapping("/")
    public String mostrarHome() {
        return "pages/home"; // Vista de inicio
    }

    @GetMapping("/cartelera")
    public String mostrarCartelera() {
        return "pages/cartelera"; // Vista de la cartelera
    }
}
