package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

public class DictionnaireTrigramme {
    private Hashtable<String, ArrayList<String>> TD = new Hashtable<>();

    DictionnaireTrigramme(String trigramme, dico d) throws IOException {
        createTD(trigramme, d);
    }

    public void appendTrigDic(String trigramme, dico d) throws IOException {
        createTD(trigramme, d);
    }

    public void createTD(String trigramme, dico d){
        Set<Integer> set = d.getDictionnary().keySet();
        for (Integer i : set) {
            String mot = d.getDictionnary().get(i);
            Trigramme t = new Trigramme(mot);
            if (t.getTrigramme().contains(trigramme)) {
                if (!TD.containsKey(trigramme)) {
                    ArrayList<String> mots = new ArrayList<>();
                    mots.add(mot);
                    TD.put(trigramme, mots);
                } else {
                    ArrayList<String> mots = new ArrayList<>();
                    mots.add(mot);
                    TD.get(trigramme).addAll(mots);
                }
            }
        }
    }


    public Hashtable<String, ArrayList<String>> getTD() {
        return TD;
    }

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        dico d = new dico("tp2Algo-correcteur/src/com/company/dico.txt");
        DictionnaireTrigramme td = new DictionnaireTrigramme("acc",d);
        List<String> mots = td.getTD().get("acc");
        for(String mot : mots){
            System.out.println(mot);
        }
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println(elapsedTime);
    }
}
