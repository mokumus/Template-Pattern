package com.muhammedokumus;

import java.util.Random;
import java.util.StringJoiner;

public class Individual {
    Double fitness;
    Double x1;
    Double x2;

    public Individual() {
        Random rand = new Random();
        //Set genes randomly for each individual
        x1 = (double) rand.nextInt(6);
        x2 = (double) rand.nextInt(6);
        calcFitness();
    }

    public double calcFitness() {
        fitness = 0.0;
        if(x1 > 5 || x1 < 0)
            fitness += -1000;
        if(x2 > 5 || x2 < 0)
            fitness += -1000;
        if((x1 + x2) > 5 )
            fitness += -1000;

        fitness += func2max();

        return fitness;
    }

    private double func2max(){
        return (20*x1*x2) + (16*x2) - (2*x1*x1) - (x2*x2) - ((x1+x2)*(x1+x2));
    }

    @Override
    public String toString() {
        return new StringJoiner(", ",   "[", "]")
                .add("x1: " + x1 + ", x2: " + x2 +", f: " + fitness)
                .toString();
    }
}
