package reservas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VueloOperacion {
    private final List<Vuelo> vuelos = new ArrayList<>();

    public void registrarVuelo(Vuelo vuelo) {
        Objects.requireNonNull(vuelo, "El vuelo no puede ser null");
        validarCampoObligatorio(vuelo.getCodigo(), "El código del vuelo es obligatorio");
        validarCampoObligatorio(vuelo.getAerolinea(), "La aerolínea es obligatoria");
        validarCampoObligatorio(vuelo.getOrigen(), "La ciudad de origen es obligatoria");
        validarCampoObligatorio(vuelo.getDestino(), "La ciudad de destino es obligatoria");
        validarCampoObligatorio(vuelo.getFechaSalida(), "La fecha/hora de salida es obligatoria");
        validarCampoObligatorio(vuelo.getFechaLlegada(), "La fecha/hora de llegada es obligatoria");

        if (buscarVuelo(vuelo.getCodigo().trim()) != null) {
            throw new IllegalArgumentException("Ya existe un vuelo registrado con el código: " + vuelo.getCodigo());
        }

        if (vuelo.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio del vuelo no puede ser negativo");
        }

        if (vuelo.getOrigen().trim().equalsIgnoreCase(vuelo.getDestino().trim())) {
            throw new IllegalArgumentException("El origen no puede ser igual al destino");
        }

        vuelos.add(vuelo);
    }

    public Vuelo buscarVuelo(String codigo) {
        if (codigo == null) {
            return null;
        }
        for (Vuelo vuelo : vuelos) {
            if (Objects.equals(vuelo.getCodigo(), codigo.trim())) {
                return vuelo;
            }
        }
        return null;
    }

    public List<Vuelo> listarVuelos() {
        return new ArrayList<>(vuelos);
    }

    private void validarCampoObligatorio(String valor, String mensaje) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensaje);
        }
    }
}
