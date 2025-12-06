import controlador.ConcesionarioControlador;
import vista.ConcesionarioVista;

public class Program {
    static void main(String[] args) {
        ConcesionarioVista vista = new ConcesionarioVista();
        ConcesionarioControlador controlador = new ConcesionarioControlador(vista);

        controlador.cargarDatosDePrueba();

        controlador.ejecuta();
    }
}
