package results;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import serializer.Helper;
import serializer.SER_PROTOCOL;

import java.util.*;

public class BmResultsSersize {

    private SER_PROTOCOL serprotocol;
    private String benchmarkerName = "";
    private String dateTime = "unknown";
    private String machineName = "machinename";
    private int physCores = 0;
    private int virtCores = 0;
    private int numOfParallelBenchmarkers = 0;
    private BenchmarkResult.SER_MODE sermode = BenchmarkResult.SER_MODE.ANY;
    private String sertype = "sertype";

    private List<BenchmarkResult> results = new LinkedList<>();

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
//        this.benchmarkerName = name.replace("Benchmarker", "").replace("SerSizer", "");
        this.benchmarkerName = name.replace("ContainerBenchmarker", "")
                .replace("TestobjBenchmarker", "")
                .replace("SerSizer", "");
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String datetime) {
        this.dateTime = datetime;
    }

    public BenchmarkResult.SER_MODE getSerMode() {
        return sermode;
    }

    public void setSerMode(BenchmarkResult.SER_MODE sermode) {
        this.sermode = sermode;
    }

    public String getSerType() {
        return sertype;
    }

    public SER_PROTOCOL getSerprotocol() {
        return serprotocol;
    }

    public void setSerprotocol(SER_PROTOCOL serprotocol) {
        this.serprotocol = serprotocol;
    }

    public BenchmarkResult.SER_MODE getSermode() {
        return sermode;
    }

    public void setSermode(BenchmarkResult.SER_MODE sermode) {
        this.sermode = sermode;
    }

    public String getSertype() {
        return sertype;
    }

    public void setSertype(String sertype) {
        this.sertype = sertype;
    }

    public void setSerType(String sertype) {
        if(sertype.equals("java")
                || sertype.equals("avro")
                || sertype.equals("avrojson")
                || sertype.equals("proto")
                || sertype.equals("protostuff")
                || sertype.equals("fst")
                || sertype.equals("hessian")
                || sertype.equals("kryo") ) {
            this.sertype = sertype;
        } else {
            Helper.debugPrint("Sertype not permittet: '" + sertype + "'");
        }
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

    public List<BenchmarkResult> getBmResults(String nameContains) {
        List<BenchmarkResult> bmResult = new LinkedList<>();
        for(BenchmarkResult bmr : this.results) {
            if(bmr.getBenchmarkName().contains(nameContains)) {
                bmResult.add(bmr);
            }
        }
        return bmResult;
    }

// -------- FUNCTIONALITY --------

    public boolean addBenchmarkResult(BenchmarkResult result) {
        return results.add(result);
    }

    public List<BenchmarkResult> getBenchmarks() {
        return new LinkedList<>(this.results);
    }

    private BmResultsSersize addMeasurementToEach(double measurement, double error, BenchmarkResult.SER_MODE sermode) {
        BmResultsSersize amendedResultSet = new BmResultsSersize(this);
        amendedResultSet.setBenchmarkerName(getBenchmarkerName());
        for (BenchmarkResult result : results) {
            if (sermode.equals(result.getSerMode())) {
                double newResult = result.getScore() + measurement;
                double newError = Math.sqrt(Math.pow(result.getScoreError(), 2) + Math.pow(error, 2));
                amendedResultSet.addBenchmarkResult(new BenchmarkResult(newResult, newError, result.getBenchmarkName(), result.getSerMode()));
            }
        }
        return amendedResultSet;
    }

    private BmResultsSersize setOldBenchmarkResultsFromJSON(String jsonString, BenchmarkResult.SER_MODE sermode, String bmnameContains) throws com.google.gson.JsonSyntaxException {
        JsonArray benchmarkResults = (JsonArray) new JsonParser().parse(jsonString);

        String benchmark = benchmarkResults.get(0).getAsJsonObject().get("benchmark").getAsString();
        int numBmNameParts = benchmark.split("\\.").length;
        setBenchmarkerName( benchmark.split("\\.")[numBmNameParts - 2] );
        setSerMode( sermode );
        setSerType( benchmark.split("\\.")[numBmNameParts - 1].split("_")[0] );
        setSerprotocol( Helper.stringToSerProtocol(getBenchmarkerName().split("_")[0]) );

        for(int i = 0; i < benchmarkResults.size(); i++) {
            JsonObject currBenchmark = benchmarkResults.get(i).getAsJsonObject();

            int valcnt = 0;
            boolean hasValCount = currBenchmark.has("params");
            String valcntparam = hasValCount ? "-" + currBenchmark.getAsJsonObject("params").get("valueCount").getAsString() + "values" : "";
            String benchmarkName = currBenchmark.get("benchmark").getAsString() + valcntparam;

            BenchmarkResult.SER_MODE mode = benchmarkName.contains("deser") ? BenchmarkResult.SER_MODE.DESER : BenchmarkResult.SER_MODE.SER;

            if((bmnameContains.isEmpty()
                    || bmnameContains.equals("*")
                    || benchmarkName.contains(bmnameContains))
                    && (mode.equals(sermode) || sermode.equals(BenchmarkResult.SER_MODE.ANY)) ) {
                double score = currBenchmark.get("primaryMetric").getAsJsonObject().get("score").getAsDouble();
                double scoreError = currBenchmark.get("primaryMetric").getAsJsonObject().get("scoreError").getAsDouble();

                BenchmarkResult res = new BenchmarkResult(score, scoreError, benchmarkName);

                if(hasValCount) {
                    res.setValueCount(currBenchmark.getAsJsonObject("params").get("valueCount").getAsInt());
                }
                addBenchmarkResult(res);
            }
        }
        return this;
    }

    private BmResultsSersize setNewBenchmarkResultsFromJSON(String jsonString, BenchmarkResult.SER_MODE sermode) throws com.google.gson.JsonSyntaxException {
        JsonObject benchmarkResults = (JsonObject) new JsonParser().parse(jsonString.trim());

        setBenchmarkerName( benchmarkResults.get("benchmarker").getAsJsonObject().get("name").getAsString() );
        setSerprotocol( Helper.stringToSerProtocol(getBenchmarkerName()) );
        setSerType( benchmarkResults.get("benchmarker").getAsJsonObject().get("type").getAsString() );
        setSerMode( sermode );
        JsonObject environment = benchmarkResults.get("environment").getAsJsonObject();
        setDateTime( environment.get("date").getAsString() );
        setMachineName( environment.get("machineName").getAsString() );
        setPhysCores( environment.get("physicalCores").getAsInt() );
        setVirtCores( environment.get("virtualCores").getAsInt() );
        setNumOfParallelBenchmarkers( environment.get("numberOfParallelBenchmarkers").getAsInt() );

        JsonArray bmValues = benchmarkResults.get("values").getAsJsonArray();
        for(int i = 0; i < bmValues.size(); i++) {
            JsonObject currBenchmark = bmValues.get(i).getAsJsonObject();
            String benchmarkName = currBenchmark.get("benchmark").getAsString();
            BenchmarkResult.SER_MODE mode;
            if (currBenchmark.has("mode")) {
                mode = currBenchmark.get("mode").getAsString().equals("deser") ? BenchmarkResult.SER_MODE.DESER : BenchmarkResult.SER_MODE.SER;
            }
             else {
                mode = BenchmarkResult.SER_MODE.SER;
            }

            if(mode.equals(sermode) || sermode.equals(BenchmarkResult.SER_MODE.ANY)) {
                double score = currBenchmark.get("score").getAsDouble();
                double scoreError = 0;
                if(currBenchmark.has("scoreError")) {
                    scoreError = currBenchmark.get("scoreError").getAsDouble();
                }

                int valueCount = 0;
                if(currBenchmark.has("valueCount")) {
                    valueCount = currBenchmark.get("valueCount").getAsInt();
                }

                BenchmarkResult.SER_MODE serMode = mode;
                String objectName = currBenchmark.get("object").getAsString();

                BenchmarkResult res = new BenchmarkResult(score, scoreError, benchmarkName, objectName, serMode, valueCount);
                addBenchmarkResult(res);
            }
        }

        return this;
    }

    public BmResultsSersize setBenchmarkResultsFromJSON(String jsonString, BenchmarkResult.SER_MODE sermode, String bmNameContains) throws com.google.gson.JsonSyntaxException {
        boolean oldFormat = new JsonParser().parse(jsonString).isJsonArray();

        if (oldFormat) {
            return setOldBenchmarkResultsFromJSON(jsonString, sermode, bmNameContains);
        } else {
            return setNewBenchmarkResultsFromJSON(jsonString, sermode);
        }
    }

    public BmResultsSersize getResultsOfType(BenchmarkResult.SER_MODE serMode){
        BmResultsSersize selectedResultSet = new BmResultsSersize();
        selectedResultSet.setBenchmarkerName(getBenchmarkerName() + "-" + serMode);

        for (BenchmarkResult result : results) {
            if (result.getSerMode() == serMode) {
                selectedResultSet.addBenchmarkResult(result);
            }
        }
        return selectedResultSet;
    }

    public BmResultsSersize getResultsWhereNameContains(String namepart, boolean shouldContain){
        BmResultsSersize selectedResultSet = new BmResultsSersize();
        selectedResultSet.setBenchmarkerName(getBenchmarkerName());

        for (BenchmarkResult result : results) {
            if (result.getBenchmarkName().contains(namepart) == shouldContain) {
                selectedResultSet.addBenchmarkResult(result);
            }
        }
        return selectedResultSet;
    }

    public BmResultsSersize(String jsonString, BenchmarkResult.SER_MODE sermode, String bmNameContains) {
        setBenchmarkResultsFromJSON(jsonString, sermode, bmNameContains);
    }

    public BmResultsSersize() {
    }

    public BmResultsSersize(BmResultsSersize bmrs) {
        this.results = bmrs.getBenchmarks();
        setBenchmarkerName(bmrs.getBenchmarkerName());
    }

    public BmResultsSersize(String name, List<BenchmarkResult> results) {
        setBenchmarkerName(name);
        this.results = new LinkedList<>(results);
    }
}
