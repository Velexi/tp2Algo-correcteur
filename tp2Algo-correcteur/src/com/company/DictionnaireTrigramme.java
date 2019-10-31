package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

public class DictionnaireTrigramme {
    private Hashtable<String, List<String>> TD = new Hashtable<>();

    DictionnaireTrigramme(String trigramme) throws IOException {
        createTD(trigramme);
    }

    public void appendTrigDic(String trigramme) throws IOException {
        createTD(trigramme);
    }
    public void createTD(String trigramme) throws IOException {
        Dictionnaire d = new Dictionnaire("tp2Algo-correcteur/src/com/company/dico.txt");
        Set<List<String>> set = d.getDictionnaire().keySet();
        for (List<String> key : set) {
            for (String mot : d.getDictionnaire().get(key)) {
                Trigramme t = new Trigramme(mot);
                List<String> tlist = t.getTrigrammeList("<"+mot+">");
                if (tlist.contains(trigramme)) {
                    if (!TD.containsKey(trigramme)) {
                        List<String> mots = new ArrayList<>();
                        mots.add(mot);
                        TD.put(trigramme, mots);
                    } else {
                        List<String> mots = new ArrayList<>();
                        mots.add(mot);
                        for(String string :mots){
                            if(!TD.get(trigramme).contains(string)) TD.get(trigramme).add(string);
                        }
                    }
                }
            }
        }
    }

    public Hashtable<String, List<String>> getTD() {
        return TD;
    }

    public static void main(String args[]) throws IOException {
        String trig = "acc";
        String trig2 = "<Ad";
        DictionnaireTrigramme td = new DictionnaireTrigramme(trig);
        td.appendTrigDic(trig2);
        List<String> tl = td.getTD().get(trig);
        List<String> tl2 = td.getTD().get(trig2);
        for(String mots : tl){
            System.out.println(mots);
        }
        System.out.println("===================");
        for (String mo : tl2){
            System.out.println(mo);
        }
    }
}