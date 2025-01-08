package io.juanqui.creational;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 * ! Factory Method:
 * El patrón Factory Method permite crear objetos sin especificar
 * la clase exacta del objeto que se creará.
 * <p>
 * En lugar de eso, delegamos la creación de objetos a subclases o métodos
 * que encapsulan esta lógica.
 * <p>
 * * Es útil cuando una clase no puede anticipar la clase
 * * de objetos que debe crear.
 * <p>
 * https://refactoring.guru/es/design-patterns/factory-method
 */
public class Factory {

    /**
     * !Descripción:
     * 1.	Completen las clases SalesReport e InventoryReport para implementar
     * la interfaz Report, generando el contenido de cada reporte en el método generate.
     * <p>
     * 2.	Implementen las clases SalesReportFactory e InventoryReportFactory
     * para crear instancias de SalesReport y InventoryReport, respectivamente.
     * <p>
     * 3.	Prueben el programa generando diferentes tipos de reportes usando
     * el prompt para seleccionar el tipo de reporte.
     */


    // 1. Definir la interfaz Report
    interface Report {
        void generate();
    }

// 2. Clases concretas de Reportes
// Implementar SalesReport e InventoryReport

    static class SalesReport implements Report {
        // TODO: implementar el método e imprimir en consola:
        // 'Generando reporte de ventas...'
        @Override
        public void generate() {
            System.out.println("Generando reporte de ventas...");
        }
    }

    static class InventoryReport implements Report {
        @Override
        public void generate() {
            // TODO: implementar el método e imprimir en consola:
            // 'Generando reporte de inventario...'
            System.out.println("Generando reporte de inventario...");
        }
    }

// 3. Clase Base ReportFactory con el Método Factory

    abstract static class ReportFactory {
        abstract Report createReport();

        void generateReport() {
            Report report = this.createReport();
            report.generate();
        }
    }

// 4. Clases Concretas de Fábricas de Reportes

    static class SalesReportFactory extends ReportFactory {
        Report createReport() {
            return new SalesReport();
        }
    }

    static class InventoryReportFactory extends ReportFactory {

        @Override
        Report createReport() {
            return new InventoryReport();
        }
    }

    public static void main(String[] args) {
        // Map factories to report types for scalability
        Map<String, ReportFactory> factoryMap = new HashMap<>();
        factoryMap.put("ventas", new SalesReportFactory());
        factoryMap.put("inventario", new InventoryReportFactory());

        System.out.println("¿Qué tipo de reporte desea generar? (ventas/inventario)");
        Scanner scanner = new Scanner(System.in);
        String type = scanner.nextLine().toLowerCase();

        // Validate input and generate report
        ReportFactory reportFactory = factoryMap.get(type);
        if (reportFactory == null) {
            System.out.println("Error: Tipo de reporte inválido. Intente con 'ventas' o 'inventario'.");
        } else {
            reportFactory.generateReport();
        }
    }


}

