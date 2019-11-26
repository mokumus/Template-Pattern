package com.muhammedokumus;

import java.util.Random;

import static java.lang.Math.abs;

public class RankGenetic extends GeneticFramework {
    public RankGenetic(Population p) {
        super(p);
    }


    public Individual chooseOnFitness(Population p) {
        Random rand = new Random();
        Double totalFitness = 0.0;
        for (Individual i : p.getIndividuals())
            totalFitness += i.fitness;
        Double r = Math.random() * totalFitness;
        Double countFitness = 0.0;
        for (Individual i : p.getIndividuals()) {
            countFitness += i.fitness;
            if (countFitness >= r)
                return i;
        }
        return population.getIndividuals().get(rand.nextInt(population.size));
    }

    @Override
    void selection() {
        Individual i1 = chooseOnFitness(population);
        Individual i2 = chooseOnFitness(population);

        crossover(i1, i2);

        i1.calcFitness();
        i2.calcFitness();
    }

    /**
     * 2 point cross-over
     * @param i1 individual 1
     * @param i2 individual 2
     */
    @Override
    void crossover(Individual i1, Individual i2) {
        Random rand = new Random();

        targetedMutation(i1);
        targetedMutation(i2);

        StringBuilder sb1 = new StringBuilder(String.format("%.2f", abs(i1.x1))).append(String.format("%.2f", abs(i1.x2)));
        StringBuilder sb2 = new StringBuilder(String.format("%.2f", abs(i2.x1))).append(String.format("%.2f", abs(i2.x2)));

        String tmp1 = sb1.substring(2,4);
        String tmp2 = sb2.substring(6,8);



        sb1.replace(2,4, tmp2);
        sb2.replace(6,8, tmp1);

        i1.x1 = Double.parseDouble(sb1.substring(2,4));
        i1.x2 = Double.parseDouble(sb1.substring(6,8));

        i2.x1 = Double.parseDouble(sb2.substring(2,4));
        i2.x2 = Double.parseDouble(sb2.substring(6,8));

        i1.calcFitness();
        i2.calcFitness();
    }


}
