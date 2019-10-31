package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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


        String word = "util";
        if (d.contains(word)) {
            System.exit(-1);
        }
        else{
            System.out.println("le mot est mal orthographié");
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

            /*for(String string : wclose){
                System.out.println(string);
            }*/
            for(String string : wclose){
                if(Levenstein.computeDistance(string,word)<3) System.out.println(string);

            }

            //System.out.println(occurenceInList(motsTrig,"outile"));
        }







    }
    }

