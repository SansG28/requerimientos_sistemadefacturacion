import java.util.ArrayList;
import java.util.List;

public class ClienteOperacion {
    private final List<Cliente> clientes;

    public ClienteOperacion() {
        clientes = new ArrayList<>();
    }

    public void registrarCliente(String nombre, int identificacion, String correo, String direccion) {
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setIdentificacion(identificacion);
        cliente.setCorreo(correo);
        cliente.setDireccion(direccion);
        clientes.add(cliente);
    }

    public Cliente buscarCliente(int identificacion) {
        for (Cliente cliente : clientes) {
            if (cliente.getIdentificacion() == identificacion) {
                return cliente;
            }
        }
        return null;
    }

    public List<Cliente> listaCliente() {
        return new ArrayList<>(clientes);
    }
}
