package com.ejemplo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Pruebas unitarias usando mocks para simular dependencias externas
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas del servicio de pedidos con mocks")
class PedidoServiceMockTest {

    @Mock
    private DescuentoRepository mockRepository;
    
    private PedidoService service;

    @BeforeEach
    void setUp() {
        service = new PedidoService(mockRepository);
    }

    @Test
    @DisplayName("Calcular total con mock de descuento PROMO10")
    void testConMockDeDescuento() {
        // Configurar el mock
        when(mockRepository.obtenerPorcentaje("PROMO10")).thenReturn(0.10);

        // Ejecutar la prueba (service ya está configurado en @BeforeEach)
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

        double total = service.calcularTotal(100, "CODIGO_INEXISTENTE", false);

        assertEquals(110.0, total, 0.01); // 100 + 10 = 110 (sin descuento)
        verify(mockRepository).obtenerPorcentaje("CODIGO_INEXISTENTE");
    }

    @Test
    @DisplayName("Calcular total con descuento VIP")
    void testConDescuentoVIP() {
        when(mockRepository.obtenerPorcentaje("VIP")).thenReturn(0.15);

        double total = service.calcularTotal(200, "VIP", true);

        assertEquals(190.0, total, 0.01); // 200 - 15% + 20 = 170 + 20 = 190
        verify(mockRepository).obtenerPorcentaje("VIP");
    }

    @Test
    @DisplayName("Calcular descuento con mock")
    void testCalcularDescuentoConMock() {
        when(mockRepository.obtenerPorcentaje("PROMO20")).thenReturn(0.20);

        double descuento = service.calcularDescuento(100, "PROMO20");

        assertEquals(20.0, descuento, 0.01); // 100 * 0.20 = 20
        verify(mockRepository).obtenerPorcentaje("PROMO20");
    }

    @Test
    @DisplayName("Verificar que se lanza excepción con subtotal negativo")
    void testSubtotalNegativo() {
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

        double total = service.calcularTotal(100, null, false);

        assertEquals(110.0, total, 0.01); // 100 + 10 = 110
        verify(mockRepository).obtenerPorcentaje(null);
    }
}
