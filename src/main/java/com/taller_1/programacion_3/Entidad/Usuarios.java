package com.taller_1.programacion_3.Entidad;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Usuarios {

    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres.")
    private String nombre;

    @NotBlank(message = "El correo electrónico es obligatorio.")
    @Email(message = "Debe proporcionar un correo electrónico válido.")
    private String correo;

    @NotBlank(message = "La contraseña es obligatoria.")
    @Size(min = 8, max = 100, message = "La contraseña debe tener entre 8 y 100 caracteres.")
    private String contraseña;

    @NotBlank(message = "El teléfono de contacto es obligatorio.")
    @Pattern(
            regexp = "^\\d{10}$",
            message = "El número de teléfono debe tener exactamente 10 dígitos."
    )
    private String telefono;

    @NotBlank(message = "El tipo de usuario es obligatorio.")
    @Pattern(
            regexp = "^(cliente|administrador)$",
            message = "El tipo de usuarios debe ser 'cliente' o 'administrador'."
    )
    private String tipoUsuarios;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipoUsuarios() {
        return tipoUsuarios;
    }

    public void setTipoUsuarios(String tipoUsuarios) {
        this.tipoUsuarios = tipoUsuarios;
    }
}
