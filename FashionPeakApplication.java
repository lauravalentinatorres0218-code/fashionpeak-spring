package com.fashionpeak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * FashionPeakApplication - Clase principal de Spring Boot.
 * GA7-220501096-AA3-EV01
 * @author Laura Valentina Torres Chaparro
 */
@SpringBootApplication
public class FashionPeakApplication {
    public static void main(String[] args) {
        SpringApplication.run(FashionPeakApplication.class, args);
        System.out.println("✅ Fashion Peak iniciado en http://localhost:8080");
    }
}
