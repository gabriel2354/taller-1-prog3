package com.taller_1.programacion_3.Servicio;

import com.taller_1.programacion_3.Entidad.Funciones;
import com.taller_1.programacion_3.Repositorio.FuncionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionServicio {
@Autowired
    private FuncionRepositorio funcionRepositorio;
    public List<Funciones> listarFunciones() {
        return funcionRepositorio.findAll();
    }

    public Optional<Funciones> obtenerFuncionPorId(Long id) {
        return funcionRepositorio.findById(id);
    }

    public Funciones guardarFuncion(Funciones funciones) {
        return funcionRepositorio.save(funciones);
    }

    public void eliminarFuncion(Long id) {
        funcionRepositorio.deleteById(id);
    }

    public List<Funciones> buscarPorRangoDeHorario(LocalDateTime inicio, LocalDateTime fin) {
        return funcionRepositorio.findByHorarioBetween(inicio, fin);
    }
}
