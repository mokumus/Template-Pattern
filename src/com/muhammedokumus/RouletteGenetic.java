package com.muhammedokumus;

import java.util.Random;
import static java.lang.Math.abs;

public class RouletteGenetic extends GeneticFramework {

    public RouletteGenetic(Population p) {
        super(p);
    }

    /**
     * Roulette wheel selection
     */
    @Override
    void selection() {
        Random rand = new Random();
        Double maxFitness = population.getFittest().fitness;
        Double minFitness = population.getLeastFittest().fitness;
        int randomInt1 = rand.nextInt( (int) Math.round(maxFitness) - (int) Math.round(minFitness) ) + (int) Math.round(minFitness);
        int randomInt2 = rand.nextInt( (int) Math.round(maxFitness) - (int) Math.round(minFitness) ) + (int) Math.round(minFitness);
        /*
        System.out.println("SW Point1: " + randomInt1);
        System.out.println("Closest to Point1: " + population.getClosestFitness(randomInt1));
        System.out.println("SW Point2: " + randomInt2);
        System.out.println("Closest to Point2: " + population.getClosestFitness(randomInt2));
        */
        crossover(population.getClosestFitness(randomInt1), population.getClosestFitness(randomInt2));
    }





}
