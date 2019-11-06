package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static int occurenceInList(List<String> mots, String mot){
        return Collections.frequency(mots,mot);
    }
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        dico d = new dico("tp2Algo-correcteur/src/com/company/dico.txt");

            String word = "disfonctionnement";
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
                long startMotTrig = System.currentTimeMillis();
                for (int i = 1; i < tList.size(); i++) {
                    td.appendTrigDic(tList.get(i), d);
                    if(td.getTD().get(tList.get(i)) == null) continue;
                    motsTrig.addAll(td.getTD().get(tList.get(i)));
                }
                long endMotTrig = System.currentTimeMillis();

                HashMap<String,Integer> nb_occ = new HashMap<>();
                for (String string : motsTrig) {
                    if(!nb_occ.containsKey(string)){
                        nb_occ.put(string,1);
                    }else{
                        nb_occ.replace(string,nb_occ.get(string)+1);
                    }
                }


                Map<Integer, ArrayList<String>> motsFinaux = new HashMap<>();
                Set<String> keyNbOcc = nb_occ.keySet();
                for(String mot : keyNbOcc){
                    if(nb_occ.get(mot) >= 3){
                        int dist = Levenstein.computeDistance(mot, word);
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
                for (int cpt = 0; cpt < 5; cpt++) {
                    System.out.println(spellchecked.get(cpt) + " ");
                }

                System.out.println("Time of trig "+(endMotTrig -startMotTrig  )+"  ms");
            }

            long endTime = System.currentTimeMillis();
            long elapsedtime = endTime - startTime;
            System.out.println("Time Total : "+elapsedtime + " ms");

        }
    }


