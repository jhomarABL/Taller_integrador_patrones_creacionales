public class AppBonus {

    public static void main(String[] args) {

        System.out.println("=== Reto extra: Prototype (repetir pedido) ===");

        Pedido original = new Pedido.Builder()
                .conCliente("Ana Torres")
                .conTipoEntrega(TipoEntrega.DOMICILIO)
                .conDireccion("Cra 7 # 45-10, Bogota")
                .agregarItem(new ItemPedido("Bandeja paisa", 28000, 1))
                .conCupon(10)
                .conPropina(3000)
                .construir();

        Pedido repetido = original.clonar();
        repetido.agregarItem(new ItemPedido("Postre", 8000, 1));

        System.out.println("-- Original --");
        original.mostrarResumen();
        System.out.println("-- Repetido (copia con un item extra) --");
        repetido.mostrarResumen();
    }
}
