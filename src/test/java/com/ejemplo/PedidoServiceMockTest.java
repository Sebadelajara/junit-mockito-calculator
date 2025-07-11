package com.ejemplo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias usando mocks para simular dependencias externas
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas del servicio de pedidos con mocks")
class PedidoServiceMockTest {

    @Mock
    private DescuentoRepository mockRepository;

    @Test
    @DisplayName("Calcular total con mock de descuento PROMO10")
    void testConMockDeDescuento() {
        // Configurar el mock
        when(mockRepository.obtenerPorcentaje("PROMO10")).thenReturn(0.10);

        // Crear el servicio con el mock
        PedidoService service = new PedidoService(mockRepository);

        // Ejecutar la prueba
        double total = service.calcularTotal(100, "PROMO10", true);

        // Verificar el resultado
        assertEquals(110.0, total, 0.01); // 100 - 10% + 20 = 90 + 20 = 110

        // Verificar que se llamó al mock
        verify(mockRepository).obtenerPorcentaje("PROMO10");
    }

    @Test
    @DisplayName("Calcular total con descuento inexistente")
    void testConCodigoInexistente() {
        // Configurar el mock para código inexistente
        when(mockRepository.obtenerPorcentaje("CODIGO_INEXISTENTE")).thenReturn(0.0);

        PedidoService service = new PedidoService(mockRepository);
        double total = service.calcularTotal(100, "CODIGO_INEXISTENTE", false);

        assertEquals(110.0, total, 0.01); // 100 + 10 = 110 (sin descuento)
        verify(mockRepository).obtenerPorcentaje("CODIGO_INEXISTENTE");
    }

    @Test
    @DisplayName("Calcular total con descuento VIP")
    void testConDescuentoVIP() {
        when(mockRepository.obtenerPorcentaje("VIP")).thenReturn(0.15);

        PedidoService service = new PedidoService(mockRepository);
        double total = service.calcularTotal(200, "VIP", true);

        assertEquals(190.0, total, 0.01); // 200 - 15% + 20 = 170 + 20 = 190
        verify(mockRepository).obtenerPorcentaje("VIP");
    }

    @Test
    @DisplayName("Calcular descuento con mock")
    void testCalcularDescuentoConMock() {
        when(mockRepository.obtenerPorcentaje("PROMO20")).thenReturn(0.20);

        PedidoService service = new PedidoService(mockRepository);
        double descuento = service.calcularDescuento(100, "PROMO20");

        assertEquals(20.0, descuento, 0.01); // 100 * 0.20 = 20
        verify(mockRepository).obtenerPorcentaje("PROMO20");
    }

    @Test
    @DisplayName("Verificar que se lanza excepción con subtotal negativo")
    void testSubtotalNegativo() {
        PedidoService service = new PedidoService(mockRepository);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> 
            service.calcularTotal(-10, "PROMO10", false)
        );

        assertNotNull(exception);
        
        // Verificar que no se llamó al repositorio
        verifyNoInteractions(mockRepository);
    }

    @Test
    @DisplayName("Calcular total con código null")
    void testConCodigoNull() {
        when(mockRepository.obtenerPorcentaje(null)).thenReturn(0.0);

        PedidoService service = new PedidoService(mockRepository);
        double total = service.calcularTotal(100, null, false);

        assertEquals(110.0, total, 0.01); // 100 + 10 = 110
        verify(mockRepository).obtenerPorcentaje(null);
    }
}
