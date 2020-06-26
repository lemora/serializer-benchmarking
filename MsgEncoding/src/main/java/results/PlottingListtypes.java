package results;

import org.jfree.chart.JFreeChart;
import org.jfree.data.KeyToGroupMap;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import serializer.Helper;
import serializer.SER_PROTOCOL;

import java.io.IOException;
import java.util.*;

import static results.PlottingCharts.*;
import static results.ResultFileIO.getStringFromFile;
import static results.BmResultsContainer.*;

public class PlottingListtypes {


//    private static XYDataset createXYDataset(BenchmarkResultsSet ... benchmarkResults) {
//        final XYSeriesCollection dataset = new XYSeriesCollection();
//
//        for( int i = 0; i < benchmarkResults.length; i++) {
//            List<BenchmarkResult> resultList = benchmarkResults[i].getBenchmarks();
//            System.out.println("Series name: " + benchmarkResults[i].getBenchmarkerName());
//            final XYSeries series = new XYSeries(benchmarkResults[i].getBenchmarkerName());
//
//            for(int b = 0; b < resultList.size(); b++) {
//                series.add(b, resultList.get(b).getScore());
//            }
//
//            dataset.addSeries(series);
//        }
//
//        return dataset;
//    }
//
//    private static XYDataset createXYDataset(List<BenchmarkResultsSet> benchmarkResults) {
//        List<BenchmarkResultsSet> ordered = new LinkedList<>(benchmarkResults);
//        ordered.sort(Comparator.comparing(BenchmarkResultsSet::getDateTime));
//
//        final XYSeriesCollection dataset = new XYSeriesCollection();
//        List<String> bmSetNames = new LinkedList<>();
//
//        for( BenchmarkResultsSet bmResSet : ordered) {
//            List<BenchmarkResult> resultList = bmResSet.getBenchmarks();
//
//            String bmName = bmResSet.getBenchmarkerName() + "-" + bmResSet.getSerMode()
//                    + "\n" + bmResSet.getMachineName()
//                    + "\n" + bmResSet.getDateTime();
//            int i = 1;
//            while( bmSetNames.contains(bmName) ) {
//                if (bmName.contains("#")) {
//                    bmName = bmName.substring(0, bmName.length() - 2) + "#" + i;
//                } else {
//                    bmName = bmName + "#" + i;
//                }
//                i++;
//            }
//            bmSetNames.add(bmName);
//            XYSeries series = new XYSeries(bmName);
//
//            for(int b = 0; b < resultList.size(); b++) {
//                series.add(b, resultList.get(b).getScore());
//            }
//            dataset.addSeries(series);
//        }
//        DatasetGroup dg = new DatasetGroup("*19-07-08");
//        dataset.setGroup(dg);
//        return dataset;
//    }

    private static XYDataset createXYDataset(List<BmResultsSersize> benchmarkResults, ResultFileIO.Benchmarker_Key bmKey) {
        List<BmResultsSersize> ordered = new LinkedList<>(benchmarkResults);
        ordered.sort(Comparator.comparing(BmResultsSersize::getBenchmarkerName));
        switch (bmKey) {
            case DATE:
                ordered.sort(Comparator.comparing(BmResultsSersize::getDateTime));
                break;
            case BMNAME:
                ordered.sort(Comparator.comparing(BmResultsSersize::getBenchmarkerName));
                break;
            case MACHINE:
                ordered.sort(Comparator.comparing(BmResultsSersize::getMachineName));
                break;
        }

        final XYSeriesCollection dataset = new XYSeriesCollection();
        List<String> bmSetNames = new LinkedList<>();

        for( BmResultsSersize bmResSet : ordered) {
            List<BenchmarkResult> resultList = bmResSet.getBenchmarks();

            String bmName = bmResSet.getSerprotocol().abbrev;
//            String bmName = bmResSet.getBenchmarkerName();
            // setting bmName
            switch (bmKey) {
                case DATE:
                    bmName = bmResSet.getDateTime();
                    break;
                case BMNAME:
                    bmName = bmResSet.getSerprotocol().abbrev;
                    break;
                case MACHINE:
                    bmName = bmResSet.getMachineName();
                    break;
            }

            int i = 1;
            while( bmSetNames.contains(bmName) ) {
                if (bmName.contains("#")) {
                    bmName = bmName.substring(0, bmName.length() - 2) + "#" + i;
                } else {
                    bmName = bmName + "#" + i;
                }
                i++;
            }
            bmSetNames.add(bmName);
            XYSeries series = new XYSeries(bmName);

            for(int b = 0; b < resultList.size(); b++) {
                System.out.println(resultList.get(b).getValueCount() + " -- " + resultList.get(b).getScore());
                series.add(resultList.get(b).getValueCount(), resultList.get(b).getScore());
            }
            dataset.addSeries(series);
        }
        DatasetGroup dg = new DatasetGroup("*19-07-08");
        dataset.setGroup(dg);
        return dataset;
    }


