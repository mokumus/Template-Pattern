package com.muhammedokumus;

import java.util.ArrayList;
import java.util.StringJoiner;

/**
 * Population class that hold all the individuals to represent a gene pool
 */
public class Population {
    ArrayList<Individual> individuals = new ArrayList<>();
    int size;

    /**
     * Create a population of random individuals of given size
     * @param size population size
     */
    public Population(int size) {
        for (int i = 0; i < size; i++) {
            individuals.add(new Individual());
        }
        this.size = size;
    }

    /**
     * @return fittest individual in the population
     */
    public Individual getFittest(){
        Double maxFit = Double.MIN_VALUE;
        int maxFitIndex = 0;
        for (int i = 0; i < size; i++) {
            if (maxFit <= individuals.get(i).fitness) {
                maxFit = individuals.get(i).fitness;
                maxFitIndex = i;
            }
        }
        return individuals.get(maxFitIndex);
    }

    /**
     * @return second fittest individual in the population
     */
    public Individual getSecondFittest() {
        int maxFit1 = 0;
        int maxFit2 = 0;
        for (int i = 0; i < size; i++) {
            if (individuals.get(i).fitness > individuals.get(maxFit1).fitness) {
                maxFit2 = maxFit1;
                maxFit1 = i;
            }
            else if (individuals.get(i).fitness > individuals.get(maxFit2).fitness) {
                maxFit2 = i;
            }
        }
        return individuals.get(maxFit2);
    }

    /**
     * @return index of the least fit individual in the population
     */
    public int getLeastFittestIndex() {
        Double minFitVal = Double.MAX_VALUE;
        int minFitIndex = 0;
        for (int i = 0; i < size; i++) {
            if (minFitVal >= individuals.get(i).fitness) {
                minFitVal = individuals.get(i).fitness;
                minFitIndex = i;
            }
        }
        return minFitIndex;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ")
                .add("Population: " + individuals)
                .toString();
    }
}
