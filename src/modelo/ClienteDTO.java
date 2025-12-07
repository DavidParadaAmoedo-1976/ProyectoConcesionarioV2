package modelo;

import java.util.ArrayList;
import java.util.List;

public class ClienteDTO {
    private String dni;
    private String nombreCompleto;
    private String telefono;
    private List<CocheDTO> cochesComprados = new ArrayList<>();


    public ClienteDTO(String dni, String nombreCompleto, String telefono) {
        this.dni = dni;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<CocheDTO> getCochesComprados() {
        return cochesComprados;
    }

    public void setCochesComprado(CocheDTO cocheComprados) {
        this.cochesComprados = cochesComprados;
    }
}
