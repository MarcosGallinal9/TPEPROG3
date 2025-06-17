import utils.Maquina;

import java.util.ArrayList;
import java.util.Set;

public class Main {
    public static void main(String args[]) {
        Servicios servicios = new Servicios("datasets\\Maquinas.csv");

         ArrayList<Maquina> backtracking = servicios.backtracking();
         ArrayList<Maquina> greedy = servicios.solucionGreedy();
         Set<Maquina> noRepetidos = servicios.noRepetidos(greedy);


        System.out.println("=== BACKTRACKING ===");
        System.out.println("Cantidad total de piezas a producir: " + servicios.getCantPiezas());
        System.out.println("Secuencia de máquinas:");
        for (Maquina maq : backtracking) {
            System.out.println(maq.getNombre());
        }
        System.out.println("Piezas producidas: " + servicios.getPiezasProducidasFinal());
        System.out.println("Puestas en funcionamiento: " + servicios.getPuestaFuncionamiento());
        System.out.println("Estados generados: " + servicios.getEstadosGenerados());

        System.out.println("\n=== GREEDY ===");
        System.out.println("Secuencia de máquinas:");
        for (Maquina maq : greedy) {
            System.out.println(maq);
        }
        System.out.println("Piezas producidas: " + servicios.getCantPiezas());
        System.out.println("Puestas en funcionamiento: " + greedy.size());

        System.out.println("\nMáquinas sin repetidos en solución greedy:");
        for (Maquina maq : noRepetidos) {
            System.out.println(maq);
        }
    }

}
