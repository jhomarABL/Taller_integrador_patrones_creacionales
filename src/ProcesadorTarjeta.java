public class ProcesadorTarjeta extends ProcesadorPago {

    @Override
    protected PasarelaPago crearPasarela() {
        return new PasarelaTarjeta();
    }
}
