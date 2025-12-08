package vista;

import modelo.*;
import java.util.List;
import java.util.Scanner;

public class ConcesionarioVista {

    private final Scanner sc = new Scanner(System.in);

    public void mostrarMenu() {
        OpcionesMenuEnum[] opciones = OpcionesMenuEnum.values();
        System.out.println("\n" + "\t".repeat(6) + ColoresEnum.ORO.getFormato() + ColoresEnum.SUBRAYADO.getFormato() + "*** Menú de opciones ***" + ColoresEnum.RESET.getFormato() + "\n");
        for (int i = 1; i < opciones.length; i++) {
            System.out.println("\t".repeat(2) + i + ".\t" + ColoresEnum.NARANJA.getFormato() + opciones[i].getTextoOpciones() + ColoresEnum.RESET.getFormato());
        }
        System.out.println("\t".repeat(2) + "0." + ColoresEnum.NARANJA.getFormato() + "\tSalir." + ColoresEnum.RESET.getFormato());
    }

    public String solicitarEntrada(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    public void mostrarSalida() {
        System.out.println("Saliendo del programa...");
    }

    public void mensajeError(String mensaje) {
        System.out.println(ColoresEnum.ROJO.getFormato() +  mensaje + ColoresEnum.RESET.getFormato());
    }

    public void mensaje(String mensaje) {
        System.out.println(mensaje);
        System.out.println();
    }

    public void mostrarClientes(List<ClienteDTO> clientes) {
        for (ClienteDTO cliente : clientes) {
            if (cliente.getCochesComprados() == null || cliente.getCochesComprados().isEmpty()) {
                System.out.println("El cliente aún no tiene coches comprados");
            } else {
                for (CocheDTO coche : cliente.getCochesComprados()) {
                    System.out.printf("\n%-25s DNI: %-9s Tlf: %-15s %-10s %-10s %-9s",
                            cliente.getNombreCompleto(),
                            cliente.getDni(),
                            cliente.getTelefono(),
                            coche.getMarca(),
                            coche.getModelo(),
                            coche.getMatricula());
                }
            }
        }
        esperarIntro();
    }

    public void mostrarVentas(List<VentaDTO> ventas) {
        if (ventas == null || ventas.isEmpty()) {
            System.err.println("No hay ventas en la lista.");
            return;
        }
        System.out.println(ColoresEnum.MORADO.getFormato() + ColoresEnum.SUBRAYADO.getFormato() + "\n" + "\t".repeat(8) + "*** Lista de ventas ***" + ColoresEnum.RESET.getFormato());
        logo();
        System.out.print("\t".repeat(3) + " \u250C" + "\u2500".repeat(6) + "\u252C" + "\u2500".repeat(12) + "\u252C" + "\u2500".repeat(32) + "\u252C" + "\u2500".repeat(32) + "\u252C" + "\u2500".repeat(13) + "\u252C" + "\u2500".repeat(14) + "\u252C" + "\u2500".repeat(14) + "\u252C" + "\u2500".repeat(13) + "\u252C" + "\u2500".repeat(12) + "\u2510");
        System.out.printf("\n" + "\t".repeat(3) + " \u2502 %-4s \u2502 %-10s \u2502 %-30s \u2502 %-30s \u2502 %-10s \u2502 %-12s \u2502 %-12s \u2502  %-7s \u2502 %-10s \u2502 ", " Id ", "   Fecha ", "     Nombre del vendedor", "     Nombre del cliente", "DNI cliente", "   Marca ", "   Modelo ", "Matricula ", "  Precio");
        System.out.print("\n" + "\t".repeat(3) + " \u251C" + "\u2500".repeat(6) + "\u253C" + "\u2500".repeat(12) + "\u253C" + "\u2500".repeat(32) + "\u253C" + "\u2500".repeat(32) + "\u253C" + "\u2500".repeat(13) + "\u253C" + "\u2500".repeat(14) + "\u253C" + "\u2500".repeat(14) + "\u253C" + "\u2500".repeat(13) + "\u253C" + "\u2500".repeat(12) + "\u2524");

        for (VentaDTO venta : ventas) {
            if (venta.getCliente() == null || venta.getCoche() == null || venta.getVendedor() == null) {
                System.out.println("Venta incompleta (ID: " + venta.getIdVenta() + ")");
                continue;
            }
            System.out.printf("\n" + "\t".repeat(3) + " \u2502 %3d  \u2502 %-10s \u2502 %-30s \u2502 %-30s \u2502 %11s \u2502 %12s \u2502 %12s \u2502   %-9s \u2502 %10.2f \u2502",
                    venta.getIdVenta(),
                    venta.getFecha(),
                    venta.getVendedor().getNombreCompleto(),
                    venta.getCliente().getNombreCompleto(),
                    venta.getCliente().getDni(),
                    venta.getCoche().getMarca(),
                    venta.getCoche().getModelo(),
                    venta.getCoche().getMatricula(),
                    venta.getCoche().getPrecio());
        }
        System.out.print("\n" + "\t".repeat(3) + " \u2514" + "\u2500".repeat(6) + "\u2534" + "\u2500".repeat(12) + "\u2534" + "\u2500".repeat(32) + "\u2534" + "\u2500".repeat(32) + "\u2534" + "\u2500".repeat(13) + "\u2534" + "\u2500".repeat(14) + "\u2534" + "\u2500".repeat(14) + "\u2534" + "\u2500".repeat(13) + "\u2534" + "\u2500".repeat(12) + "\u2518");
        esperarIntro();
    }

    public void mostrarCochesSimple(List<CocheDTO> coches) {
        if (coches == null || coches.isEmpty()) {
            System.err.println("No hay coches en la lista.");
            return;
        }
        for (CocheDTO coche : coches) {
            if (coche.isDisponible()) {
                System.out.printf("%-10s %-10s %-10s %10.2f €%n",
                        coche.getMarca(),
                        coche.getModelo(),
                        coche.getMatricula(),
                        coche.getPrecio());
            }
        }
    }

    public void mostrarCoches(List<CocheDTO> listaCoches, String tipo) {
        if (listaCoches == null || listaCoches.isEmpty()) {
            System.err.println("No hay coches en la lista.");
            return;
        }
        System.out.println(ColoresEnum.MORADO.getFormato() + ColoresEnum.SUBRAYADO.getFormato() + "\n" + "\t".repeat(8) + "*** Lista de " + tipo + " ***" + ColoresEnum.RESET.getFormato());
        logo();
        System.out.print("\t".repeat(10) + " \u250C" + "\u2500".repeat(14) + "\u252C" + "\u2500".repeat(14) + "\u252C" + "\u2500".repeat(11) + "\u252C" + "\u2500".repeat(6) + "\u252C" + "\u2500".repeat(12) + "\u252C" + "\u2500".repeat(10) + "\u2510");
        System.out.printf("\n" + "\t".repeat(10) + " \u2502 %-12s \u2502 %-12s \u2502 %-7s \u2502 %-4s \u2502 %-10s \u2502 %-8s \u2502", "   Marca", "   Modelo", "Matrícula", "Año", "Kilómetros", " Precio");
        System.out.print("\n" + "\t".repeat(10) + " \u251C" + "\u2500".repeat(14) + "\u253C" + "\u2500".repeat(14) + "\u253C" + "\u2500".repeat(11) + "\u253C" + "\u2500".repeat(6) + "\u253C" + "\u2500".repeat(12) + "\u253C" + "\u2500".repeat(10) + "\u2524");

        for (CocheDTO coche : listaCoches) {
            if (!coche.isDisponible()) {
                System.out.printf("\n" + "\t".repeat(10) + " \u2502"
                                + ColoresEnum.ROJO.getFormato() + " %-12s " + ColoresEnum.RESET.getFormato() + "\u2502"
                                + ColoresEnum.ROJO.getFormato() + " %-12s " + ColoresEnum.RESET.getFormato() + "\u2502 "
                                + ColoresEnum.ROJO.getFormato() + " %-7s " + ColoresEnum.RESET.getFormato() + " \u2502"
                                + ColoresEnum.ROJO.getFormato() + " %-4d " + ColoresEnum.RESET.getFormato() + "\u2502 "
                                + ColoresEnum.ROJO.getFormato() + " %9d " + ColoresEnum.RESET.getFormato() + "\u2502"
                                + ColoresEnum.ROJO.getFormato() + " %8.2f " + ColoresEnum.RESET.getFormato() + "\u2502 ",
                        coche.getMarca(),
                        coche.getModelo(),
                        coche.getMatricula(),
                        coche.getAnioMatriculacion(),
                        coche.getKm(),
                        coche.getPrecio());
            } else  {
                System.out.printf("\n" + "\t".repeat(10) + " \u2502"
                                + ColoresEnum.VERDE.getFormato() + " %-12s " + ColoresEnum.RESET.getFormato() + "\u2502"
                                + ColoresEnum.VERDE.getFormato() + " %-12s " + ColoresEnum.RESET.getFormato() + "\u2502 "
                                + ColoresEnum.VERDE.getFormato() + " %-7s " + ColoresEnum.RESET.getFormato() + " \u2502"
                                + ColoresEnum.VERDE.getFormato() + " %-4d " + ColoresEnum.RESET.getFormato() + "\u2502 "
                                + ColoresEnum.VERDE.getFormato() + " %9d " + ColoresEnum.RESET.getFormato() + "\u2502"
                                + ColoresEnum.VERDE.getFormato() + " %8.2f " + ColoresEnum.RESET.getFormato() + "\u2502 ",
                        coche.getMarca(),
                        coche.getModelo(),
                        coche.getMatricula(),
                        coche.getAnioMatriculacion(),
                        coche.getKm(),
                        coche.getPrecio());
            }
        }
        System.out.print("\n" + "\t".repeat(10) + " \u2514" + "\u2500".repeat(14) + "\u2534" + "\u2500".repeat(14) + "\u2534" + "\u2500".repeat(11) + "\u2534" + "\u2500".repeat(6) + "\u2534" + "\u2500".repeat(12) + "\u2534" + "\u2500".repeat(10) + "\u2518");
        esperarIntro();
    }

    public void mostrarVendedores(List<VendedorDTO> vendedores) {
        if (vendedores == null || vendedores.isEmpty()) {
            System.err.println("No hay vendedores en la lista.");
            return;
        }
        for (VendedorDTO vendedor : vendedores) {
            System.out.println(vendedor.getIdVendedor() + ".- " + vendedor.getNombreCompleto());
        }
    }

    public void mostrarEstadisticas(VendedorDTO vendedor, double mediaPrecio, CocheDTO cocheMasCaro, int numeroCochesVendidos, List<CocheDTO> cochesVendidosVendedor, double sumaPrecios) {
        System.out.println(ColoresEnum.MORADO.getFormato() + ColoresEnum.SUBRAYADO.getFormato() + "\n" + "\t".repeat(15) + "*** Estadisticas del Vendedor ***\n" + ColoresEnum.RESET.getFormato() +
                ColoresEnum.CYAN.getFormato() + vendedor.getNombreCompleto() + ColoresEnum.RESET.getFormato() + "\n" +
                "\nPrecio medio de los coches vendidos = " + ColoresEnum.CYAN.getFormato() + mediaPrecio + ColoresEnum.RESET.getFormato() +
                "\nEl coche mas caro fue: " + ColoresEnum.CYAN.getFormato() + cocheMasCaro.getMarca() + ", " + cocheMasCaro.getModelo() + ", con matrícula: " + cocheMasCaro.getMatricula() + " y con un precio de " + cocheMasCaro.getPrecio() + " €." + ColoresEnum.RESET.getFormato() +
                "\nEl número de coches vendidos fue de " + ColoresEnum.CYAN.getFormato() + numeroCochesVendidos + " coches." + ColoresEnum.RESET.getFormato() +
                "\nEl total de los coches vendidos suman: " + ColoresEnum.CYAN.getFormato() + sumaPrecios + ColoresEnum.RESET.getFormato());
        mostrarCoches(cochesVendidosVendedor, "vendidos por el vendedor");
    }

    public void logo() {
        System.out.println("""                
                \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   \u250C\u2500\u2500\u2500\u252C\u2500\u2500\u2500\u2510
                \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\u250C\u2500\u2500\u2534\u2500\u2500\u2500\u2534\u2500\u2500\u2500\u2534\u2500\u2500\u2500\u2500\u2510
                \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\u254F  AUTOS TEIS   \u2593
                \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\u2567(\u2600)\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500(\u2600)\u2567
                """);
    }

    private void esperarIntro() {
        System.out.println("\npulsa intro para volver al menú");
        sc.nextLine();
        System.out.println("\n".repeat(50));
    }
}