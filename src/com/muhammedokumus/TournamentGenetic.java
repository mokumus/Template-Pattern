package com.muhammedokumus;

import java.util.Random;

public class TournamentGenetic extends GeneticFramework {
    public TournamentGenetic(Population p) {
        super(p);
    }

    @Override
    void selection() {
        Random rand = new Random();
        Population tournamentWinners = new Population(0);

        //3-way tournament
        while(tournamentWinners.size != population.size){
            Individual i1 = population.getIndividuals().get(rand.nextInt(population.size));
            Individual i2 = population.getIndividuals().get(rand.nextInt(population.size));
            Individual i3 = population.getIndividuals().get(rand.nextInt(population.size));
            Double winnerFitness = Math.max(i3.fitness, Math.max(i1.fitness, i2.fitness));
            //System.out.println("Contestants: " + i1 +"  "+ i2 + "  " + i3);
           if(winnerFitness.equals(i1.fitness)){
               //System.out.println("Winner: " + i1 );
               tournamentWinners.getIndividuals().add(i1);
           }
           else if(winnerFitness.equals(i2.fitness)){
               //System.out.println("Winner: " + i2 );
               tournamentWinners.getIndividuals().add(i2);
            }
           else if(winnerFitness.equals(i3.fitness)){
               //System.out.println("Winner: " + i3 );
               tournamentWinners.getIndividuals().add(i3);
            }
           tournamentWinners.size++;
        }

        population = tournamentWinners;
    }

    @Override
    void loopRecipe() {
        selection();
        mutation();
        mutation();
        mutation();
    }


}
