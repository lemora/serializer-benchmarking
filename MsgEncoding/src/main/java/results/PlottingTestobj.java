package results;

import org.jfree.chart.JFreeChart;
import org.jfree.data.KeyToGroupMap;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.statistics.DefaultStatisticalCategoryDataset;
import serializer.Helper;
import serializer.SER_PROTOCOL;

import java.io.IOException;
import java.util.*;

import static results.PlottingCharts.createStackedBarChart_testobj;
import static results.ResultFileIO.getStringFromFile;
import static results.BmResultsTestobj.*;
import static results.ResultFileIO.*;



public class PlottingTestobj {

    private static CategoryDataset createStatisticalCategoryDataset(BmResultsSersize benchmarkResults) {
        List<BenchmarkResult> resultList = benchmarkResults.getBenchmarks();
        DefaultStatisticalCategoryDataset dataset = new DefaultStatisticalCategoryDataset();

        for(BenchmarkResult res : resultList) {
            dataset.add(res.getScore(), res.getScoreError(), res.getSerMode(), res.getSerializedStructName());
        }

        return dataset;
    }

    private static CategoryDataset createCategoryDataset(BmResultsSersize... benchmarkResults) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for( int singleResultList = 0; singleResultList < benchmarkResults.length; singleResultList++) {
            String benchmarkerName = benchmarkResults[singleResultList].getBenchmarkerName();
            List<BenchmarkResult> resultList = benchmarkResults[singleResultList].getBenchmarks();

            for(int singleResult = 0; singleResult < resultList.size(); singleResult++) {
                // value, row key, col key
                String benchmarkerAndMode = benchmarkerName + "-" + resultList.get(singleResult).getSerMode();
                dataset.addValue(resultList.get(singleResult).getScore(), benchmarkerAndMode, resultList.get(singleResult).getSerializedStructName());
                Helper.debugPrint(resultList.get(singleResult).getScore() + " | " + benchmarkerAndMode + " | " + benchmarkerName);
            }
        }
        return dataset;
    }

    private static DefaultStatisticalCategoryDataset createStatisticalCategoryDataset_singleProtocol_testobj(BmResultsTestobj bmResult) {
        DefaultStatisticalCategoryDataset dataset = new DefaultStatisticalCategoryDataset();

        String benchmarkerName = bmResult.getBenchmarkerName();
        for(BmResultsTestobj.Testobj_Type objType : BmResultsTestobj.Testobj_Type.values()) {
            double valSer = bmResult.getResults_ser().get(objType).getScore();
            double errSer = bmResult.getResults_ser().get(objType).getScoreError();
            double valDeser = bmResult.getResults_deser().get(objType).getScore();
            double errDeser = bmResult.getResults_deser().get(objType).getScoreError();
            dataset.add(valSer, errSer, benchmarkerName + "-SER", objType.displayname.toUpperCase());
            dataset.add(valDeser, errDeser, benchmarkerName + "-DESER", objType.displayname.toUpperCase());
        }
//        bmResult.getResults_ser().entrySet().stream().forEach(entry -> dataset.addValue(entry.getValue().getScore(), benchmarkerName + "-SER", entry.getKey().fieldname.toUpperCase()));
//        bmResult.getResults_deser().entrySet().stream().forEach(entry -> dataset.addValue(entry.getValue().getScore(), benchmarkerName + "-DESER", entry.getKey().fieldname.toUpperCase()));

        return dataset;
    }

    private static CategoryDataset createCategoryDataset_singleProtocol_testobj(BmResultsTestobj bmResult) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        String benchmarkerName = bmResult.getBenchmarkerName();
        for(BmResultsTestobj.Testobj_Type objType : BmResultsTestobj.Testobj_Type.values()) {
            double valSer = bmResult.getResults_ser().get(objType).getScore();
            double valDeser = bmResult.getResults_deser().get(objType).getScore();
            dataset.addValue(valSer, benchmarkerName + "-SER", objType.displayname.toUpperCase());
            dataset.addValue(valDeser, benchmarkerName + "-DESER", objType.displayname.toUpperCase());
        }
        return dataset;
    }

    private static CategoryDataset createCategoryDataset_multiProtocol_testobj(List<BmResultsTestobj> bmResults, BmResultsTestobj.Testobj_Type totype) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for(BmResultsTestobj bmrt : bmResults) {
            double valSer = bmrt.getBmResult(BenchmarkResult.SER_MODE.SER, totype).getScore();
            double valDeser = bmrt.getBmResult(BenchmarkResult.SER_MODE.DESER, totype).getScore();
            // value,
            dataset.addValue(valSer, bmrt.getSerprotocol().abbrev+"-SER", bmrt.getSerprotocol().abbrev);
            dataset.addValue(valDeser, bmrt.getSerprotocol().abbrev+"-DESER", bmrt.getSerprotocol().abbrev);
            System.out.println("rowkey: " + bmrt.getSerprotocol().abbrev+"-DESER");
        }

        return dataset;
    }

    private static CategoryDataset createCategoryDataset_multiProtocol_testobj(List<BmResultsTestobj> bmResults, List<Testobj_Type> totypes) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for(BmResultsTestobj bmrt : bmResults) {
            for(Testobj_Type totype : totypes) {
                double valSer = bmrt.getBmResult(BenchmarkResult.SER_MODE.SER, totype).getScore();
                double valDeser = bmrt.getBmResult(BenchmarkResult.SER_MODE.DESER, totype).getScore();
                // unique key per value in group, value per group
                dataset.addValue(valSer, bmrt.getMachine().shortname + "-SER", totype.displayname);
                dataset.addValue(valDeser, bmrt.getMachine().shortname + "-DESER", totype.displayname);
//                // value, one bar with both values, one bargroup
//                dataset.addValue(valSer, totype.displayname + "-SER", bmrt.getMachineName().toLowerCase());
//                dataset.addValue(valDeser, totype.displayname + "-DESER", bmrt.getMachineName().toLowerCase());
                System.out.println("rowkey: " + bmrt.getMachine().shortname + "-DESER -- colkey: " + totype.displayname);
            }
        }

        return dataset;
    }


    private static List<BmResultsTestobj> createBmResultsTestobjListFromFilenames(List<String> filenames) {
        List<BmResultsTestobj> bmResultTestobjList = new LinkedList<>();
        List<String> bmNames = new LinkedList<>();

        for (String filename : filenames) {
            BmResultsTestobj bmrs = createBmResultsTestobj(filename);
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
            bmResultTestobjList.add(bmrs);
        }
        return bmResultTestobjList;
    }

    private static BmResultsTestobj createBmResultsTestobj(String filepathname) {
        System.out.println("filename: " + filepathname);
        int fn_fileparts = filepathname.split("/").length;
        String filename = filepathname.split("/")[fn_fileparts-1];
        String benchmarkJSONString = "";
        BmResultsTestobj bmrs = new BmResultsTestobj();
        try {
            benchmarkJSONString = getStringFromFile(filepathname);
            bmrs = new BmResultsTestobj(benchmarkJSONString);
            bmrs.setMachineName(filename.split("-")[4]);
            bmrs.setDateTime(filename.substring(0,8));
            bmrs.setMachineName(filename.split("-")[4]);
            System.out.println("Machinename: " + bmrs.getMachineName());
        } catch (IOException e) {
            System.out.println("Could not read file: '" + filepathname + "'. Skipping benchmark.");
        }
        return bmrs;
    }

    private static BmResultsTestobj createAverageBmResultsTestobj(List<BmResultsTestobj> bmResultSets) {
        assert bmResultSets.size() == 5;
        BmResultsTestobj avgResSet = new BmResultsTestobj(bmResultSets.get(0));

        List<Map<BmResultsTestobj.Testobj_Type, BenchmarkResult>> map_results_ser = new LinkedList<Map<BmResultsTestobj.Testobj_Type, BenchmarkResult>>();
        List<Map<BmResultsTestobj.Testobj_Type, BenchmarkResult>> map_results_deser = new LinkedList<Map<BmResultsTestobj.Testobj_Type, BenchmarkResult>>();

        for(BmResultsTestobj bmrt : bmResultSets) {
            map_results_ser.add(bmrt.getResults_ser());
            map_results_deser.add(bmrt.getResults_deser());
        }

        // set results
        avgResSet.setResults_ser( calcAverageResultMap_testobj(map_results_ser) );
        avgResSet.setResults_deser( calcAverageResultMap_testobj(map_results_deser) );

        return avgResSet;
    }


    private static Map<BmResultsTestobj.Testobj_Type, BenchmarkResult> calcAverageResultMap_testobj(List<Map<Testobj_Type, BenchmarkResult>> mapList) {
        Map<BmResultsTestobj.Testobj_Type, BenchmarkResult> resultMap = new HashMap<>();
        double mapCount = mapList.size();

        double sumVals = 0;
        double  sumErrs = 0;
        String methodName = "";
        for(BmResultsTestobj.Testobj_Type key : mapList.get(0).keySet()) {
            sumVals = 0;
            sumErrs = 0;
            methodName = mapList.get(0).get(key).getBenchmarkName();
            for(Map<BmResultsTestobj.Testobj_Type, BenchmarkResult> map : mapList) {
                sumVals += map.get(key).getScore();
                sumErrs += map.get(key).getScoreError();
            }
            BenchmarkResult bmr = new BenchmarkResult(sumVals/mapCount, sumErrs/mapCount, methodName);
            resultMap.put(key, bmr);
        }

        return resultMap;
    }


