public class ItemPedido {

    private final String nombre;
    private final double precioUnitario;
    private final int cantidad;

    public ItemPedido(String nombre, double precioUnitario, int cantidad) {
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getSubtotal() {
        return precioUnitario * cantidad;
    }
}
