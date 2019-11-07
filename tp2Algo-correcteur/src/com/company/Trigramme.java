package com.company;

import java.util.ArrayList;

public class Trigramme {
    private ArrayList<String> trigramme = new ArrayList<>();
    private String mot;


    Trigramme(String mot){
        this.mot = mot;
        createTrigramme();
    }
    private void createTrigramme(){
        String tmot = "<"+mot+">";

        for(int i = 0; i < tmot.length() - 2; i++ ){
            trigramme.add(tmot.substring(i,i+3));
        }
    }

    ArrayList<String> getTrigramme() {
        return trigramme;
    }

    public String getMot() {
        return mot;
    }
}
