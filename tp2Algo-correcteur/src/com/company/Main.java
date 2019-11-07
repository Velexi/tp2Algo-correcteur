package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        dico d = new dico("tp2Algo-correcteur/src/com/company/dico.txt");

        BufferedReader r = new BufferedReader(new FileReader("tp2Algo-correcteur/src/com/company/fautes.txt"));
        String motFaux;
        while(r.ready()){
            motFaux = r.readLine();
            Trigramme t = new Trigramme(motFaux);
            ArrayList<String> tList = t.getTrigramme();
            List<String> motsTrig = new ArrayList<>();
            for (String trigramme : tList) {
                if (d.dictionnary.get(trigramme) == null) continue;
                motsTrig.addAll(d.dictionnary.get(trigramme));
            }

            HashMap<String, Integer> nb_occ = new HashMap<>();
            for (String string : motsTrig) {
                if (!nb_occ.containsKey(string)) {
                    nb_occ.put(string, 1);
                } else {
                    nb_occ.replace(string, nb_occ.get(string) + 1);
                }
            }

            Map<Integer, ArrayList<String>> motsFinaux = new HashMap<>();
            Set<String> keyNbOcc = nb_occ.keySet();

            for (String mot : keyNbOcc) {
                if (nb_occ.get(mot) >= 3 ) {
                    int dist = Levenstein.computeDistance(mot, motFaux);
                    if (!motsFinaux.containsKey(dist)) {
                        ArrayList<String> list = new ArrayList<>();
                        list.add(mot);
                        motsFinaux.put(dist, list);
                    } else {
                        ArrayList<String> list = new ArrayList<>();
                        list.add(mot);
                        for (String x : list)
                            if (!motsFinaux.get(dist).contains(x)) motsFinaux.get(dist).add(x);
                    }
                }
            }


            Set<Integer> clefs = motsFinaux.keySet();
            List<String> spellchecked = new LinkedList<>();
            for (Integer integer : clefs) {
                spellchecked.addAll(motsFinaux.get(integer));
            }
            System.out.println("Mot Faux : "+motFaux);
            for (int cpt = 0; cpt < 5; cpt++) {
                if(spellchecked.size() <=  cpt) break;
                System.out.println(spellchecked.get(cpt) + " ");
            }
            System.out.println("*******************************************");

        }


        long endTime = System.currentTimeMillis();
        long elapsedtime = endTime - startTime;
        System.out.println("Time Total : " + elapsedtime + " ms");

    }
}


