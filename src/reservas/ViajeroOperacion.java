package reservas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ViajeroOperacion {
    private final List<Viajero> viajeros = new ArrayList<>();

    public void registrarViajero(Viajero viajero) {
        Objects.requireNonNull(viajero, "El viajero no puede ser null");
        validarCampoObligatorio(viajero.getDocumento(), "El documento de identidad es obligatorio");
        validarCampoObligatorio(viajero.getNombre(), "El nombre es obligatorio");
        validarCampoObligatorio(viajero.getCorreo(), "El correo electrónico es obligatorio");
        validarCampoObligatorio(viajero.getTelefono(), "El teléfono es obligatorio");

        if (buscarViajero(viajero.getDocumento().trim()) != null) {
            throw new IllegalArgumentException(
                    "El documento ya se encuentra registrado: " + viajero.getDocumento());
        }

        viajeros.add(viajero);
    }

    public Viajero buscarViajero(String documento) {
        if (documento == null) {
            return null;
        }
        for (Viajero viajero : viajeros) {
            if (Objects.equals(viajero.getDocumento(), documento.trim())) {
                return viajero;
            }
        }
        return null;
    }

    public List<Viajero> listarViajeros() {
        return new ArrayList<>(viajeros);
    }

    private void validarCampoObligatorio(String valor, String mensaje) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensaje);
        }
    }
}
