package com.fashionpeak.service;

import com.fashionpeak.model.Producto;
import com.fashionpeak.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * ProductoService - Capa de servicio para la lógica de negocio de Producto.
 * Actúa como intermediario entre el Controller y el Repository.
 *
 * @author Laura Valentina Torres Chaparro
 * @version 1.0
 */
@Service
public class ProductoService {

    /** Inyección del repositorio mediante Spring */
    @Autowired
    private ProductoRepository productoRepository;

    /**
     * Obtiene todos los productos de la base de datos
     * @return lista completa de productos
     */
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    /**
     * Obtiene los productos disponibles (cantidad > 0)
     * @return lista de productos con stock
     */
    public List<Producto> obtenerDisponibles() {
        return productoRepository.findProductosDisponibles();
    }

    /**
     * Busca un producto por su ID
     * @param id identificador del producto
     * @return Optional con el producto o vacío si no existe
     */
    public Optional<Producto> obtenerPorId(int id) {
        return productoRepository.findById(id);
    }

    /**
     * Filtra productos por categoría
     * @param categoria Mujer u Hombre
     * @return lista de productos de esa categoría
     */
    public List<Producto> obtenerPorCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    /**
     * Busca productos por nombre (búsqueda parcial)
     * @param nombre texto a buscar
     * @return lista de productos que coincidan
     */
    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    /**
     * Guarda o actualiza un producto (INSERT o UPDATE)
     * @param producto objeto Producto a guardar
     * @return producto guardado con su ID generado
     */
    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    /**
     * Elimina un producto por su ID
     * @param id identificador del producto a eliminar
     */
    public void eliminar(int id) {
        productoRepository.deleteById(id);
    }
}
