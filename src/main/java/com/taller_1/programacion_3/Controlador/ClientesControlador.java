package com.taller_1.programacion_3.Controlador;

import com.taller_1.programacion_3.Entidad.Clientes;
import com.taller_1.programacion_3.Servicio.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/clientes") // Ruta base para todos los endpoints relacionados con clientes
public class ClientesControlador {

    @Autowired
    private ClienteServicio clienteServicio;

    // Listar Clientes
    @GetMapping
    public String mostrarClientes(@RequestParam(name = "buscarCliente", required = false, defaultValue = "") String buscarCliente, Model model) {
        List<Clientes> clientes = clienteServicio.buscarClientesPorNombre(buscarCliente);
        model.addAttribute("buscarCliente", buscarCliente);
        model.addAttribute("clientes", clientes);
        return "Listas/listaFormulario"; // Ajusta la ruta según el archivo existente
    }

    // Crear Cliente
    @GetMapping("/formulario")
    public String formularioCliente(Model model) {
        model.addAttribute("cliente", new Clientes());
        return "pages/formularioClientes"; // Verifica esta ubicación
    }

    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute Clientes cliente) {
        if (cliente.getId() != null) {
            // Si el ID no es nulo, verifica si el cliente existe
            Optional<Clientes> clienteExistente = clienteServicio.buscarClientePorId(cliente.getId());
            if (clienteExistente.isPresent()) {
                Clientes clienteActualizado = clienteExistente.get();
                clienteActualizado.setNombre(cliente.getNombre());
                clienteActualizado.setApellido(cliente.getApellido());
                clienteActualizado.setDireccion(cliente.getDireccion());
                clienteActualizado.setTelefono(cliente.getTelefono());
                clienteActualizado.setFechaNacimiento(cliente.getFechaNacimiento());
                clienteServicio.guardarCliente(clienteActualizado);
            } else {
                // Si el cliente no existe, simplemente guarda el nuevo
                clienteServicio.guardarCliente(cliente);
            }
        } else {
            // Si el ID es nulo, es un cliente nuevo
            clienteServicio.guardarCliente(cliente);
        }
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String editarCliente(@PathVariable Long id, Model model) {
        Optional<Clientes> cliente = clienteServicio.buscarClientePorId(id);
        if (cliente.isPresent()) {
            model.addAttribute("cliente", cliente.get());
        } else {
            model.addAttribute("cliente", new Clientes());
            model.addAttribute("error", "Cliente no encontrado");
        }
        return "pages/formularioClientes"; // Asegúrate de que esta ruta sea correcta
    }

    // Eliminar Cliente
    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteServicio.eliminarCliente(id);
        return "redirect:/clientes";
    }
}
