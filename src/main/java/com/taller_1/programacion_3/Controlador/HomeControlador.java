package com.taller_1.programacion_3.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeControlador {

    @PostMapping("/home")
    public String mostrarHome() {
        return "pages/home"; // Vista de inicio
    }

    @GetMapping("/cartelera")
    public String mostrarCartelera() {
        return "pages/cartelera"; // Nombre del archivo cartelera.html en templates
    }

}

