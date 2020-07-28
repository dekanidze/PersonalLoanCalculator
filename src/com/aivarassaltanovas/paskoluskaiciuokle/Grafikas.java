package com.aivarassaltanovas.paskoluskaiciuokle;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class Grafikas {

    GUI gui;
    Table table;
    double[] duomenys;

    public Grafikas(String lentelesPavadinimas, double[] duomenys, GUI gui, Table table) {

        this.duomenys = duomenys;
        this.gui = gui;
        this.table = table;

        JFreeChart lineChart = ChartFactory.createLineChart(lentelesPavadinimas,"Mėnesiai", "Įmokos suma", sukurtiDuomenis(), PlotOrientation.VERTICAL,
                true, true, false);

        StandardChartTheme theme = (StandardChartTheme) org.jfree.chart.StandardChartTheme.createJFreeTheme();
        String fontName = "Arial";

        theme.setTitlePaint(Color.decode("#4572a7"));
        theme.setExtraLargeFont(new Font(fontName, Font.BOLD, 20));
        theme.setLargeFont(new Font(fontName, Font.BOLD, 15));
        theme.setRegularFont(new Font(fontName, Font.PLAIN, 12));
        theme.setRangeGridlinePaint(Color.LIGHT_GRAY);
        theme.setPlotBackgroundPaint(Color.WHITE);
        theme.setChartBackgroundPaint(Color.WHITE);
        theme.setGridBandPaint(Color.RED);
        theme.setShadowVisible(false);
        theme.setShadowVisible(false);
        theme.apply(lineChart);

        lineChart.getCategoryPlot().setOutlineVisible(false); // remas
        lineChart.getCategoryPlot().getRangeAxis().setAxisLineVisible(false); // asiu linijos
        lineChart.getCategoryPlot().getRangeAxis().setTickMarksVisible(false); // mazos linijos asyje
        lineChart.getCategoryPlot().setRangeGridlineStroke(new BasicStroke());
        lineChart.getCategoryPlot().getRangeAxis().setTickLabelPaint(Color.GRAY); // y asies reiksmiu spalva
        lineChart.getCategoryPlot().getDomainAxis().setTickLabelPaint(Color.GRAY); // x asies reiksmiu spalva

        JFrame frame = new JFrame();
        ImageIcon img = new ImageIcon(getClass().getResource("icon.png"));
        frame.setIconImage(img.getImage());
        frame.setTitle(lentelesPavadinimas);
        frame.setContentPane(new ChartPanel(lineChart));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private DefaultCategoryDataset sukurtiDuomenis() {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        int j = 2;
        for (int i = 0; i < gui.Menesiai; i++) {
            dataset.addValue(duomenys[j], "Mėnesinė įmoka", String.valueOf(i + 1 + GUI.index));
            j += 4;
        }
        return dataset;
    }
}