    private static XYDataset createXYDataset_singleProtocol_container(BmResultsContainer bmRes, BenchmarkResult.SER_MODE sermode) {
        final XYSeriesCollection dataset = new XYSeriesCollection();

        if(sermode.equals(BenchmarkResult.SER_MODE.SER)) {
            XYSeries doubleSeries = new XYSeries("DoubleList");
            bmRes.getDoubleListsResults_ser().entrySet().stream().forEach(entry -> doubleSeries.add(new Double(entry.getKey()), (Double)entry.getValue().getScore()));
            dataset.addSeries(doubleSeries);

            XYSeries intSeries = new XYSeries("IntList");
            bmRes.getIntListsResults_ser().entrySet().stream().forEach(entry -> intSeries.add(new Double(entry.getKey()), (Double)entry.getValue().getScore()));
            dataset.addSeries(intSeries);

            XYSeries stringSeries = new XYSeries("StringList");
            bmRes.getStringListsResults_ser().entrySet().stream().forEach(entry -> stringSeries.add(new Double(entry.getKey()), (Double)entry.getValue().getScore()));
            dataset.addSeries(stringSeries);
        }
        else {
            XYSeries doubleSeries = new XYSeries("DoubleList");
            bmRes.getDoubleListsResults_deser().entrySet().stream().forEach(entry -> doubleSeries.add(new Double(entry.getKey()), (Double)entry.getValue().getScore()));
            dataset.addSeries(doubleSeries);

            XYSeries intSeries = new XYSeries("IntList");
            bmRes.getIntListsResults_deser().entrySet().stream().forEach(entry -> intSeries.add(new Double(entry.getKey()), (Double)entry.getValue().getScore()));
            dataset.addSeries(intSeries);

            XYSeries stringSeries = new XYSeries("StringList");
            bmRes.getStringListsResults_deser().entrySet().stream().forEach(entry -> stringSeries.add(new Double(entry.getKey()), (Double)entry.getValue().getScore()));
            dataset.addSeries(stringSeries);
        }

        return dataset;
    }

    private static XYDataset createXYDataset_multiProtocol_container(List<BmResultsContainer> bmResults, List_Type listtype, BenchmarkResult.SER_MODE sermode) {
        final XYSeriesCollection dataset = new XYSeriesCollection();
        boolean ser = sermode.equals(BenchmarkResult.SER_MODE.SER);

        XYSeries series_java = new XYSeries(SER_PROTOCOL.JAVA.abbrev);
        XYSeries series_protobuf = new XYSeries(SER_PROTOCOL.PROTOBUF.abbrev);
        XYSeries series_protostuff = new XYSeries(SER_PROTOCOL.PROTOSTUFF.abbrev);
        XYSeries series_avro = new XYSeries(SER_PROTOCOL.AVRO.abbrev);
        XYSeries series_avrojson = new XYSeries(SER_PROTOCOL.AVRO_JSON.abbrev);
        XYSeries series_fst = new XYSeries(SER_PROTOCOL.FST.abbrev);
        XYSeries series_kryo = new XYSeries(SER_PROTOCOL.KRYO.abbrev);
        XYSeries series_hessian = new XYSeries(SER_PROTOCOL.HESSIAN.abbrev);

        for(BmResultsContainer bmRes : bmResults) {
            SER_PROTOCOL serprotocol = bmRes.getSerprotocol();
            Map<Integer, BenchmarkResult> results = bmRes.getBenchmarks(listtype, sermode);
            switch(serprotocol) {
                case JAVA:
                    results.entrySet().stream().forEach(entry -> series_java.add(new Double(entry.getKey()), (Double)entry.getValue().getScore()));
                    break;
                case PROTOBUF:
                    results.entrySet().stream().forEach(entry -> series_protobuf.add(new Double(entry.getKey()), (Double)entry.getValue().getScore()));
                    break;
                case PROTOSTUFF:
                    results.entrySet().stream().forEach(entry -> series_protostuff.add(new Double(entry.getKey()), (Double)entry.getValue().getScore()));
                    break;
                case AVRO:
                    results.entrySet().stream().forEach(entry -> series_avro.add(new Double(entry.getKey()), (Double)entry.getValue().getScore()));
                    break;
                case AVRO_JSON:
                    results.entrySet().stream().forEach(entry -> series_avrojson.add(new Double(entry.getKey()), (Double)entry.getValue().getScore()));
                    break;
                case FST:
                    results.entrySet().stream().forEach(entry -> series_fst.add(new Double(entry.getKey()), (Double)entry.getValue().getScore()));
                    break;
                case KRYO:
                    results.entrySet().stream().forEach(entry -> series_kryo.add(new Double(entry.getKey()), (Double)entry.getValue().getScore()));
                    break;
                case HESSIAN:
                    results.entrySet().stream().forEach(entry -> series_hessian.add(new Double(entry.getKey()), (Double)entry.getValue().getScore()));
                    break;
            }
        }
        // order of adding like in plot!
        dataset.addSeries(series_avro);
        dataset.addSeries(series_avrojson);
        dataset.addSeries(series_fst);
        dataset.addSeries(series_hessian);
        dataset.addSeries(series_java);
        dataset.addSeries(series_kryo);
        dataset.addSeries(series_protobuf);
        dataset.addSeries(series_protostuff);
        return dataset;
    }

