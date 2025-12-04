package controlador;

import vista.ConcesionarioVista;

import java.util.Scanner;

public class ValidarDatos {

    private final Scanner sc;
    private ConcesionarioVista vista;

    public ValidarDatos() {
        this.sc = new Scanner(System.in);
    }

    public boolean validarOpcionMenu(String input, int min, int max) {
        int num = -1;
        try {
            num = Integer.parseInt(input);
            if (num >= min && num <= max) {
                return true;
            }
            vista.mensajeError("El valor debe estar entre " + min + " y " + max);
            return false;

        } catch (NumberFormatException e) {
            vista.mensajeError("Error, introduce un número entero.");
            return false;
        }
    }


    public String leerNombre() {
        String nombre = "";
        try {
            do {
                System.out.println("Introduzca su nombre (mínimo 3 caracteres, solo letras y espacios):");
                nombre = sc.nextLine().trim();
            } while (nombre.length() < 3 || !nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+"));
        } catch (Exception e) {
            System.out.println("Error al leer el nombre. Inténtelo nuevamente.");
        }
        return nombre;
    }

//    public String leerDni(List<ClienteDTO> clienteDTOS) {
//        String dni;
//        do {
//            System.out.println("Introduzca su DNI (8 números + 1 letra):");
//            dni = sc.nextLine().trim();
//            if (!validarDni(dni)) {
//                vista.mensajeError("Formato de DNI incorrecto.");
//            } else if (!validarDniRepetido(clienteDTOS, dni)) {
//                vista.mensajeError("El DNI introducido ya existe.");
//            }
//
//        } while (!validarDni(dni) || !validarDniRepetido(clienteDTOS, dni));
//
//        return dni.toUpperCase();
//    }


    private boolean validarDni(String dni) {
        return dni.matches("[0-9]{8}[A-Za-z]");
    }

    public boolean leerBooleano(String dato) {
        boolean datoOk = false;
        String input = "";

        while (!datoOk) {
            System.out.println("Introduzca " + dato);
            System.out.println("S|s o N|n");
            input = sc.nextLine();
            if (input.equalsIgnoreCase("S") || input.equalsIgnoreCase("N")) {
                datoOk = true;
            } else {
                System.out.println("Error: Introduzca S o N correctamente.");
            }
        }
        return input.equalsIgnoreCase("S");
    }

    public String leerTelefono() {
        String telefono = "";
        try {
            do {
                System.out.println("Introduzca su número de teléfono (puede incluir código de país):");
                telefono = sc.nextLine().trim();
            } while (!validarTelefono(telefono));
        } catch (Exception e) {
            System.out.println("Error al leer el teléfono. Inténtelo nuevamente.");
        }
        return telefono;
    }

    private boolean validarTelefono(String telefono) {
        return telefono.matches("\\+?[0-9]{1,3}[ -]?\\d{9}") || telefono.matches("\\d{9}");
    }
}
