package results;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;

import serializer.Helper;
import serializer.SER_PROTOCOL;

public class BmResultsContainer {

    public enum List_Type {
        STRING_LIST("String", "SL"),
        INT_LIST("Int", "IL"),
        DOUBLE_LIST("Double", "DL"),
        ANY("", "");

        public final String name;
        public final String bmName;
        public final String abbrev;
        List_Type(String name, String abbrev) {
            this.name = name.toLowerCase();
            this.bmName = name.equals("") ? "" : name + "List";
            this.abbrev = abbrev;
        }
    }

    public enum Container_ValCount {
        V10(10),
        V100(100),
        V500(500),
        V1000(1000),
        V10000(10000),
        V100000(100000);

        public final int valcount;
        Container_ValCount(int valcount) {
            this.valcount = valcount;
        }
    }

    private SER_PROTOCOL serprotocol;
    private String benchmarkerName = "";
    private String dateTime = "unknown";
    private String machineName = "machinename";
    private ResultFileIO.Machine machine;
    private int physCores = 0;
    private int virtCores = 0;
    private int numOfParallelBenchmarkers = 0;

    Map<Integer, BenchmarkResult> stringListsResults_ser = new HashMap<>();
    Map<Integer, BenchmarkResult> stringListsResults_deser = new HashMap<>();

    Map<Integer, BenchmarkResult> intListsResults_ser = new HashMap<>();
    Map<Integer, BenchmarkResult> intListsResults_deser = new HashMap<>();

    Map<Integer, BenchmarkResult> doubleListsResults_ser = new HashMap<>();
    Map<Integer, BenchmarkResult> doubleListsResults_deser = new HashMap<>();

// -------- GETTER + SETTER --------

    public void setPhysCores(int physCores) {
        this.physCores = physCores;
    }

    public int getVirtCores() {
        return virtCores;
    }

    public void setVirtCores(int virtCores) {
        this.virtCores = virtCores;
    }

    public int getNumOfParallelBenchmarkers() {
        return numOfParallelBenchmarkers;
    }

    public void setNumOfParallelBenchmarkers(int numOfParallelBenchmarkers) {
        this.numOfParallelBenchmarkers = numOfParallelBenchmarkers;
    }

    public String getBenchmarkerName() {
        return benchmarkerName;
    }

    public void setBenchmarkerName(String name) {
        this.benchmarkerName = name.replace("ContainerBenchmarker", "");
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String datetime) {
        this.dateTime = datetime;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machinename) {
        this.machineName = machinename;
    }

    public int getPhysCores() {
        return physCores;
    }

    public ResultFileIO.Machine getMachine() {
        return machine;
    }

    public void setMachine(ResultFileIO.Machine machine) {
        this.machine = machine;
    }

    public SER_PROTOCOL getSerprotocol() {
        return serprotocol;
    }

    public void setSerprotocol(SER_PROTOCOL serprotocol) {
        this.serprotocol = serprotocol;
    }

    public Map<Integer, BenchmarkResult> getStringListsResults_ser() {
        return stringListsResults_ser;
    }

    public void setStringListsResults_ser(Map<Integer, BenchmarkResult> stringListsResults_ser) {
        this.stringListsResults_ser = stringListsResults_ser;
    }

    public Map<Integer, BenchmarkResult> getStringListsResults_deser() {
        return stringListsResults_deser;
    }

    public void setStringListsResults_deser(Map<Integer, BenchmarkResult> stringListsResults_deser) {
        this.stringListsResults_deser = stringListsResults_deser;
    }

    public Map<Integer, BenchmarkResult> getIntListsResults_ser() {
        return intListsResults_ser;
    }

    public void setIntListsResults_ser(Map<Integer, BenchmarkResult> intListsResults_ser) {
        this.intListsResults_ser = intListsResults_ser;
    }

    public Map<Integer, BenchmarkResult> getIntListsResults_deser() {
        return intListsResults_deser;
    }

    public void setIntListsResults_deser(Map<Integer, BenchmarkResult> intListsResults_deser) {
        this.intListsResults_deser = intListsResults_deser;
    }

    public Map<Integer, BenchmarkResult> getDoubleListsResults_ser() {
        return doubleListsResults_ser;
    }

    public void setDoubleListsResults_ser(Map<Integer, BenchmarkResult> doubleListsResults_ser) {
        this.doubleListsResults_ser = doubleListsResults_ser;
    }

    public Map<Integer, BenchmarkResult> getDoubleListsResults_deser() {
        return doubleListsResults_deser;
    }

    public void setDoubleListsResults_deser(Map<Integer, BenchmarkResult> doubleListsResults_deser) {
        this.doubleListsResults_deser = doubleListsResults_deser;
    }

    // ----- more complex requests

    public BenchmarkResult getBmResult(List_Type containertype, BenchmarkResult.SER_MODE sermode, int valuecount) {
        Map<Integer, BenchmarkResult> bmResults = getBenchmarks(containertype, sermode);
        return bmResults.get(valuecount);
    }

    public BenchmarkResult getBmResult(List_Type containertype, BenchmarkResult.SER_MODE sermode, Container_ValCount valcount) {
        Map<Integer, BenchmarkResult> bmResults = getBenchmarks(containertype, sermode);
        return bmResults.get(valcount.valcount);
    }

    public Map<Integer, BenchmarkResult> getBenchmarks(List_Type containertype, BenchmarkResult.SER_MODE sermode) {
        return new HashMap<>( getBmMapReference(containertype, sermode) );
    }

