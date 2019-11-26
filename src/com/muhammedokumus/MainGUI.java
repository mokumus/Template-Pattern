package com.muhammedokumus;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

/**
 * Creates a real-time chart using SwingWorker
 */
public class MainGUI extends JFrame implements ActionListener {
    private SwingWrapper<XYChart> sw;
    private XYChart chart;

    public static void main(String[] args) throws Exception {
        MainGUI swingWorkerRealTime = new MainGUI();
        swingWorkerRealTime.go();
    }



    private void go() {
        // Create Chart
        chart = QuickChart.getChart("Real-Time Genetic Algorithm Convergence", "TIME", "AVERAGE FITNESS", "RouletteGeneticRun", new double[]{0}, new double[]{0});
        chart.addSeries("RankGeneticRun",  new double[]{0}, new double[]{0});
        chart.addSeries("TournamentGeneticRun",  new double[]{0}, new double[]{0});

        chart.getStyler().setLegendVisible(true);
        chart.getStyler().setXAxisTicksVisible(false);

        // Show it
        sw = new SwingWrapper<XYChart>(chart);
        sw.displayChart();

        MySwingWorker mySwingWorker = new MySwingWorker();
        mySwingWorker.execute();
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private class MySwingWorker extends SwingWorker<Boolean, double[]> {
        LinkedList<Double> fifo1 = new LinkedList<Double>();
        LinkedList<Double> fifo2 = new LinkedList<Double>();
        LinkedList<Double> fifo3 = new LinkedList<Double>();

        Population pool1 = new Population(10);
        Population pool2 = new Population(10);
        Population pool3 = new Population(10);

        GeneticFramework tournamentRun = new TournamentGenetic(pool1);
        GeneticFramework rouletteRun = new RouletteGenetic(pool2);
        GeneticFramework rankRun = new RouletteGenetic(pool3);

        public MySwingWorker() {
            fifo1.add(pool1.getAverageFitness());
            fifo2.add(pool2.getAverageFitness());
            fifo3.add(pool3.getAverageFitness());
        }

        @Override
        protected Boolean doInBackground() throws Exception {
            while (!isCancelled()) {
                tournamentRun.loopRecipe();
                rouletteRun.loopRecipe();
                rankRun.loopRecipe();

                geneticDataCollector(fifo1, pool1);
                geneticDataCollector(fifo2, pool2);
                geneticDataCollector(fifo3, pool3);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // eat it. caught when interrupt is called
                    System.out.println("MySwingWorker shut down.");
                }
            }
            return true;
        }

        private void geneticDataCollector(LinkedList<Double> fifo, Population pool) {
            fifo.add(pool.getAverageFitness());

            if (fifo.size() > 500)
                fifo.removeFirst();

            double[] array1 = new double[fifo.size()];

            for (int i = 0; i < fifo.size(); i++)
                array1[i] = fifo.get(i);

            publish(array1);
        }

        @Override
        protected void process(List<double[]> chunks) {

            chart.updateXYSeries("RouletteGeneticRun", null, fifo1, null);
            chart.updateXYSeries("RankGeneticRun", null, fifo2, null);
            chart.updateXYSeries("TournamentGeneticRun", null, fifo3, null);

            sw.repaintChart();

            long start = System.currentTimeMillis();
            long duration = System.currentTimeMillis() - start;
            try {
                Thread.sleep(400 - duration); // 40 ms ==> 25fps
                // Thread.sleep(400 - duration); // 400 ms ==> 2.5fps
            } catch (InterruptedException e) {
            }
        }
    }
}