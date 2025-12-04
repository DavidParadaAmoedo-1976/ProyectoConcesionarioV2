import controlador.ConcesionarioControlador;
import controlador.ValidarDatos;
import vista.ConcesionarioVista;

public class Program {
    public static void main(String[] args) {
        ConcesionarioVista vista = new ConcesionarioVista();
        ConcesionarioControlador controlador = new ConcesionarioControlador(vista);

        controlador.cochesDePrueba();
        controlador.clientesDePrueba();
        controlador.vendedoresDePrueba();
        controlador.ejecuta();
    }
}
