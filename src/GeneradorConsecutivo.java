public final class GeneradorConsecutivo {

    private int contador;

    private GeneradorConsecutivo() {
        contador = 0;
        System.out.println("[Consecutivo] Instancia creada.");
    }

    private static class ContenedorInstancia {
        private static final GeneradorConsecutivo INSTANCIA = new GeneradorConsecutivo();
    }

    public static GeneradorConsecutivo obtenerInstancia() {
        return ContenedorInstancia.INSTANCIA;
    }

    public synchronized String siguiente() {
        contador++;
        return String.format("PED-%04d", contador);
    }
}