    private static XYDataset createXYDataset_compareMachines_container(List<BmResultsContainer> bmResults, List_Type listtype, BenchmarkResult.SER_MODE sermode) {
        final XYSeriesCollection dataset = new XYSeriesCollection();
        boolean ser = sermode.equals(BenchmarkResult.SER_MODE.SER);

        XYSeries series_dot1 = new XYSeries(ResultFileIO.Machine.DCACHE_DOT1.shortname);
        XYSeries series_dot13= new XYSeries(ResultFileIO.Machine.DCACHE_DOT13.shortname);
        XYSeries series_dot13c = new XYSeries(ResultFileIO.Machine.DCACHE_DOT13_CPULOCK.shortname);

        for(BmResultsContainer bmRes : bmResults) {
            ResultFileIO.Machine machine = bmRes.getMachine();
            Map<Integer, BenchmarkResult> results = bmRes.getBenchmarks(listtype, sermode);
            switch(machine) {
                case DCACHE_DOT1:
                    results.entrySet().forEach(entry -> series_dot1.add(new Double(entry.getKey()), (Double)entry.getValue().getScore()));
                    break;
                case DCACHE_DOT13:
                    results.entrySet().forEach(entry -> series_dot13.add(new Double(entry.getKey()), (Double)entry.getValue().getScore()));
                    break;
                case DCACHE_DOT13_CPULOCK:
                    results.entrySet().forEach(entry -> series_dot13c.add(new Double(entry.getKey()), (Double)entry.getValue().getScore()));
                    break;
            }
        }
        // order of adding like in plot!
        dataset.addSeries(series_dot1);
        dataset.addSeries(series_dot13);
        dataset.addSeries(series_dot13c);
        return dataset;
    }

    public static List<BmResultsSersize> createBenchmarkResultsSetListFromFilenames(List<String> filenames, BenchmarkResult.SER_MODE sermode, String bmNameContains) {
        List<BmResultsSersize> bmResultSets = new LinkedList<>();
        List<String> bmNames = new LinkedList<>();

        for (String filename : filenames) {
            BmResultsSersize bmrs = createBenchmarkResultSet(filename, sermode, bmNameContains);
            String bmName = bmrs.getBenchmarkerName();
            int i = 1;
            while( bmNames.contains(bmName) ) {
                if (bmNames.contains("#")) {
                    bmName = bmName.substring(0, bmName.length() - 2) + "#" + i;
                } else {
                    bmName = bmName + "#" + i;
                }
                i++;
            }
            bmrs.setBenchmarkerName(bmName);
            bmResultSets.add(bmrs);
        }
        return bmResultSets;
    }

    private static BmResultsContainer createSingleprotocolAvg_container(SER_PROTOCOL serprotocol, String machineFolder) {
        List<String> fileList;
        fileList = ResultFileIO.getNewResultFilesforType( machineFolder + "/container", serprotocol, "*" );

        List<BmResultsContainer> bmResSets = createBmResultsContainerListFromFilenames(fileList);
        BmResultsContainer avgBmResSet = createAverageBmResultsContainer( bmResSets );
        return avgBmResSet;
    }

