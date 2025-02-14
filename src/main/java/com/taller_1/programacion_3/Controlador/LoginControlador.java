package com.taller_1.programacion_3.Controlador;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginControlador {
    @GetMapping("/login")
    public String login(){
        return "pages/Login";
    }

    @GetMapping("/postLogin")
    public String dirigirPorRol(Authentication authentication){
        User usuario= (User)  authentication.getPrincipal();
        String role= usuario.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .findFirst()
                .orElse("");
        if(role.equals("ROLE_ADMIN")){
            return "redirect:/admin";
        }else if (role.equals("ROLE_CLIENTE")){
            return "redirect:/peliculas/";

        }else if(role.equals("ROLE_EMPLEADO")){
            return "redirect:/reservas";
        }
        return "redirect:/login?error";
    }
}
