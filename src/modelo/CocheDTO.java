package modelo;

public class CocheDTO {
    private String marca;
    private String modelo;
    private String matricula;
    private double precio;
    private int anioMatriculacion;
    private int km;
    private boolean disponible;

    public CocheDTO(String marca, String modelo, String matricula, double precio, int anioMatriculacion, int km) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.precio = precio;
        this.anioMatriculacion = anioMatriculacion;
        this.km = km;
        this.disponible = true;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double predio) {
        this.precio = predio;
    }

    public int getAnioMatriculacion() {
        return anioMatriculacion;
    }

    public void setAnioMatriculacion(int anioMatriculacion) {
        this.anioMatriculacion = anioMatriculacion;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}