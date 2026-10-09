public class PasarelaTarjeta implements PasarelaPago {

    private static final double LIMITE = 500000;

    @Override
    public String nombre() {
        return "Tarjeta";
    }

    @Override
    public boolean cobrar(double monto) {
        return monto <= LIMITE;
    }
}
