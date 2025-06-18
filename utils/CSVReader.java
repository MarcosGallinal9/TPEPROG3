package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class CSVReader {
    public CSVReader(){

    }
    private ArrayList<String[]> readContent(String path) {
        ArrayList<String[]> lines = new ArrayList<String[]>();

        File file = new File(path);
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                lines.add(line.split(";"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (bufferedReader != null)
                try {
                    bufferedReader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
        }

        return lines;
    }
    public int leerArchivo(String path, LinkedList<Maquina> maquinas) {
        ArrayList<String[]> lines = this.readContent(path);
        int cantidadMaxima = 0;

        for (int i = 0; i < lines.size(); i++) {
            String[] line = lines.get(i);

            if (i == 0 && line.length == 1) {
                cantidadMaxima = Integer.parseInt(line[0].trim());
            } else if (line.length >= 2) {
                String nombre = line[0].trim();
                int cantPiezas = Integer.parseInt(line[1].trim());

                if (cantPiezas > 0) {
                    maquinas.add(new Maquina(nombre, cantPiezas));
                } else {
                    System.out.println("Maquina " + nombre + " descartada porque produce " + cantPiezas + " piezas (valor invalido).");
                }
            }
        }

        return cantidadMaxima;
    }

}
