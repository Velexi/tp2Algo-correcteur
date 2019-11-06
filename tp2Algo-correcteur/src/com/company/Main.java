package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static int occurenceInList(List<String> mots, String mot){
        int occ =0;
        if(!mots.contains(mot)) return 0;
        else {
            for(int i=0; i<mots.size(); i++){
                if (mot.equals(mots.get(i))){
                    occ++;
                }
            }
        }

        return occ;

    }
    public static void main(String[] args) throws IOException {
        long startTime = System.nanoTime();
        dico d = new dico("tp2Algo-correcteur/src/com/company/dico.txt");

            String word = "abcisse";
            if (d.contains(word)) {
                System.out.println("Le mot " + word + " est bien orthographié");
                System.exit(-1);
            } else {
                System.out.println("le mot " + word + " est mal orthographié choisissez parmis ces mots :");
                Trigramme t = new Trigramme(word);
                ArrayList<String> tList = t.getTrigramme();
                String firstT = tList.get(0);
                DictionnaireTrigramme td = new DictionnaireTrigramme(firstT, d);
                ArrayList<String> motsTrig = new ArrayList<>(td.getTD().get(firstT));
                for (int i = 1; i < tList.size(); i++) {
                    td.appendTrigDic(tList.get(i), d);
                    if(td.getTD().get(tList.get(i)) == null) continue;
                    motsTrig.addAll(td.getTD().get(tList.get(i)));
                }
                ArrayList<String> wclose = new ArrayList<>();
                for (String string : motsTrig) {
                    if (occurenceInList(motsTrig, string) >= 3) {
                        if (!wclose.contains(string)) wclose.add(string);
                    }
                }

                Map<Integer, ArrayList<String>> motsfinaux = new HashMap<>();
                for (String string : wclose) {
                    int dist = Levenstein.computeDistance(string, word);
                    if (!motsfinaux.containsKey(dist)) {
                        ArrayList<String> list = new ArrayList<>();
                        list.add(string);
                        motsfinaux.put(dist, list);
                    } else {
                        ArrayList<String> list = new ArrayList<>();
                        list.add(string);
                        for (String x : list)
                            if (!motsfinaux.get(dist).contains(x)) motsfinaux.get(dist).add(x);
                    }
                }
                Set<Integer> clefs = motsfinaux.keySet();
                List<String> spellchecked = new LinkedList<>();
                for (Integer integer : clefs) {
                    spellchecked.addAll(motsfinaux.get(integer));
                }
                for (int cpt = 0; cpt < 5; cpt++) {
                    System.out.print(spellchecked.get(cpt) + " ");
                }


            }

            long endTime = System.nanoTime();
            long elapsedtime = endTime - startTime;
            System.out.println(elapsedtime / 100000 + " ms");


        }
    }


