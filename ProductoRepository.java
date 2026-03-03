package com.fashionpeak.repository;

import com.fashionpeak.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ProductoRepository - Repositorio JPA para la entidad Producto.
 * Extiende JpaRepository para heredar operaciones CRUD automáticas.
 *
 * @author Laura Valentina Torres Chaparro
 * @version 1.0
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    /**
     * Busca productos por categoría
     * @param categoria categoría a filtrar (Mujer / Hombre)
     * @return lista de productos de esa categoría
     */
    List<Producto> findByCategoria(String categoria);

    /**
     * Busca productos cuyo nombre contenga el texto indicado (búsqueda)
     * @param nombre texto a buscar en el nombre
     * @return lista de productos que coincidan
     */
    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    /**
     * Consulta productos con stock disponible mayor a 0
     * @return lista de productos disponibles
     */
    @Query("SELECT p FROM Producto p WHERE p.cantidad > 0 ORDER BY p.nombre")
    List<Producto> findProductosDisponibles();
}
