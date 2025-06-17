import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import utils.CSVReader;
import utils.Maquina;


public class Servicios {
    private LinkedList<Maquina> maquinas;
    private int piezasTotales; // este guarda el 12
    private ArrayList<Maquina> solucionFinal;
    private int estadosGenerados, puestaFuncionamiento, piezasProducidasFinal;

    /*
     * La complejidad temporal del constructor es O(n) donde N es el numero de
     * maquinas en el archivo
     */
    public Servicios(String maquinasPath) {
        CSVReader reader = new CSVReader();
        this.maquinas = new LinkedList<>();
        this.solucionFinal = new ArrayList<>();

        this.piezasTotales = reader.leerArchivo(maquinasPath, maquinas);

    }

    public int getCantPiezas() {
        return this.piezasTotales;
    }

    /* Backtracking:
       Se exploran todas las combinaciones posibles de activaciones de máquinas
       que sumen exactamente la cantidad de piezas deseada.
       Cada nodo del árbol representa un estado donde se ha producido cierta cantidad parcial
       de piezas usando un número dado de activaciones.
       Los estados finales son aquellos en los que se alcanza exactamente la cantidad de piezas objetivo.
       Una solución es válida si produce exactamente la cantidad requerida, y se guarda la que usa menos activaciones.
       Se aplica poda cuando:
       La cantidad de piezas producidas supera la deseada.
       El número de activaciones ya supera el mínimo encontrado hasta ese momento.
     */



    public ArrayList<Maquina> backtracking() {
        ArrayList<Maquina> m = new ArrayList<>();
        solucionBacktracking(0, m);
        puestaFuncionamiento = solucionFinal.size();
        piezasProducidasFinal = 0;
        for (Maquina maq : solucionFinal) {
            piezasProducidasFinal += maq.getCantPiezas();
        }
        return solucionFinal;
    }

    private void solucionBacktracking(int piezasProducidas, ArrayList<Maquina> estadoActual) {
        estadosGenerados++;
        if (piezasProducidas == piezasTotales) {
            if (estadoActual.size() < solucionFinal.size() || solucionFinal.isEmpty()) {
                solucionFinal.clear();
                solucionFinal.addAll(estadoActual);
            }
        } else {
            for (Maquina m : maquinas) {
                int resto = piezasTotales - piezasProducidas;
                if (m.getCantPiezas() <= resto) {
                    estadoActual.add(m);
                    piezasProducidas += m.getCantPiezas();
                    solucionBacktracking(piezasProducidas, estadoActual);
                    piezasProducidas -= m.getCantPiezas();
                    estadoActual.remove(estadoActual.size() - 1);
                }
            }

        }
    }

    /*
    // Greedy:
    // La estrategia que seguimos consiste en ordenar las máquinas
    // desde la que más piezas produce hasta la que menos.
    // Luego, se utiliza la misma máquina tantas veces como sea posible.
    // Si ya no se puede usar, se continúa con la siguiente en la lista.

      Los candidatos son las máquinas disponibles, y se pueden usar repetidamente.
      En cada paso, se selecciona la máquina que produce más piezas sin pasarse del total requerido.
      El objetivo es aproximarse a la cantidad deseada en la menor cantidad de activaciones posible.
      No garantiza encontrar la solución óptima en todos los casos, pero permite una solución rápida y aceptable.
     */



    public ArrayList<Maquina> solucionGreedy() {
        ArrayList<Maquina> solucion = new ArrayList<>();
        int piezas = 0;
        Collections.sort(maquinas);
        
        int i = 0;
        while(i < maquinas.size() && piezas < piezasTotales) {
            Maquina m = maquinas.get(i);
            if (piezasTotales - piezas >= m.getCantPiezas()) {
                solucion.add(m);
                piezas += m.getCantPiezas();
            }
            else{
            i++;
            }
            

        }

        return solucion;
    }

    public int getEstadosGenerados() {
        return estadosGenerados;
    }

    public void setEstadosGenerados(int estadosGenerados) {
        this.estadosGenerados = estadosGenerados;
    }

    public int getPuestaFuncionamiento() {
        return puestaFuncionamiento;
    }

    public void setPuestaFuncionamiento(int puestaFuncionamiento) {
        this.puestaFuncionamiento = puestaFuncionamiento;
    }

    public int getPiezasProducidasFinal() {
        return piezasProducidasFinal;
    }

    public void setPiezasProducidasFinal(int piezasProducidasFinal) {
        this.piezasProducidasFinal = piezasProducidasFinal;
    }

    public Set<Maquina> noRepetidos(ArrayList<Maquina> lista) {
        Set<Maquina> conjuntoSinRepetidos = new HashSet<>(lista);
        return conjuntoSinRepetidos;
        // Devuelve un conjunto sin repeticiones a partir de una lista de máquinas
        // para analizar cuántos tipos distintos de máquinas se usaron.

    }

}
