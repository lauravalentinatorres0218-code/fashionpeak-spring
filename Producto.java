package com.fashionpeak.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

/**
 * Entidad Producto - Representa un producto de Fashion Peak.
 * Mapeada a la tabla 'productos' con Spring Data JPA.
 *
 * @author Laura Valentina Torres Chaparro
 * @version 1.0
 */
@Entity
@Table(name = "productos")
public class Producto {

    /** Identificador único auto-generado */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private int idProducto;

    /** Nombre del producto - obligatorio, máximo 100 caracteres */
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar 100 caracteres")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    /** Precio del producto - debe ser positivo */
    @Positive(message = "El precio debe ser mayor a 0")
    @Column(name = "precio", nullable = false)
    private double precio;

    /** Cantidad disponible en inventario */
    @Min(value = 0, message = "La cantidad no puede ser negativa")
    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    /** Categoría del producto: Mujer u Hombre */
    @NotBlank(message = "La categoría es obligatoria")
    @Column(name = "categoria", nullable = false)
    private String categoria;

    /** Nombre del archivo de imagen */
    @Column(name = "imagen")
    private String imagen;

    // Constructor vacío requerido por JPA
    public Producto() {}

    // Constructor completo
    public Producto(String nombre, double precio, int cantidad, String categoria, String imagen) {
        this.nombre    = nombre;
        this.precio    = precio;
        this.cantidad  = cantidad;
        this.categoria = categoria;
        this.imagen    = imagen;
    }

    // Getters y Setters
    public int    getIdProducto() { return idProducto; }
    public String getNombre()     { return nombre; }
    public double getPrecio()     { return precio; }
    public int    getCantidad()   { return cantidad; }
    public String getCategoria()  { return categoria; }
    public String getImagen()     { return imagen; }

    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }
    public void setNombre(String nombre)       { this.nombre = nombre; }
    public void setPrecio(double precio)       { this.precio = precio; }
    public void setCantidad(int cantidad)      { this.cantidad = cantidad; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setImagen(String imagen)       { this.imagen = imagen; }

    @Override
    public String toString() {
        return String.format("Producto{id=%d, nombre='%s', precio=%.2f}", idProducto, nombre, precio);
    }
}
