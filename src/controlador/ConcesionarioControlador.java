package controlador;

import modelo.ClienteDTO;
import modelo.CocheDTO;
import modelo.VendedorDTO;
import modelo.VentaDTO;
import modelo.OpcionesMenu;
import vista.ConcesionarioVista;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConcesionarioControlador {
    private final int ZERO = 0, MENU_MAXIMO = 8, MENU_BUSCAR_MAXIMO = 3, ANIO_MATRICULACION_MINIMO = 1950, ANIO_MATRICULACION_MAXIMO = 2025, MAX_KM = Integer.MAX_VALUE;
    private final double PRECIO_MAXIMO = Double.MAX_VALUE;
    private final ConcesionarioVista vista;
    private final List<ClienteDTO> clientes;
    private final List<CocheDTO> coches;
    private final List<VentaDTO> ventas;
    private final List<VendedorDTO> vendedores;

    public ConcesionarioControlador(ConcesionarioVista vista) {
        this.vista = vista;
        this.clientes = new ArrayList<>();
        this.coches = new ArrayList<>();
        this.ventas = new ArrayList<>();
        this.vendedores = new ArrayList<>();
    }

    public void ejecuta() {
        OpcionesMenu opcion = null;
        while (true) {
            vista.mostrarMenu();
            int seleccion = solicitarInt("\nIntroduce una opcion",1,OpcionesMenu.values().length);
            opcion = OpcionesMenu.values()[seleccion];
            switch (opcion) {
                case ANHADIR_COCHE -> anadirCoche();
                case MOSTRAR_COCHES -> mostrarCoches(coches);
                case BUSCAR_COCHE -> buscarCoches(coches);
                case ANHADIR_CLIENTE -> anadirCliente();
                case MOSTRAR_CLIENTES -> vista.mostrarClientes(clientes);
                case ANHADIR_VENTAS -> registrarVenta();
                case MOSTRAR_VENTAS -> vista.mostrarVentas(ventas);
                case ANHADIR_VENDEDOR -> {}
                case MOSTRAR_VENDEDORES -> {}
                case MOSTRAR_ESTADISTICAS -> {}
                case MOSTRAR_CLIENTE_ORDENADOS -> mostrarOrdenados();
                case MOSTRAR_TODOS_LOS_COCHES -> {}
                case SALIR -> vista.mostrarSalida();
                case null -> {}
            }
        }
    }

    private void mostrarOrdenados() {

    }

    private void anadirCoche() {
        CocheDTO nuevoCocheDTO = obtenerDatosCoche();
        coches.add(nuevoCocheDTO);
        vista.mensaje("Añadido " + nuevoCocheDTO.getMarca() + " " + nuevoCocheDTO.getModelo() + " con matricula: " + nuevoCocheDTO.getMatricula());
    }

    public void mostrarCoches(List<CocheDTO> coches) {
        List<CocheDTO> cochesDisponiples = new ArrayList<>();
        if (coches.isEmpty()) vista.mensaje("No hay coches en la lista");
        for (CocheDTO coche : coches) {
            if (coche.isDisponible()) {
                cochesDisponiples.add(coche);
            }
        }
        vista.mostrarCoches(cochesDisponiples);
    }

    private void buscarCoches(List<CocheDTO> coches) {
        int opcion = -1;
        while (opcion != 0) {
            vista.mostrarMenuBuscar();
            opcion = solicitarInt("Introduce una opción: ",ZERO ,MENU_BUSCAR_MAXIMO);
            switch (opcion) {
                case 1 -> buscarPorMarca();
                case 2 -> buscarPorPrecio();
                case 3 -> buscarPorAnio();
            }
        }
    }

    private void anadirCliente() {
        String dni = solicitarDni();
        String nombre = vista.solicitarEntrada("Introduce el nombre completo del cliente: \n");
        String telefono = solicitarTelefono();

        ClienteDTO nuevoClienteDTO = new ClienteDTO(dni, nombre, telefono);

        clientes.add(nuevoClienteDTO);
        vista.mensaje("\n" + nuevoClienteDTO.getNombreCompleto() + " añadido a la lista de clientes.");
    }

    private String solicitarDni() {
        String dni;
        while (true) {
            dni = vista.solicitarEntrada("DNI: ");

            if (!validarFormatoDni(dni)) {
                vista.mensajeError("Formato incorrecto. Debe ser 8 números y 1 letra, ej: 12345678A");
                continue;
            }

            if (dniExisteEnClientes(dni)) {
                vista.mensajeError("Ese DNI ya está registrado.");
                continue;
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

//    private String solicitarDni() {
//        String dni = "";
//        boolean datoOk = false;
//        while (!datoOk) {
//            dni = vista.solicitarEntrada("Introduce el DNI del cliente: ");
//            if (!dni.matches("[0-9]{8}[A-Za-z]")) {
//                vista.mensaje("Formato de DNI incorrecto.");
//                continue;
//            }
//            boolean existe = false;
//            for (ClienteDTO clienteDTO : clientes) {
//                if (clienteDTO.getDni().equalsIgnoreCase(dni)) {
//                    vista.mensaje("El dni introducido ya esta en la lista");
//                    existe = true;
//                    break;
//                }
//            }
//            if (!existe)
//                datoOk = true;
//        }
//        return dni;
//    }

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

    private void registrarVenta() {
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

        VentaDTO nuevaVenta = new VentaDTO(cliente, coche, vendedor, fecha, precioVenta);
        ventas.add(nuevaVenta);
        vendedor.getCochesVendidos().add(coche);
        coche.setDisponible(false);
        vista.mensaje("Venta registrada correctamente.");

    }

    private void buscarPorAnio() {
        List<CocheDTO> busquedaCoche = new ArrayList<>();
        if (coches.isEmpty()) vista.mensaje("No hay coches en la lista");
        int busqueda = solicitarInt("Introduce el año de matriculación por el que quieres buscar: ", ANIO_MATRICULACION_MINIMO, ANIO_MATRICULACION_MAXIMO);
        for (CocheDTO coche : coches) {
            if (coche.getAnioMatriculacion() == busqueda) {
                busquedaCoche.add(coche);
            }
        }
        vista.mostrarCoches(busquedaCoche);
    }

    private void buscarPorPrecio() {
        List<CocheDTO> busquedaCoche = new ArrayList<>();
        if (coches.isEmpty()) vista.mensaje("No hay coches en la lista");
        double precioMinimo = solicitarDouble("Introduce el precio mínimo: ", ZERO, PRECIO_MAXIMO);
        double precioMaximo = solicitarDouble("Introduce el precio máximo: ", ZERO, PRECIO_MAXIMO);
        for (CocheDTO coche : coches) {
            if (coche.getPrecio() >= precioMinimo && coche.getPrecio() <= precioMaximo) {
                busquedaCoche.add(coche);
            }
        }
        vista.mostrarCoches(busquedaCoche);
    }

    private void buscarPorMarca() {
        List<CocheDTO> busquedaCoche = new ArrayList<>();
        if (coches.isEmpty()) vista.mensaje("No hay coches en la lista");
        String busqueda = vista.solicitarEntrada("Introduce marca del coche que quieres buscar: ");
        for (CocheDTO coche : coches) {
            if (coche.getMarca().equals(busqueda)) {
                busquedaCoche.add(coche);
            }
        }
        vista.mostrarCoches(busquedaCoche);
    }

    public CocheDTO obtenerDatosCoche() {
        String marca = vista.solicitarEntrada("Introduce la marca del coche: ");
        String modelo = vista.solicitarEntrada("Introduce el modelo del coche: ");
        String matricula = vista.solicitarEntrada("Introduce la matrícula del coche: ").toUpperCase();
        double precio = solicitarDouble("Introduce el precio del coche: ", ZERO, PRECIO_MAXIMO);
        int anioMatriculacion = solicitarInt("Introduce el año de matriculación del coche: ", ANIO_MATRICULACION_MINIMO, ANIO_MATRICULACION_MAXIMO);
        int kilometros = solicitarInt("Introduce los kilometro que tiene el coche: ", ZERO,MAX_KM);
        return new CocheDTO(marca, modelo, matricula, precio, anioMatriculacion, kilometros);
    }

    public void cochesDePrueba() {
        coches.add(new CocheDTO("Toyota", "Corolla", "1234JBC", 9000, 2015, 110000));
        coches.add(new CocheDTO("Honda", "Civic", "5678JKL", 10500, 2015, 98000));
        coches.add(new CocheDTO("Ford", "Focus", "2345KDF", 11500, 2016, 90000));
        coches.add(new CocheDTO("Volkswagen", "Golf", "7890KRT", 12000, 2016, 85000));
        coches.add(new CocheDTO("BMW", "120d", "1123LBG", 13500, 2017, 78000));
        coches.add(new CocheDTO("Mercedes", "A180", "3345LLM", 14000, 2017, 72000));
        coches.add(new CocheDTO("Audi", "A3", "5566MCP", 15000, 2018, 65000));
        coches.add(new CocheDTO("Seat", "Ibiza", "7788MZH", 13000, 2018, 82000));
        coches.add(new CocheDTO("Seat", "León", "9900NDF", 16000, 2019, 54000));
        coches.add(new CocheDTO("Renault", "Clio", "2233NGT", 15000, 2019, 62000));
        coches.add(new CocheDTO("Peugeot", "308", "4412PFK", 18500, 2020, 41000));
        coches.add(new CocheDTO("Toyota", "Yaris", "6634PMN", 17000, 2020, 38000));
        coches.add(new CocheDTO("Hyundai", "i30", "3456LHR", 12500, 2017, 83000));
        coches.add(new CocheDTO("Kia", "Ceed", "8765MTS", 15500, 2018, 60000));
        coches.add(new CocheDTO("Nissan", "Qashqai", "1987NXP", 18000, 2019, 50000));
        coches.add(new CocheDTO("Mazda", "3", "4567KMB", 11000, 2016, 95000));
        coches.add(new CocheDTO("Citroën", "C4", "7654JZR", 8500, 2015, 120000));
        coches.add(new CocheDTO("Opel", "Astra", "5674LDC", 12500, 2017, 88000));
        coches.add(new CocheDTO("Skoda", "Octavia", "6483MFR", 16500, 2018, 56000));
        coches.add(new CocheDTO("Fiat", "Tipo", "7823PKH", 17500, 2020, 42000));

    }

    public void clientesDePrueba() {
        clientes.add(new ClienteDTO("12345678A", "Carlos Martínez López", "600123456"));
        clientes.add(new ClienteDTO("23456789B", "María Gómez Ruiz", "611987654"));
        clientes.add(new ClienteDTO("34567890C", "Javier Ortega Pérez", "622456789"));
        clientes.add(new ClienteDTO("45678901D", "Lucía Hernández Soto", "633567890"));
        clientes.add(new ClienteDTO("56789012E", "Elena Torres García", "644678901"));
    }

    public void vendedoresDePrueba() {
        vendedores.add(new VendedorDTO("Pedro Martinez Iglesias", "11223344A", new ArrayList<>()));
        vendedores.add(new VendedorDTO("Ana López Seoane", "22334455B", new ArrayList<>()));
    }

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
}

