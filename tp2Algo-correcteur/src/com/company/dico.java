package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dico {
    Map<String, List<String>> dictionnary = new HashMap<>();


    dico(String path) throws IOException {
        initializeDico(path);
    }

    private void initializeDico(String path) throws IOException {
        Parser.parse(dictionnary, path);
    }

    public Map<String, List<String>> getDictionnary() {
        return dictionnary;
    }

    private static class Parser {

        static void parse(Map<String, List<String>> dico, String path) throws IOException {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String ligne;
            while (reader.ready()) {
                ligne = reader.readLine();

                for (int i = 0; i < ligne.length() - 2; i++) {
                    String mot = "<" + ligne + ">";
                    String trigramme = mot.substring(i, i + 3);
                    if (!dico.containsKey(trigramme)) {
                        List<String> listDeMotContenantCeTrigramme = new ArrayList<>();
                        dico.put(trigramme, listDeMotContenantCeTrigramme);
                    }
                    dico.get(trigramme).add(ligne);
                }
            }
        }
    }
}
