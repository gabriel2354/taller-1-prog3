package com.taller_1.programacion_3.Entidad;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservasPeliculas {

    @NotBlank(message = "El nombre es obligatorio.")
    private String nombreUsuario;

    @NotBlank(message = "Debe seleccionar una película.")
    private String idPelicula;

    @NotBlank(message = "Debe seleccionar un horario.")
    private String idFuncion;

    @NotBlank(message = "El asiento es obligatorio.")
    @Size(max = 10, message = "El número de asiento no debe exceder los 10 caracteres.")
    private String asiento;

    @NotNull(message = "El monto pagado es obligatorio.")
    @DecimalMin(value = "1.0", message = "El monto debe ser mayor o igual a 1.")
    @DecimalMax(value = "500.0", message = "El monto no puede exceder los 500.")
    private Double montoPagado;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(String idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getIdFuncion() {
        return idFuncion;
    }

    public void setIdFuncion(String idFuncion) {
        this.idFuncion = idFuncion;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    public Double getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(Double montoPagado) {
        this.montoPagado = montoPagado;
    }
}
