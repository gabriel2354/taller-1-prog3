package com.taller_1.programacion_3.Controlador;

import com.taller_1.programacion_3.Entidad.Pelicula;
import com.taller_1.programacion_3.Servicio.PeliculaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/peliculas")
public class controladorPelicula {

    @Autowired
    private PeliculaServicio peliculaServicio;

    // Mostrar lista de películas
    @GetMapping
    public String listarPeliculas(Model model) {
        List<Pelicula> peliculas = peliculaServicio.listarPeliculas();
        model.addAttribute("peliculas", peliculas);
        return "listas/listaPelicula"; // Apunta al archivo HTML para mostrar la lista
    }

    // Mostrar formulario para crear una nueva película
    @GetMapping("/formulario")
    public String mostrarFormularioPelicula(Model model) {
        Pelicula pelicula = new Pelicula();
        model.addAttribute("pelicula", pelicula);
        return "pages/formularioPelicula"; // Apunta al archivo HTML del formulario
    }

    // Guardar una nueva película o actualizar una existente
    @PostMapping("/guardar")
    public String guardarPelicula(@ModelAttribute("pelicula") Pelicula pelicula) {
        peliculaServicio.guardarPelicula(pelicula);
        return "redirect:/peliculas"; // Redirige a la lista de películas
    }

    // Mostrar formulario para editar una película existente
    @GetMapping("/editar/{id}")
    public String editarPelicula(@PathVariable("id") Integer id, Model model) {
        Pelicula pelicula = peliculaServicio.obtenerPeliculaPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de película no válido: " + id));
        model.addAttribute("pelicula", pelicula);
        return "pages/formularioPelicula"; // Reutiliza el formulario
    }

    // Eliminar una película
    @GetMapping("/eliminar/{id}")
    public String eliminarPelicula(@PathVariable("id") Integer id) {
        peliculaServicio.eliminarPelicula(id);
        return "redirect:/peliculas"; // Redirige a la lista de películas
    }
}