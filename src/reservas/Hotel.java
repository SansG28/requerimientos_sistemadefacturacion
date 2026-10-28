package reservas;

public class Hotel {
    private String codigo;
    private String nombre;
    private String ciudad;
    private double precioPorNoche;

    public Hotel() {
    }

    public Hotel(String codigo, String nombre, String ciudad, double precioPorNoche) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.precioPorNoche = precioPorNoche;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public double getPrecioPorNoche() {
        return precioPorNoche;
    }

    public void setPrecioPorNoche(double precioPorNoche) {
        this.precioPorNoche = precioPorNoche;
    }
}
