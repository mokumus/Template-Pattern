package com.muhammedokumus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Implements the GeneticFramework using tournament selection
 */
public class TournamentGenetic extends GeneticFramework {

    /**
     * Defines a framework that will work on the provided population
     * @param p
     */
    public TournamentGenetic(Population p) {
        super(p);
    }

    /**
     * Tournament selection, using 6-way tournament
     */
    @Override
    void selection() {
        Random rand = new Random();
        Population tournamentWinners = new Population(0);

        while(tournamentWinners.size != population.size){
            List<Individual> contestants = new ArrayList<>();
            for(int i = 0; i < 6; i++){
                Individual i1 = population.getIndividuals().get(rand.nextInt(population.size));
                contestants.add(i1);
            }
            tournamentWinners.getIndividuals().add(fittestInList(contestants));
            tournamentWinners.size++;
        }
        population = tournamentWinners;
    }

    /**
     * Find and return the fittest individual in the provided list
     * @param individuals list of individuals
     * @return fittest individual
     */
    private Individual fittestInList(List<Individual> individuals){
        Individual tempFittest = individuals.get(0);
        Double fitnessLevel = individuals.get(0).fitness;

        for(Individual i : individuals){
            if(i.fitness > fitnessLevel){
                tempFittest = i;
                fitnessLevel = i.fitness;
            }
        }
        return tempFittest;
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
