
public class App {

    public static void main(String[] args) {

        System.out.println("=== 1. Singleton: consecutivo unico ===");
        GeneradorConsecutivo g1 = GeneradorConsecutivo.obtenerInstancia();
        GeneradorConsecutivo g2 = GeneradorConsecutivo.obtenerInstancia();
        System.out.println("g1 y g2 son el mismo objeto: " + (g1 == g2));

        System.out.println();
        System.out.println("=== 2. Builder: construccion de pedidos ===");

        Pedido p1 = new Pedido.Builder()
                .conCliente("Ana Torres")
                .conTipoEntrega(TipoEntrega.DOMICILIO)
                .conDireccion("Cra 7 # 45-10, Bogota")
                .agregarItem(new ItemPedido("Bandeja paisa", 28000, 1))
                .agregarItem(new ItemPedido("Limonada", 6000, 2))
                .conCupon(10)
                .conPropina(3000)
                .construir();
        p1.mostrarResumen();

        Pedido p2 = new Pedido.Builder()
                .conCliente("Luis Perez")
                .conTipoEntrega(TipoEntrega.RECOGER)
                .agregarItem(new ItemPedido("Empanada", 3500, 4))
                .conNotas("Sin aji")
                .construir();
        p2.mostrarResumen();

        // Pedidos invalidos: deben ser rechazados SIN consumir un numero de pedido.
        try {
            new Pedido.Builder()
                    .conCliente("Marta Gomez")
                    .construir();
        } catch (IllegalStateException e) {
            System.out.println("Pedido rechazado: " + e.getMessage());
        }

        try {
            new Pedido.Builder()
                    .conCliente("Pedro Ruiz")
                    .conTipoEntrega(TipoEntrega.DOMICILIO)
                    .agregarItem(new ItemPedido("Jugo", 5000, 1))
                    .construir();
        } catch (IllegalStateException e) {
            System.out.println("Pedido rechazado: " + e.getMessage());
        }

        Pedido p3 = new Pedido.Builder()
                .conCliente("Empresa Andina")
                .agregarItem(new ItemPedido("Combo familiar", 30000, 20))
                .construir();
        p3.mostrarResumen();

        System.out.println();
        System.out.println("=== 3. Factory Method: medios de pago ===");

        ProcesadorPago tarjeta = new ProcesadorTarjeta();
        ProcesadorPago pse = new ProcesadorPSE();
        ProcesadorPago efectivo = new ProcesadorEfectivo();

        tarjeta.procesar(p1);
        efectivo.procesar(p2);
        tarjeta.procesar(p3);   // supera el limite de la tarjeta
        pse.procesar(p3);       // PSE si lo aprueba

        // INICIO PUNTO 4 (descomenta cuando hayas creado ProcesadorNequi)
        // System.out.println();
        // System.out.println("=== 4. Extension sin modificar codigo existente ===");
        // ProcesadorPago nequi = new ProcesadorNequi();
        // nequi.procesar(p2);
        // nequi.procesar(p3);
        // FIN PUNTO 4
        System.out.println("=== Reto extra: Prototype (repetir pedido) ===");

        

        Pedido repetido = p1.clonar();
        repetido.agregarItem(new ItemPedido("Postre", 8000, 1));
        

        System.out.println("-- Original --");
        p1.mostrarResumen();
        System.out.println("-- Repetido (copia con postre) --");
        repetido.mostrarResumen();
    }

}
