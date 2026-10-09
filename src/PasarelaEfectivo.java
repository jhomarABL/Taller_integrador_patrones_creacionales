public class PasarelaEfectivo implements PasarelaPago {

    @Override
    public String nombre() {
        return "Efectivo";
    }

    @Override
    public boolean cobrar(double monto) {
        return true;
    }
}
