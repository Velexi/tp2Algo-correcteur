package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        Dictionnaire d = new Dictionnaire("tp2Algo-correcteur/src/com/company/dico.txt");
        Set<List<String>> set = Dictionnaire.getDictionnaire().keySet();
        int lignes = 0;
        for (List<String> quelquechose : set) {
            for (String string : Dictionnaire.getDictionnaire().get(quelquechose)) {
                lignes++;
                System.out.println(string);
                System.out.println(lignes);
            }
        }
    }
}