    private static List<BmResultsContainer> createBmResultsContainerListFromFilenames(List<String> filenames) {
        List<BmResultsContainer> bmResultContainerList = new LinkedList<>();
        List<String> bmNames = new LinkedList<>();

        for (String filename : filenames) {
            BmResultsContainer bmrs = createBmResultsContainer(filename);
            String bmName = bmrs.getBenchmarkerName();
            int i = 1;
            while( bmNames.contains(bmName) ) {
                if (bmNames.contains("#")) {
                    bmName = bmName.substring(0, bmName.length() - 2) + "#" + i;
                } else {
                    bmName = bmName + "#" + i;
                }
                i++;
            }
            bmrs.setBenchmarkerName(bmName);
            bmResultContainerList.add(bmrs);
        }
        return bmResultContainerList;
    }

    private static BmResultsContainer createAverageBmResultsContainer(List<BmResultsContainer> bmResultSets) {
        assert bmResultSets.size() == 5;
        BmResultsContainer avgResSet = new BmResultsContainer();
        avgResSet.setSerprotocol(bmResultSets.get(0).getSerprotocol());
        avgResSet.setBenchmarkerName(bmResultSets.get(0).getBenchmarkerName());
        avgResSet.setDateTime(bmResultSets.get(0).getDateTime());
        avgResSet.setMachineName(bmResultSets.get(0).getMachineName());
        avgResSet.setMachine(bmResultSets.get(0).getMachine());

        List<Map<Integer, BenchmarkResult>> map_stringListsResults_ser = new LinkedList<Map<Integer, BenchmarkResult>>();
        List<Map<Integer, BenchmarkResult>> map_stringListsResults_deser = new LinkedList<Map<Integer, BenchmarkResult>>();

        List<Map<Integer, BenchmarkResult>> map_intListsResults_ser = new LinkedList<Map<Integer, BenchmarkResult>>();
        List<Map<Integer, BenchmarkResult>> map_intListsResults_deser = new LinkedList<Map<Integer, BenchmarkResult>>();

        List<Map<Integer, BenchmarkResult>> map_doubleListsResults_ser = new LinkedList<Map<Integer, BenchmarkResult>>();
        List<Map<Integer, BenchmarkResult>> map_doubleListsResults_deser = new LinkedList<Map<Integer, BenchmarkResult>>();

        for(BmResultsContainer c : bmResultSets) {
            map_stringListsResults_ser.add(c.getStringListsResults_ser());
            map_stringListsResults_deser.add(c.getStringListsResults_deser());

            map_doubleListsResults_ser.add(c.getDoubleListsResults_ser());
            map_doubleListsResults_deser.add(c.getDoubleListsResults_deser());

            map_intListsResults_ser.add(c.getIntListsResults_ser());
            map_intListsResults_deser.add(c.getIntListsResults_deser());
        }

        // set results
        avgResSet.setStringListsResults_ser( calcAverageResultMap_container(map_stringListsResults_ser) );
        avgResSet.setStringListsResults_deser( calcAverageResultMap_container(map_stringListsResults_deser) );

        avgResSet.setDoubleListsResults_ser( calcAverageResultMap_container(map_doubleListsResults_ser) );
        avgResSet.setDoubleListsResults_deser( calcAverageResultMap_container(map_doubleListsResults_deser) );

        avgResSet.setIntListsResults_ser( calcAverageResultMap_container(map_intListsResults_ser) );
        avgResSet.setIntListsResults_deser( calcAverageResultMap_container(map_intListsResults_deser) );

        return avgResSet;
    }

    private static BmResultsContainer createBmResultsContainer_ratio(BmResultsContainer dividend, BmResultsContainer divisor) {
        BmResultsContainer ratioResSet = new BmResultsContainer(dividend);

        for(BmResultsContainer.Container_ValCount valcount : BmResultsContainer.Container_ValCount.values()) {
            ratioResSet.getBmResult(List_Type.INT_LIST, BenchmarkResult.SER_MODE.SER, valcount).divideScore(divisor.getBmResult(List_Type.INT_LIST, BenchmarkResult.SER_MODE.SER, valcount).getScore());
            ratioResSet.getBmResult(List_Type.INT_LIST, BenchmarkResult.SER_MODE.DESER, valcount).divideScore(divisor.getBmResult(List_Type.INT_LIST, BenchmarkResult.SER_MODE.DESER, valcount).getScore());

            ratioResSet.getBmResult(List_Type.DOUBLE_LIST, BenchmarkResult.SER_MODE.SER, valcount).divideScore(divisor.getBmResult(List_Type.DOUBLE_LIST, BenchmarkResult.SER_MODE.SER, valcount).getScore());
            ratioResSet.getBmResult(List_Type.DOUBLE_LIST, BenchmarkResult.SER_MODE.DESER, valcount).divideScore(divisor.getBmResult(List_Type.DOUBLE_LIST, BenchmarkResult.SER_MODE.DESER, valcount).getScore());

            ratioResSet.getBmResult(List_Type.STRING_LIST, BenchmarkResult.SER_MODE.SER, valcount).divideScore(divisor.getBmResult(List_Type.STRING_LIST, BenchmarkResult.SER_MODE.SER, valcount).getScore());
            ratioResSet.getBmResult(List_Type.STRING_LIST, BenchmarkResult.SER_MODE.DESER, valcount).divideScore(divisor.getBmResult(List_Type.STRING_LIST, BenchmarkResult.SER_MODE.DESER, valcount).getScore());
        }

        return ratioResSet;
    }

