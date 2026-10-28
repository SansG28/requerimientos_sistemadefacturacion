package reservas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HotelOperacion {
    private final List<Hotel> hoteles = new ArrayList<>();

    public void registrarHotel(Hotel hotel) {
        Objects.requireNonNull(hotel, "El hotel no puede ser null");
        validarCampoObligatorio(hotel.getCodigo(), "El código del hotel es obligatorio");
        validarCampoObligatorio(hotel.getNombre(), "El nombre del hotel es obligatorio");
        validarCampoObligatorio(hotel.getCiudad(), "La ciudad del hotel es obligatoria");

        if (buscarHotelPorCodigo(hotel.getCodigo().trim()) != null) {
            throw new IllegalArgumentException("Ya existe un hotel registrado con el código: " + hotel.getCodigo());
        }

        if (hotel.getPrecioPorNoche() < 0) {
            throw new IllegalArgumentException("El precio por noche no puede ser negativo");
        }

        hoteles.add(hotel);
    }

    public Hotel buscarHotelPorCodigo(String codigo) {
        if (codigo == null) {
            return null;
        }
        for (Hotel hotel : hoteles) {
            if (Objects.equals(hotel.getCodigo(), codigo.trim())) {
                return hotel;
            }
        }
        return null;
    }

    public Hotel buscarHotelPorNombre(String nombre) {
        if (nombre == null) {
            return null;
        }
        for (Hotel hotel : hoteles) {
            if (hotel.getNombre() != null && hotel.getNombre().equalsIgnoreCase(nombre.trim())) {
                return hotel;
            }
        }
        return null;
    }

    public List<Hotel> listarHoteles() {
        return new ArrayList<>(hoteles);
    }

    private void validarCampoObligatorio(String valor, String mensaje) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensaje);
        }
    }
}
