package com.taller_1.programacion_3.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControlador {

    @GetMapping("/home")
    public String mostrarHome() {
        return "pages/home"; // Vista de inicio
    }

    @GetMapping("/cartelera")
    public String mostrarCartelera() {
        return "pages/cartelera"; // Nombre del archivo cartelera.html en templates
    }

    // Página de Login
    @GetMapping("/login")
    public String mostrarLogin() {
        return "pages/login"; // Verifica que el archivo login.html existe en templates/pages/
    }
}

