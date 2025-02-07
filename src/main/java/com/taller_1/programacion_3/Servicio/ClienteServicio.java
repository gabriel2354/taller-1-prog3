package com.taller_1.programacion_3.Servicio;

import com.taller_1.programacion_3.Entidad.Clientes;
import com.taller_1.programacion_3.Repositorio.ClientesRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicio {

    @Autowired
    private ClientesRepositorio clientesRepositorio;

    // Guardar o Actualizar un Cliente
    public void guardarCliente(Clientes cliente) {
        clientesRepositorio.save(cliente);
    }

    // Buscar Cliente por ID
    public Optional<Clientes> buscarClientePorId(Long id) {
        return clientesRepositorio.findById(id);
    }

    // Eliminar Cliente por ID
    public void eliminarCliente(Long id) {
        if (clientesRepositorio.existsById(id)) {
            clientesRepositorio.deleteById(id);
        }
    }

    // Listar Todos los Clientes
    public List<Clientes> listarTodosLosClientes() {
        return clientesRepositorio.findAll();
    }

    // Buscar Clientes por Nombre
    public List<Clientes> buscarClientesPorNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            return clientesRepositorio.findAll(); // Si no se especifica nombre, devuelve todos los clientes
        }
        return clientesRepositorio.findByNombreContainingIgnoreCase(nombre);
    }

    // Generar un PDF con la lista de clientes (Lógica simulada, ajusta según tu implementación)
    public String generarPdf() throws Exception {
        // Simulación de la ruta donde se generará el archivo PDF
        String rutaPdf = "clientes.pdf";

        // Aquí deberías implementar la lógica para generar el PDF, por ejemplo, usando Apache PDFBox
        // Ejemplo:
        // PDDocument document = new PDDocument();
        // ... lógica de creación de contenido ...
        // document.save(rutaPdf);
        // document.close();

        // Devuelve la ruta del PDF generado
        return rutaPdf;
    }
}
