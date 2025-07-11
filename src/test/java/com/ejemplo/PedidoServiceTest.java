package com.ejemplo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Pruebas unitarias para PedidoService (versión original)
 */
@DisplayName("Pruebas del servicio de pedidos")
class PedidoServiceTest {

    private PedidoService service;

    @BeforeEach
    void setUp() {
        service = new PedidoService();
    }

    @Test
    @DisplayName("Calcular total sin descuento y envío normal")
    void testSinDescuentoYEnvioNormal() {
        double total = service.calcularTotal(100, false, false);
        assertEquals(110.0, total, 0.01);
    }

    @Test
    @DisplayName("Calcular total con descuento y envío express")
    void testConDescuentoYEnvioExpress() {
        double total = service.calcularTotal(100, true, true);
        assertEquals(110.0, total, 0.01); // 100 - 10% + 20 = 90 + 20 = 110
    }

    @Test
    @DisplayName("Calcular total con descuento y envío normal")
    void testConDescuentoYEnvioNormal() {
        double total = service.calcularTotal(200, true, false);
        assertEquals(190.0, total, 0.01); // 200 - 10% + 10 = 180 + 10 = 190
    }

    @Test
    @DisplayName("Calcular total sin descuento y envío express")
    void testSinDescuentoYEnvioExpress() {
        double total = service.calcularTotal(150, false, true);
        assertEquals(170.0, total, 0.01); // 150 + 20 = 170
    }

    @Test
    @DisplayName("Calcular total con subtotal cero")
    void testSubtotalCero() {
        double total = service.calcularTotal(0, false, false);
        assertEquals(10.0, total, 0.01); // 0 + 10 = 10
    }

    @Test
    @DisplayName("Calcular costo de envío normal")
    void testEnvioNormal() {
        double envio = service.calcularEnvio(false);
        assertEquals(10.0, envio, 0.01);
    }

    @Test
    @DisplayName("Calcular costo de envío express")
    void testEnvioExpress() {
        double envio = service.calcularEnvio(true);
        assertEquals(20.0, envio, 0.01);
    }
}
