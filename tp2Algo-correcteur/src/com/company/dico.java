package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class dico {
    Map<Integer, String> dictionnary = new HashMap<>();


    public dico(String path) throws IOException {
        initializeDico(path);
    }

    private void initializeDico(String path) throws IOException {
        Parser.parse(dictionnary, path);
    }

    public boolean contains(String mot){
        return dictionnary.containsValue(mot);
    }

    public Map<Integer, String> getDictionnary() {
        return dictionnary;
    }

    private static class Parser {


        static void parse(Map<Integer, String> dico, String path) throws IOException {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            int i = 1;
            String ligne;
            while (reader.ready()) {
                ligne = reader.readLine();
                addOnDico(dico, ligne,i);
                i++;
            }
        }

        private static void addOnDico(Map<Integer, String> dico, String line, int key) {

            dico.put(key, line);
        }

    }

    public static void main(String args[]) throws IOException {
        dico d = new dico("tp2Algo-correcteur/src/com/company/dico.txt");
        long startTime = System.nanoTime();
        System.out.println(d.contains("util"));
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.println(elapsedTime/100000);




    }
}
