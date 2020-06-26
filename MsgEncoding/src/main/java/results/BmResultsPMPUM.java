package results;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import serializer.Helper;
import serializer.SER_PROTOCOL;

import java.util.HashMap;
import java.util.Map;

public class BmResultsPMPUM {

    private String benchmarkerName = "";
    private String dateTime = "unknown";
    private ResultFileIO.Machine machine = ResultFileIO.Machine.ZITPCX22820;
    private int numOfParallelBenchmarkers = 0;

    Map<SER_PROTOCOL, BenchmarkResult> results_ser = new HashMap<>();
    Map<SER_PROTOCOL, BenchmarkResult> results_deser = new HashMap<>();

// -------- GETTER + SETTER --------

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
        this.benchmarkerName = name.replace("TestobjBenchmarker", "");
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String datetime) {
        this.dateTime = datetime;
    }

    public Map<SER_PROTOCOL, BenchmarkResult> getResults_ser() {
        return results_ser;
    }

    public void setResults_ser(Map<SER_PROTOCOL, BenchmarkResult> results_ser) {
        this.results_ser = results_ser;
    }

    public Map<SER_PROTOCOL, BenchmarkResult> getResults_deser() {
        return results_deser;
    }

    public void setResults_deser(Map<SER_PROTOCOL, BenchmarkResult> results_deser) {
        this.results_deser = results_deser;
    }

    public ResultFileIO.Machine getMachine() {
        return machine;
    }

    public void setMachine(ResultFileIO.Machine machine) {
        this.machine = machine;
    }

    public String getMachineName() {
        return machine.name;
    }

    public void setMachineName(String machineName) {
        switch (machineName.toLowerCase()) {
            case "zitpcx22820":
                setMachine(ResultFileIO.Machine.ZITPCX22820);
                break;
            case "dot1":
            case "dcache-dot1":
                setMachine(ResultFileIO.Machine.DCACHE_DOT1);
                break;
            case "dot13":
            case "dcache-dot13":
            case "dot13cpulock":
                setMachine(ResultFileIO.Machine.DCACHE_DOT13);
                break;
            case "dca03":
            case "dcache-core-atlas03":
                setMachine(ResultFileIO.Machine.DCACHE_CORE_ATLAS03);
                break;
            default:
                throw new IllegalArgumentException("Machine name '" + machineName + "' not known.");
        }
    }

    // ----- more complex requests

    public BenchmarkResult getBmResult(BenchmarkResult.SER_MODE sermode, SER_PROTOCOL objType) {
        Map<SER_PROTOCOL, BenchmarkResult> bmResults = getBmMap(sermode);
        return bmResults.get(objType);
    }

    public Map<SER_PROTOCOL, BenchmarkResult> getBmMap(BenchmarkResult.SER_MODE sermode) {
        return new HashMap<>( getBmMapReference(sermode) );
    }

    private Map<SER_PROTOCOL, BenchmarkResult> getBmMapReference(BenchmarkResult.SER_MODE sermode) {
        return sermode.equals(BenchmarkResult.SER_MODE.DESER) ? this.results_deser : this.results_ser;
    }


// -------- FUNCTIONALITY --------

    private BmResultsPMPUM setBmResultsFromJSON(String jsonString) throws com.google.gson.JsonSyntaxException {
//        System.out.println(jsonString);
        JsonArray benchmarkResults = (JsonArray) new JsonParser().parse(jsonString);

        String benchmark = benchmarkResults.get(0).getAsJsonObject().get("benchmark").getAsString();
        int numBmNameParts = benchmark.split("\\.").length;
        setBenchmarkerName( benchmark.split("\\.")[numBmNameParts - 2] );

        for(int i = 0; i < benchmarkResults.size(); i++) {
            JsonObject currBenchmark = benchmarkResults.get(i).getAsJsonObject();

            String bmMethodName = currBenchmark.get("benchmark").getAsString();
            bmMethodName = bmMethodName.split("\\.")[bmMethodName.split("\\.").length-1];
            String serProtocol = bmMethodName.split("_")[0];

            BenchmarkResult.SER_MODE serMode = bmMethodName.contains("deser") ? BenchmarkResult.SER_MODE.DESER : BenchmarkResult.SER_MODE.SER;

            double score = currBenchmark.get("primaryMetric").getAsJsonObject().get("score").getAsDouble();
            double scoreError = currBenchmark.get("primaryMetric").getAsJsonObject().get("scoreError").getAsDouble();

            BenchmarkResult res = new BenchmarkResult(score, scoreError, bmMethodName);

            if(serMode.equals(BenchmarkResult.SER_MODE.DESER)) {
                this.results_deser.put(Helper.stringToSerProtocol(serProtocol), res);
            }
            else {
                this.results_ser.put(Helper.stringToSerProtocol(serProtocol), res);
            }
        }
        return this;
    }

    public BmResultsPMPUM() {
    }

    public BmResultsPMPUM(String jsonString) {
        setBmResultsFromJSON(jsonString);
    }

    public BmResultsPMPUM(BmResultsPMPUM bmrs) {
        this.results_ser = bmrs.getResults_ser();
        this.results_deser = bmrs.getResults_deser();

        setBenchmarkerName(bmrs.getBenchmarkerName());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("key | results ser | results deser\n");

        for(SER_PROTOCOL key : this.results_ser.keySet()) {
            sb.append(key + " -> ");
            sb.append( this.results_ser.get(key).getScore() + " ; " );
            sb.append( this.results_deser.get(key).getScore() + " ; " );
            sb.append("\n");
        }
        return sb.toString();
    }

}
