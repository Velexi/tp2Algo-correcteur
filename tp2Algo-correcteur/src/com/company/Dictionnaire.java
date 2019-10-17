package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;


class Dictionnaire {

    private static Hashtable<List<String>, List<String>> dictionnaire = new Hashtable<>();

    Dictionnaire(String path) throws IOException {
        initializeDico(path);
    }

    private void initializeDico(String path) throws IOException {
        Parser.parse(dictionnaire, path);
    }

    static Hashtable<List<String>, List<String>> getDictionnaire() {
        return dictionnaire;
    }

    public static boolean contains(String mot){
        char[] motArranger = Parser.bubbleSort(mot);
        return dictionnaire.contains(motArranger);
    }


    private static class Parser {


        static void parse(Hashtable<List<String>, List<String>> dico, String path) throws IOException {
            BufferedReader reader = new BufferedReader(new FileReader(path));

            String ligne;
            while (reader.ready()) {
                ligne = reader.readLine();
                char[] array = bubbleSort(ligne);
                List<String> listDeCaractereArrangeParOrdreAlphabetique = new ArrayList<>();
                listDeCaractereArrangeParOrdreAlphabetique.add(Arrays.toString(array));
                addOnDico(dico, ligne, listDeCaractereArrangeParOrdreAlphabetique);
            }
        }

        private static void addOnDico(Hashtable<List<String>, List<String>> dico, String line, List<String> listDeMotsExistant) {
            if (!dico.containsKey(listDeMotsExistant)) {
                List<String> nouveauxMot = new ArrayList<>();
                dico.put(listDeMotsExistant, nouveauxMot);
            }
            dico.get(listDeMotsExistant).add(line);
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