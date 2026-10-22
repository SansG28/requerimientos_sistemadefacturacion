import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductoOperacion {
    private final List<Producto> productos = new ArrayList<>();

    public void registrarProducto(Producto producto) {
        productos.add(Objects.requireNonNull(producto, "El producto no puede ser null"));
    }

    public Producto buscarProducto(String codigo) {
        for (Producto producto : productos) {
            if (Objects.equals(producto.getCodigo(), codigo)) {
                return producto;
            }
        }
        return null;
    }

    public List<Producto> listarProducto() {
        return new ArrayList<>(productos);
    }
}
