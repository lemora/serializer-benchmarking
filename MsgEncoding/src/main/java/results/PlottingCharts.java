package results;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.*;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.KeyToGroupMap;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.statistics.DefaultStatisticalCategoryDataset;
import org.jfree.data.xy.XYDataset;
import serializer.Helper;

import java.awt.*;

public class PlottingCharts {


    public static JFreeChart createStackedBarChart_errorbars_testobj(final DefaultStatisticalCategoryDataset dataset, KeyToGroupMap ktgm, String title, String xaxis, String yaxis) {

        JFreeChart chart = ChartFactory.createStackedBarChart(
                "",
                xaxis,
                yaxis,
                dataset,                     // data
                PlotOrientation.VERTICAL,    // the plot orientation
                true,                        // legend
                true,                        // tooltips
                false                        // urls
        );

        Font tickFont = new Font("tickfont", Font.PLAIN, 15);
        Font labelFont = new Font("labelfont", Font.TRUETYPE_FONT, 12);

//        String fontName = "Palatino";
//        chart.getTitle().setFont(new Font(fontName, Font.BOLD, 18));
//        chart.addSubtitle(new TextTitle("Stacked Bar Chart", new Font(fontName, Font.PLAIN, 14)));

        GroupedStackedBarRenderer renderer = new GroupedStackedBarRenderer();

        renderer.setSeriesToGroupMap(ktgm);

        // color
        Paint p1 = new Color(18, 70, 120);
        Paint p2 = new Color(10, 180, 180);
//        Paint p1 = new Color(18, 83, 158);
//        Paint p2 = new Color(190, 50, 60);
//        Paint p2 = new Color(140, 200, 60);

        int numStacked = dataset.getRowCount() / ktgm.getGroupCount();
        int valcount = dataset.getColumnCount() + dataset.getRowCount();
        for (int i = 0; i < valcount; i++) {
            Paint p = numStacked==1 ? p1 : (i%2==0 ? p1: p2);
            renderer.setSeriesPaint(i, p);
        }

//        renderer.setItemMargin(0.0);
//        renderer.setShadowVisible(false);
//        renderer.setDrawBarOutline(true);
//        renderer.setIncludeBaseInRange(false);
//        BarPainter barPainter = new StandardBarPainter();
//        renderer.setBarPainter(barPainter);
//
//
//        SubCategoryAxis domainAxis = new SubCategoryAxis(xaxis);
//        domainAxis.setCategoryMargin(0.08);
        // ordering based on sequence of added groups before

        // show subcategory: protocol name
//        for (int i = 0; i < ktgm.getGroupCount(); i ++) {
//            domainAxis.addSubCategory(ktgm.getGroups().get(i).toString());
//        }

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        final StatisticalBarRenderer barRenderer = new StatisticalBarRenderer();
        plot.setRenderer(barRenderer);
        plot.getDomainAxis().setTickLabelFont(tickFont);
        plot.getDomainAxis().setLabelFont(labelFont);
        plot.getRangeAxis().setStandardTickUnits(NumberAxis.createStandardTickUnits());
        plot.getRangeAxis().setTickLabelFont(tickFont);
        plot.getRangeAxis().setLabelFont(labelFont);

//        plot.setDomainAxis(domainAxis);
//        plot.setBackgroundPaint(Color.WHITE);
//        plot.setRenderer(renderer);
//        BarRenderer br = (BarRenderer) plot.getRenderer();
//        br.setMaximumBarWidth(.1); // set maximum bar width to 35% of chart

        System.out.println("plot: " + dataset.getRowKeys().get(0));

        LegendItem li1 = new LegendItem(dataset.getRowKeys().get(0).toString().split("-")[1], p1);
        LegendItem li2 = new LegendItem(dataset.getRowKeys().get(1).toString().split("-")[1], p2);
        if(numStacked==2){
            LegendItemCollection lic = new LegendItemCollection();
            lic.add(li1);
            lic.add(li2);
            plot.setFixedLegendItems(lic);
        }

        return chart;
    }


