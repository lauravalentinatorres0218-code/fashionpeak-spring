# Fashion Peak - Spring Boot Framework
## GA7-220501096-AA3-EV01

**Autor:** Laura Valentina Torres Chaparro | **Ficha:** 2977463 | **SENA 2026**

## Framework utilizado: Spring Boot 3.2 + Thymeleaf + Spring Data JPA

## Estructura
```
src/main/java/com/fashionpeak/
├── FashionPeakApplication.java   ← Inicio Spring Boot
├── model/Producto.java            ← Entidad JPA
├── repository/ProductoRepository  ← Spring Data JPA
├── service/ProductoService        ← Lógica de negocio
└── controller/
    ├── ProductoController         ← CRUD Admin (GET/POST)
    └── CatalogoController         ← Vista cliente
src/main/resources/
├── application.properties         ← Configuración BD
└── templates/
    ├── catalogo.html              ← Thymeleaf catálogo
    └── admin/
        ├── lista-productos.html   ← Tabla admin
        └── formulario-producto.html ← Formulario
```

## Requisitos
- Java 17+, Maven 3.8+, MySQL 8+

## Ejecutar
```bash
mvn spring-boot:run
```
Abrir: http://localhost:8080
