package com.muhammedokumus;

public class Main {

    public static void main(String[] args) {
        Population pool = new Population(100);
        System.out.println(pool.toString());
        System.out.println("Initial Fittest: " + pool.getFittest());
        System.out.println("Initial Worst: " + pool.getLeastFittest());
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



    }
}