    public static JFreeChart createStackedBarChart_pmpum(final CategoryDataset dataset, KeyToGroupMap ktgm, String title, String xaxis, String yaxis) {

        JFreeChart chart = ChartFactory.createStackedBarChart(
                "",
                xaxis,
                yaxis,
                dataset,                     // data
                PlotOrientation.VERTICAL,    // the plot orientation
                true,                        // legend
                true,                        // tooltips
                false                        // urls
        );

        Font tickFont = new Font("tickfont", Font.TRUETYPE_FONT, 15);
        Font labelFont = new Font("labelfont", Font.TRUETYPE_FONT, 14);
        Font smallLabelFont = new Font("labelfont", Font.TRUETYPE_FONT, 11);
//        String fontName = "Palatino";
//        chart.getTitle().setFont(new Font(fontName, Font.BOLD, 18));
//        chart.addSubtitle(new TextTitle("Stacked Bar Chart", new Font(fontName, Font.PLAIN, 14)));

        GroupedStackedBarRenderer renderer = new GroupedStackedBarRenderer();

        renderer.setSeriesToGroupMap(ktgm);

        // color
        Paint p1 = new Color(18, 70, 120);
        Paint p2 = new Color(10, 180, 180);
//        Paint p1 = new Color(18, 83, 158);
//        Paint p2 = new Color(190, 50, 60);
//        Paint p2 = new Color(140, 200, 60);

//        int numStacked = ktgm.getGroupCount();
        int numStacked = dataset.getColumnCount() / dataset.getRowCount();
        System.out.println("groupcont/numstacked: " + numStacked);
        int valcount = dataset.getColumnCount() + dataset.getRowCount();
        System.out.println("colcount: " + dataset.getColumnCount() + " ; rowcount: " + dataset.getRowCount());
        for (int i = 0; i < valcount; i++) {
            Paint p = numStacked==1 ? p1 : (i%2==0 ? p1: p2);
            renderer.setSeriesPaint(i, p);
        }

        renderer.setItemMargin(0.0);
        renderer.setShadowVisible(false);
        renderer.setDrawBarOutline(true);
        renderer.setIncludeBaseInRange(false);
        BarPainter barPainter = new StandardBarPainter();
        renderer.setBarPainter(barPainter);
//        renderer.setDefaultItemLabelFont(tickFont);


        SubCategoryAxis domainAxis = new SubCategoryAxis(xaxis);
        domainAxis.setCategoryMargin(0.08);
        domainAxis.setLabelFont(labelFont);
        domainAxis.setSubLabelFont(labelFont);
        domainAxis.setTickLabelFont(smallLabelFont);
        // ordering based on sequence of added groups before

        // show subcategory: protocol name
//        for (int i = 0; i < ktgm.getGroupCount(); i ++) {
//            domainAxis.addSubCategory(ktgm.getGroups().get(i).toString());
//        }

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setDomainAxis(domainAxis);
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRenderer(renderer);
        BarRenderer br = (BarRenderer) plot.getRenderer();
        br.setMaximumBarWidth(.1); // set maximum bar width to 35% of chart
//        plot.getDomainAxis().setTickLabelFont(tickFont);
//        plot.getDomainAxis().setLabelFont(labelFont);
        plot.getRangeAxis().setStandardTickUnits(NumberAxis.createStandardTickUnits());
        plot.getRangeAxis().setTickLabelFont(tickFont);
        plot.getRangeAxis().setLabelFont(labelFont);

        LegendItem li1 = new LegendItem(dataset.getRowKeys().get(0).toString().split("-")[1], p1);
        LegendItem li2 = new LegendItem(dataset.getRowKeys().get(1).toString().split("-")[1], p2);
//        if(numStacked==1){
//            LegendItemCollection lic = new LegendItemCollection();
//            lic.add(li1);
//            plot.setFixedLegendItems(lic);
//        }
//        else if(numStacked==2) {
            LegendItemCollection lic = new LegendItemCollection();
            lic.add(li1);
            lic.add(li2);
            plot.setFixedLegendItems(lic);
//        }

        return chart;
    }

    public static JFreeChart createStackedBarChart_testobj(final CategoryDataset dataset, KeyToGroupMap ktgm, String title, String xaxis, String yaxis) {
        return createStackedBarChart_testobj(dataset, ktgm, title, xaxis, yaxis, false, false);
    }

    public static JFreeChart createStackedBarChart_testobj(final CategoryDataset dataset, KeyToGroupMap ktgm, String title, String xaxis, String yaxis, boolean subaxis) {
        return createStackedBarChart_testobj(dataset, ktgm, title, xaxis, yaxis, subaxis, false);
    }

