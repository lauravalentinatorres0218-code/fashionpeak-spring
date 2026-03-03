package com.fashionpeak.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * HomeController - Controlador para páginas principales de Fashion Peak.
 *
 * @author Laura Valentina Torres Chaparro
 * @version 1.0
 */
@Controller
public class HomeController {

    /** Página principal - redirige al catálogo */
    @GetMapping("/")
    public String inicio() {
        return "redirect:/catalogo";
    }

    /** Catálogo de productos */
    @GetMapping("/catalogo")
    public String catalogo() {
        return "catalogo";
    }

    /** Página de login */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /** Carrito de compras */
    @GetMapping("/carrito")
    public String carrito() {
        return "carrito";
    }

    /** Confirmación de compra */
    @GetMapping("/confirmacion")
    public String confirmacion() {
        return "confirmacion";
    }
}
