package com.company;

import java.util.Arrays;

public class Levenstein {
    public static int computeDistance(String x, String y) {
        int[][] distance = new int[x.length() + 1][y.length() + 1];

        for(int i=0;i<=x.length();i++){
            for(int j=0; j<=y.length();j++){

                if(i==0) {distance[i][j] = j;} //remplir la premiere ligne
                else if(j==0){ distance[i][j] = i;} //remplir la premiere colonne
                else { //remplir le reste de la matrice avec l'equation de levenstein
                    distance[i][j] = min(distance[i-1][j-1]+costOfSubstitution(x.charAt(i-1),y.charAt(j-1)),distance[i-1][j]+1,distance[i][j-1]+1);
                }

            }
        }

        return distance[x.length()][y.length()];
    }

    public static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    public static int min(int... nombres) {
        return Arrays.stream(nombres).min().orElse(Integer.MAX_VALUE);
    }

    public static void main(String[] args) {
        long starTime = System.nanoTime();
        System.out.println(computeDistance("logarytmique","algorithmique"));
        long endTime = System.nanoTime();

        long elapsedTime = endTime - starTime;
        System.out.println("Execution time in milliseconds :" + elapsedTime/100000 +" ms");
    }
}

