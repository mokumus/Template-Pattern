package com.muhammedokumus;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Population pool = new Population(10);
        System.out.println(pool.toString());
        System.out.println("Initial Fittest: " + pool.getFittest());
        System.out.println("Initial Worst: " + pool.getLeastFittest());

        Random rand = new Random();
        GeneticFramework geneticFramework = new RankGenetic(pool);
        geneticFramework.selection();








        /*
        GeneticFramework geneticFramework = new RouletteGenetic(pool);
        geneticFramework.mutation();
        geneticFramework.selection();





        for(int i = 0; i < 100; i++){
            geneticFramework.selection();
            geneticFramework.selection();
            geneticFramework.selection();
            geneticFramework.selection();

            geneticFramework.mutation();
            geneticFramework.mutation();
            geneticFramework.mutation();
            geneticFramework.mutation();
            geneticFramework.mutation();

            System.out.println("----------------------------------------------");
            System.out.println("GEN" + i +" Fittest: " + pool.getFittest());
            System.out.println("GEN" + i +" Worst: " + pool.getLeastFittest());
        }
        */


    }
}
