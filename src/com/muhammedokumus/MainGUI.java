package com.muhammedokumus;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import java.awt.*;

/**
 * Creates a simple real-time chart
 */
public class MainGUI {
    public static void main(String[] args) throws Exception {
        double[][] initdata = getEvolutionData();

        // Create Chart
        final XYChart chart = QuickChart.getChart("Genetic Algorithm Loops", "Generation", "Fitness", "sine", initdata[0], initdata[1]);
        chart.getStyler().setPlotGridLinesColor(Color.RED);

        // Show it
        final SwingWrapper<XYChart> sw = new SwingWrapper<XYChart>(chart);
        sw.displayChart();


        while (true) {
            Thread.sleep(1000);
            final double[][] data = getEvolutionData();
            chart.updateXYSeries("sine", data[0], data[1], null);
            sw.repaintChart();
        }
    }

    private static double[][] getEvolutionData() {
        Population pool = new Population(100);
        GeneticFramework geneticFramework = new TournamentGenetic(pool);

        double[] xData = new double[100];
        double[] yData = new double[100];

        for (int i = 0; i < xData.length; i++) {

            geneticFramework.selection();
            geneticFramework.mutation();
            geneticFramework.mutation();
            geneticFramework.mutation();



            xData[i] = i;
            yData[i] = pool.getFittest().fitness;

        }
        return new double[][] { xData, yData };
    }
}