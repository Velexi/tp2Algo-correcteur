package com.company;

import java.util.*;

public class Trigramme {
    private Hashtable<String, List<String>> trigramme = new Hashtable<>();
    private List<String> L = new ArrayList<>() ;


    public Trigramme(String mot){

        createTrigramme(mot);
    }
    public void createTrigramme(String mot){
        mot = "<"+mot+">";
        if(trigramme.containsKey(mot)) return;
        List<String> aaa = new ArrayList<>();
        for(int i = 0; i < mot.length() - 2; i++ ){
            aaa.add(mot.substring(i,i+3));
        }
        trigramme.put(mot,aaa);
    }

    public void printTrigramme(String mot){
        mot = "<"+mot+">";
       List<String> t = getTrigrammeList(mot);
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
        List<String> list = u.getTrigrammeList(w);
        List<String> trigrammes = this.getTrigrammeList(mot);
        for(String trig : list){
            if(trigrammes.contains(trig)){
                L.add(w.replace(">","").replace("<",""));
                break;
            }
            else continue;
        }
    }

    public int occurenceInList(List<String> mots, String mot){
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

    public List<String> getTrigrammeList(String mot){
        return trigramme.get(mot);
    }

    public List<String> getL() {
        return L;
    }


}
