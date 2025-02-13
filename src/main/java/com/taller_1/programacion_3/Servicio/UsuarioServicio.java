package com.taller_1.programacion_3.Servicio;

import com.taller_1.programacion_3.Entidad.Usuario;
import com.taller_1.programacion_3.Repositorio.UsuarioRepositorio;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {




    private final UsuarioRepositorio usuarioRepositorio;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioServicio(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // ✅ Guardar un nuevo usuario con contraseña encriptada
    public Usuario guardarUsuario(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword())); // 🔐 Encripta la contraseña antes de guardar
        return usuarioRepositorio.save(usuario);
    }

    // ✅ Obtener todos los usuarios
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepositorio.findAll();
    }

    // ✅ Buscar usuario por ID
    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepositorio.findById(id);
    }

    // ✅ Buscar usuario por nombre de usuario (para autenticación)
    public Usuario obtenerUsuarioPorUsername(String username) {
        return usuarioRepositorio.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con username: " + username));
    }

    // ✅ Buscar usuarios por coincidencia en el nombre (búsqueda flexible)
    public List<Usuario> buscarUsuariosPorNombre(String nombre) {
        return usuarioRepositorio.findByNombreContainingIgnoreCase(nombre);
    }

    public void eliminarUsuario(Long id) {
        if (usuarioRepositorio.existsById(id)) {
            usuarioRepositorio.deleteById(id);
        } else {
            throw new IllegalArgumentException("El usuario con ID " + id + " no existe.");
        }
    }


    // ✅ Método para actualizar usuario
    public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) {
        Optional<Usuario> usuarioExistente = usuarioRepositorio.findById(id);

        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();
            usuario.setNombre(usuarioActualizado.getNombre());

            // 🔹 Solo encripta la contraseña si se ha proporcionado una nueva
            if (usuarioActualizado.getPassword() != null && !usuarioActualizado.getPassword().isEmpty()) {
                usuario.setPassword(passwordEncoder.encode(usuarioActualizado.getPassword()));
            }

            return usuarioRepositorio.save(usuario);
        } else {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
    }



}
