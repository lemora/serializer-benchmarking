package results;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import serializer.Helper;
import serializer.SER_PROTOCOL;

import java.util.HashMap;
import java.util.Map;

public class BmResultsTestobj {

    public enum Testobj_Type {
        MSG("rw00", "C0"),
        MSG_RW01("rw01", "C1"),
        MSG_RW02("rw02", "C2"),
        MSG_RW03("rw03", "C3"),
        MSG_RW04("rw04", "C4"),
        MSG_RW05("rw05", "C5");

        public final String fieldname;
        public final String displayname;
        Testobj_Type(String name, String bettername) {
            this.fieldname = name.toLowerCase();
            this.displayname = bettername.toUpperCase();
        }
    }

    private SER_PROTOCOL serprotocol;
    private String benchmarkerName = "";
    private String dateTime = "unknown";
    private ResultFileIO.Machine machine = ResultFileIO.Machine.ZITPCX22820;
    private int numOfParallelBenchmarkers = 0;

    Map<Testobj_Type, BenchmarkResult> results_ser = new HashMap<>();
    Map<Testobj_Type, BenchmarkResult> results_deser = new HashMap<>();

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

    public Map<Testobj_Type, BenchmarkResult> getResults_ser() {
        return results_ser;
    }

    public void setResults_ser(Map<Testobj_Type, BenchmarkResult> results_ser) {
        this.results_ser = results_ser;
    }

    public Map<Testobj_Type, BenchmarkResult> getResults_deser() {
        return results_deser;
    }

    public void setResults_deser(Map<Testobj_Type, BenchmarkResult> results_deser) {
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

    public SER_PROTOCOL getSerprotocol() {
        return serprotocol;
    }

    public void setSerprotocol(SER_PROTOCOL serprotocol) {
        this.serprotocol = serprotocol;
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
                setMachine(ResultFileIO.Machine.DCACHE_DOT13);
                break;
            case "dcache-dot13":
            case "dot13cpulock":
                setMachine(ResultFileIO.Machine.DCACHE_DOT13_CPULOCK);
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

    public BenchmarkResult getBmResult(BenchmarkResult.SER_MODE sermode, Testobj_Type objType) {
        Map<Testobj_Type, BenchmarkResult> bmResults = getBmMap(sermode);
        return bmResults.get(objType);
    }

    public Map<Testobj_Type, BenchmarkResult> getBmMap(BenchmarkResult.SER_MODE sermode) {
        return new HashMap<>( getBmMapReference(sermode) );
    }

    private Map<Testobj_Type, BenchmarkResult> getBmMapReference(BenchmarkResult.SER_MODE sermode) {
        return sermode.equals(BenchmarkResult.SER_MODE.DESER) ? this.results_deser : this.results_ser;
    }


// -------- FUNCTIONALITY --------

    private BmResultsTestobj setBmResultsFromJSON(String jsonString) throws com.google.gson.JsonSyntaxException {
//        System.out.println(jsonString);
        JsonArray benchmarkResults = (JsonArray) new JsonParser().parse(jsonString);

        String benchmark = benchmarkResults.get(0).getAsJsonObject().get("benchmark").getAsString();
        int numBmNameParts = benchmark.split("\\.").length;
        setBenchmarkerName( benchmark.split("\\.")[numBmNameParts - 2] );
        setSerprotocol( Helper.stringToSerProtocol(getBenchmarkerName().split("_")[0]) );

        for(int i = 0; i < benchmarkResults.size(); i++) {
            JsonObject currBenchmark = benchmarkResults.get(i).getAsJsonObject();

            String bmMethodName = currBenchmark.get("benchmark").getAsString();
            bmMethodName = bmMethodName.split("\\.")[bmMethodName.split("\\.").length-1];
            String objType = bmMethodName.split("_")[1];

            BenchmarkResult.SER_MODE serMode = bmMethodName.contains("deser") ? BenchmarkResult.SER_MODE.DESER : BenchmarkResult.SER_MODE.SER;

            double score = currBenchmark.get("primaryMetric").getAsJsonObject().get("score").getAsDouble();
            double scoreError = currBenchmark.get("primaryMetric").getAsJsonObject().get("scoreError").getAsDouble();

            BenchmarkResult res = new BenchmarkResult(score, scoreError, bmMethodName);

            if(serMode.equals(BenchmarkResult.SER_MODE.DESER)) {
                this.results_deser.put(stringToTestobjType(objType), res);
            }
            else {
                this.results_ser.put(stringToTestobjType(objType), res);
            }
        }
        return this;
    }

    public BmResultsTestobj() {
    }

    public BmResultsTestobj(String jsonString) {
        setBmResultsFromJSON(jsonString);
    }

    public BmResultsTestobj(BmResultsTestobj bmrs) {
        this.results_ser = bmrs.getResults_ser();
        this.results_deser = bmrs.getResults_deser();

        setBenchmarkerName(bmrs.getBenchmarkerName());
        setSerprotocol(bmrs.getSerprotocol());
        setMachineName(bmrs.getMachineName());
        setMachine(bmrs.getMachine());
        setDateTime(bmrs.getDateTime());
    }

    private Testobj_Type stringToTestobjType(String objType) {
        switch (objType) {
            case "rw00" :
                return Testobj_Type.MSG;
            case "rw01" :
                return Testobj_Type.MSG_RW01;
            case "rw02" :
                return Testobj_Type.MSG_RW02;
            case "rw03" :
                return Testobj_Type.MSG_RW03;
            case "rw04" :
                return Testobj_Type.MSG_RW04;
            case "rw05" :
                return Testobj_Type.MSG_RW05;
            default:
                throw new UnsupportedOperationException("Object type '" + objType + "' not known.");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("key | results ser | results deser\n");

        for(Testobj_Type key : this.results_ser.keySet()) {
            sb.append(key.fieldname + " -> ");
            sb.append( this.results_ser.get(key).getScore() + " ; " );
            sb.append( this.results_deser.get(key).getScore() + " ; " );
            sb.append("\n");
        }
        return sb.toString();
    }

}
