package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
	    Dictionnaire dico = new Dictionnaire("/amuhome/h17024156/Bureau/tp2Algo-correcteur/src/com/company/dico.txt");
        Set<ArrayList<String>> set = dico.getDictionnaire().keySet();
        int lignes =0;
        for (ArrayList<String> quelquechose : set){
                for (String string : dico.getDictionnaire().get(quelquechose)){
                    lignes++;
                    System.out.println(string);
                    System.out.println(lignes);
            }
        }
    }
}
