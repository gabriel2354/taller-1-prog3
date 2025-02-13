package com.taller_1.programacion_3.Controlador;

import com.taller_1.programacion_3.Entidad.Usuario;
import com.taller_1.programacion_3.Servicio.UsuarioServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usuarios")
public class UsuarioControlador {

    private final UsuarioServicio usuarioServicio;

    public UsuarioControlador(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }
    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute Usuario usuario) {
        usuarioServicio.guardarUsuario(usuario);
        return "redirect:/usuarios?success"; // Redirige después de registrar el usuario
    }



    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("editMode", false); // ✅ Agregar `editMode` como `false` por defecto
        return "Usuario/formularioUsuario";
    }



    // ✅ Procesar el formulario de registro de usuario
    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuario usuario) {
        usuarioServicio.guardarUsuario(usuario);
        return "redirect:/usuarios"; // Redirige a la lista después del registro
    }

    // ✅ Mostrar la lista de usuarios
    @GetMapping
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioServicio.obtenerTodosLosUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "Usuario/ListaUsuario"; // 🔹 Asegurar que está dentro de templates/Usuario/
    }

    // ✅ Buscar usuarios por nombre
    @GetMapping("/buscar")
    public String buscarUsuarios(@RequestParam String nombre, Model model) {
        List<Usuario> usuarios = usuarioServicio.buscarUsuariosPorNombre(nombre);
        model.addAttribute("usuarios", usuarios);
        return "Usuario/ListaUsuario"; // 🔹 Carga la misma vista de lista con los resultados
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Optional<Usuario> usuario = usuarioServicio.obtenerUsuarioPorId(id);
        if (usuario.isPresent()) {
            model.addAttribute("usuario", usuario.get());
            model.addAttribute("editMode", true); // 🔹 Agregar modo edición para diferenciar entre crear y editar
            return "Usuario/FormularioUsuario"; // ✅ Reutilizando el mismo formulario
        } else {
            return "redirect:/usuarios?error"; // Redirige si el usuario no existe
        }
    }
    @PostMapping("/actualizar/{id}")
    public String actualizarUsuario(@PathVariable Long id, @ModelAttribute Usuario usuario, Model model) {
        try {
            usuarioServicio.actualizarUsuario(id, usuario);
            return "redirect:/usuarios?success"; // ✅ Redirige después de actualizar
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("usuario", usuario);
            model.addAttribute("editMode", true);
            return "Usuario/FormularioUsuario"; // 🔹 Mantiene el usuario en el formulario con el error
        }
    }

    // ✅ Eliminar usuario
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioServicio.eliminarUsuario(id);
        return "redirect:/usuarios?deleted"; // Redirige a la lista después de eliminar
    }

}
