package results;

import org.jfree.chart.JFreeChart;
import org.jfree.data.KeyToGroupMap;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import serializer.SER_PROTOCOL;

import java.io.IOException;
import java.util.*;

import static results.PlottingCharts.createStackedBarChart_pmpum;
import static results.ResultFileIO.getStringFromFile;

public class PlottingPMPUM {

    private static List<BmResultsPMPUM> createBmResultsPMPUMListFromFilenames(List<String> filenames) {
        List<BmResultsPMPUM> bmResultTestobjList = new LinkedList<>();
        List<String> bmNames = new LinkedList<>();

        for (String filename : filenames) {
            BmResultsPMPUM bmrs = createBmResultsPMPUM(filename);
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

    private static BmResultsPMPUM createBmResultsPMPUM(String filepathname) {
        System.out.println("bm result from filename: " + filepathname);
        int fn_fileparts = filepathname.split("/").length;
        String filename = filepathname.split("/")[fn_fileparts-1];
        String benchmarkJSONString = "";
        BmResultsPMPUM bmrs = new BmResultsPMPUM();
        try {
            benchmarkJSONString = getStringFromFile(filepathname);
            bmrs = new BmResultsPMPUM(benchmarkJSONString);
            bmrs.setMachineName(filename.split("-")[4]);
            bmrs.setDateTime(filename.substring(0,8));
            if(bmrs.getMachineName().equals("machinename")) {
                bmrs.setMachineName(filename.split("-")[4]);
            }
        } catch (IOException e) {
            System.out.println("Could not read file: '" + filepathname + "'. Skipping benchmark.");
        }
        return bmrs;
    }

    private static BmResultsPMPUM createAverageBmResultsPMPUM(List<BmResultsPMPUM> bmResultSets) {
        assert bmResultSets.size() == 5;
        BmResultsPMPUM avgResSet = new BmResultsPMPUM();

        List<Map<SER_PROTOCOL, BenchmarkResult>> map_results_ser = new LinkedList<Map<SER_PROTOCOL, BenchmarkResult>>();
        List<Map<SER_PROTOCOL, BenchmarkResult>> map_results_deser = new LinkedList<Map<SER_PROTOCOL, BenchmarkResult>>();

        for(BmResultsPMPUM bmrt : bmResultSets) {
            map_results_ser.add(bmrt.getResults_ser());
            map_results_deser.add(bmrt.getResults_deser());
        }

        // set results
        avgResSet.setResults_ser( calcAverageResultMap_pmpum(map_results_ser) );
        avgResSet.setResults_deser( calcAverageResultMap_pmpum(map_results_deser) );

        return avgResSet;
    }


    private static Map<SER_PROTOCOL, BenchmarkResult> calcAverageResultMap_pmpum(List<Map<SER_PROTOCOL, BenchmarkResult>> mapList) {
        Map<SER_PROTOCOL, BenchmarkResult> resultMap = new HashMap<>();
        double mapCount = mapList.size();

        double sumVals = 0;
        double  sumErrs = 0;
        String methodName = "";
        for(SER_PROTOCOL key : mapList.get(0).keySet()) {
            sumVals = 0;
            sumErrs = 0;
            methodName = mapList.get(0).get(key).getBenchmarkName();
            for(Map<SER_PROTOCOL, BenchmarkResult> map : mapList) {
                sumVals += map.get(key).getScore();
                sumErrs += map.get(key).getScoreError();
            }
            BenchmarkResult bmr = new BenchmarkResult(sumVals/mapCount, sumErrs/mapCount, methodName);
            resultMap.put(key, bmr);
        }

        return resultMap;
    }

    private static CategoryDataset createCategoryDataset_multiProtocol_pmpum(BmResultsPMPUM bmResult) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<SER_PROTOCOL> serprotocols = new LinkedList<>();
        serprotocols.add(SER_PROTOCOL.FST);
        serprotocols.add(SER_PROTOCOL.HESSIAN);
        serprotocols.add(SER_PROTOCOL.JAVA);
        serprotocols.add(SER_PROTOCOL.KRYO);
        serprotocols.add(SER_PROTOCOL.PROTOSTUFF);

        String benchmarkerName = bmResult.getBenchmarkerName();
        for(SER_PROTOCOL serprot : serprotocols) {
            double valSer = bmResult.getResults_ser().get(serprot).getScore();
            double valDeser = bmResult.getResults_deser().get(serprot).getScore();
            dataset.addValue(valSer, serprot.abbrev.toUpperCase() + "-SER", serprot.abbrev.toUpperCase());
            dataset.addValue(valDeser, serprot.abbrev.toUpperCase() + "-DESER", serprot.abbrev.toUpperCase());
        }
        return dataset;
    }

    private static CategoryDataset createCategoryDataset_serSize_pmpum(List<BmResultsSersize> bmResSets) {
        bmResSets.sort(Comparator.comparing(BmResultsSersize::getBenchmarkerName));
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<SER_PROTOCOL> serprotocols = new LinkedList<>();
        serprotocols.add(SER_PROTOCOL.FST);
        serprotocols.add(SER_PROTOCOL.HESSIAN);
        serprotocols.add(SER_PROTOCOL.JAVA);
        serprotocols.add(SER_PROTOCOL.KRYO);
        serprotocols.add(SER_PROTOCOL.PROTOSTUFF);

        for(BmResultsSersize bmResultSersize : bmResSets) {
            String serprotocol = bmResultSersize.getSerprotocol().abbrev;
            for(BenchmarkResult res : bmResultSersize.getBenchmarks()) {
                double val = res.getScore();
                String sermode = res.getSerMode().toString();
                dataset.addValue(val, serprotocol + "-" + sermode, serprotocol);
            }
        }
        return dataset;
    }


// -------------------------------------------------
// ------------ Plotting Main Functions ------------
// -------------------------------------------------

    public static JFreeChart plot_CompareSersize_pmpum() {
        List<String> fileList = ResultFileIO.getResultFiles_SerSize("dcachey");
        List<BmResultsSersize> bmResSets = PlottingListtypes.createBenchmarkResultsSetListFromFilenames(fileList, BenchmarkResult.SER_MODE.ANY, "*");

        CategoryDataset categoryDataset = createCategoryDataset_serSize_pmpum(bmResSets);

        KeyToGroupMap ktgm = new KeyToGroupMap("PMPUM");

        ktgm.mapKeyToGroup("FST-SER", "PMPUM");
        ktgm.mapKeyToGroup("FST-DESER", "PMPUM");
        ktgm.mapKeyToGroup("HES-SER", "PMPUM");
        ktgm.mapKeyToGroup("HES-DESER", "PMPUM");
        ktgm.mapKeyToGroup("JOS-SER", "PMPUM");
        ktgm.mapKeyToGroup("JOS-DESER", "PMPUM");
        ktgm.mapKeyToGroup("KRY-SER", "PMPUM");
        ktgm.mapKeyToGroup("KRY-DESER", "PMPUM");
        ktgm.mapKeyToGroup("PS-SER", "PMPUM");
        ktgm.mapKeyToGroup("PS-DESER", "PMPUM");

        String title = "Comparing Encoding Size of PMPUM";
        String xaxis = "Serialization Protocol";
        String yaxis = "Size of Encoded PMPUM in Bytes";
        return createStackedBarChart_pmpum( categoryDataset, ktgm, title, xaxis, yaxis );
    }

    public static JFreeChart plot_multiProtocol_pmpum(String machineFolder) {
        String typeFolder = "dcachey";
        List<String> fileList = ResultFileIO.getNewResultFilesforType( machineFolder + "/"+typeFolder, "*" );
        System.out.println("Looking for files in: " + machineFolder + "/"+typeFolder);

        List<BmResultsPMPUM> bmResSets = createBmResultsPMPUMListFromFilenames(fileList);
        BmResultsPMPUM avgBmResSet = createAverageBmResultsPMPUM( bmResSets );
        System.out.println("avaraged BmResultTestobj for PMPUM:\n" + avgBmResSet);

        CategoryDataset categoryDataset = createCategoryDataset_multiProtocol_pmpum(avgBmResSet);

        KeyToGroupMap ktgm = new KeyToGroupMap("PMPUM");
        ktgm.mapKeyToGroup("FST-SER", "PMPUM");
        ktgm.mapKeyToGroup("FST-DESER", "PMPUM");
        ktgm.mapKeyToGroup("HES-SER", "PMPUM");
        ktgm.mapKeyToGroup("HES-DESER", "PMPUM");
        ktgm.mapKeyToGroup("JOS-SER", "PMPUM");
        ktgm.mapKeyToGroup("JOS-DESER", "PMPUM");
        ktgm.mapKeyToGroup("KRY-SER", "PMPUM");
        ktgm.mapKeyToGroup("KRY-DESER", "PMPUM");
        ktgm.mapKeyToGroup("PSR-SER", "PMPUM");
        ktgm.mapKeyToGroup("PSR-DESER", "PMPUM");


        String title = "Comparing (De)Serialization Speed of PoolManagerPoolUpMessage on " + machineFolder;
        String xaxis = "Serialization Protocol";
        String yaxis = "(DE)SER Speed of PoolManagerPoolUpMessage in us";
        return createStackedBarChart_pmpum( categoryDataset, ktgm, title, xaxis, yaxis );
//        return createStackedBarChart_errorbars_testobj( categoryDataset, ktgm, title, xaxis, yaxis );
    }

}
