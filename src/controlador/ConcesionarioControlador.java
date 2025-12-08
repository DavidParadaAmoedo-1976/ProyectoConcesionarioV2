package controlador;

import modelo.*;
import vista.ConcesionarioVista;

import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ConcesionarioControlador {
    private final int ZERO = 0, ANIO_MATRICULACION_MINIMO = 1950, ANIO_MATRICULACION_MAXIMO = 2025, KM_MAX = Integer.MAX_VALUE;
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
            int seleccion = solicitarInt("\nIntroduce una opción: ", ZERO, OpcionesMenuEnum.values().length, false);
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
        for (CocheDTO coche : coches) {
            if (coche.isDisponible()) {
                cochesDisponiples.add(coche);
            }
        }
        vista.mostrarCoches(cochesDisponiples, "los coches disponibles para vender");
    }

    private void listarBusqueda(List<CocheDTO> coches) {
        List<CocheDTO> busquedaCoches;
        busquedaCoches = obtenerDatosParaLaBusqueda(coches);
        vista.mostrarCoches(busquedaCoches, "coches con los parámetros de búsqueda especificados.");
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

        int idVendedor = solicitarInt("Introduce el id del vendedor para ver sus estadisticas: ", 1, vendedores.size(), false);

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

    // Obtener datos

    // Coche
    public CocheDTO obtenerDatosCoche() {
        String marca = vista.solicitarEntrada("Introduce la marca del coche: ");
        String modelo = vista.solicitarEntrada("Introduce el modelo del coche: ");
        String matricula = solicitarMatricula(false);
        double precio = solicitarDouble("Introduce el precio del coche: ", ZERO, PRECIO_MAXIMO, false);
        int anioMatriculacion = solicitarInt("Introduce el año de matriculación del coche: ", ANIO_MATRICULACION_MINIMO, ANIO_MATRICULACION_MAXIMO, false);
        int kilometros = solicitarInt("Introduce los kilometro que tiene el coche: ", ZERO, KM_MAX, false);

        return new CocheDTO(marca, modelo, matricula, precio, anioMatriculacion, kilometros);
    }

    // Cliente
    private ClienteDTO obtenerDatosCliente() {
        String dni = solicitarDni("Cliente");
        String nombre = vista.solicitarEntrada("Introduce el nombre completo del cliente: \n");
        String telefono = solicitarTelefono();
        return new ClienteDTO(dni, nombre, telefono);
    }

    // Busqueda
    private List<CocheDTO> obtenerDatosParaLaBusqueda(List<CocheDTO> coches) {
        List<CocheDTO> busqueda = new ArrayList<>();
        if (coches == null || coches.isEmpty()) {
            vista.mensajeError("No hay coches en la lista");
            return null;
        }
        String matricula = solicitarMatricula(true);
        if (!matricula.isBlank()) {
            for (CocheDTO c : coches) {
                if (c.getMatricula().equalsIgnoreCase(matricula)) {
                    busqueda.add(c);
                    return busqueda;
                }
            }
            vista.mensaje("No existen coches con esa matrícula.");
            return busqueda;
        }

        double precioMin, precioMax;
        int anioMin, kmMax;
        String marca = vista.solicitarEntrada("Introduce la marca del coche a buscar: ");
        String modelo = vista.solicitarEntrada("Introduce el modelo del coche a buscar: ");

        double precioMinimo = solicitarDouble("Introduce el precio mas bajo para la busqueda: ", ZERO, PRECIO_MAXIMO, true);
        if (precioMinimo == -1) {
            precioMin = ZERO;
        } else {
            precioMin = precioMinimo;
        }
        double precioMaximo = solicitarDouble("Introduce el precio mas alto para la busqueda: ", ZERO, PRECIO_MAXIMO, true);
        if (precioMaximo == -1) {
            precioMax = PRECIO_MAXIMO;
        } else {
            precioMax = precioMaximo;
        }
        int anioMinimo = solicitarInt("Introduce el año a partir del que quieres realizar la busqueda: ", ANIO_MATRICULACION_MINIMO, ANIO_MATRICULACION_MAXIMO, true);
        if (anioMinimo == -1) {
            anioMin = ANIO_MATRICULACION_MINIMO;
        } else {
            anioMin = anioMinimo;
        }
        int kmMaximos = solicitarInt("Introduce los Kilometros máximos del coche para la busqueda: ", ZERO, KM_MAX, true);
        if (kmMaximos == -1) {
            kmMax = ZERO;
        } else {
            kmMax = kmMaximos;
        }
        boolean disponible = solicitarBoolean();

        List<CocheDTO> busquedaTemp = new ArrayList<>();
        for (CocheDTO coche : coches) {
            if (coche.isDisponible() == disponible) {
                busquedaTemp.add(coche);
            }
        }
        if (disponible) {
            vista.mensaje("Vas a buscar en la lista de coches disponibles");
        } else {
            vista.mensaje("Vas a buscar en la lista de coches vendidos");
        }


        for (CocheDTO elemento : busquedaTemp) {
            if (elemento.getMarca().equalsIgnoreCase(marca)) {
                busqueda.add(elemento);
                continue;
            }
            if (elemento.getModelo().equalsIgnoreCase(modelo)) {
                busqueda.add(elemento);
                continue;
            }

            if (elemento.getPrecio() >= precioMin && elemento.getPrecio() <= precioMax) {
                busqueda.add(elemento);
                continue;
            }
            if (elemento.getAnioMatriculacion() > anioMin) {
                busqueda.add(elemento);
                continue;
            }
            if (elemento.getKm() <= kmMax) {
                busqueda.add(elemento);
            }
        }
        if (busqueda.isEmpty()) {
            vista.mensaje("No existen coches con esos parametros.");
            return busqueda;
        }
        return busqueda;
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
            if (coche == null) vista.mensaje("No se encuentra la matrícula del coche.");
        }
        vista.mostrarVendedores(vendedores);
        while (vendedor == null) {
            int idVendedor = solicitarInt("Introduce el id del vendedor: ", ZERO, vendedores.size(), false);
            for (VendedorDTO vendedorVenta : vendedores) {
                if (vendedorVenta.getIdVendedor() == idVendedor) {
                    vendedor = vendedorVenta;
                    break;
                }
            }
            if (vendedor == null)
                vista.mensaje("No se encuentra el vendedor");
        }
        LocalDate fecha = LocalDate.now(Clock.systemUTC());
        double precioVenta = coche.getPrecio();

        vendedor.getCochesVendidos().add(coche);
        cliente.getCochesComprados().add(coche);
        coche.setDisponible(false);

        return new VentaDTO(cliente, coche, vendedor, fecha, precioVenta);
    }

    // Validar datos

    private int solicitarInt(String mensaje, int min, int max, boolean nulo) {
        while (true) {
            String input = vista.solicitarEntrada(mensaje);
            if (nulo) {
                if (input.isBlank()) return -1;
            }
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

    private double solicitarDouble(String mensaje, double min, double max, boolean nulo) {
        while (true) {
            String input = vista.solicitarEntrada(mensaje);
            if (nulo) {
                if (input.isBlank()) return -1;
            }
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

    private boolean solicitarBoolean() {
        String input = "";

        while (true) {
            input = vista.solicitarEntrada("Introduce d|D para coches disponibles o v|V para coches ya vendidos");
            if (input.equalsIgnoreCase("D") || input.equalsIgnoreCase("V")) {
                break;
            } else {
                vista.mensajeError("Error: Introduzca D o V correctamente.");
            }
        }
        return input.equalsIgnoreCase("D");
    }

    private String solicitarTelefono() {
        String telefono = "";
        boolean datoOk = false;
        while (!datoOk) {
            telefono = vista.solicitarEntrada("Introduce el teléfono del cliente: ");
            if (telefono.matches("\\+?[0-9]{1,3}[ -]?\\d{9}") || telefono.matches("\\d{9}")) {
                datoOk = true;
            } else {
                vista.mensaje("Número de teléfono incorrecto:");
            }
        }
        return telefono;
    }

    private String solicitarDni(String persona) {
        String dni;
        while (true) {
            dni = vista.solicitarEntrada("DNI: ");

            if (!validarFormatoDni(dni)) {
                vista.mensajeError("Formato incorrecto. Debe ser 8 números y 1 letra.");
                continue;
            }
            String personaDni = "Cliente";
            if (!personaDni.equalsIgnoreCase(persona)) {
                if (dniExisteEnClientes(dni)) {
                    vista.mensajeError("Ese DNI ya está registrado en clientes.");
                    break;
                }
            } else {
                if (!dniExisteEnVendedores(dni)) {
                    vista.mensajeError("Ese DNI ya está registrado en vendedores.");
                    break;
                }
            }
            break;
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

    private String solicitarMatricula(boolean nulo) {
        String matricula;
        while (true) {
            matricula = vista.solicitarEntrada("Introduce la matrícula del coche sin espacios ni guiones: ").toUpperCase();
            if (nulo) {
                if (matricula == null) break;
            }
            if (!validarFormatoMatricula(matricula)) {
                vista.mensajeError("El formato de matrícula es incorrecto para este país.");
                continue;
            }

            if (matriculaExiste(matricula)) {
                vista.mensajeError("Esa matrícula ya existe en el concesionario.");
                continue;
            }
            break;
        }
        return matricula;
    }

    private boolean validarFormatoMatricula(String matricula) {
        return matricula != null && (matricula.matches("\\d{4}[a-zA-Z]{3}") || matricula.matches("[a-zA-Z]{2}\\d{4}[a-zA-Z]{1,2}"));
    }

    private boolean matriculaExiste(String matricula) {
        for (CocheDTO coche : coches) {
            if (coche.getMatricula().equalsIgnoreCase(matricula)) return true;
        }
        return false;
    }
}