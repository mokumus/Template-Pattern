package com.muhammedokumus;

public class Main {

    public static void main(String[] args) {
        Population pool = new Population(10);
        System.out.println(pool.toString());
        System.out.println(pool.getFittest());
        System.out.println(pool.getSecondFittest());
        System.out.println(pool.getLeastFittestIndex());
    }
}
