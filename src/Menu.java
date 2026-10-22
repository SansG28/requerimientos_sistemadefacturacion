import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final ClienteOperacion clienteOperacion;
    private final ProductoOperacion productoOperacion;
    private final FacturaOperacion facturaOperacion;
    private final Scanner scanner;

    public Menu() {
        clienteOperacion = new ClienteOperacion();
        productoOperacion = new ProductoOperacion();
        facturaOperacion = new FacturaOperacion();
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n===== SISTEMA DE FACTURACIÓN =====");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Buscar cliente");
            System.out.println("3. Registrar producto");
            System.out.println("4. Buscar producto");
            System.out.println("5. Listar productos");
            System.out.println("6. Registrar factura");
            System.out.println("7. Agregar cliente a factura");
            System.out.println("8. Agregar producto a factura");
            System.out.println("9. Calcular impuesto");
            System.out.println("10. Calcular subtotal");
            System.out.println("11. Calcular total");
            System.out.println("12. Buscar factura");
            System.out.println("13. Listar facturas");
            System.out.println("0. Salir");

            Integer opcion = leerEntero("Seleccione una opción: ");
            if (opcion == null) {
                continuar = false;
                continue;
            }

            switch (opcion) {
                case 1:
                    registrarCliente();
                    break;
                case 2:
                    buscarCliente();
                    break;
                case 3:
                    registrarProducto();
                    break;
                case 4:
                    buscarProducto();
                    break;
                case 5:
                    listarProductos();
                    break;
                case 6:
                    registrarFactura();
                    break;
                case 7:
                    agregarCliente();
                    break;
                case 8:
                    agregarProducto();
                    break;
                case 9:
                    calcularImpuesto();
                    break;
                case 10:
                    calcularSubtotal();
                    break;
                case 11:
                    calcularTotal();
                    break;
                case 12:
                    buscarFactura();
                    break;
                case 13:
                    listarFacturas();
                    break;
                case 0:
                    continuar = false;
                    System.out.println("Hasta luego.");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }

    public void registrarCliente() {
        String nombre = leerLinea("Nombre: ");
        if (nombre == null) return;
        Integer identificacion = leerEntero("Identificación: ");
        if (identificacion == null) return;
        String correo = leerLinea("Correo: ");
        if (correo == null) return;
        String direccion = leerLinea("Dirección: ");
        if (direccion == null) return;

        clienteOperacion.registrarCliente(nombre, identificacion, correo, direccion);
        System.out.println("Cliente registrado.");
    }

    public void buscarCliente() {
        Integer identificacion = leerEntero("Identificación del cliente: ");
        if (identificacion == null) return;

        Cliente cliente = clienteOperacion.buscarCliente(identificacion);
        if (cliente == null) {
            System.out.println("No se encontró el cliente.");
            return;
        }
        imprimirCliente(cliente);
    }

    public void registrarProducto() {
        String codigo = leerLinea("Código: ");
        if (codigo == null) return;
        String nombre = leerLinea("Nombre: ");
        if (nombre == null) return;
        Double precio = leerDouble("Precio: ");
        if (precio == null) return;
        Integer stock = leerEntero("Stock: ");
        if (stock == null) return;

        Producto producto = new Producto();
        producto.setCodigo(codigo);
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setStock(stock);
        productoOperacion.registrarProducto(producto);
        System.out.println("Producto registrado.");
    }

    public void buscarProducto() {
        String codigo = leerLinea("Código del producto: ");
        if (codigo == null) return;

        Producto producto = productoOperacion.buscarProducto(codigo);
        if (producto == null) {
            System.out.println("No se encontró el producto.");
            return;
        }
        imprimirProducto(producto);
    }

    public void listarProductos() {
        List<Producto> productos = productoOperacion.listarProducto();
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }
        for (Producto producto : productos) {
            imprimirProducto(producto);
        }
    }

    public void registrarFactura() {
        Integer consecutivo = leerEntero("Consecutivo de la factura: ");
        if (consecutivo == null) return;

        Factura factura = new Factura();
        factura.setConsecutivo(consecutivo);
        factura.setFecha(new Date());
        facturaOperacion.registrarFactura(factura);
        System.out.println("Factura registrada. Use \"Agregar cliente a factura\" para asociar un cliente.");
    }

    public void agregarCliente() {
        Integer consecutivo = leerEntero("Consecutivo de la factura: ");
        if (consecutivo == null) return;
        Integer identificacion = leerEntero("Identificación del cliente: ");
        if (identificacion == null) return;

        Cliente cliente = clienteOperacion.buscarCliente(identificacion);
        if (cliente == null) {
            System.out.println("No se encontró el cliente.");
            return;
        }
        try {
            facturaOperacion.buscarFactura(consecutivo).setCliente(cliente);
            System.out.println("Cliente asociado a la factura.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void agregarProducto() {
        Integer consecutivo = leerEntero("Consecutivo de la factura: ");
        if (consecutivo == null) return;
        String codigo = leerLinea("Código del producto: ");
        if (codigo == null) return;

        Producto producto = productoOperacion.buscarProducto(codigo);
        if (producto == null) {
            System.out.println("No se encontró el producto.");
            return;
        }
        try {
            facturaOperacion.agregarProducto(consecutivo, producto);
            System.out.println("Producto agregado a la factura.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void calcularImpuesto() {
        Integer consecutivo = leerEntero("Consecutivo de la factura: ");
        if (consecutivo == null) return;
        try {
            System.out.printf("Impuesto: %.2f%n", facturaOperacion.calcularImpuesto(consecutivo));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void calcularSubtotal() {
        Integer consecutivo = leerEntero("Consecutivo de la factura: ");
        if (consecutivo == null) return;
        try {
            System.out.printf("Subtotal: %.2f%n", facturaOperacion.calculaSubtotal(consecutivo));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void calcularTotal() {
        Integer consecutivo = leerEntero("Consecutivo de la factura: ");
        if (consecutivo == null) return;
        try {
            System.out.printf("Total: %.2f%n", facturaOperacion.calculaTotal(consecutivo));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarFactura() {
        Integer consecutivo = leerEntero("Consecutivo de la factura: ");
        if (consecutivo == null) return;
        try {
            imprimirFactura(facturaOperacion.buscarFactura(consecutivo));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listarFacturas() {
        List<Factura> facturas = facturaOperacion.listaFactura();
        if (facturas.isEmpty()) {
            System.out.println("No hay facturas registradas.");
            return;
        }
        for (Factura factura : facturas) {
            imprimirFactura(factura);
        }
    }

    private String leerLinea(String mensaje) {
        System.out.print(mensaje);
        return scanner.hasNextLine() ? scanner.nextLine() : null;
    }

    private Integer leerEntero(String mensaje) {
        while (true) {
            String entrada = leerLinea(mensaje);
            if (entrada == null) return null;
            try {
                return Integer.parseInt(entrada.trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
    }

    private Double leerDouble(String mensaje) {
        while (true) {
            String entrada = leerLinea(mensaje);
            if (entrada == null) return null;
            try {
                return Double.parseDouble(entrada.trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
            }
        }
    }

    private void imprimirCliente(Cliente cliente) {
        System.out.printf("Cliente: %s | Identificación: %d | Correo: %s | Dirección: %s%n",
                cliente.getNombre(), cliente.getIdentificacion(), cliente.getCorreo(), cliente.getDireccion());
    }

    private void imprimirProducto(Producto producto) {
        System.out.printf("Producto: %s | Código: %s | Precio: %.2f | Stock: %d%n",
                producto.getNombre(), producto.getCodigo(), producto.getPrecio(), producto.getStock());
    }

    private void imprimirFactura(Factura factura) {
        System.out.printf("Factura %d | Fecha: %s | Cliente: %s | Productos: %d"
                        + " | Subtotal: %.2f | Impuesto: %.2f | Total: %.2f%n",
                factura.getConsecutivo(), factura.getFecha(),
                factura.getCliente() == null ? "Sin asignar" : factura.getCliente().getNombre(),
                factura.getProductos().size(), factura.getSubtotal(),
                factura.getImpuesto(), factura.getTotal());
    }
}
