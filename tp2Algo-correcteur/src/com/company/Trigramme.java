package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class Trigramme {
    private Hashtable<String, List<String>> trigramme = new Hashtable<>();

    public void createTrigramme(String mot){
        mot = "<"+mot+">";
        if(trigramme.containsKey(mot)) return;
        List<String> aaa = new ArrayList<>();
        for(int i = 0; i < mot.length() - 2; i++ ){
            aaa.add(mot.substring(i,i+3));
        }
        trigramme.put(mot,aaa);
    }

}
