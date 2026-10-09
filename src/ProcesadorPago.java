import java.util.Locale;

public abstract class ProcesadorPago {

    public void procesar(Pedido pedido) {
        PasarelaPago pasarela = crearPasarela();
        double total = pedido.calcularTotal();

        System.out.println("Procesando " + pedido.getId()
                + " con " + pasarela.nombre()
                + " por $" + String.format(Locale.US, "%.0f", total));

        boolean aprobado = pasarela.cobrar(total);
        System.out.println(aprobado ? "  -> Pago APROBADO" : "  -> Pago RECHAZADO");
    }

    protected abstract PasarelaPago crearPasarela();
}
