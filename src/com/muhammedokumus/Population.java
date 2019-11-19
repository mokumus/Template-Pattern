package com.muhammedokumus;

import java.util.ArrayList;
import java.util.StringJoiner;


public class Population {
    ArrayList<Individual> individuals = new ArrayList<>();


    //Initialize population
    public Population(int size) {
        for (int i = 0; i < size; i++) {
            individuals.add(new Individual());
        }
    }


    @Override
    public String toString() {
        return new StringJoiner(", ")
                .add("Population: " + individuals)
                .toString();
    }
}
