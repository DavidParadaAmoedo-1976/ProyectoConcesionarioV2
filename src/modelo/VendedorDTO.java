package modelo;

import java.util.List;

public class VendedorDTO {
    private static int contador = 1;
    private int idVendedor;
    private String nombreCompleto;
    private String dni;
    private List<CocheDTO> cochesVendidos;

    public VendedorDTO(String nombreCompleto, String dni, List<CocheDTO> cochesVendidos) {
        this.idVendedor = contador++;
        this.nombreCompleto = nombreCompleto;
        this.dni = dni;
        this.cochesVendidos = cochesVendidos;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public List<CocheDTO> getCochesVendidos() {
        return cochesVendidos;
    }

    public void setCochesVendidos(List<CocheDTO> cochesVendidos) {
        this.cochesVendidos = cochesVendidos;
    }
}