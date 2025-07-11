package com.ejemplo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Pruebas de integración para DescuentoRepository
 */
@DisplayName("Pruebas del repositorio de descuentos")
class DescuentoRepositoryTest {

    private DescuentoRepository repository;

    @BeforeEach
    void setUp() {
        repository = new DescuentoRepository();
    }

    @Test
    @DisplayName("Obtener descuento PROMO10")
    void testDescuentoPROMO10() {
        double descuento = repository.obtenerPorcentaje("PROMO10");
        assertEquals(0.10, descuento, 0.001);
    }

    @Test
    @DisplayName("Obtener descuento PROMO20")
    void testDescuentoPROMO20() {
        double descuento = repository.obtenerPorcentaje("PROMO20");
        assertEquals(0.20, descuento, 0.001);
    }

    @Test
    @DisplayName("Obtener descuento VIP")
    void testDescuentoVIP() {
        double descuento = repository.obtenerPorcentaje("VIP");
        assertEquals(0.15, descuento, 0.001);
    }

    @Test
    @DisplayName("Código inexistente debe retornar 0")
    void testCodigoInexistente() {
        double descuento = repository.obtenerPorcentaje("INEXISTENTE");
        assertEquals(0.0, descuento, 0.001);
    }

    @Test
    @DisplayName("Código null debe retornar 0")
    void testCodigoNull() {
        double descuento = repository.obtenerPorcentaje(null);
        assertEquals(0.0, descuento, 0.001);
    }

    @Test
    @DisplayName("Código en minúsculas debe funcionar")
    void testCodigoMinusculas() {
        double descuento = repository.obtenerPorcentaje("promo10");
        assertEquals(0.10, descuento, 0.001);
    }
}
