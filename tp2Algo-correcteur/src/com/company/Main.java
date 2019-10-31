package com.company;

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
        Dictionnaire d = new Dictionnaire("tp2Algo-correcteur/src/com/company/dico.txt");
        Set<List<String>> set = d.getDictionnaire().keySet();
        /*int lignes = 0;
        for (List<String> quelquechose : set) {
            for (String string : d.getDictionnaire().get(quelquechose)) {
                lignes++;
                System.out.println(string);
                System.out.println(lignes);
            }
        }*/

        String word = "renomé";
        if (d.contains(word)) {
            System.out.println("Le mot "+word+" est bien orthographié");
            System.exit(-1);
        }
        else{
            System.out.println("le mot " + word+" est mal orthographié choisissez parmis ces mots :");
            Trigramme t = new Trigramme(word);
            List<String> tList = t.getTrigrammeList("<"+word+">");
            String firstT = tList.get(0);
            List<String> motsTrig = new ArrayList<>();
            DictionnaireTrigramme td = new DictionnaireTrigramme(firstT);
            motsTrig.addAll(td.getTD().get(tList.get(0)));
            for(int i = 1; i<tList.size(); i++){
                td.appendTrigDic(tList.get(i));
                motsTrig.addAll(td.getTD().get(tList.get(i)));
            }
            List<String> wclose = new ArrayList<>();
            for(String string : motsTrig){
                if (occurenceInList(motsTrig,string)>=3){
                    if(!wclose.contains(string)) wclose.add(string);
                }
            }

            Map<Integer, List<String>> motsfinaux = new HashMap<>();
            for(String string : wclose){
                int dist = Levenstein.computeDistance(string,word);
                if(!motsfinaux.containsKey(dist)){
                    List<String> list = new ArrayList<>();
                    list.add(string);
                    motsfinaux.put(dist,list);
                } else {
                    List<String> list = new ArrayList<>();
                    list.add(string);
                    for (String x :list)
                        if(!motsfinaux.get(dist).contains(x)) motsfinaux.get(dist).add(x);
                }
            }
            Set<Integer> clefs = motsfinaux.keySet();
            List<String> spellchecked = new LinkedList<>();
            for(Integer integer: clefs){
              spellchecked.addAll(motsfinaux.get(integer));
            }
            for(int cpt = 0; cpt <5; cpt++){
                System.out.print(spellchecked.get(cpt)+" ");
            }


        }







    }
    }