    private Map<Integer, BenchmarkResult> getBmMapReference(List_Type containertype, BenchmarkResult.SER_MODE sermode) {
        switch (containertype) {
            case STRING_LIST:
                return sermode.equals(BenchmarkResult.SER_MODE.DESER) ? this.stringListsResults_deser : this.stringListsResults_ser;
            case INT_LIST:
                return sermode.equals(BenchmarkResult.SER_MODE.DESER) ? this.intListsResults_deser : this.intListsResults_ser;
            case DOUBLE_LIST:
                return sermode.equals(BenchmarkResult.SER_MODE.DESER) ? this.doubleListsResults_deser : this.doubleListsResults_ser;
            case ANY:
                throw new UnsupportedOperationException("No Benchmark Result to retrieve for ANY list type.");
        }
        return null;
    }


// -------- FUNCTIONALITY --------

    private BmResultsContainer setBmResultsFromJSON(String jsonString) throws com.google.gson.JsonSyntaxException {
//        System.out.println(jsonString);
        JsonArray benchmarkResults = (JsonArray) new JsonParser().parse(jsonString);

        String benchmark = benchmarkResults.get(0).getAsJsonObject().get("benchmark").getAsString();
        int numBmNameParts = benchmark.split("\\.").length;
        setBenchmarkerName( benchmark.split("\\.")[numBmNameParts - 2] );
        setSerprotocol( Helper.stringToSerProtocol(getBenchmarkerName().split("_")[0]) );

        for(int i = 0; i < benchmarkResults.size(); i++) {
            JsonObject currBenchmark = benchmarkResults.get(i).getAsJsonObject();

            int valcnt = currBenchmark.getAsJsonObject("params").get("valueCount").getAsInt();
            String bmMethodName = currBenchmark.get("benchmark").getAsString();
            bmMethodName = bmMethodName.split("\\.")[bmMethodName.split("\\.").length-1] + "_" + valcnt;

            BenchmarkResult.SER_MODE mode = bmMethodName.contains("deser") ? BenchmarkResult.SER_MODE.DESER : BenchmarkResult.SER_MODE.SER;

            double score = currBenchmark.get("primaryMetric").getAsJsonObject().get("score").getAsDouble();
            double scoreError = currBenchmark.get("primaryMetric").getAsJsonObject().get("scoreError").getAsDouble();

            BenchmarkResult res = new BenchmarkResult(score, scoreError, bmMethodName);
            res.setValueCount(currBenchmark.getAsJsonObject("params").get("valueCount").getAsInt());

//            System.out.println("Containertype: " + bmMethodName.split("_")[1]);

            String containertype = bmMethodName.split("_")[1];
            getBmMapReference( stringToContainertype(containertype), mode ).put( valcnt, res );
        }
        return this;
    }

    public BmResultsContainer() {
    }

    public BmResultsContainer(String jsonString) {
        setBmResultsFromJSON(jsonString);
    }

    public BmResultsContainer(BmResultsContainer bmrs) {
        this.doubleListsResults_ser = new HashMap<>( bmrs.getBenchmarks(List_Type.DOUBLE_LIST, BenchmarkResult.SER_MODE.SER) );
        this.doubleListsResults_deser = new HashMap<>( bmrs.getBenchmarks(List_Type.DOUBLE_LIST, BenchmarkResult.SER_MODE.DESER) );

        this.intListsResults_ser = new HashMap<>( bmrs.getBenchmarks(List_Type.INT_LIST, BenchmarkResult.SER_MODE.SER) );
        this.intListsResults_deser = new HashMap<>( bmrs.getBenchmarks(List_Type.INT_LIST, BenchmarkResult.SER_MODE.DESER) );

        this.stringListsResults_ser = new HashMap<>( bmrs.getBenchmarks(List_Type.STRING_LIST, BenchmarkResult.SER_MODE.SER) );
        this.stringListsResults_deser = new HashMap<>( bmrs.getBenchmarks(List_Type.STRING_LIST, BenchmarkResult.SER_MODE.DESER) );

        setSerprotocol(bmrs.getSerprotocol());
        setBenchmarkerName(bmrs.getBenchmarkerName());
        setDateTime(bmrs.getDateTime());
        setMachineName(bmrs.getMachineName());
    }

    private List_Type stringToContainertype(String containertype) {
        switch (containertype) {
            case "DoubleList" :
                return List_Type.DOUBLE_LIST;
            case "StringList" :
                return List_Type.STRING_LIST;
            case "IntList" :
                return List_Type.INT_LIST;
            default:
                throw new UnsupportedOperationException("Containertype '" + containertype + "' not known.");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("key | int-list ser | int-list deser | double-list ser | double-list deser | string-list ser | string-list deser \n");

        for(Integer key : this.getIntListsResults_ser().keySet()) {
            sb.append(key + " -> ");

            sb.append( this.intListsResults_ser.get(key).getScore() + " ; " );
            sb.append( this.intListsResults_deser.get(key).getScore() + " ; " );

            sb.append( this.doubleListsResults_ser.get(key).getScore() + " ; " );
            sb.append( this.doubleListsResults_deser.get(key).getScore() + " ; " );

            sb.append( this.stringListsResults_ser.get(key).getScore() + " ; " );
            sb.append( this.stringListsResults_deser.get(key).getScore() + " ; " );
            sb.append("\n");
        }

        return sb.toString();
    }

}
