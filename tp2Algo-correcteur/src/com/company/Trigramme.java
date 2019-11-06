package com.company;

import java.util.*;

public class Trigramme {
    ArrayList<String> trigramme = new ArrayList<>();
    private List<String> L = new ArrayList<>() ;
    private String mot;


    public Trigramme(String mot){
        this.mot = mot;
        createTrigramme();
    }
    public void createTrigramme(){
        String tmot = "<"+mot+">";

        for(int i = 0; i < tmot.length() - 2; i++ ){
            trigramme.add(tmot.substring(i,i+3));
        }

    }

    public void printTrigramme(){

       ArrayList<String> t = getTrigramme();
       String output = "";
       for(int i=0; i<t.size(); i++){
           output = output + t.get(i)+", ";
       }
        output = output.substring(0,output.length()-2);
        System.out.println(output);
    }

    public  void createL(String mot,String w){
        Trigramme u = new Trigramme(w);
        w="<"+w+">";
        mot="<"+mot+">";
        ArrayList<String> list = u.getTrigramme();
        ArrayList<String> trigrammes = this.getTrigramme();
        for(String trig : list){
            if(trigrammes.contains(trig)){
                L.add(w.replace(">","").replace("<",""));
                break;
            }
            else continue;
        }
    }


    public ArrayList<String> getTrigramme() {
        return trigramme;
    }

    public String getMot() {
        return mot;
    }

    public List<String> getL() {
        return L;
    }


}
