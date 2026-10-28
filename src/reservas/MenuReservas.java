package reservas;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class MenuReservas {
    private final ViajeroOperacion viajeroOperacion;
    private final VueloOperacion vueloOperacion;
    private final HotelOperacion hotelOperacion;
    private final ReservaOperacion reservaOperacion;
    private final Scanner scanner;
    private final NumberFormat formatoMoneda;

    public MenuReservas() {
        this.viajeroOperacion = new ViajeroOperacion();
        this.vueloOperacion = new VueloOperacion();
        this.hotelOperacion = new HotelOperacion();
        this.reservaOperacion = new ReservaOperacion();
        this.scanner = new Scanner(System.in);
        this.formatoMoneda = NumberFormat.getCurrencyInstance(Locale.of("es", "CO"));
    }

    public void mostrarMenu() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n===== SISTEMA DE RESERVAS DE VUELOS Y HOTELES =====");
            System.out.println("1. Registrar viajero");
            System.out.println("2. Buscar viajero");
            System.out.println("3. Listar viajeros");
            System.out.println("4. Registrar vuelo");
            System.out.println("5. Buscar vuelo");
            System.out.println("6. Listar vuelos");
            System.out.println("7. Registrar hotel");
            System.out.println("8. Buscar hotel");
            System.out.println("9. Listar hoteles");
            System.out.println("10. Crear reserva de vuelo y hotel");
            System.out.println("11. Buscar reserva");
            System.out.println("12. Listar reservas");
            System.out.println("13. Salir");

            Integer opcion = leerEntero("Seleccione una opción: ");
            if (opcion == null) {
                continuar = false;
                continue;
            }

            switch (opcion) {
                case 1:
                    registrarViajero();
                    break;
                case 2:
                    buscarViajero();
                    break;
                case 3:
                    listarViajeros();
                    break;
                case 4:
                    registrarVuelo();
                    break;
                case 5:
                    buscarVuelo();
                    break;
                case 6:
                    listarVuelos();
                    break;
                case 7:
                    registrarHotel();
                    break;
                case 8:
                    buscarHotel();
                    break;
                case 9:
                    listarHoteles();
                    break;
                case 10:
                    crearReserva();
                    break;
                case 11:
                    buscarReserva();
                    break;
                case 12:
                    listarReservas();
                    break;
                case 13:
                case 0:
                    continuar = false;
                    System.out.println("Gracias por usar el Sistema de Reservas. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }

    // ==================== VIAJERO ====================

    public void registrarViajero() {
        System.out.println("\n--- Registrar Viajero ---");
        String documento = leerLinea("Documento de identidad: ");
        if (documento == null) return;
        String nombre = leerLinea("Nombre completo: ");
        if (nombre == null) return;
        String correo = leerLinea("Correo electrónico: ");
        if (correo == null) return;
        String telefono = leerLinea("Teléfono: ");
        if (telefono == null) return;

        try {
            Viajero viajero = new Viajero(documento, nombre, correo, telefono);
            viajeroOperacion.registrarViajero(viajero);
            System.out.println("Viajero registrado exitosamente.");
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("Error al registrar viajero: " + e.getMessage());
        }
    }

    public void buscarViajero() {
        System.out.println("\n--- Buscar Viajero ---");
        String documento = leerLinea("Ingrese el documento del viajero: ");
        if (documento == null) return;

        Viajero viajero = viajeroOperacion.buscarViajero(documento);
        if (viajero == null) {
            System.out.println("No se encontró ningún viajero con el documento ingresado.");
            return;
        }
        imprimirViajero(viajero);
    }

    public void listarViajeros() {
        System.out.println("\n--- Listado de Viajeros ---");
        List<Viajero> viajeros = viajeroOperacion.listarViajeros();
        if (viajeros.isEmpty()) {
            System.out.println("No hay viajeros registrados en el sistema.");
            return;
        }
        for (Viajero viajero : viajeros) {
            imprimirViajero(viajero);
        }
    }

    // ==================== VUELO ====================

    public void registrarVuelo() {
        System.out.println("\n--- Registrar Vuelo ---");
        String codigo = leerLinea("Código del vuelo: ");
        if (codigo == null) return;
        String aerolinea = leerLinea("Aerolínea: ");
        if (aerolinea == null) return;
        String origen = leerLinea("Ciudad de origen: ");
        if (origen == null) return;
        String destino = leerLinea("Ciudad de destino: ");
        if (destino == null) return;
        String fechaSalida = leerLinea("Fecha y hora de salida (ej. 2026-11-01 08:30): ");
        if (fechaSalida == null) return;
        String fechaLlegada = leerLinea("Fecha y hora de llegada (ej. 2026-11-01 10:00): ");
        if (fechaLlegada == null) return;
        Double precio = leerDouble("Precio del vuelo (en COP): ");
        if (precio == null) return;

        try {
            Vuelo vuelo = new Vuelo(codigo, aerolinea, origen, destino, fechaSalida, fechaLlegada, precio);
            vueloOperacion.registrarVuelo(vuelo);
            System.out.println("Vuelo registrado exitosamente.");
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("Error al registrar vuelo: " + e.getMessage());
        }
    }

    public void buscarVuelo() {
        System.out.println("\n--- Buscar Vuelo ---");
        String codigo = leerLinea("Ingrese el código del vuelo: ");
        if (codigo == null) return;

        Vuelo vuelo = vueloOperacion.buscarVuelo(codigo);
        if (vuelo == null) {
            System.out.println("No se encontró ningún vuelo con el código ingresado.");
            return;
        }
        imprimirVuelo(vuelo);
    }

    public void listarVuelos() {
        System.out.println("\n--- Catálogo de Vuelos ---");
        List<Vuelo> vuelos = vueloOperacion.listarVuelos();
        if (vuelos.isEmpty()) {
            System.out.println("No hay vuelos disponibles en el catálogo.");
            return;
        }
        for (Vuelo vuelo : vuelos) {
            imprimirVuelo(vuelo);
        }
    }

    // ==================== HOTEL ====================

    public void registrarHotel() {
        System.out.println("\n--- Registrar Hotel ---");
        String codigo = leerLinea("Código del hotel: ");
        if (codigo == null) return;
        String nombre = leerLinea("Nombre del hotel: ");
        if (nombre == null) return;
        String ciudad = leerLinea("Ciudad: ");
        if (ciudad == null) return;
        Double precioPorNoche = leerDouble("Precio por noche (en COP): ");
        if (precioPorNoche == null) return;

        try {
            Hotel hotel = new Hotel(codigo, nombre, ciudad, precioPorNoche);
            hotelOperacion.registrarHotel(hotel);
            System.out.println("Hotel registrado exitosamente.");
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("Error al registrar hotel: " + e.getMessage());
        }
    }

    public void buscarHotel() {
        System.out.println("\n--- Buscar Hotel ---");
        String criterio = leerLinea("Ingrese el nombre del hotel a buscar: ");
        if (criterio == null) return;

        Hotel hotel = hotelOperacion.buscarHotelPorNombre(criterio);
        if (hotel == null) {
            // Intento secundario por código para mayor flexibilidad
            hotel = hotelOperacion.buscarHotelPorCodigo(criterio);
        }

        if (hotel == null) {
            System.out.println("No se encontró ningún hotel con el nombre ingresado.");
            return;
        }
        imprimirHotel(hotel);
    }

    public void listarHoteles() {
        System.out.println("\n--- Catálogo de Hoteles ---");
        List<Hotel> hoteles = hotelOperacion.listarHoteles();
        if (hoteles.isEmpty()) {
            System.out.println("El catálogo de hoteles está vacío.");
            return;
        }
        for (Hotel hotel : hoteles) {
            imprimirHotel(hotel);
        }
    }

    // ==================== RESERVA ====================

    public void crearReserva() {
        System.out.println("\n--- Crear Reserva de Vuelo y Hotel ---");
        String codigoReserva = leerLinea("Código único para la reserva: ");
        if (codigoReserva == null) return;

        String documentoViajero = leerLinea("Documento del viajero: ");
        if (documentoViajero == null) return;
        Viajero viajero = viajeroOperacion.buscarViajero(documentoViajero);
        if (viajero == null) {
            System.out.println("Error: No existe un viajero registrado con ese documento. Regístrelo primero.");
            return;
        }

        String codigoVuelo = leerLinea("Código del vuelo: ");
        if (codigoVuelo == null) return;
        Vuelo vuelo = vueloOperacion.buscarVuelo(codigoVuelo);
        if (vuelo == null) {
            System.out.println("Error: No existe un vuelo registrado con ese código.");
            return;
        }

        String criterioHotel = leerLinea("Código o nombre del hotel: ");
        if (criterioHotel == null) return;
        Hotel hotel = hotelOperacion.buscarHotelPorCodigo(criterioHotel);
        if (hotel == null) {
            hotel = hotelOperacion.buscarHotelPorNombre(criterioHotel);
        }
        if (hotel == null) {
            System.out.println("Error: No existe un hotel registrado con ese código o nombre.");
            return;
        }

        Integer noches = leerEntero("Cantidad de noches de estadía: ");
        if (noches == null) return;
        if (noches <= 0) {
            System.out.println("Error: La cantidad de noches debe ser mayor a 0.");
            return;
        }

        try {
            Reserva reserva = new Reserva();
            reserva.setCodigo(codigoReserva);
            reserva.setViajero(viajero);
            reserva.setVuelo(vuelo);
            reserva.setHotel(hotel);
            reserva.setNoches(noches);

            reservaOperacion.registrarReserva(reserva);

            System.out.println("\n¡Reserva confirmada exitosamente!");
            System.out.println("Detalles económicos del itinerario:");
            System.out.println("  - Costo del vuelo:       " + formatearMoneda(reserva.getCostoVuelo()));
            System.out.printf("  - Costo de hotel (%d n):  %s%n", noches, formatearMoneda(reserva.getCostoHotel()));
            System.out.println("  --------------------------------------------------");
            System.out.println("  TOTAL A PAGAR:          " + formatearMoneda(reserva.getTotal()));
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("Error al crear reserva: " + e.getMessage());
        }
    }

    public void buscarReserva() {
        System.out.println("\n--- Buscar Reserva ---");
        String codigo = leerLinea("Código de la reserva: ");
        if (codigo == null) return;

        try {
            Reserva reserva = reservaOperacion.buscarReserva(codigo);
            imprimirReserva(reserva);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listarReservas() {
        System.out.println("\n--- Listado de Reservas Confirmadas ---");
        List<Reserva> reservas = reservaOperacion.listarReservas();
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas registradas en el sistema.");
            return;
        }
        for (Reserva reserva : reservas) {
            imprimirReserva(reserva);
        }
    }

    // ==================== LECTURA DE DATOS ====================

    private String leerLinea(String mensaje) {
        System.out.print(mensaje);
        return scanner.hasNextLine() ? scanner.nextLine().trim() : null;
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
                System.out.println("Ingrese un valor numérico válido.");
            }
        }
    }

    private String formatearMoneda(double valor) {
        return formatoMoneda.format(valor) + " COP";
    }

    // ==================== IMPRESIÓN FORMATEADA ====================

    private void imprimirViajero(Viajero viajero) {
        System.out.printf("Viajero: %s | Documento: %s | Correo: %s | Teléfono: %s%n",
                viajero.getNombre(), viajero.getDocumento(), viajero.getCorreo(), viajero.getTelefono());
    }

    private void imprimirVuelo(Vuelo vuelo) {
        System.out.printf("Vuelo: %s | Aerolínea: %s | Origen: %s -> Destino: %s | Salida: %s | Llegada: %s | Precio: %s%n",
                vuelo.getCodigo(), vuelo.getAerolinea(), vuelo.getOrigen(), vuelo.getDestino(),
                vuelo.getFechaSalida(), vuelo.getFechaLlegada(), formatearMoneda(vuelo.getPrecio()));
    }

    private void imprimirHotel(Hotel hotel) {
        System.out.printf("Hotel: %s | Código: %s | Ciudad: %s | Precio por noche: %s%n",
                hotel.getNombre(), hotel.getCodigo(), hotel.getCiudad(), formatearMoneda(hotel.getPrecioPorNoche()));
    }

    private void imprimirReserva(Reserva reserva) {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("Reserva Código: %s | Fecha: %s%n", reserva.getCodigo(), reserva.getFechaCreacion());
        System.out.printf("  Viajero: %s (Doc: %s, Tel: %s)%n",
                reserva.getViajero().getNombre(),
                reserva.getViajero().getDocumento(),
                reserva.getViajero().getTelefono());
        System.out.printf("  Vuelo:   %s (%s) | %s -> %s | Salida: %s | Llegada: %s | Costo: %s%n",
                reserva.getVuelo().getCodigo(),
                reserva.getVuelo().getAerolinea(),
                reserva.getVuelo().getOrigen(),
                reserva.getVuelo().getDestino(),
                reserva.getVuelo().getFechaSalida(),
                reserva.getVuelo().getFechaLlegada(),
                formatearMoneda(reserva.getCostoVuelo()));
        System.out.printf("  Hotel:   %s (%s) | %s | %d noche(s) x %s | Costo Hotel: %s%n",
                reserva.getHotel().getNombre(),
                reserva.getHotel().getCodigo(),
                reserva.getHotel().getCiudad(),
                reserva.getNoches(),
                formatearMoneda(reserva.getHotel().getPrecioPorNoche()),
                formatearMoneda(reserva.getCostoHotel()));
        System.out.printf("  TOTAL RESERVA: %s%n", formatearMoneda(reserva.getTotal()));
        System.out.println("--------------------------------------------------------------------------------");
    }
}
