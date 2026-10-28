package reservas;

import java.util.Date;

public class Reserva {
    private String codigo;
    private Viajero viajero;
    private Vuelo vuelo;
    private Hotel hotel;
    private int noches;
    private double costoVuelo;
    private double costoHotel;
    private double total;
    private Date fechaCreacion;

    public Reserva() {
        this.fechaCreacion = new Date();
    }

    public Reserva(String codigo, Viajero viajero, Vuelo vuelo, Hotel hotel, int noches) {
        this.codigo = codigo;
        this.viajero = viajero;
        this.vuelo = vuelo;
        this.hotel = hotel;
        this.noches = noches;
        this.fechaCreacion = new Date();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Viajero getViajero() {
        return viajero;
    }

    public void setViajero(Viajero viajero) {
        this.viajero = viajero;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public int getNoches() {
        return noches;
    }

    public void setNoches(int noches) {
        this.noches = noches;
    }

    public double getCostoVuelo() {
        return costoVuelo;
    }

    public void setCostoVuelo(double costoVuelo) {
        this.costoVuelo = costoVuelo;
    }

    public double getCostoHotel() {
        return costoHotel;
    }

    public void setCostoHotel(double costoHotel) {
        this.costoHotel = costoHotel;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
