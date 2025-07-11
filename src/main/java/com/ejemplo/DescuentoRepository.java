package com.ejemplo;

/**
 * Repositorio para obtener descuentos aplicables
 */
public class DescuentoRepository {

    /**
     * Obtiene el porcentaje de descuento para un código dado
     * @param codigo Código del descuento
     * @return Porcentaje de descuento (0.0 a 1.0)
     */
    public double obtenerPorcentaje(String codigo) {
        if (codigo == null) {
            return 0.0;
        }
        
        switch (codigo.toUpperCase()) {
            case "PROMO10":
                return 0.10;
            case "PROMO20":
                return 0.20;
            case "VIP":
                return 0.15;
            default:
                return 0.0;
        }
    }
}
