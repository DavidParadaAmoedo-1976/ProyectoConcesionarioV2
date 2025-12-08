package modelo;

import java.time.LocalDate;

public class VentaDTO {
    private static int contador = 1;
    private int idVenta;
    private ClienteDTO cliente;
    private CocheDTO coche;
    private VendedorDTO vendedor;
    private LocalDate fecha;
    private double precioVenta;

    public VentaDTO(ClienteDTO cliente, CocheDTO coche, VendedorDTO vendedor, LocalDate fecha, double precioVenta) {
        this.idVenta = contador++;
        this.cliente = cliente;
        this.coche = coche;
        this.vendedor = vendedor;
        this.fecha = fecha;
        this.precioVenta = precioVenta;
    }

    public VentaDTO(int idVenta, ClienteDTO cliente, CocheDTO coche, VendedorDTO vendedor, LocalDate fecha, double precioVenta) {
        this.idVenta = idVenta;
        this.cliente = cliente;
        this.coche = coche;
        this.vendedor = vendedor;
        this.fecha = fecha;
        this.precioVenta = precioVenta;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public CocheDTO getCoche() {
        return coche;
    }

    public void setCoche(CocheDTO coche) {
        this.coche = coche;
    }

    public VendedorDTO getVendedor() {
        return vendedor;
    }

    public void setVendedor(VendedorDTO vendedor) {
        this.vendedor = vendedor;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }
}