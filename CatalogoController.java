package com.fashionpeak.controller;

import com.fashionpeak.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * CatalogoController - Controlador para el catálogo público de Fashion Peak.
 * Maneja la vista del cliente: catálogo, filtros y búsqueda.
 *
 * @author Laura Valentina Torres Chaparro
 * @version 1.0
 */
@Controller
public class CatalogoController {

    @Autowired
    private ProductoService productoService;

    /**
     * Página de inicio - redirige al catálogo
     */
    @GetMapping("/")
    public String inicio() {
        return "redirect:/catalogo";
    }

    /**
     * Muestra el catálogo de productos disponibles
     * @param categoria filtro opcional por categoría
     * @param buscar    búsqueda opcional por nombre
     * @param model     modelo para la vista
     */
    @GetMapping("/catalogo")
    public String catalogo(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String buscar,
            Model model) {

        // Aplicar filtros según los parámetros recibidos
        if (buscar != null && !buscar.trim().isEmpty()) {
            model.addAttribute("productos", productoService.buscarPorNombre(buscar));
            model.addAttribute("buscar", buscar);
        } else if (categoria != null && !categoria.isEmpty()) {
            model.addAttribute("productos", productoService.obtenerPorCategoria(categoria));
            model.addAttribute("categoriaActiva", categoria);
        } else {
            model.addAttribute("productos", productoService.obtenerDisponibles());
        }
        return "catalogo";
    }
}
