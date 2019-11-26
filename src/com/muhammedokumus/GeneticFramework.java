package com.muhammedokumus;

import java.util.Random;
import static java.lang.Math.abs;

public abstract class GeneticFramework {
    Population population;

    public GeneticFramework(Population p){
        this.population = p;
    }

    abstract void selection();

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
        String tmp2 = sb2.substring(4,8);

        sb1.replace(0,4, tmp2);
        sb2.replace(4,8, tmp1);

        i1.x1 = Double.parseDouble(sb1.substring(0,4));
        i1.x2 = Double.parseDouble(sb1.substring(4,8));

        i2.x1 = Double.parseDouble(sb2.substring(0,4));
        i2.x2 = Double.parseDouble(sb2.substring(4,8));
    }

    void mutation(){
        Random rand = new Random();
        int randomIndex = rand.nextInt(population.size);
        Individual randomIndividual = population.getIndividuals().get(randomIndex);
        //System.out.println("random:" + randomIndividual);
        int randomInt= rand.nextInt(4);
        Double randomDouble1 = rand.nextDouble();
        Double randomDouble2 = rand.nextDouble();
        if(randomInt == 0){
            randomIndividual.x1 += randomDouble1;
            randomIndividual.x2 += randomDouble2;
        }
        else if(randomInt == 1){
            randomIndividual.x1 -= randomDouble1;
            randomIndividual.x2 -= randomDouble2;
        }
        else if(randomInt == 2){
            randomIndividual.x1 += randomDouble1;
            randomIndividual.x2 -= randomDouble2;
        }
        else{
            randomIndividual.x1 -= randomDouble1;
            randomIndividual.x2 += randomDouble2;
        }

        randomIndividual.calcFitness();
        //System.out.println("after mutation:" + randomIndividual);
    }
}