// -------------------------------------------------
// ------------ Plotting Main Functions ------------
// -------------------------------------------------

 public static JFreeChart plot_singleProtocol_testobj(SER_PROTOCOL serprotocol, String machineFolder) {
        String typeFolder = "testobj";
        List<String> fileList;
        fileList = ResultFileIO.getNewResultFilesforType( machineFolder + "/"+typeFolder, serprotocol, "*" );

        List<BmResultsTestobj> bmResSets = createBmResultsTestobjListFromFilenames(fileList);
        BmResultsTestobj avgBmResSet = createAverageBmResultsTestobj( bmResSets );

        CategoryDataset categoryDataset = createCategoryDataset_singleProtocol_testobj(avgBmResSet);

        KeyToGroupMap ktgm = new KeyToGroupMap(serprotocol);
        ktgm.mapKeyToGroup(serprotocol + "-SER", serprotocol);
        ktgm.mapKeyToGroup(serprotocol + "-DESER", serprotocol);

        String title = "Comparing " + serprotocol.toString().toUpperCase() + " Speed of container types on Machine " + machineFolder;
        String xaxis = "Example Objects";
        String yaxis = "(DE)SER Speed of " + serprotocol.getShortName().toUpperCase() + " in us";
        return createStackedBarChart_testobj( categoryDataset, ktgm, title, xaxis, yaxis );
//        return createStackedBarChart_errorbars_testobj( categoryDataset, ktgm, title, xaxis, yaxis );
    }

    private static BmResultsTestobj createSingleprotocolAvg_testobj(String machineFolder, SER_PROTOCOL serprot) {
        String typeFolder = "testobj";
        List<String> fileList;
        fileList = ResultFileIO.getNewResultFilesforType( machineFolder + "/"+typeFolder, serprot, "*" );

        List<BmResultsTestobj> bmResSets = createBmResultsTestobjListFromFilenames(fileList);
        BmResultsTestobj avgBmResSet = createAverageBmResultsTestobj( bmResSets );
        return avgBmResSet;
    }

    public static JFreeChart plot_compareProtocols_testobj(String machineFolder, BmResultsTestobj.Testobj_Type totype) {
        List<BmResultsTestobj> bmResSets = new LinkedList<>();

        bmResSets.add( createSingleprotocolAvg_testobj(machineFolder, SER_PROTOCOL.AVRO) );
        bmResSets.add( createSingleprotocolAvg_testobj(machineFolder, SER_PROTOCOL.AVRO_JSON) );
        bmResSets.add( createSingleprotocolAvg_testobj(machineFolder, SER_PROTOCOL.FST) );
        bmResSets.add( createSingleprotocolAvg_testobj(machineFolder, SER_PROTOCOL.HESSIAN) );
        bmResSets.add( createSingleprotocolAvg_testobj(machineFolder, SER_PROTOCOL.JAVA) );
        bmResSets.add( createSingleprotocolAvg_testobj(machineFolder, SER_PROTOCOL.KRYO) );
        bmResSets.add( createSingleprotocolAvg_testobj(machineFolder, SER_PROTOCOL.PROTOBUF) );
        bmResSets.add( createSingleprotocolAvg_testobj(machineFolder, SER_PROTOCOL.PROTOSTUFF) );

        CategoryDataset categoryDataset = createCategoryDataset_multiProtocol_testobj( bmResSets, totype );

        KeyToGroupMap ktgm = new KeyToGroupMap("x");

        ktgm.mapKeyToGroup("AV-SER", "x");
        ktgm.mapKeyToGroup("AV-DESER", "x");
        ktgm.mapKeyToGroup("AVJ-SER", "x");
        ktgm.mapKeyToGroup("AVJ-DESER", "x");

        ktgm.mapKeyToGroup("FST-SER", "x");
        ktgm.mapKeyToGroup("FST-DESER", "x");

        ktgm.mapKeyToGroup("HES-SER", "x");
        ktgm.mapKeyToGroup("HES-DESER", "x");

        ktgm.mapKeyToGroup("JOS-SER", "x");
        ktgm.mapKeyToGroup("JOS-DESER", "x");

        ktgm.mapKeyToGroup("KRY-SER", "x");
        ktgm.mapKeyToGroup("KRY-DESER", "x");

        ktgm.mapKeyToGroup("PB-SER", "x");
        ktgm.mapKeyToGroup("PB-DESER", "x");
        ktgm.mapKeyToGroup("PSR-SER", "x");
        ktgm.mapKeyToGroup("PSR-DESER", "x");


        String title = "Comparing (De)Serialization Speed of PoolManagerPoolUpMessage on " + machineFolder;
        String xaxis = "Serialization Protocol";
        String yaxis = "(DE)SER Speed of " +totype.displayname+ " in us";
        return createStackedBarChart_testobj( categoryDataset, ktgm, title, xaxis, yaxis );
//        return createStackedBarChart_errorbars_testobj( categoryDataset, ktgm, title, xaxis, yaxis );
    }

    public static JFreeChart plot_compareProtocols_testobj_x(String machineFolder, BmResultsTestobj.Testobj_Type totype) {
        List<BmResultsTestobj> bmResSets = new LinkedList<>();

        bmResSets.add( createSingleprotocolAvg_testobj(machineFolder, SER_PROTOCOL.KRYO) );
        bmResSets.add( createSingleprotocolAvg_testobj(machineFolder, SER_PROTOCOL.FST) );
        bmResSets.add( createSingleprotocolAvg_testobj(machineFolder, SER_PROTOCOL.PROTOSTUFF) );

        CategoryDataset categoryDataset = createCategoryDataset_multiProtocol_testobj( bmResSets, totype );

        KeyToGroupMap ktgm = new KeyToGroupMap("x");

        ktgm.mapKeyToGroup("KRY-SER", "x");
        ktgm.mapKeyToGroup("KRY-DESER", "x");
        ktgm.mapKeyToGroup("FST-SER", "x");
        ktgm.mapKeyToGroup("FST-DESER", "x");
        ktgm.mapKeyToGroup("PSR-SER", "x");
        ktgm.mapKeyToGroup("PSR-DESER", "x");


        String title = "Comparing (De)Serialization Speed of PoolManagerPoolUpMessage on " + machineFolder;
        String xaxis = "Serialization Protocol";
        String yaxis = "(DE)SER Speed of " +totype.displayname+ " in us";
        return createStackedBarChart_testobj( categoryDataset, ktgm, title, xaxis, yaxis );
//        return createStackedBarChart_errorbars_testobj( categoryDataset, ktgm, title, xaxis, yaxis );
    }

    public static JFreeChart plot_compareMachines_testobj(SER_PROTOCOL serprotocol) {
        List<BmResultsTestobj> bmResSets = new LinkedList<>();
        bmResSets.add( createSingleprotocolAvg_testobj("dot1", serprotocol) );
        bmResSets.add( createSingleprotocolAvg_testobj("dot13", serprotocol) );
        bmResSets.add( createSingleprotocolAvg_testobj("dot13-cpulock", serprotocol) );

        List<Testobj_Type> totypes = new LinkedList<>(Arrays.asList(
                new Testobj_Type[]{Testobj_Type.MSG,
                        Testobj_Type.MSG_RW01,
                        Testobj_Type.MSG_RW02,
                        Testobj_Type.MSG_RW03,
                        Testobj_Type.MSG_RW04,
                        Testobj_Type.MSG_RW05} ));

        CategoryDataset categoryDataset = createCategoryDataset_multiProtocol_testobj( bmResSets, totypes );

        KeyToGroupMap ktgm = new KeyToGroupMap( Machine.DCACHE_DOT1.shortname);

        System.out.println("bmresset size: " + bmResSets.size());
        for(BmResultsTestobj b : bmResSets) {
            System.out.println("machine: " + b.getMachine().shortname);
        }

        // mapping row key (unique keys within group) groups that form a bar
        ktgm.mapKeyToGroup(Machine.DCACHE_DOT1.shortname + "-SER", Machine.DCACHE_DOT1.shortname);
        ktgm.mapKeyToGroup(Machine.DCACHE_DOT1.shortname + "-DESER", Machine.DCACHE_DOT1.shortname);
        ktgm.mapKeyToGroup(Machine.DCACHE_DOT13.shortname + "-SER", Machine.DCACHE_DOT13.shortname);
        ktgm.mapKeyToGroup(Machine.DCACHE_DOT13.shortname + "-DESER", Machine.DCACHE_DOT13.shortname);
        ktgm.mapKeyToGroup(Machine.DCACHE_DOT13_CPULOCK.shortname + "-SER", Machine.DCACHE_DOT13_CPULOCK.shortname);
        ktgm.mapKeyToGroup(Machine.DCACHE_DOT13_CPULOCK.shortname + "-DESER", Machine.DCACHE_DOT13_CPULOCK.shortname);

        String title = "Comparing (De)Serialization Speed of Composites on Different Machines";
        String xaxis = "Composite Object";
        String yaxis = "(DE)SER Speed of " + serprotocol.shortName.toUpperCase() + " on Different Machines in us";
        return createStackedBarChart_testobj( categoryDataset, ktgm, title, xaxis, yaxis, true, false );
    }

}
