package com.muhammedokumus;

import java.util.Random;

/**
 * Implements the GeneticFramework using roulette wheel selection
 */
public class RouletteGenetic extends GeneticFramework {

    /**
     * Defines a framework that will work on the provided population
     * @param p
     */
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
        targetedMutation(population.getLeastFittest());
    }

    /**
     * Recipe for genetic algorithm to execute each generation
     */
    @Override
    void loopRecipe() {
        selection();
        mutation();
        mutation();
        mutation();
    }


}