    public static JFreeChart createStackedBarChart_testobj(final CategoryDataset dataset, KeyToGroupMap ktgm, String title, String xaxis, String yaxis, boolean subaxis, boolean logYAxis) {

        JFreeChart chart = ChartFactory.createStackedBarChart(
                "",
                xaxis,
                yaxis,
                dataset,                     // data
                PlotOrientation.VERTICAL,    // the plot orientation
                true,                        // legend
                true,                        // tooltips
                false                        // urls
        );

        Font tickFont = new Font("tickfont", Font.TRUETYPE_FONT, 15);
        Font labelFont = new Font("labelfont", Font.TRUETYPE_FONT, 12);
        Font smallLabelFont = new Font("labelfont", Font.TRUETYPE_FONT, 11);
//        String fontName = "Palatino";
//        chart.getTitle().setFont(new Font(fontName, Font.BOLD, 18));
//        chart.addSubtitle(new TextTitle("Stacked Bar Chart", new Font(fontName, Font.PLAIN, 14)));

        GroupedStackedBarRenderer renderer = new GroupedStackedBarRenderer();

        renderer.setSeriesToGroupMap(ktgm);

        // color
        Paint p1 = new Color(18, 70, 120);
        Paint p2 = new Color(10, 180, 180);
//        Paint p1 = new Color(18, 83, 158);
//        Paint p2 = new Color(190, 50, 60);
//        Paint p2 = new Color(140, 200, 60);

        int numStacked = ktgm.getGroupCount() / dataset.getRowCount();
        System.out.println("numstacked: " + numStacked);
        int valcount = dataset.getColumnCount() + dataset.getRowCount();
        for (int i = 0; i < valcount; i++) {
            Paint p = numStacked==1 ? p1 : (i%2==0 ? p1: p2);
            renderer.setSeriesPaint(i, p);
        }

        renderer.setItemMargin(0.0);
        renderer.setShadowVisible(false);
        renderer.setDrawBarOutline(true);
        renderer.setIncludeBaseInRange(false);
        BarPainter barPainter = new StandardBarPainter();
        renderer.setBarPainter(barPainter);
//        renderer.setDefaultItemLabelFont(tickFont);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        // range axis

        plot.getRangeAxis().setStandardTickUnits(NumberAxis.createStandardTickUnits());
        plot.getRangeAxis().setTickLabelFont(tickFont);
        plot.getRangeAxis().setLabelFont(labelFont);
        plot.getRangeAxis().setRange(0, 78);

        SubCategoryAxis domainAxis = new SubCategoryAxis(xaxis);
        domainAxis.setCategoryMargin(0.08);
        domainAxis.setLabelFont(labelFont);
        domainAxis.setSubLabelFont(smallLabelFont);
        domainAxis.setTickLabelFont(tickFont);
        plot.setDomainAxis(domainAxis);
        plot.setBackgroundPaint(Color.WHITE);
        // ordering based on sequence of added groups before

        if(false) {
            LogarithmicAxis ylogAxis = new LogarithmicAxis(yaxis);
//            ylogAxis.setMinorTickMarksVisible(true);
//            ylogAxis.setAutoRange(true);
            ylogAxis.setRange(.01, 10000);
            plot.setRangeAxis(ylogAxis);
        }

        // show subcategory: protocol name
        System.out.println("rowkeys" + dataset.getRowKeys());
        if(subaxis) {
            for (int i = 0; i < dataset.getRowKeys().size(); i+=2) {
//                domainAxis.addSubCategory(ktgm.getGroups().get(i).toString());
                domainAxis.addSubCategory(dataset.getRowKey(i+1).toString().split("-")[0]);
                System.out.println("Key: " + dataset.getRowKey(i+1).toString().split("-")[0]);
            }
        }

        plot.setRenderer(renderer);
        BarRenderer br = (BarRenderer) plot.getRenderer();
        br.setMaximumBarWidth(.1); // set maximum bar width to 35% of chart

        System.out.println("plot: " + dataset.getRowKeys().get(0));

//        if(numStacked==2){
        LegendItem li1 = new LegendItem(dataset.getRowKeys().get(0).toString().split("-")[1], p1);
        LegendItem li2 = new LegendItem(dataset.getRowKeys().get(1).toString().split("-")[1], p2);
        LegendItemCollection lic = new LegendItemCollection();
        lic.add(li1);
        lic.add(li2);
        plot.setFixedLegendItems(lic);
//        }

        return chart;
    }

