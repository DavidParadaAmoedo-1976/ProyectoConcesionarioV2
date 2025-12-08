package modelo;

public enum OpcionesMenuEnum {
    SALIR("Salir del programa."),
    ANHADIR_COCHE("Añadir un nuevo coche."),
    MOSTRAR_COCHES("Mostrar Los coches disponibles."),
    BUSCAR_COCHE("Buscar un coche."),
    ANHADIR_CLIENTE("Añadir un cliente."),
    MOSTRAR_CLIENTES("Mostrar los clientes registrados."),
    ANHADIR_VENTAS("Añadir una nueva venta."),
    MOSTRAR_VENTAS("Mostrar las ventas realizadas."),
    ANHADIR_VENDEDOR("Crear un nuevo vendedor."),
    MOSTRAR_VENDEDORES("Mostrar los vendedores."),
    MOSTRAR_ESTADISTICAS("Mostrar las estadisticas de un vendedor."),
    MOSTRAR_COCHES_ORDENADOS("Mostrar los coches ordenados por precio marca y año de matriculación."),
    MOSTRAR_TODOS_LOS_COCHES("Mostrar todos los coches que han pasado por el concesionario.");

    private final String textoOpciones;

    OpcionesMenuEnum(String textoOpciones) {
        this.textoOpciones = textoOpciones;
    }

    public String getTextoOpciones() {
        return textoOpciones;
    }
}