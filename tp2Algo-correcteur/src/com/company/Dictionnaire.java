package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;


class Dictionnaire {

    private Hashtable<List<String>, List<String>> dictionnaire = new Hashtable<>();

    Dictionnaire(String path) throws IOException {
        initializeDico(path);
    }

    private void initializeDico(String path) throws IOException {
        Parser.parse(dictionnaire, path);
    }

    Hashtable<List<String>, List<String>> getDictionnaire() {
        return dictionnaire;
    }


    private static class Parser {


        static void parse(Hashtable<List<String>, List<String>> dico, String path) throws IOException {
            BufferedReader reader = new BufferedReader(new FileReader(path));

            String line;
            while (reader.ready()) {
                line = reader.readLine();
                char[] arr = bubbleSort(line);
                List<String> e = new ArrayList<>();
                e.add(Arrays.toString(arr));
                addOnDico(dico, line, e);
            }
        }

        private static void addOnDico(Hashtable<List<String>, List<String>> dico, String line, List<String> listDeMotsExistant) {

            if (dico.containsKey(listDeMotsExistant)) {
                dico.get(listDeMotsExistant).add(line);
            } else {
                List<String> nouveauxMot = new ArrayList<>();
                nouveauxMot.add(line);
                dico.put(listDeMotsExistant, nouveauxMot);
            }
        }

        private static char[] bubbleSort(String line) {
            char[] arr = line.toCharArray();
            int n = arr.length;
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (arr[j] > arr[j + 1]) {
                        char temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
            return arr;
        }

    }
}
