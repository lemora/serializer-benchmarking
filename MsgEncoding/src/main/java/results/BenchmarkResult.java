package results;

public class BenchmarkResult {

    public static enum SER_MODE {
        SER, DESER, ANY
    }

    // Name of the used benchmark method
    private String benchmarkName;
    // name of the serialized object
    private String serObjectName;
    private SER_MODE serMode;
    // result of benchmark
    private double score;
    private double scoreError;
    private double valueCount = 1;

    public BenchmarkResult(double score, double scoreError, String benchmarkName) {
        this(score, scoreError, benchmarkName, getObjNameFromBMName(benchmarkName), getSerModeFromBMName(benchmarkName));
    }

    public BenchmarkResult(double score, String benchmarkName, int valueCount) {
        this(score, 0, benchmarkName, getObjNameFromBMName(benchmarkName), getSerModeFromBMName(benchmarkName), valueCount);
    }

    public BenchmarkResult(double score, double scoreError, String benchmarkName, String objectName) {
        this(score, scoreError, benchmarkName, objectName, getSerModeFromBMName(benchmarkName));
    }

    public BenchmarkResult(double score, double scoreError, String benchmarkName, SER_MODE sermode) {
        this(score, scoreError, benchmarkName, getObjNameFromBMName(benchmarkName), getSerModeFromBMName(benchmarkName));
    }

    public BenchmarkResult(double score, String benchmarkName, SER_MODE sermode, int valueCount) {
        this(score, 0, benchmarkName, getObjNameFromBMName(benchmarkName), getSerModeFromBMName(benchmarkName), valueCount);
    }

    public BenchmarkResult(double score, double scoreError, String benchmarkName, String objectName, SER_MODE serMode) {
        this(score, 0, benchmarkName, getObjNameFromBMName(benchmarkName), getSerModeFromBMName(benchmarkName), 1);
    }

    public BenchmarkResult(double score, double scoreError, String benchmarkName, String objectName, SER_MODE serMode, int valueCount) {
        this.score = score;
        this.scoreError = scoreError;
        this.serMode = serMode;
        this.benchmarkName = benchmarkName;
        this.serObjectName = objectName;
        this.serMode = serMode;
        this.valueCount = valueCount;
    }

    public String getBenchmarkName() {
        return benchmarkName;
    }

    public String getSerializedStructName() {
        String[] nameParts = benchmarkName.split("_");
        return nameParts[1];
    }

    public boolean isModeDeser() {
        return serMode == SER_MODE.DESER;
    }

    public SER_MODE getSerMode() {
        return serMode;
    }

    public String getSerModeString() {
        return serMode == SER_MODE.SER ? "ser" : "deser";
    }

    public double getScore() {
        return score;
    }

    public void divideScore(double divisor) {
        assert divisor != 0;
        this.score = this.score / divisor;
    }

    public double getScoreError() {
        return scoreError;
    }

    private String getSerObjectName() {
        return serObjectName;
    }

    public double getValueCount() {
        return valueCount;
    }

    public void setValueCount(double valueCount) {
        this.valueCount = valueCount;
    }


// ---------- Helper ----------

    private static String getObjNameFromBMName(String bmName) {
        String objectName = "object";

        int numBmNameParts = bmName.split("_").length;
        if ( numBmNameParts >= 3 ) {
            objectName = bmName.split("_")[1];
        }
        return objectName;
    }

    private static SER_MODE getSerModeFromBMName(String bmName) {
        SER_MODE sermode = SER_MODE.ANY;

        int numBmNameParts = bmName.split("_").length;
        if ( numBmNameParts >= 3 ) {
            String serModeStr = bmName.split("_")[numBmNameParts - 1];
            sermode = serModeStr.equals("ser") ? SER_MODE.SER : SER_MODE.DESER;
        }
        return sermode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BenchmarkResult: " + this.getBenchmarkName() + "\n");
        sb.append( "score: " + this.getScore() + "\n");
        sb.append( "scoreError: " + this.getScoreError() + "\n");
        return sb.toString();
    }

}
