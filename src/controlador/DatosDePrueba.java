package controlador;

import modelo.ClienteDTO;
import modelo.CocheDTO;
import modelo.VendedorDTO;
import modelo.VentaDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class DatosDePrueba {
    public List<CocheDTO> cochesDePrueba() {
        List<CocheDTO> cochesDePrueba = new ArrayList<>();
        cochesDePrueba.add(new CocheDTO("Ford", "Fiesta", "0123KGN", 12500, 2018, 83000));
        cochesDePrueba.add(new CocheDTO("Volkswagen", "Golf", "9012KSP", 12500, 2018, 83000));
        cochesDePrueba.add(new CocheDTO("Toyota", "Yaris", "2345JBY", 17000, 2015, 38000));
        cochesDePrueba.add(new CocheDTO("BMW", "120d", "3456JVC", 13500, 2017, 78000));
        cochesDePrueba.add(new CocheDTO("Seat", "Ibiza", "5678JVC", 13500, 2017, 80000));
        cochesDePrueba.add(new CocheDTO("Renault", "Clio", "6789JKZ", 15000, 2016, 62000));
        cochesDePrueba.add(new CocheDTO("Mazda", "3", "9112KSP", 11000, 2018, 95000));
        cochesDePrueba.add(new CocheDTO("Toyota", "Corolla", "1234JBY", 9000, 2015, 110000));
        cochesDePrueba.add(new CocheDTO("Audi", "A3", "5678LDR", 15000, 2019, 65000));
        cochesDePrueba.add(new CocheDTO("Volkswagen", "Polo", "1012JVC", 14200, 2017, 58000));
        cochesDePrueba.add(new CocheDTO("Seat", "León", "4567KSR", 16000, 2019, 54000));
        cochesDePrueba.add(new CocheDTO("Ford", "Focus", "0123JVC", 11500, 2017, 90000));
        cochesDePrueba.add(new CocheDTO("Toyota", "Corolla", "2385JVZ", 9800, 2016, 95000));
        cochesDePrueba.add(new CocheDTO("Peugeot", "308", "6789LDS", 18500, 2020, 41000));
        cochesDePrueba.add(new CocheDTO("Renault", "Megane", "7890JKZ", 17800, 2016, 43000));
        cochesDePrueba.add(new CocheDTO("Volkswagen", "Golf", "3901JVZ", 12000, 2016, 85000));
        cochesDePrueba.add(new CocheDTO("Citroën", "C4", "0123JBY", 8500, 2015, 120000));
        cochesDePrueba.add(new CocheDTO("Toyota", "Yaris", "3456KSR", 16500, 2019, 40000));
        cochesDePrueba.add(new CocheDTO("Ford", "Fiesta", "1234JBY", 12800, 2015, 81000));
        cochesDePrueba.add(new CocheDTO("Skoda", "Octavia", "2345KSP", 16500, 2018, 56000));
        cochesDePrueba.add(new CocheDTO("Renault", "Clio", "7890LDQ", 14800, 2019, 60000));
        cochesDePrueba.add(new CocheDTO("Seat", "Ibiza", "4567KGN", 13000, 2018, 82000));
        cochesDePrueba.add(new CocheDTO("Mercedes", "A180", "4567KSP", 14000, 2018, 72000));
        cochesDePrueba.add(new CocheDTO("Toyota", "Corolla", "7456KSP", 10200, 2018, 87000));
        cochesDePrueba.add(new CocheDTO("Volkswagen", "Polo", "8901KSP", 14000, 2018, 60000));
        cochesDePrueba.add(new CocheDTO("Ford", "Focus", "1234LMC", 11800, 2020, 88000));
        cochesDePrueba.add(new CocheDTO("Honda", "Civic", "2345JKZ", 10500, 2016, 98000));
        cochesDePrueba.add(new CocheDTO("Seat", "León", "5678LDS", 15800, 2020, 56000));
        cochesDePrueba.add(new CocheDTO("Opel", "Astra", "1234JVC", 12500, 2017, 88000));
        cochesDePrueba.add(new CocheDTO("Renault", "Megane", "4789LDS", 17500, 2020, 45000));
        cochesDePrueba.add(new CocheDTO("Nissan", "Qashqai", "1901JVZ", 18000, 2016, 50000));
        cochesDePrueba.add(new CocheDTO("Fiat", "Tipo", "3456LMC", 17500, 2020, 42000));

        // Lista de coches vendidos

        CocheDTO coche1 = new CocheDTO("Toyota", "Corolla", "4567JKZ", 9500, 2016, 97000);
        coche1.setDisponible(false);
        cochesDePrueba.add(coche1);

        CocheDTO coche2 = new CocheDTO("Seat", "Ibiza", "6789KSP", 12000, 2018, 75000);
        coche2.setDisponible(false);
        cochesDePrueba.add(coche2);

        CocheDTO coche3 = new CocheDTO("Renault", "Clio", "7890LDR", 14000, 2019, 61000);
        coche3.setDisponible(false);
        cochesDePrueba.add(coche3);

        CocheDTO coche4 = new CocheDTO("Volkswagen", "Golf", "8901JVZ", 12500, 2016, 88000);
        coche4.setDisponible(false);
        cochesDePrueba.add(coche4);

        CocheDTO coche5 = new CocheDTO("Ford", "Focus", "9012JVC", 11000, 2017, 93000);
        coche5.setDisponible(false);
        cochesDePrueba.add(coche5);

        CocheDTO coche6 = new CocheDTO("Audi", "A3", "0123KSR", 15500, 2019, 58000);
        coche6.setDisponible(false);
        cochesDePrueba.add(coche6);

        CocheDTO coche7 = new CocheDTO("BMW", "320d", "1234LDS", 18500, 2020, 45000);
        coche7.setDisponible(false);
        cochesDePrueba.add(coche7);

        CocheDTO coche8 = new CocheDTO("Mercedes", "C200", "2345JVZ", 20000, 2016, 70000);
        coche8.setDisponible(false);
        cochesDePrueba.add(coche8);

        CocheDTO coche9 = new CocheDTO("Peugeot", "208", "3456KSP", 13500, 2018, 67000);
        coche9.setDisponible(false);
        cochesDePrueba.add(coche9);

        CocheDTO coche10 = new CocheDTO("Fiat", "Tipo", "4567LMC", 16000, 2020, 42000);
        coche10.setDisponible(false);
        cochesDePrueba.add(coche10);



        return cochesDePrueba;
    }

    public List<ClienteDTO> clientesDePrueba() {
        List<ClienteDTO> clientesDePrueba = new ArrayList<>();

        clientesDePrueba.add(new ClienteDTO("12345678A", "Carlos Martínez López", "600123456"));
        clientesDePrueba.add(new ClienteDTO("23456789B", "María Gómez Ruiz", "611987654"));
        clientesDePrueba.add(new ClienteDTO("34567890C", "Javier Ortega Pérez", "622456789"));
        clientesDePrueba.add(new ClienteDTO("45678901D", "Lucía Hernández Soto", "633567890"));
        clientesDePrueba.add(new ClienteDTO("56789012E", "Elena Torres García", "644678901"));
        clientesDePrueba.add(new ClienteDTO("67890123F", "Andrés Fernández Vidal", "655789012"));
        clientesDePrueba.add(new ClienteDTO("78901234G", "Patricia Domínguez Ramos", "666890123"));
        clientesDePrueba.add(new ClienteDTO("89012345H", "Miguel Sánchez Torres", "677901234"));
        clientesDePrueba.add(new ClienteDTO("90123456I", "Laura Pérez Gómez", "688012345"));
        clientesDePrueba.add(new ClienteDTO("01234567J", "David Alonso Castro", "699123456"));

        return clientesDePrueba;
    }

    public List<VendedorDTO> vendedoresDePrueba() {
        List<VendedorDTO> vendedoresDePrueba = new ArrayList<>();
        vendedoresDePrueba.add(new VendedorDTO("Pedro Martinez Iglesias", "11223344A", new ArrayList<>()));
        vendedoresDePrueba.add(new VendedorDTO("Ana López Seoane", "22334455B", new ArrayList<>()));
        return vendedoresDePrueba;
    }

    public List<VentaDTO> ventasDePrueba(
        List<ClienteDTO> clientes,
        List<CocheDTO> coches,
        List<VendedorDTO> vendedores) {

        List<CocheDTO> cochesVendidos = new ArrayList<>();

        for (CocheDTO coche : coches){
            if (!coche.isDisponible()){
                cochesVendidos.add(coche);
            }
        }

        List<VentaDTO> ventasDePrueba = new ArrayList<>();

        ventasDePrueba.add(new VentaDTO(clientes.get(0), cochesVendidos.get(0), vendedores.get(0), LocalDate.of(2025, 1, 10), cochesVendidos.get(0).getPrecio()));
        ventasDePrueba.add(new VentaDTO(clientes.get(1), cochesVendidos.get(1), vendedores.get(1), LocalDate.of(2025, 2, 5), cochesVendidos.get(1).getPrecio()));
        ventasDePrueba.add(new VentaDTO(clientes.get(2), cochesVendidos.get(2), vendedores.get(0), LocalDate.of(2025, 3, 12), cochesVendidos.get(2).getPrecio()));
        ventasDePrueba.add(new VentaDTO(clientes.get(3), cochesVendidos.get(3), vendedores.get(1), LocalDate.of(2025, 4, 20), cochesVendidos.get(3).getPrecio()));
        ventasDePrueba.add(new VentaDTO(clientes.get(4), cochesVendidos.get(4), vendedores.get(0), LocalDate.of(2025, 5, 15), cochesVendidos.get(4).getPrecio()));
        ventasDePrueba.add(new VentaDTO(clientes.get(5), cochesVendidos.get(5), vendedores.get(1), LocalDate.of(2025, 6, 8), cochesVendidos.get(5).getPrecio()));
        ventasDePrueba.add(new VentaDTO(clientes.get(6), cochesVendidos.get(6), vendedores.get(0), LocalDate.of(2025, 7, 25), cochesVendidos.get(6).getPrecio()));
        ventasDePrueba.add(new VentaDTO(clientes.get(7), cochesVendidos.get(7), vendedores.get(1), LocalDate.of(2025, 8, 18), cochesVendidos.get(7).getPrecio()));
        ventasDePrueba.add(new VentaDTO(clientes.get(8), cochesVendidos.get(8), vendedores.get(0), LocalDate.of(2025, 9, 30), cochesVendidos.get(8).getPrecio()));
        ventasDePrueba.add(new VentaDTO(clientes.get(9), cochesVendidos.get(9), vendedores.get(1), LocalDate.of(2025, 10, 22), cochesVendidos.get(9).getPrecio()));

        for (VentaDTO venta : ventasDePrueba) {
            VendedorDTO vendedor = venta.getVendedor();
            vendedor.getCochesVendidos().add(venta.getCoche());

            ClienteDTO cliente = venta.getCliente();
            cliente.getCochesComprados().add(venta.getCoche());
        }
        return ventasDePrueba;
    }



}
