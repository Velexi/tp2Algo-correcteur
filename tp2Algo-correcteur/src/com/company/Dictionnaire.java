package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;



public class  Dictionnaire {
    public Hashtable<ArrayList<String>, ArrayList<String>> getDictionnaire() {
        return dictionnaire;
    }

    private Hashtable<ArrayList<String>, ArrayList<String>> dictionnaire = new Hashtable<>();

    public Dictionnaire(String path) throws IOException {
        initializeDico(path);
    }

    public void initializeDico(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));

        String line;
        while (reader.ready()) {
            line = reader.readLine();
            char[] arr = bubbleSort(line);
            ArrayList<String> e = new ArrayList<>();
            e.add(Arrays.toString(arr));
            addOnDico(line, e);
        }
    }

    private void addOnDico(String line, ArrayList<String> listDeMotsExistant) {
        if(dictionnaire.containsKey(listDeMotsExistant)){
            dictionnaire.get(listDeMotsExistant).add(line);
        }else{
            ArrayList<String> nouveauxMot = new ArrayList<>();
            nouveauxMot.add(line);
            dictionnaire.put(listDeMotsExistant,nouveauxMot);
        }
    }

    private static char[] bubbleSort(String line) {
        char[] arr = line.toCharArray();
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // swap arr[j+1] and arr[i]
                    char temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

}
