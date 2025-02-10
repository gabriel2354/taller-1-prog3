package com.taller_1.programacion_3.Controlador;

import com.taller_1.programacion_3.Entidad.Funciones;
import com.taller_1.programacion_3.Servicio.FuncionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funciones")
public class ControladorFunciones {
   @Autowired
   private FuncionServicio funcionServicio;


    @GetMapping
    public List<Funciones> listarFunciones() {
        return funcionServicio.listarFunciones();
    }

    @GetMapping("obtenerFuncion/{id}")
    public ResponseEntity<Funciones> obtenerFuncionPorId(@PathVariable Long id) {
        Optional<Funciones> funcion = funcionServicio.obtenerFuncionPorId(id);
        return funcion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Funciones crearFuncion(@RequestBody Funciones funcion) {
        return funcionServicio.guardarFuncion(funcion);
    }

    @DeleteMapping("/eliminarFuncion/{id}")
    public ResponseEntity<Void> eliminarFuncion(@PathVariable Long id) {
        if (funcionServicio.obtenerFuncionPorId(id).isPresent()) {
            funcionServicio.eliminarFuncion(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/buscarFuncion")
    public List<Funciones> buscarPorHorario(@RequestParam LocalDateTime inicio, @RequestParam LocalDateTime fin) {
        return funcionServicio.buscarPorRangoDeHorario(inicio, fin);
    }

}
