package com.muhammedokumus;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.StringJoiner;

/**
 * Holds genes that represent solutions for the function to maximize
 */
public class Individual {
    Double fitness;
    Double x1;
    Double x2;
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    /**
     * Creates an individual with 2 random genes that are in range(0,5) double values, 0 and 5 included
     */
    public Individual() {
        Random rand = new Random();
        //Set genes randomly for each individual
        x1 = (double) rand.nextInt(6);
        x2 = (double) rand.nextInt(6);
        calcFitness();
    }

    /**
     * Calculates fitness of the gene in relation to the constraints and function maximize, can be a negative value
     * @return double fitness value
     */
    public double calcFitness() {
        fitness = func2max();

        if(x1 > 5 || x1 < 0)
            fitness /= 10;
        if(x2 > 5 || x2 < 0)
            fitness /= 10;
        if((x1 + x2) > 5 )
            fitness /= 10;

        return fitness;
    }

    private double func2max(){
        return (20*x1*x2) + (16*x2) - (2*x1*x1) - (x2*x2) - ((x1+x2)*(x1+x2));
    }

    @Override
    public String toString() {
        return new StringJoiner(", ",   "[", "]")
                .add("x1: " + df2.format(x1) + ", x2: " + df2.format(x2) +", f: " + df2.format(fitness))
                .toString();
    }
}
