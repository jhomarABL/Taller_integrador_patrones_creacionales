public class ProcesadorNequi extends ProcesadorPago {

    @Override
    protected PasarelaPago crearPasarela() {
        return new PasarelaNequi();
    }
}
