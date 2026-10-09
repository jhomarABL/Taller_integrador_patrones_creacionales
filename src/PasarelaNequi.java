public class PasarelaNequi implements PasarelaPago {

    private static final double LIMITE = 300000;

    @Override
    public String nombre() {
        return "Nequi";
    }

    @Override
    public boolean cobrar(double monto) {
        return monto <= LIMITE;
    }
}