    private static Map<Integer, BenchmarkResult> calcAverageResultMap_container(List<Map<Integer, BenchmarkResult>> mapList) {
        Map<Integer, BenchmarkResult> resultMap = new HashMap<>();
        double mapCount = mapList.size();

        double sumVals = 0;
        double  sumErrs = 0;
        String methodName = "";
        for(Integer key : mapList.get(0).keySet()) {
            sumVals = 0;
            sumErrs = 0;
            methodName = mapList.get(0).get(key).getBenchmarkName();
            for(Map<Integer, BenchmarkResult> map : mapList) {
                sumVals += map.get(key).getScore();
                sumErrs += map.get(key).getScoreError();
            }
            BenchmarkResult bmr = new BenchmarkResult(sumVals/mapCount, sumErrs/mapCount, methodName);
            resultMap.put(key, bmr);
        }

        return resultMap;
    }

    private static double calcAvgScore(List<Double> scores){
        double sum = scores.stream().mapToDouble(Double::doubleValue).sum();
        return sum / scores.size();
    }

//    private static JFreeChart createLineChart_yLogAxis_container(List<String> filenames, BenchmarkResult.SER_MODE sermode, String bmNameContains, String title, String xaxis, String yaxis) {
//        List<BenchmarkResultsSet> bmResultSets = new LinkedList<>();
//
//        for (String filename : filenames) {
//            bmResultSets.add( createBenchmarkResultSet(filename, sermode, bmNameContains) );
//        }
//        XYDataset xydataset = createXYDataset(bmResultSets);
//        return createLineChart_yLogAxis_container( xydataset, title, xaxis, yaxis );
//    }

    private static BmResultsSersize createBenchmarkResultSet(String filepathname, BenchmarkResult.SER_MODE sermode, String bmNameContains) {
//        System.out.println("filename: " + filepathname);
        int fn_fileparts = filepathname.split("/").length;
        String filename = filepathname.split("/")[fn_fileparts-1];
        String benchmarkJSONString = "";
        BmResultsSersize bmrs = new BmResultsSersize();
        try {
            benchmarkJSONString = getStringFromFile(filepathname);
            bmrs = new BmResultsSersize(benchmarkJSONString, sermode, bmNameContains);
//            bmrs.setMachineName(filename.split("/")[4].split("-")[4]);
            bmrs.setDateTime(filename.substring(0,8));
            if(bmrs.getMachineName().equals("machinename")) {
                bmrs.setMachineName(filename.split("-")[4]);
            }
        } catch (IOException e) {
            System.out.println("Could not read file: '" + filepathname + "'. Skipping benchmark.");
        }
        return bmrs;
    }

    private static BmResultsContainer createBmResultsContainer(String filepathname) {
//        System.out.println("filename: " + filepathname);
        int fn_fileparts = filepathname.split("/").length;
        String filename = filepathname.split("/")[fn_fileparts-1];
        String benchmarkJSONString = "";
        BmResultsContainer bmrs = new BmResultsContainer();
        try {
            benchmarkJSONString = getStringFromFile(filepathname);
            bmrs = new BmResultsContainer(benchmarkJSONString);
//            bmrs.setMachineName(filename.split("/")[4].split("-")[4]);
            bmrs.setDateTime(filename.substring(0,8));
            bmrs.setMachineName(filename.split("-")[4]);
            bmrs.setMachine(Helper.stringToMachine(bmrs.getMachineName()) );
        } catch (IOException e) {
            System.out.println("Could not read file: '" + filepathname + "'. Skipping benchmark.");
        }
        return bmrs;
    }

