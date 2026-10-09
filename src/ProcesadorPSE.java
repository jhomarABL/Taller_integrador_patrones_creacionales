public class ProcesadorPSE extends ProcesadorPago {

    @Override
    protected PasarelaPago crearPasarela() {
        return new PasarelaPSE();
    }
}
