package vista;

import modelo.ClienteDTO;
import modelo.CocheDTO;
import modelo.VendedorDTO;
import modelo.VentaDTO;
import modelo.OpcionesMenu;

import java.util.List;
import java.util.Scanner;

public class ConcesionarioVista {

    private final Scanner sc = new Scanner(System.in);

    public void mostrarMenu() {
        OpcionesMenu[] opciones = OpcionesMenu.values();

        for (int i = 1; i < opciones.length; i++) {
            System.out.println(i + ".\t" + opciones[i].getTextoOpciones());
        }
        System.out.println("0.\tSalir.");
    }

    public void mostrarMenuBuscar() {
        System.out.println("""
                
                1.- Marca.
                2.- Rango de precios.
                3.- Año.
                0.- Menú principal.
                """);
    }

    public String solicitarEntrada(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine();
    }

    public void mostrarSalida() {
        System.out.println("Saliendo del programa...");

    }


    public void mensajeError(String mensaje) {
        System.err.println(mensaje);
    }

    public void mensaje(String mensaje) {
        System.out.println(mensaje);
        System.out.println();
    }

    public void mostrarClientes(List<ClienteDTO> clienteDTOS) {
        for (ClienteDTO clienteDTO : clienteDTOS) {
            System.out.printf("%-25s DNI: %-9s Tlf: %-15s%n",
                    clienteDTO.getNombreCompleto(),
                    clienteDTO.getDni(),
                    clienteDTO.getTelefono());
        }
        System.out.println();
    }

    public void mostrarVentas(List<VentaDTO> ventas) {

        for (VentaDTO venta : ventas) {
            if (venta.getCliente() == null || venta.getCoche() == null || venta.getVendedor() == null) {
                System.out.println("Venta incompleta (ID: " + venta.getIdVenta() + ")");
                continue;
            }
            System.out.println(venta.getIdVenta() + ".- " + venta.getFecha() + " El vendedor " + venta.getVendedor().getNombreCompleto() + " a vendido a " +
                    venta.getCliente().getNombreCompleto() + " con DNI: " + venta.getCliente().getDni() + " el coche " +
                    venta.getCoche().getMarca() + " " + venta.getCoche().getModelo() + " con matrícula " + venta.getCoche().getMatricula() +
                    " por " + venta.getCoche().getPrecio() + " €.");

//            System.out.printf("%-3d %-10s %-10s Matricula: %-7s vendido a %-30s con DNI: %-9s por el vendedor %-30s%n",
//                    venta.getIdVenta(),
//                    venta.getCoche().getMarca(),
//                    venta.getCoche().getModelo(),
//                    venta.getCoche().getMatricula(),
//                    venta.getCliente().getNombreCompleto(),
//                    venta.getCliente().getDni(),
//                    venta.getVendedor().getNombreCompleto());
        }
    }

    public void mostrarCochesSimple(List<CocheDTO> coches) {
        for (CocheDTO coche : coches) {
            System.out.printf("%-10s %-10s %-10s %10.2f €%n",
                    coche.getMarca(),
                    coche.getModelo(),
                    coche.getMatricula(),
                    coche.getPrecio());
        }
    }

    public void mostrarCoches(List<CocheDTO> cochesDisponibles) {
        logo();
        System.out.print("\t".repeat(10) + " \u250C" + "\u2500".repeat(14) + "\u252C" + "\u2500".repeat(14) + "\u252C" + "\u2500".repeat(11) + "\u252C" + "\u2500".repeat(6) + "\u252C" + "\u2500".repeat(12) + "\u252C" + "\u2500".repeat(10) + "\u2510");
        System.out.printf("\n" + "\t".repeat(10) + " \u2502 %-12s \u2502 %-12s \u2502 %-7s \u2502 %-4s \u2502 %-10s \u2502 %-8s \u2502", "   Marca", "   Modelo", "Matrícula", "Año", "Kilómetros", " Precio");
        System.out.print("\n" + "\t".repeat(10) + " \u251C" + "\u2500".repeat(14) + "\u253C" + "\u2500".repeat(14) + "\u253C" + "\u2500".repeat(11) + "\u253C" + "\u2500".repeat(6) + "\u253C" + "\u2500".repeat(12) + "\u253C" + "\u2500".repeat(10) + "\u2524");

        for (CocheDTO coche : cochesDisponibles) {
            System.out.printf("\n" + "\t".repeat(10) + " \u2502 %-12s \u2502 %-12s \u2502  %-7s  \u2502 %-4d \u2502  %9d \u2502 %8.2f \u2502 ", coche.getMarca(), coche.getModelo(), coche.getMatricula(), coche.getAnioMatriculacion(), coche.getKm(), coche.getPrecio());
        }
        System.out.print("\n" + "\t".repeat(10) + " \u2514" + "\u2500".repeat(14) + "\u2534" + "\u2500".repeat(14) + "\u2534" + "\u2500".repeat(11) + "\u2534" + "\u2500".repeat(6) + "\u2534" + "\u2500".repeat(12) + "\u2534" + "\u2500".repeat(10) + "\u2518");
        System.out.println("\npulsa intro para volver al menú");
        sc.nextLine();
        System.out.println("\n".repeat(50));
    }

    public void mostrarVendedores(List<VendedorDTO> vendedores) {
        for (VendedorDTO vendedor : vendedores) {
            System.out.println(vendedor.getIdVendedor() + ".- " + vendedor.getNombreCompleto());
        }
    }

    public void logo() {
        System.out.println("""
                
                \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   \u250C\u2500\u2500\u2500\u252C\u2500\u2500\u2500\u2510
                \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\u250C\u2500\u2500\u2534\u2500\u2500\u2500\u2534\u2500\u2500\u2500\u2534\u2500\u2500\u2500\u2500\u2510
                \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\u254F  AUTOS TEIS   \u2593
                \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\u2567(\u2600)\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500(\u2600)\u2567
                """);

    }

}
