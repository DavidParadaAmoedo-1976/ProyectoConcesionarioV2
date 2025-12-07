package controlador;

import modelo.*;
import vista.ConcesionarioVista;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ConcesionarioControlador {
    private final int ZERO = 0, MENU_BUSCAR_MAXIMO = 3, ANIO_MATRICULACION_MINIMO = 1950, ANIO_MATRICULACION_MAXIMO = 2025, MAX_KM = Integer.MAX_VALUE;
    private final double PRECIO_MAXIMO = Double.MAX_VALUE;
    private final ConcesionarioVista vista;
    private final List<ClienteDTO> clientes;
    private final List<VentaDTO> ventas;
    private final List<VendedorDTO> vendedores;
    private final List<CocheDTO> coches;

    public ConcesionarioControlador(ConcesionarioVista vista) {
        this.vista = vista;
        this.clientes = new ArrayList<>();
        this.coches = new ArrayList<>();
        this.ventas = new ArrayList<>();
        this.vendedores = new ArrayList<>();
    }

    public void cargarDatosDePrueba() {
        DatosDePrueba datosDePrueba = new DatosDePrueba();
        this.coches.addAll(datosDePrueba.cochesDePrueba());
        this.clientes.addAll(datosDePrueba.clientesDePrueba());
        this.vendedores.addAll(datosDePrueba.vendedoresDePrueba());
        this.ventas.addAll(datosDePrueba.ventasDePrueba(this.clientes, this.coches, this.vendedores));
    }

    public void ejecuta() {
        OpcionesMenuEnum opcion = null;
        while (true) {
            vista.mostrarMenu();
            int seleccion = solicitarInt("\nIntroduce una opción: ", ZERO, OpcionesMenuEnum.values().length);
            opcion = OpcionesMenuEnum.values()[seleccion];
            switch (opcion) {
                case ANHADIR_COCHE -> anhadirCoche();
                case MOSTRAR_COCHES -> listarDisponibles(coches);
                case BUSCAR_COCHE -> listarBusqueda(coches);
                case ANHADIR_CLIENTE -> anadirCliente();
                case MOSTRAR_CLIENTES -> vista.mostrarClientes(clientes);
                case ANHADIR_VENTAS -> registrarVenta();
                case MOSTRAR_VENTAS -> vista.mostrarVentas(ventas);
                case ANHADIR_VENDEDOR -> anhadirVendedor();
                case MOSTRAR_VENDEDORES -> vista.mostrarVendedores(vendedores);
                case MOSTRAR_ESTADISTICAS -> extraerEstadisticas();
                case MOSTRAR_COCHES_ORDENADOS -> listarOrdenados(coches);
                case MOSTRAR_TODOS_LOS_COCHES -> vista.mostrarCoches(coches, "todos los coches del concesionario");
                case SALIR -> {
                    vista.mostrarSalida();
                    return;
                }
            }
        }
    }


    //  Funciones del SWITCH

    private void anhadirCoche() {
        CocheDTO nuevoCoche = obtenerDatosCoche();
        coches.add(nuevoCoche);
        vista.mensaje("Añadido " + nuevoCoche.getMarca() + " " + nuevoCoche.getModelo() + " con matrícula: " + nuevoCoche.getMatricula());
    }

    private void listarDisponibles(List<CocheDTO> coches) {
        List<CocheDTO> cochesDisponiples = new ArrayList<>();
        if (coches.isEmpty()) vista.mensaje("No hay coches en la lista");
        for (CocheDTO coche : coches) {
            if (coche.isDisponible()) {
                cochesDisponiples.add(coche);
            }
        }
        vista.mostrarCoches(cochesDisponiples, "los coches disponibles para vender");
    }

    private void listarBusqueda(List<CocheDTO> coches) {
    }

    private void anadirCliente() {
        ClienteDTO nuevoCliente = obtenerDatosCliente();
        clientes.add(nuevoCliente);
        vista.mensaje("\n" + nuevoCliente.getNombreCompleto() + " añadido a la lista de clientes.");
    }

    private void registrarVenta() {
        VentaDTO nuevaVenta = obtenerDatosVenta();
        ventas.add(nuevaVenta);
        vista.mensaje("Venta registrada correctamente.");
    }

    private void anhadirVendedor() {
        VendedorDTO nuevoVendedor = obtenerDatosVendedor();
        vendedores.add(nuevoVendedor);
    }

    private void extraerEstadisticas() {

        vista.mostrarVendedores(vendedores);

        int idVendedor = solicitarInt("Introduce el id del vendedor para ver sus estadisticas: ", 1, vendedores.size());

        VendedorDTO vendedorSeleccionado = null;

        for (VendedorDTO vendedor : vendedores) {
            if (vendedor.getIdVendedor() == idVendedor) {
                vendedorSeleccionado = vendedor;
                break;
            }
        }
        if (vendedorSeleccionado == null) {
            System.out.println("El id no corresponde a ningun vendedor.");
            return;
        }

        List<CocheDTO> cochesVendidosVendedor = vendedorSeleccionado.getCochesVendidos();

        if (cochesVendidosVendedor.isEmpty()) {
            System.out.println("No hay coches vendidos en la lista.");
            return;
        }
        double sumaPrecios = 0;
        double precioMasAlto = cochesVendidosVendedor.get(0).getPrecio();
        CocheDTO cocheMasCaro = cochesVendidosVendedor.get(0);
        for (CocheDTO coche : cochesVendidosVendedor) {
            sumaPrecios += coche.getPrecio();
            if (precioMasAlto < coche.getPrecio()) {
                precioMasAlto = coche.getPrecio();
                cocheMasCaro = coche;
            }
        }
        double mediaPrecio = sumaPrecios / cochesVendidosVendedor.size();

        vista.mostrarEstadisticas(vendedorSeleccionado, mediaPrecio, cocheMasCaro, cochesVendidosVendedor.size(), cochesVendidosVendedor, sumaPrecios);
    }

    private void listarOrdenados(List<CocheDTO> coches) {
        List<CocheDTO> listaDeCochesOrdenada = new ArrayList<>(coches);
        listaDeCochesOrdenada.sort(Comparator.comparingDouble(CocheDTO::getPrecio).thenComparing(CocheDTO::getMarca).thenComparingInt(CocheDTO::getAnioMatriculacion));

        vista.mostrarCoches(listaDeCochesOrdenada, "todos los coches, ordenada por precio, marca y fecha de matriculación");
    }
//

//
//    public void mostrarCoches(List<CocheDTO> coches) {
//        List<CocheDTO> cochesDisponiples = new ArrayList<>();
//        if (coches.isEmpty()) vista.mensaje("No hay coches en la lista");
//        for (CocheDTO coche : coches) {
//            if (coche.isDisponible()) {
//                cochesDisponiples.add(coche);
//            }
//        }
//        vista.mostrarCoches(cochesDisponiples);
//    }
//
//    private void buscarCoches(List<CocheDTO> coches) {
//        int opcion = -1;
//        while (opcion != 0) {
//            vista.mostrarMenuBuscar();
//            opcion = solicitarInt("Introduce una opción: ",ZERO ,MENU_BUSCAR_MAXIMO);
//            switch (opcion) {
//                case 1 -> buscarPorMarca();
//                case 2 -> buscarPorPrecio();
//                case 3 -> buscarPorAnio();
//            }
//        }
//    }
//
//    private void buscarPorAnio() {
//        List<CocheDTO> busquedaCoche = new ArrayList<>();
//        if (coches.isEmpty()) vista.mensaje("No hay coches en la lista");
//        int busqueda = solicitarInt("Introduce el año de matriculación por el que quieres buscar: ", ANIO_MATRICULACION_MINIMO, ANIO_MATRICULACION_MAXIMO);
//        for (CocheDTO coche : coches) {
//            if (coche.getAnioMatriculacion() == busqueda) {
//                busquedaCoche.add(coche);
//            }
//        }
//        vista.mostrarCoches(busquedaCoche);
//    }
//
//    private void buscarPorPrecio() {
//        List<CocheDTO> busquedaCoche = new ArrayList<>();
//        if (coches.isEmpty()) vista.mensaje("No hay coches en la lista");
//        double precioMinimo = solicitarDouble("Introduce el precio mínimo: ", ZERO, PRECIO_MAXIMO);
//        double precioMaximo = solicitarDouble("Introduce el precio máximo: ", ZERO, PRECIO_MAXIMO);
//        for (CocheDTO coche : coches) {
//            if (coche.getPrecio() >= precioMinimo && coche.getPrecio() <= precioMaximo) {
//                busquedaCoche.add(coche);
//            }
//        }
//        vista.mostrarCoches(busquedaCoche);
//    }
//
//    private void buscarPorMarca() {
//        List<CocheDTO> busquedaCoche = new ArrayList<>();
//        if (coches.isEmpty()) vista.mensaje("No hay coches en la lista");
//        String busqueda = vista.solicitarEntrada("Introduce marca del coche que quieres buscar: ");
//        for (CocheDTO coche : coches) {
//            if (coche.getMarca().equals(busqueda)) {
//                busquedaCoche.add(coche);
//            }
//        }
//        vista.mostrarCoches(busquedaCoche);
//    }
//

//
//
    // Obtener datos

    // Coche
    public CocheDTO obtenerDatosCoche() {
        String marca = vista.solicitarEntrada("Introduce la marca del coche: ");
        String modelo = vista.solicitarEntrada("Introduce el modelo del coche: ");
        String matricula = vista.solicitarEntrada("Introduce la matrícula del coche: ").toUpperCase();
        double precio = solicitarDouble("Introduce el precio del coche: ", ZERO, PRECIO_MAXIMO);
        int anioMatriculacion = solicitarInt("Introduce el año de matriculación del coche: ", ANIO_MATRICULACION_MINIMO, ANIO_MATRICULACION_MAXIMO);
        int kilometros = solicitarInt("Introduce los kilometro que tiene el coche: ", ZERO, MAX_KM);

        return new CocheDTO(marca, modelo, matricula, precio, anioMatriculacion, kilometros);
    }

    // Cliente
    private ClienteDTO obtenerDatosCliente() {
        String dni = solicitarDni("Cliente");
        String nombre = vista.solicitarEntrada("Introduce el nombre completo del cliente: \n");
        String telefono = solicitarTelefono();
        return new ClienteDTO(dni, nombre, telefono);
    }

    // Vendedor
    private VendedorDTO obtenerDatosVendedor() {
        String nombre = vista.solicitarEntrada("Introduce el nombre completo del nuevo vendedor.");
        String dni = solicitarDni("Vendedor");

        return new VendedorDTO(nombre, dni, null);
    }

    // Venta
    private VentaDTO obtenerDatosVenta() {
        ClienteDTO cliente = null;
        CocheDTO coche = null;
        VendedorDTO vendedor = null;

        vista.mostrarClientes(clientes);
        while (cliente == null) {
            String dniCliente = vista.solicitarEntrada("Introduce el DNI del cliente: ").toUpperCase();
            for (ClienteDTO clienteVenta : clientes) {
                if (clienteVenta.getDni().equalsIgnoreCase(dniCliente)) {
                    cliente = clienteVenta;
                    break;
                }
            }
            if (cliente == null) vista.mensaje("No se encuentra el DNI.");
        }
        vista.mostrarCochesSimple(coches);
        while (coche == null) {
            String matriculaVenta = vista.solicitarEntrada("Introduce la matricula del coche: ").toUpperCase();
            for (CocheDTO cocheVenta : coches) {
                if (cocheVenta.getMatricula().equalsIgnoreCase(matriculaVenta)) {
                    coche = cocheVenta;
                    break;
                }
            }
            if (coche == null) vista.mensaje("No se encuentra la Matricula del cohce.");
        }
        vista.mostrarVendedores(vendedores);
        while (vendedor == null) {
            int idVendedor = solicitarInt("Introduce el id del vendedor: ", ZERO, vendedores.size());
            for (VendedorDTO vendedorVenta : vendedores) {
                if (vendedorVenta.getIdVendedor() == idVendedor) {
                    vendedor = vendedorVenta;
                    break;
                }
            }
            if (vendedor == null)
                vista.mensaje("No se encuentra el vendedor");
        }
        LocalDate fecha = LocalDate.now();
        double precioVenta = coche.getPrecio();

        vendedor.getCochesVendidos().add(coche);
        cliente.getCochesComprados().add(coche);
        coche.setDisponible(false);

        return new VentaDTO(cliente, coche, vendedor, fecha, precioVenta);
    }


    // Validar datos

    private int solicitarInt(String mensaje, int min, int max) {

        while (true) {
            String input = vista.solicitarEntrada(mensaje);
            try {
                int valor = Integer.parseInt(input);
                if (valor >= min && valor <= max) {
                    return valor;
                } else {
                    vista.mensaje("!!! ERROR !!!  El valor debe estar entre " + min + " y " + max + ".");
                }
            } catch (NumberFormatException e) {
                vista.mensaje("!!! ERROR !!!  Introduce un número entero válido.");
            }
        }
    }

    private double solicitarDouble(String mensaje, double min, double max) {
        while (true) {
            String input = vista.solicitarEntrada(mensaje);
            try {
                double valor = Double.parseDouble(input);
                if (valor >= min && valor <= max) {
                    return valor;
                } else {
                    vista.mensaje("!!! ERROR !!!  El valor debe estar entre " + min + " y " + max + ".");
                }
            } catch (NumberFormatException e) {
                vista.mensaje("!!! ERROR !!!  Introduce un número válido.");
            }
        }
    }

    private String solicitarTelefono() {
        String telefono = "";
        boolean datoOk = false;
        while (!datoOk) {
            telefono = vista.solicitarEntrada("Introduce el teléfono del cliente: ");
            if (telefono.matches("\\+?[0-9]{1,3}[ -]?\\d{9}") || telefono.matches("\\d{9}")) {
                datoOk = true;
            } else {
                vista.mensaje("Número de telefono incorrecto:");
            }
        }
        return telefono;
    }

    private String solicitarDni(String persona) {
        String dni;
        while (true) {
            dni = vista.solicitarEntrada("DNI: ");

            if (!validarFormatoDni(dni)) {
                vista.mensajeError("Formato incorrecto. Debe ser 8 números y 1 letra, ej: 12345678A");
                continue;
            }
            String personaDni = "Cliente";
            if (personaDni.equalsIgnoreCase(persona)) {
                if (dniExisteEnClientes(dni)) {
                    vista.mensajeError("Ese DNI ya está registrado.");
                    break;
                }
            }
            if (dniExisteEnVendedores(dni)) {
                vista.mensajeError("Ese DNI ya está registrado.");
                break;
            }
            break; // DNI válido y no repetido
        }
        return dni;
    }

    private boolean validarFormatoDni(String dni) {
        return dni != null && dni.matches("\\d{8}[a-zA-Z]");
    }

    private boolean dniExisteEnClientes(String dni) {
        for (ClienteDTO cliente : clientes) {
            if (cliente.getDni().equalsIgnoreCase(dni)) return true;
        }
        return false;
    }

    private boolean dniExisteEnVendedores(String dni) {
        for (VendedorDTO vendedor : vendedores) {
            if (vendedor.getDni().equalsIgnoreCase(dni)) return true;
        }
        return false;
    }
}



