package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        Dictionnaire d = new Dictionnaire("tp2Algo-correcteur/src/com/company/dico.txt");
        Set<List<String>> set = d.getDictionnaire().keySet();
        int lignes = 0;
        for (List<String> quelquechose : set) {
            for (String string : d.getDictionnaire().get(quelquechose)) {
                lignes++;
                System.out.println(string);
                System.out.println(lignes);
            }
        }

        long starTime = System.nanoTime();
        System.out.println(d.contains("anarquer"));
        long endTime = System.nanoTime();

        long elapsedTime = endTime - starTime;
        System.out.println("Execution time in milliseconds :" + elapsedTime/100000 +" ms");
        System.out.println();

        System.out.println("=========Test des trigrammes===================");
        System.out.println();


        String mot = "Allier";
        String w = "Alleur";
        Trigramme t = new Trigramme(mot);
        t.printTrigramme(mot);

        t.createL(mot,w);
        List<String> test = t.getL();
        for(String k : test){
            System.out.println(k);
        }
        System.out.println(t.occurenceInList(test,w));
    }
    }

