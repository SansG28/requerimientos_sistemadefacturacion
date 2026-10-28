package reservas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ReservaOperacion {
    private final List<Reserva> reservas = new ArrayList<>();

    public void registrarReserva(Reserva reserva) {
        Objects.requireNonNull(reserva, "La reserva no puede ser null");
        if (reserva.getCodigo() == null || reserva.getCodigo().trim().isEmpty()) {
            throw new IllegalArgumentException("El código de la reserva es obligatorio");
        }

        if (buscarPorCodigo(reserva.getCodigo().trim()) != null) {
            throw new IllegalArgumentException("Ya existe una reserva con el código: " + reserva.getCodigo());
        }

        Objects.requireNonNull(reserva.getViajero(), "La reserva debe tener un viajero asociado");
        Objects.requireNonNull(reserva.getVuelo(), "La reserva debe tener un vuelo asociado");
        Objects.requireNonNull(reserva.getHotel(), "La reserva debe tener un hotel asociado");

        if (reserva.getNoches() <= 0) {
            throw new IllegalArgumentException("La cantidad de noches debe ser mayor a 0");
        }

        double costoVuelo = calcularCostoVuelo(reserva.getVuelo());
        double costoHotel = calcularCostoHotel(reserva.getHotel(), reserva.getNoches());
        double total = calcularTotal(costoVuelo, costoHotel);

        reserva.setCostoVuelo(costoVuelo);
        reserva.setCostoHotel(costoHotel);
        reserva.setTotal(total);

        if (reserva.getFechaCreacion() == null) {
            reserva.setFechaCreacion(new Date());
        }

        reservas.add(reserva);
    }

    public Reserva buscarReserva(String codigo) {
        Reserva encontrada = buscarPorCodigo(codigo);
        if (encontrada == null) {
            throw new IllegalArgumentException("No existe una reserva con el código indicado");
        }
        return encontrada;
    }

    public Reserva buscarPorCodigo(String codigo) {
        if (codigo == null) {
            return null;
        }
        for (Reserva reserva : reservas) {
            if (Objects.equals(reserva.getCodigo(), codigo.trim())) {
                return reserva;
            }
        }
        return null;
    }

    public double calcularCostoVuelo(Vuelo vuelo) {
        Objects.requireNonNull(vuelo, "El vuelo no puede ser null");
        return redondear(vuelo.getPrecio());
    }

    public double calcularCostoHotel(Hotel hotel, int noches) {
        Objects.requireNonNull(hotel, "El hotel no puede ser null");
        if (noches <= 0) {
            throw new IllegalArgumentException("La cantidad de noches debe ser mayor a 0");
        }
        return redondear(hotel.getPrecioPorNoche() * noches);
    }

    public double calcularTotal(double costoVuelo, double costoHotel) {
        return redondear(costoVuelo + costoHotel);
    }

    public List<Reserva> listarReservas() {
        return new ArrayList<>(reservas);
    }

    private double redondear(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}
