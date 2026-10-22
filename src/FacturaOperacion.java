import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FacturaOperacion {
    private static final double TASA_IMPUESTO = 0.19;
    private final List<Factura> factura = new ArrayList<>();

    public void registrarFactura(Factura factura) {
        this.factura.add(Objects.requireNonNull(factura, "La factura no puede ser null"));
    }

    public void buscarFactura(int consecutivo, Cliente cliente) {
        Factura encontrada = buscarPorConsecutivo(consecutivo);
        if (encontrada == null || !mismoCliente(encontrada.getCliente(), cliente)) {
            throw new IllegalArgumentException(
                    "No existe una factura con ese consecutivo asociada al cliente indicado");
        }
    }

    public void agregarProducto(int consecutivo, Producto producto) {
        Factura facturaEncontrada = buscarFactura(consecutivo);
        Objects.requireNonNull(producto, "El producto no puede ser null");

        List<Producto> productos = facturaEncontrada.getProductos();
        productos.add(producto);
        facturaEncontrada.setProductos(productos);
    }

    public Factura buscarFactura(int consecutivo) {
        Factura encontrada = buscarPorConsecutivo(consecutivo);
        if (encontrada == null) {
            throw new IllegalArgumentException("No existe una factura con ese consecutivo");
        }
        return encontrada;
    }

    public List<Factura> listaFactura() {
        return new ArrayList<>(factura);
    }

    public double calcularImpuesto(int consecutivo) {
        Factura facturaEncontrada = buscarFactura(consecutivo);
        double impuesto = redondear(calculaSubtotal(consecutivo) * TASA_IMPUESTO);
        facturaEncontrada.setImpuesto(impuesto);
        return impuesto;
    }

    public double calculaSubtotal(int consecutivo) {
        Factura facturaEncontrada = buscarFactura(consecutivo);
        double subtotal = 0;
        for (Producto producto : facturaEncontrada.getProductos()) {
            subtotal += producto.getPrecio();
        }
        subtotal = redondear(subtotal);
        facturaEncontrada.setSubtotal(subtotal);
        return subtotal;
    }

    public double calculaTotal(int consecutivo) {
        Factura facturaEncontrada = buscarFactura(consecutivo);
        double total = redondear(calculaSubtotal(consecutivo) + calcularImpuesto(consecutivo));
        facturaEncontrada.setTotal(total);
        return total;
    }

    private Factura buscarPorConsecutivo(int consecutivo) {
        for (Factura facturaActual : factura) {
            if (facturaActual.getConsecutivo() == consecutivo) {
                return facturaActual;
            }
        }
        return null;
    }

    private boolean mismoCliente(Cliente primero, Cliente segundo) {
        return primero != null
                && segundo != null
                && primero.getIdentificacion() == segundo.getIdentificacion();
    }

    private double redondear(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}