    private static CategoryDataset createCategoryDataset_multiProtocol_container(List_Type listtype, List<BmResultsContainer> bmResults, List<Container_ValCount> valcounts) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for(BmResultsContainer bmrt : bmResults) {
            for(Container_ValCount valcount : valcounts) {
                double valSer = bmrt.getBmResult(listtype, BenchmarkResult.SER_MODE.SER, valcount).getScore();
                double valDeser = bmrt.getBmResult(listtype, BenchmarkResult.SER_MODE.DESER, valcount).getScore();
                // unique key per value in group, value per group
                dataset.addValue(valSer, bmrt.getMachine().shortname + "-SER", valcount.valcount+"");
                dataset.addValue(valDeser, bmrt.getMachine().shortname + "-DESER", valcount.valcount+"");
//                // value, one bar with both values, one bargroup
//                dataset.addValue(valSer, totype.displayname + "-SER", bmrt.getMachineName().toLowerCase());
//                dataset.addValue(valDeser, totype.displayname + "-DESER", bmrt.getMachineName().toLowerCase());
                System.out.println("rowkey: " + bmrt.getMachine().shortname + "-DESER -- colkey: " + valcount.valcount);
            }
        }

        return dataset;
    }


// -------------------------------------------------
// ------------ Plotting Main Functions ------------
// -------------------------------------------------

    public static JFreeChart plot_CompareSersize_container(List_Type containerType) {
        List<String> fileList = new LinkedList<>();
        List<String> fileListPlain = ResultFileIO.getResultFiles_SerSize(containerType.name);
        fileList.addAll(fileListPlain);
        List<BmResultsSersize> bmResSets = createBenchmarkResultsSetListFromFilenames(fileList, BenchmarkResult.SER_MODE.ANY, "*");
        XYDataset xydataset = createXYDataset(bmResSets, ResultFileIO.Benchmarker_Key.BMNAME);

        String title = "Comparing Encoding Size of Filled " + containerType.name.toUpperCase() + "-Lists";
        String xaxis = "Value Count in " + containerType.name.toUpperCase() + "-List";
        String yaxis = "Size of Encoded " + containerType.name.toUpperCase() + "-List in Bytes";
        return createLineChart_yLogAxis_container( xydataset, title, xaxis, yaxis, 10, 11000000 );
    }


    public static JFreeChart plot_singleProtocol_container(SER_PROTOCOL serprotocol, BenchmarkResult.SER_MODE sermode, String machineFolder) {
        BmResultsContainer avgBmResSet = createSingleprotocolAvg_container(serprotocol, machineFolder);
        XYDataset xydataset = createXYDataset_singleProtocol_container(avgBmResSet, sermode);

        String title = "Comparing " + serprotocol.toString().toUpperCase() + " Speed of container types on Machine " + machineFolder;
        String xaxis = "Value Count in List";
        String yaxis = sermode.toString() + " Speed of " + serprotocol.getShortName().toUpperCase() + " in us";
        return createLineChart_yLogAxis_container( xydataset, title, xaxis, yaxis );
    }

    public static JFreeChart plot_compareProtocols_ratio_container(BenchmarkResult.SER_MODE sermode, List_Type listtype, String machineFolder) {
        List<BmResultsContainer> bmResSets = new LinkedList<>();

        BmResultsContainer bmResultJava = createSingleprotocolAvg_container(SER_PROTOCOL.JAVA, machineFolder);
        bmResSets.add( createSingleprotocolAvg_container(SER_PROTOCOL.JAVA, machineFolder) );
        bmResSets.add( createSingleprotocolAvg_container(SER_PROTOCOL.PROTOBUF, machineFolder) );
        bmResSets.add( createSingleprotocolAvg_container(SER_PROTOCOL.PROTOSTUFF, machineFolder) );
        bmResSets.add( createSingleprotocolAvg_container(SER_PROTOCOL.AVRO, machineFolder) );
        bmResSets.add( createSingleprotocolAvg_container(SER_PROTOCOL.AVRO_JSON, machineFolder) );
        bmResSets.add( createSingleprotocolAvg_container(SER_PROTOCOL.FST, machineFolder) );
        bmResSets.add( createSingleprotocolAvg_container(SER_PROTOCOL.KRYO, machineFolder) );
        bmResSets.add( createSingleprotocolAvg_container(SER_PROTOCOL.HESSIAN, machineFolder) );

        List<BmResultsContainer> bmResSets_ratio = new LinkedList<>();

        for(BmResultsContainer bmResult : bmResSets) {
            bmResSets_ratio.add( createBmResultsContainer_ratio(bmResult, bmResultJava) );
        }

        XYDataset xydataset = createXYDataset_multiProtocol_container(bmResSets_ratio, listtype, sermode);

        String title = "Comparing " + sermode.toString().toUpperCase() + " Speed of " + listtype.name.toUpperCase() + "-Lists on Machine " + machineFolder;
        String xaxis = "Value Count in " + listtype.name.toUpperCase() + "-List";
        String yaxis = "Ratio Prot./JOS " + sermode.toString() + " Speed of " + listtype.name.toUpperCase() + "-List on " + machineFolder;
        return createLineChart_container( xydataset, title, xaxis, yaxis, -.5, 3 );
    }

    public static JFreeChart plot_compareProtocols_container_X(BenchmarkResult.SER_MODE sermode, List_Type listtype, String machineFolder) {
        List<BmResultsContainer> bmResSets = new LinkedList<>();

//        BmResultsContainer bmResultJava = createSingleprotocolAvg_container(SER_PROTOCOL.JAVA, machineFolder);
//        bmResSets.add( createSingleprotocolAvg_container(SER_PROTOCOL.JAVA, machineFolder) );
//        bmResSets.add( createSingleprotocolAvg_container(SER_PROTOCOL.PROTOBUF, machineFolder) );
        bmResSets.add( createSingleprotocolAvg_container(SER_PROTOCOL.PROTOSTUFF, machineFolder) );
//        bmResSets.add( createSingleprotocolAvg_container(SER_PROTOCOL.AVRO, machineFolder) );
//        bmResSets.add( createSingleprotocolAvg_container(SER_PROTOCOL.AVRO_JSON, machineFolder) );
        bmResSets.add( createSingleprotocolAvg_container(SER_PROTOCOL.FST, machineFolder) );
        bmResSets.add( createSingleprotocolAvg_container(SER_PROTOCOL.KRYO, machineFolder) );
//        bmResSets.add( createSingleprotocolAvg_container(SER_PROTOCOL.HESSIAN, machineFolder) );

        List<BmResultsContainer> bmResSets_ratio = new LinkedList<>();

        for(BmResultsContainer bmResult : bmResSets) {
//            bmResSets_ratio.add( createBmResultsContainer_ratio(bmResult, bmResultJava) );
            bmResSets_ratio.add( bmResult );
        }

        XYDataset xydataset = createXYDataset_multiProtocol_container(bmResSets_ratio, listtype, sermode);

        String title = "Comparing " + sermode.toString().toUpperCase() + " Speed of " + listtype.name.toUpperCase() + "-Lists on Machine " + machineFolder;
        String xaxis = "Value Count in " + listtype.name.toUpperCase() + "-List";
//        String yaxis = "Ratio Prot./JOS " + sermode.toString() + " Speed of " + listtype.name.toUpperCase() + "-List on " + machineFolder + " in us";
        String yaxis = sermode.toString() + " Speed of " + listtype.name.toUpperCase() + "-List on " + machineFolder + " in us";
        return createLineChart_yLogAxis_container( xydataset, title, xaxis, yaxis, .05, 110000 );
    }

    public static JFreeChart plot_compareProtocols_container(BenchmarkResult.SER_MODE sermode, List_Type listtype, String machineFolder) {
        List<BmResultsContainer> bmResSets = new LinkedList<>();

        bmResSets.add( createSingleprotocolAvg_container(SER_PROTOCOL.JAVA, machineFolder) );
        bmResSets.add( createSingleprotocolAvg_container(SER_PROTOCOL.PROTOBUF, machineFolder) );
        bmResSets.add( createSingleprotocolAvg_container(SER_PROTOCOL.PROTOSTUFF, machineFolder) );
        bmResSets.add( createSingleprotocolAvg_container(SER_PROTOCOL.AVRO, machineFolder) );
        bmResSets.add( createSingleprotocolAvg_container(SER_PROTOCOL.AVRO_JSON, machineFolder) );
        bmResSets.add( createSingleprotocolAvg_container(SER_PROTOCOL.FST, machineFolder) );
        bmResSets.add( createSingleprotocolAvg_container(SER_PROTOCOL.KRYO, machineFolder) );
        bmResSets.add( createSingleprotocolAvg_container(SER_PROTOCOL.HESSIAN, machineFolder) );

        XYDataset xydataset = createXYDataset_multiProtocol_container(bmResSets, listtype, sermode);

        String title = "Comparing " + sermode.toString().toUpperCase() + " Speed of " + listtype.name.toUpperCase() + "-Lists on Machine " + machineFolder;
        String xaxis = "Value Count in " + listtype.name.toUpperCase() + "-List";
        String yaxis = sermode.toString() + " Speed of " + listtype.name.toUpperCase() + "-List on " + machineFolder + " in us";
        return createLineChart_yLogAxis_container( xydataset, title, xaxis, yaxis );
    }

    public static JFreeChart plot_compareMachines_container(SER_PROTOCOL serprotocol, BenchmarkResult.SER_MODE sermode, List_Type containerType) {
        List<BmResultsContainer> bmResSets = new LinkedList<>();
        bmResSets.add( createSingleprotocolAvg_container(serprotocol, "dot1") );
        bmResSets.add( createSingleprotocolAvg_container(serprotocol, "dot13") );
        bmResSets.add( createSingleprotocolAvg_container(serprotocol, "dot13-cpulock") );
        XYDataset xydataset = createXYDataset_compareMachines_container(bmResSets, containerType, sermode);

        String title = "Comparing " + serprotocol.getShortName().toUpperCase() + " Encoding Speed of " + containerType.name.toUpperCase() + "-Lists on Different Machines";
        String xaxis = "Value Count in " + containerType.name.toUpperCase() + "-List";
        String yaxis = serprotocol.getShortName().toUpperCase() + " " + sermode.toString() + " speed of " + containerType.name.toUpperCase() + "-List in us";
        return createLineChart_yLogAxis_container( xydataset, title, xaxis, yaxis );
    }

    public static JFreeChart plot_compareMachines_barchart_container(SER_PROTOCOL serprotocol, BenchmarkResult.SER_MODE sermode, List_Type containerType) {
        List<BmResultsContainer> bmResSets = new LinkedList<>();
        bmResSets.add( createSingleprotocolAvg_container(serprotocol, "dot1") );
        bmResSets.add( createSingleprotocolAvg_container(serprotocol, "dot13") );
        bmResSets.add( createSingleprotocolAvg_container(serprotocol, "dot13-cpulock") );

        List<Container_ValCount> valcounts = Arrays.asList(new Container_ValCount[]{
                Container_ValCount.V10,
                Container_ValCount.V100,
                Container_ValCount.V500,
                Container_ValCount.V1000,
                Container_ValCount.V10000,
                Container_ValCount.V100000 });

        CategoryDataset categoryDataset = createCategoryDataset_multiProtocol_container( containerType, bmResSets, valcounts );

        KeyToGroupMap ktgm = new KeyToGroupMap( ResultFileIO.Machine.DCACHE_DOT1.shortname);

        System.out.println("bmresset size: " + bmResSets.size());
        for(BmResultsContainer b : bmResSets) {
            System.out.println("machine: " + b.getMachine().shortname);
        }

        // mapping row key (unique keys within group) groups that form a bar
        ktgm.mapKeyToGroup(ResultFileIO.Machine.DCACHE_DOT1.shortname + "-SER", ResultFileIO.Machine.DCACHE_DOT1.shortname);
        ktgm.mapKeyToGroup(ResultFileIO.Machine.DCACHE_DOT1.shortname + "-DESER", ResultFileIO.Machine.DCACHE_DOT1.shortname);
        ktgm.mapKeyToGroup(ResultFileIO.Machine.DCACHE_DOT13.shortname + "-SER", ResultFileIO.Machine.DCACHE_DOT13.shortname);
        ktgm.mapKeyToGroup(ResultFileIO.Machine.DCACHE_DOT13.shortname + "-DESER", ResultFileIO.Machine.DCACHE_DOT13.shortname);
        ktgm.mapKeyToGroup(ResultFileIO.Machine.DCACHE_DOT13_CPULOCK.shortname + "-SER", ResultFileIO.Machine.DCACHE_DOT13_CPULOCK.shortname);
        ktgm.mapKeyToGroup(ResultFileIO.Machine.DCACHE_DOT13_CPULOCK.shortname + "-DESER", ResultFileIO.Machine.DCACHE_DOT13_CPULOCK.shortname);

        String title = "Comparing (De)Serialization Speed of Composites on Different Machines";
        String xaxis = "Composite Object";
        String yaxis = "(DE)SER Speed of " + serprotocol.shortName.toUpperCase() + " on Different Machines in us";
        return createStackedBarChart_testobj( categoryDataset, ktgm, title, xaxis, yaxis, true, true );
    }
}
