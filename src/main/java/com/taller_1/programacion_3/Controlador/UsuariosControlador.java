package com.taller_1.programacion_3.Controlador;

import com.taller_1.programacion_3.Entidad.Usuarios;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class UsuariosControlador {

    @GetMapping("/formularioUsuarios")
    public String mostrarFormulario(Model model) {
        // Se crea un nuevo objeto Usuarios
        Usuarios usuarios = new Usuarios();
        // Se pasa al modelo con el nombre "usuarios" (minúscula)
        model.addAttribute("usuarios", usuarios);

        // Lista de tipos de usuario
        List<String> tiposUsuarios = Arrays.asList("cliente", "administrador");
        model.addAttribute("tiposUsuarios", tiposUsuarios);

        // Se devuelve la vista del formulario
        return "pages/formularioUsuarios";
    }

    @PostMapping("/formularioUsuarios")
    public String procesarFormulario(
            @Valid @ModelAttribute("usuarios") Usuarios usuarios,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            // Si hay errores de validación, se devuelve el formulario con los errores
            List<String> tiposUsuarios = Arrays.asList("cliente", "administrador");
            model.addAttribute("tiposUsuarios", tiposUsuarios);
            return "pages/formularioUsuarios";
        }

        // Aquí se puede implementar la lógica para guardar el usuario
        System.out.println("Usuario registrado: " + usuarios);

        // Redirige a una página de éxito o a otra funcionalidad
        return "redirect:/home";
    }
}
