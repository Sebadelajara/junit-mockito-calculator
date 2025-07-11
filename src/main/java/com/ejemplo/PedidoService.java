package com.ejemplo;

/**
 * Servicio para calcular el total de pedidos aplicando descuentos y cargos de envío
 */
public class PedidoService {

    private DescuentoRepository repository;

    /**
     * Constructor que recibe el repositorio de descuentos
     * @param repository Repositorio para obtener descuentos
     */
    public PedidoService(DescuentoRepository repository) {
        this.repository = repository;
    }

    /**
     * Constructor sin parámetros para compatibilidad con versiones anteriores
     */
    public PedidoService() {
        this.repository = new DescuentoRepository();
    }

    /**
     * Calcula el total del pedido (versión original - mantener para compatibilidad)
     * @param subtotal Subtotal del pedido
     * @param aplicaDescuento Si aplica descuento del 10%
     * @param envioExpress Si usa envío express
     * @return Total del pedido
     */
    public double calcularTotal(double subtotal, boolean aplicaDescuento, boolean envioExpress) {
        double descuento = aplicaDescuento ? 0.1 : 0;
        double envio = envioExpress ? 20.0 : 10.0;
        return (subtotal * (1 - descuento)) + envio;
    }

    /**
     * Calcula el total del pedido con código de descuento
     * @param subtotal Subtotal del pedido
     * @param codigoDescuento Código de descuento a aplicar
     * @param envioExpress Si usa envío express
     * @return Total del pedido
     */
    public double calcularTotal(double subtotal, String codigoDescuento, boolean envioExpress) {
        if (subtotal < 0) {
            throw new IllegalArgumentException("El subtotal no puede ser negativo");
        }
        
        double descuento = repository.obtenerPorcentaje(codigoDescuento);
        double envio = envioExpress ? 20.0 : 10.0;
        return (subtotal * (1 - descuento)) + envio;
    }

    /**
     * Calcula solo el descuento aplicable
     * @param subtotal Subtotal del pedido
     * @param codigoDescuento Código de descuento
     * @return Monto del descuento
     */
    public double calcularDescuento(double subtotal, String codigoDescuento) {
        double porcentaje = repository.obtenerPorcentaje(codigoDescuento);
        return subtotal * porcentaje;
    }

    /**
     * Calcula el costo de envío
     * @param envioExpress Si es envío express
     * @return Costo del envío
     */
    public double calcularEnvio(boolean envioExpress) {
        return envioExpress ? 20.0 : 10.0;
    }
}
