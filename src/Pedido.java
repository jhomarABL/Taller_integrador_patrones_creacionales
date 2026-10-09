import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Pedido implements PrototipoPedido{

    private final String id;
    private final String cliente;
    private final TipoEntrega tipoEntrega;
    private final String direccion;
    private final List<ItemPedido> items;
    private final String notas;
    private final int cupon;
    private final double propina;

    // Constructor privado: solo accesible por su propio Builder interno

    private Pedido(Builder builder, String id) {
        this.id = id;
        this.cliente = builder.cliente;
        this.tipoEntrega = builder.tipoEntrega;
        this.direccion = builder.direccion;
        this.items = new ArrayList<>(builder.items);
        this.notas = builder.notas;
        this.cupon = builder.cupon;
        this.propina = builder.propina;
    }

    public String getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public TipoEntrega getTipoEntrega() {
        return tipoEntrega;
    }

    public String getDireccion() {
        return direccion;
    }

    public List<ItemPedido> getItems() {
        return Collections.unmodifiableList(items);
    }

    public String getNotas() {
        return notas;
    }

    public int getCupon() {
        return cupon;
    }

    public double getPropina() {
        return propina;
    }

    public double calcularSubtotal() {
        double subtotal = 0;
        for (ItemPedido item : items) {
            subtotal += item.getSubtotal();
        }
        return subtotal;
    }

    public double calcularTotal() {
        return calcularSubtotal() * (100 - cupon) / 100.0 + propina;
    }

    public void mostrarResumen() {
        String entrega = tipoEntrega.toString();
        if (tipoEntrega == TipoEntrega.DOMICILIO) {
            entrega += " (" + direccion + ")";
        }

        System.out.println("Pedido " + id + " | Cliente: " + cliente + " | Entrega: " + entrega);
        for (ItemPedido item : items) {
            System.out.println("  - " + item.getCantidad() + " x " + item.getNombre()
                    + " ($" + formatearMonto(item.getPrecioUnitario()) + ")");
        }
        if (!notas.isEmpty()) {
            System.out.println("  Notas: " + notas);
        }
        System.out.println("  Cupon: " + cupon + "% | Propina: $" + formatearMonto(propina)
                + " | Total: $" + formatearMonto(calcularTotal()));
    }

    private static String formatearMonto(double monto) {
        return String.format(Locale.US, "%.0f", monto);
    }

    
    @Override 
    public Pedido clonar() {
        Builder copia = new Builder()
                .conCliente(cliente)
                .conTipoEntrega(tipoEntrega)
                .conDireccion(direccion)
                .conNotas(notas)
                .conPropina(propina);

        for (ItemPedido item : items) {
            copia.agregarItem(new ItemPedido(
                    item.getNombre(),
                    item.getPrecioUnitario(),
                    item.getCantidad()));
        }

        return copia.construir();
    }

    public void agregarItem(ItemPedido item) {
        if (item != null) {
            items.add(item);
        }
    }

    public static class Builder {

        private String cliente;
        private TipoEntrega tipoEntrega = TipoEntrega.RECOGER;
        private String direccion = "";
        private final List<ItemPedido> items = new ArrayList<>();
        private String notas = "";
        private int cupon = 0;
        private double propina = 0;

        public Builder conCliente(String cliente) {
            this.cliente = cliente;
            return this;
        }

        public Builder conTipoEntrega(TipoEntrega tipoEntrega) {
            this.tipoEntrega = tipoEntrega == null ? TipoEntrega.RECOGER : tipoEntrega;
            return this;
        }

        public Builder conDireccion(String direccion) {
            this.direccion = direccion == null ? "" : direccion;
            return this;
        }

        public Builder agregarItem(ItemPedido item) {
            if (item != null) {
                items.add(item);
            }
            return this;
        }

        public Builder conNotas(String notas) {
            this.notas = notas == null ? "" : notas;
            return this;
        }

        public Builder conCupon(int cupon) {
            this.cupon = cupon;
            return this;
        }

        public Builder conPropina(double propina) {
            this.propina = propina;
            return this;
        }

        public Pedido construir() {
            if (cliente == null || cliente.trim().isEmpty()) {
                throw new IllegalStateException("El cliente es obligatorio");
            }
            if (items.isEmpty()) {
                throw new IllegalStateException("El pedido debe tener al menos un item");
            }
            if (tipoEntrega == TipoEntrega.DOMICILIO && direccion.trim().isEmpty()) {
                throw new IllegalStateException("El domicilio requiere direccion");
            }
            if (cupon < 0 || cupon > 100) {
                throw new IllegalArgumentException("El cupon debe estar entre 0 y 100");
            }

            String id = GeneradorConsecutivo.obtenerInstancia().siguiente();
            return new Pedido(this, id);
        }
    }
}
