package modelo;

public class ClienteDTO {
    private String dni;
    private String nombreCompleto;
    private String telefono;

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
}
