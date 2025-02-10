package com.taller_1.programacion_3.Servicio;

import com.taller_1.programacion_3.Entidad.Pelicula;
import com.taller_1.programacion_3.Repositorio.PeliculaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaServicio {

    @Autowired
    private PeliculaRepositorio peliculaRepositorio;

    /**
     * Listar todas las películas
     * @return Lista de películas
     */
    public List<Pelicula> listarPeliculas() {
        return peliculaRepositorio.findAll();
    }

    /**
     * Obtener una película por ID
     * @param id ID de la película
     * @return Película encontrada (opcional)
     */
    public Optional<Pelicula> obtenerPeliculaPorId(Integer id) {
        return peliculaRepositorio.findById(id);
    }

    /**
     * Guardar o actualizar una película
     * @param pelicula Objeto película a guardar o actualizar
     * @return Película guardada
     */
    public Pelicula guardarPelicula(Pelicula pelicula) {
        return peliculaRepositorio.save(pelicula);
    }

    /**
     * Eliminar una película por ID
     * @param id ID de la película
     */
    public void eliminarPelicula(Integer id) {
        if (peliculaRepositorio.existsById(id)) {
            peliculaRepositorio.deleteById(id);
        } else {
            throw new IllegalArgumentException("La película con ID " + id + " no existe.");
        }
    }
}
