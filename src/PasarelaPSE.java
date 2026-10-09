public class PasarelaPSE implements PasarelaPago {

    @Override
    public String nombre() {
        return "PSE";
    }

    @Override
    public boolean cobrar(double monto) {
        return true;
    }
}
