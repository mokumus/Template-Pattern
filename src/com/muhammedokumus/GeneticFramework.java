package com.muhammedokumus;

import java.util.Random;
import static java.lang.Math.abs;

/**
 * Defines a framework for other GA's
 */
public abstract class GeneticFramework {
    Population population;

    /**
     * Defines a framework that will work on the provided population
     * @param p
     */
    public GeneticFramework(Population p){
        this.population = p;
    }

    /**
     * Selection algorithm
     */
    abstract void selection();

    /**
     * Recipe for genetic algorithm to execute each generation
     */
    synchronized void  loopRecipe() {
        Population currentPopulation = population;

        selection();
        mutation();
        mutation();
        mutation();


        if (population.getAverageFitness() < currentPopulation.getAverageFitness()){
            System.out.println("YO CALM DOWN");
            population = currentPopulation;
        }
    }

    /**
     * One point cross-over
     * @param i1 individual 1
     * @param i2 individual 2
     */
    void crossover(Individual i1, Individual i2) {
        Random rand = new Random();

        StringBuilder sb1 = new StringBuilder(String.format("%.2f", abs(i1.x1))).append(String.format("%.2f", abs(i1.x2)));
        StringBuilder sb2 = new StringBuilder(String.format("%.2f", abs(i2.x1))).append(String.format("%.2f", abs(i2.x2)));

        String tmp1 = sb1.substring(0,4);
        String tmp2 = sb2.substring(0,4);

        sb1.replace(0,4, tmp2);
        sb2.replace(0,4, tmp1);

        i1.x1 = Double.parseDouble(sb1.substring(0,4));
        i1.x2 = Double.parseDouble(sb1.substring(4,8));

        i2.x1 = Double.parseDouble(sb2.substring(0,4));
        i2.x2 = Double.parseDouble(sb2.substring(4,8));

        i1.calcFitness();
        i2.calcFitness();
    }

    /**
     * Mutates random individual in the working population
     */
    void mutation(){
        Random rand = new Random();
        int randomIndex = rand.nextInt(population.size);
        Individual randomIndividual = population.getIndividuals().get(randomIndex);
        mutationWrapper(randomIndividual, rand);
    }

    /**
     * Mutates targeted individual the working population
     * @param i individual
     */
    void targetedMutation(Individual i){
        Random rand = new Random();
        mutationWrapper(i, rand);
    }

    /**
     * Randomized mutating
     * @param i individual to mutate
     * @param rand random seed
     */
    private void mutationWrapper(Individual i, Random rand) {
        int randomInt= rand.nextInt(4);
        Double randomDouble1 = rand.nextDouble();
        Double randomDouble2 = rand.nextDouble();
        if(randomInt == 0){
            i.x1 += randomDouble1;
            i.x2 += randomDouble2;
        }
        else if(randomInt == 1){
            i.x1 -= randomDouble1;
            i.x2 -= randomDouble2;
        }
        else if(randomInt == 2){
            i.x1 += randomDouble1;
            i.x2 -= randomDouble2;
        }
        else{
            i.x1 -= randomDouble1;
            i.x2 += randomDouble2;
        }

        i.x1 = Math.abs(i.x1) % 6;
        i.x2 = Math.abs(i.x2) % 6;

        i.calcFitness();
    }
}