    public static JFreeChart createLineChart_container(final XYDataset dataset, String title, String xaxis, String yaxis, double yaxisMin, double yaxisMax) {

        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "",
                xaxis,
                yaxis,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        Font tickFont = new Font("tickfont", Font.TRUETYPE_FONT, 15);
        Font labelFont = new Font("labelfont", Font.TRUETYPE_FONT, 12);
        chart.setBackgroundPaint(Color.white);

        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        Paint p1 = new Color(10, 80, 150);
        int r = 50;
        int g = 80;
        int b = 150;
        int seriescount = dataset.getSeriesCount();
        String prevdate = "";
        for(int i = 0; i < seriescount; i++) {
            renderer.setSeriesLinesVisible(i, true);
            renderer.setSeriesShapesVisible(i, true);
            String date = "";
            if(dataset.getSeriesKey(i).toString().trim().split("\\s").length > 1) {
                date = dataset.getSeriesKey(i).toString().trim().split("\\s")[2].substring(0, 8);
            }

            if(prevdate.equals(date)) {
//                g = ((g+10)%220);
                g = ((g+35)%210);
                r = ((r+20)%220);
                renderer.setSeriesPaint(i, new Color(r, g, b));
            }
            else {
                r = ((r+35)%220);
                renderer.setSeriesPaint(i, new Color(r, g, b));
                int length = dataset.getSeriesKey(i).toString().length();
                prevdate = date;
                Helper.debugPrint("r" + r + " | g" + g + " | b" + b);
            }
        }
        plot.setRenderer(renderer);

        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setTickUnit(new NumberTickUnit(0.5));
        rangeAxis.setLabelFont(labelFont);
        rangeAxis.setTickLabelFont(tickFont);
        rangeAxis.setRange(yaxisMin, yaxisMax);

//        yaxis.setMinorTickMarksVisible(true);
//        yaxis.setAutoRange(true);
//        yaxis.setRange(.01, yaxisMax);
//        yaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
//        yaxis.setTickLabelFont(tickFont);
//        yaxis.setLabelFont(labelFont);
//        plot.setRangeAxis(yaxis);

//        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
//        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
//        rangeAxis.setLabelFont(labelFont);
//        rangeAxis.setTickLabelFont(tickFont);


        LogAxis xlogAxis = new LogAxis(xaxis);
        xlogAxis.setMinorTickMarksVisible(true);
        xlogAxis.setAutoRange(true);
        xlogAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        xlogAxis.setTickLabelFont(tickFont);
        xlogAxis.setLabelFont(labelFont);
        plot.setDomainAxis(xlogAxis);


        Color lightgray = new Color(200, 200, 200);
//        plot.setDomainGridlinePaint(lightgray);
//        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinePaint(lightgray);
        plot.setRangeGridlinesVisible(true);

        return chart;
    }

    public static JFreeChart createLineChart_yLogAxis_container(final XYDataset dataset, String title, String xaxis, String yaxis) {
        return createLineChart_yLogAxis_container(dataset, title, xaxis, yaxis, .01, 200000);
    }

    public static JFreeChart createLineChart_yLogAxis_container(final XYDataset dataset, String title, String xaxis, String yaxis, double yaxisMin, double yaxisMax) {

        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "",
                xaxis,
                yaxis,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        Font tickFont = new Font("tickfont", Font.TRUETYPE_FONT, 15);
        Font labelFont = new Font("labelfont", Font.TRUETYPE_FONT, 12);
        chart.setBackgroundPaint(Color.white);

        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        Paint p1 = new Color(10, 80, 150);
        int r = 50;
        int g = 80;
        int b = 150;
        int seriescount = dataset.getSeriesCount();
        String prevdate = "";
        for(int i = 0; i < seriescount; i++) {
            renderer.setSeriesLinesVisible(i, true);
            renderer.setSeriesShapesVisible(i, true);
            String date = "";
            if(dataset.getSeriesKey(i).toString().trim().split("\\s").length > 1) {
                date = dataset.getSeriesKey(i).toString().trim().split("\\s")[2].substring(0, 8);
            }

            if(prevdate.equals(date)) {
//                g = ((g+10)%220);
                g = ((g+35)%210);
                r = ((r+20)%220);
                renderer.setSeriesPaint(i, new Color(r, g, b));
            }
            else {
                r = ((r+35)%220);
                renderer.setSeriesPaint(i, new Color(r, g, b));
                int length = dataset.getSeriesKey(i).toString().length();
                prevdate = date;
                Helper.debugPrint("r" + r + " | g" + g + " | b" + b);
            }
        }
        plot.setRenderer(renderer);

//        final NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
//        domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

//        LogarithmicAxis ylogAxis = new LogarithmicAxis(yaxis);
        LogAxis ylogAxis = new LogAxis(yaxis);
        ylogAxis.setMinorTickMarksVisible(true);
        ylogAxis.setAutoRange(true);
        ylogAxis.setRange(yaxisMin, yaxisMax);
        ylogAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        ylogAxis.setTickLabelFont(tickFont);
        ylogAxis.setLabelFont(labelFont);
        plot.setRangeAxis(ylogAxis);

        LogAxis xlogAxis = new LogAxis(xaxis);
        xlogAxis.setMinorTickMarksVisible(true);
        xlogAxis.setAutoRange(true);
//        xlogAxis.setRange(1, 100000);
//        xlogAxis.setDefaultAutoRange(new Range(0.01, 5.0));
        xlogAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
//        xlogAxis.setBase(10);
//        LogFormat format = new LogFormat(xlogAxis.getBase(), "", "", true);
//        xlogAxis.setNumberFormatOverride(format);
        xlogAxis.setTickLabelFont(tickFont);
        xlogAxis.setLabelFont(labelFont);
        plot.setDomainAxis(xlogAxis);


        Color lightgray = new Color(200, 200, 200);
//        plot.setDomainGridlinePaint(lightgray);
//        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinePaint(lightgray);
        plot.setRangeGridlinesVisible(true);

        return chart;
    }
}
