import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Factura {
    private int consecutivo;
    private Date fecha;
    private double impuesto;
    private double subtotal;
    private double total;
    private List<Producto> productos = new ArrayList<>();
    private Cliente cliente;

    public int getConsecutivo() {
        return consecutivo;
    }

    public Date getFecha() {
        return fecha;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getTotal() {
        return total;
    }

    public List<Producto> getProductos() {
        return new ArrayList<>(productos);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = new ArrayList<>(productos);
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
