package com.fashionpeak.controller;

import com.fashionpeak.model.Producto;
import com.fashionpeak.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

/**
 * ProductoController - Controlador Spring MVC para la gestión de productos.
 * Maneja las peticiones HTTP GET y POST del módulo de productos Fashion Peak.
 *
 * @author Laura Valentina Torres Chaparro
 * @version 1.0
 */
@Controller
@RequestMapping("/productos")
public class ProductoController {

    /** Inyección del servicio de productos */
    @Autowired
    private ProductoService productoService;

    // =============================================
    // GET - Listar todos los productos
    // =============================================

    /**
     * Muestra la lista de todos los productos en el panel admin
     * @param model modelo para pasar datos a la vista
     * @return nombre de la plantilla Thymeleaf
     */
    @GetMapping
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.obtenerTodos());
        model.addAttribute("titulo", "Gestión de Productos");
        return "admin/lista-productos";
    }

    // =============================================
    // GET - Mostrar formulario para nuevo producto
    // =============================================

    /**
     * Muestra el formulario para crear un nuevo producto
     * @param model modelo para pasar objeto vacío al formulario
     * @return plantilla del formulario
     */
    @GetMapping("/nuevo")
    public String mostrarFormNuevo(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("titulo", "Nuevo Producto");
        model.addAttribute("accion", "Guardar");
        return "admin/formulario-producto";
    }

    // =============================================
    // GET - Mostrar formulario para editar
    // =============================================

    /**
     * Muestra el formulario pre-cargado para editar un producto existente
     * @param id    identificador del producto a editar
     * @param model modelo para pasar datos a la vista
     * @return plantilla del formulario o redirección si no existe
     */
    @GetMapping("/editar/{id}")
    public String mostrarFormEditar(@PathVariable int id, Model model) {
        Optional<Producto> producto = productoService.obtenerPorId(id);
        if (producto.isPresent()) {
            model.addAttribute("producto", producto.get());
            model.addAttribute("titulo", "Editar Producto");
            model.addAttribute("accion", "Actualizar");
            return "admin/formulario-producto";
        }
        return "redirect:/productos";
    }

    // =============================================
    // POST - Guardar (insertar o actualizar)
    // =============================================

    /**
     * Procesa el formulario y guarda el producto en la base de datos
     * @param producto          objeto Producto con los datos del formulario
     * @param bindingResult     resultado de las validaciones
     * @param redirectAttributes atributos para mensajes flash
     * @return redirección a la lista o al formulario si hay errores
     */
    @PostMapping("/guardar")
    public String guardarProducto(
            @Valid @ModelAttribute("producto") Producto producto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model) {

        // Si hay errores de validación, vuelve al formulario
        if (bindingResult.hasErrors()) {
            model.addAttribute("titulo", producto.getIdProducto() == 0 ? "Nuevo Producto" : "Editar Producto");
            model.addAttribute("accion", producto.getIdProducto() == 0 ? "Guardar" : "Actualizar");
            return "admin/formulario-producto";
        }

        productoService.guardar(producto);
        String msg = producto.getIdProducto() == 0
            ? "✅ Producto creado correctamente."
            : "✅ Producto actualizado correctamente.";
        redirectAttributes.addFlashAttribute("mensaje", msg);
        return "redirect:/productos";
    }

    // =============================================
    // GET - Eliminar producto
    // =============================================

    /**
     * Elimina un producto por su ID y redirige a la lista
     * @param id                identificador del producto a eliminar
     * @param redirectAttributes atributos para mensaje de confirmación
     * @return redirección a la lista de productos
     */
    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable int id,
                                    RedirectAttributes redirectAttributes) {
        productoService.eliminar(id);
        redirectAttributes.addFlashAttribute("mensaje", "🗑️ Producto eliminado correctamente.");
        return "redirect:/productos";
    }
}
