package results;

import com.google.gson.*;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import serializer.SER_PROTOCOL;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class ResultFileIO {

    public static final String RESULT_PATH_SERSIZE = "jmh-results/sersizes/";
    public static final String RESULT_PATH = "jmh-results/data/";
    public static final String RESULT_PATH_OLD = "jmh-results/deprecated/";

    public static final int[] containerVals = new int[] {10, 100, 500, 1000, 10000, 100000};
    private static boolean USE_RAW_FILES = true;

    public enum Machine {
        DCACHE_DOT1("dot1", "dot1", 6, 12),
        DCACHE_DOT13("dot13", "dot13", 20, 40),
        DCACHE_DOT13_CPULOCK("dot13cpulock", "dot13c", 20, 40),
        DCACHE_CORE_ATLAS03("dcatlas03", "dca03", 0, 0),
        ZITPCX22820("zitpcx22820", "local", 2, 4);

        public final String name;
        public final String shortname;
        public final int physCores;
        public final int virtCores;

        Machine(String name, String shortname, int physCores, int virtCores) {
            this.name = name;
            this.shortname = shortname;
            this.physCores = physCores;
            this.virtCores = virtCores;
        }
    }

    public enum Datastruct_Type {
        CONTAINER("container"),
        TESTOBJ("testobj"),
        DCACHEY("dcachey");

        public final String name;
        Datastruct_Type(String name) {
            this.name = name;
        }
    }

    public enum Benchmarker_Key {
        DATE, BMNAME, MACHINE;
    }


    public static void main(String[] args) throws IOException {
//        int numberOfParallelBenchmarks = 20;
//        Machine machine = Machine.DCACHE_DOT13;
//        String date = "19-07-10";
//
//        List<String> jos_resultFiles = getResultFilesforType("java", date, USE_RAW_FILES);
//        for (String file : jos_resultFiles) {
//            createMinimalResultFile(file, machine, numberOfParallelBenchmarks);
//        }
//
//        List<String> avro_resultFiles = getResultFilesforType("avro", date, USE_RAW_FILES);
//        for (String file : avro_resultFiles) {
//            if (file.toString().contains("19-07-01")
//                    || file.toString().contains("19-07-02")){
//                createMinimalResultFile(file, Machine.DCACHE_DOT1, numberOfParallelBenchmarks);
//            }
//            else {
//                createMinimalResultFile(file, machine, numberOfParallelBenchmarks);
//            }
//        }
//
//        List<String> avrojson_resultFiles = getResultFilesforType("avrojson", date, USE_RAW_FILES);
//        for (String file : avrojson_resultFiles) {
//            createMinimalResultFile(file, machine, numberOfParallelBenchmarks);
//        }
//
//        List<String> avrobinary_resultFiles = getResultFilesforType("avrobinary", date, USE_RAW_FILES);
//        for (String file : avrobinary_resultFiles) {
//            createMinimalResultFile(file, machine, numberOfParallelBenchmarks);
//        }
//
//        List<String> proto_resultFiles = getResultFilesforType("proto", date, USE_RAW_FILES);
//        for (String file : proto_resultFiles) {
//            createMinimalResultFile(file, machine, numberOfParallelBenchmarks);
//        }
//
//        List<String> protoplain_resultFiles = getResultFilesforType("protoplain", date, USE_RAW_FILES);
//        for (String file : protoplain_resultFiles) {
//            createMinimalResultFile(file, machine, numberOfParallelBenchmarks);
//        }
    }

    public static void createMinimalResultFile(String originalFile, Machine machine, int numOfParallelBenchmarkers) throws IOException {
        // TODO: rewrite!

        System.out.println("Converting: " + originalFile);
        int length = originalFile.split("/").length;
        String type = originalFile.split("/")[length-2];
        String bmFileName = originalFile.split("/")[length-1];
        String originalString = getStringFromFile("jmh-results/raw/" + type + "/" + bmFileName);

        JsonArray original = (JsonArray) new JsonParser().parse(originalString);
        JsonObject firstOfOriginal = original.get(0).getAsJsonObject();

        // create JSON Object
        JsonObject minimalResult = new JsonObject();

        JsonObject environment = new JsonObject();
        environment.add("jdkVersion", firstOfOriginal.get("jdkVersion"));
        environment.add("vmName", firstOfOriginal.get("vmName"));
        environment.add("vmVersion", firstOfOriginal.get("vmVersion"));
        environment.addProperty("machineName", machine.name);
        environment.addProperty("physicalCores", machine.physCores);
        environment.addProperty("virtualCores", machine.virtCores);
        environment.addProperty("numberOfParallelBenchmarkers", numOfParallelBenchmarkers);
        environment.addProperty("date", bmFileName.substring(0, 8));
        minimalResult.add("environment", environment);

        JsonObject jmh = new JsonObject();
        jmh.add("jmhVersion", firstOfOriginal.get("jmhVersion"));
        jmh.add("mode", firstOfOriginal.get("mode"));
        jmh.add("scoreUnit", firstOfOriginal.get("primaryMetric").getAsJsonObject().get("scoreUnit"));
        jmh.add("forks", firstOfOriginal.get("forks"));
        jmh.add("warmupIterations", firstOfOriginal.get("warmupIterations"));
        jmh.add("warmupTime", firstOfOriginal.get("warmupTime"));
        jmh.add("warmupBatchSize", firstOfOriginal.get("warmupBatchSize"));
        jmh.add("measurementIterations", firstOfOriginal.get("measurementIterations"));
        jmh.add("measurementTime", firstOfOriginal.get("measurementTime"));
        jmh.add("measurementBatchSize", firstOfOriginal.get("measurementBatchSize"));
        minimalResult.add("jmh", jmh);

        JsonObject benchmarker = new JsonObject();
        int numBmNameParts = firstOfOriginal.get("benchmark").getAsString().split("\\.").length;
        String benchmarkerName = firstOfOriginal.get("benchmark").getAsString().split("\\.")[numBmNameParts-2];
        benchmarker.addProperty("name", benchmarkerName);
        benchmarker.addProperty("type", benchmarkerName.replace("Benchmarker", "").toLowerCase());
        minimalResult.add("benchmarker", benchmarker);

        JsonArray resultValues = new JsonArray();

        for(int i = 0; i < original.size(); i++) {
            JsonObject currBenchmark = original.get(i).getAsJsonObject();
            JsonObject resultValueObject = new JsonObject();
            int numBmNamePart = currBenchmark.get("benchmark").getAsString().split("\\.").length;
            resultValueObject.addProperty("benchmark", currBenchmark.get("benchmark").getAsString().split("\\.")[numBmNamePart - 1]);
            resultValueObject.addProperty("object", currBenchmark.get("benchmark").getAsString().split("\\.")[numBmNamePart - 1].split("_")[1]);
            resultValueObject.addProperty("mode", currBenchmark.get("benchmark").getAsString().split("\\.")[numBmNamePart - 1].split("_")[2]);
            resultValueObject.add("score", currBenchmark.get("primaryMetric").getAsJsonObject().get("score"));
            resultValueObject.add("scoreError", currBenchmark.get("primaryMetric").getAsJsonObject().get("scoreError"));
            resultValues.add(resultValueObject);
        }

        minimalResult.add("values", resultValues);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(minimalResult);
        PrintWriter writer = new PrintWriter("jmh-results/processed/" + type + "/" + bmFileName, "UTF-8");
        writer.write(json);
        writer.close();
    }

    public static List<String> getNewResultFilesforType(String foldername, String nameContainsStr) {
        List<String> resultFiles = new LinkedList<>();

//        System.out.println("Finding files in...: " + RESULT_PATH + foldername + "/*.json");

        File folder = new File(RESULT_PATH + foldername);
        File[] listOfFiles = folder.listFiles();
//        System.out.println(folder.toString());

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()
                    && (nameContainsStr.isEmpty()
                    || nameContainsStr.equals("*")
                    || listOfFiles[i].toString().contains(nameContainsStr) )) {
                String filename = listOfFiles[i].getPath();
                if (filename.endsWith(".json")) {
                    resultFiles.add(filename);
                }
            }
        }

        return resultFiles;
    }

    public static List<String> getNewResultFilesforType(String foldername, SER_PROTOCOL serprotocol, String nameContainsStr) {
        List<String> resultFiles = new LinkedList<>();

//        System.out.println("Finding files in...: " + RESULT_PATH + foldername + "/*.json");

        File folder = new File(RESULT_PATH + foldername);
        File[] listOfFiles = folder.listFiles();
//        System.out.println(folder.toString());

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()
                    && (nameContainsStr.equals("*")
                        || listOfFiles[i].toString().contains(nameContainsStr+"-") )
                    && (serprotocol.equals(SER_PROTOCOL.UNKNOWN)
                        || listOfFiles[i].toString().contains(serprotocol.shortName + "-"))) {
                String filename = listOfFiles[i].getPath().toString();
                if (filename.endsWith(".json")) {
                    resultFiles.add(filename);
                }
            }
        }

        return resultFiles;
    }

    public static List<String> getNewestResultFileforType(String foldername, SER_PROTOCOL serprotocol, String nameContainsStr) {
        List<String> resultFiles = new LinkedList<>();

//        System.out.println("Finding files in...: " + RESULT_PATH + foldername);

        File folder = new File(RESULT_PATH + foldername);
        File[] listOfFiles = folder.listFiles();
        List<File> filelist = Arrays.asList(listOfFiles);
        filelist.sort(Comparator.comparing(File::getName));
//        System.out.println(folder.toString());

        int setCount = listOfFiles.length / 7;
        int lastSetStart = setCount > 1 ? 7*(setCount-1) : 0;
//        System.out.println("setcount: " + setCount + " -- lastsetstart: " + lastSetStart);

        for (int i = lastSetStart; i < filelist.size(); i++) {
//            System.out.println("Checking file nr." + i + " -> " + filelist.get(i));
            if (filelist.get(i).isFile()
                    && ( nameContainsStr.equals("*")
                        || filelist.get(i).toString().contains(nameContainsStr+"-") )
                    && ( serprotocol.equals(SER_PROTOCOL.UNKNOWN)
                     || filelist.get(i).toString().contains(serprotocol.shortName + "-")) ) {
//                System.out.println("file accepted: " + filelist.get(i));
                String filename = filelist.get(i).getPath();
                if (filename.endsWith(".json")) {
                    resultFiles.add(filename);
                }
            }
        }

        return resultFiles;
    }

    public static List<String> getResultFilesforType(SER_PROTOCOL ser_protocol, boolean raw) {
        List<String> resultFiles = new LinkedList<>();
        String foldername = getFolderNameForSerProtocol(ser_protocol);
        return getResultFilesforType(foldername, raw);
    }

    public static List<String> getResultFilesforType(String folderName, boolean raw) {
        List<String> resultFiles = new LinkedList<>();

//        System.out.println("Finding files in...: " + RESULT_PATH_OLD + (raw? "raw/": "processed/") + getFolderForType(folderName) + "/*.json");

        File folder = new File(RESULT_PATH_OLD + (raw? "raw/": "processed/") + getFolderForType(folderName));
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String filename = listOfFiles[i].getPath().toString();
                if (filename.endsWith(".json")) {
                    resultFiles.add(filename);
                }
            }
        }

        return resultFiles;
    }

    public static List<String> getResultFilesforType(String folderName, String nameContainsStr, boolean raw) {
        List<String> resultFiles = new LinkedList<>();

//        System.out.println("Finding files in...: " + "MsgEncoding/jmh-results/" + (raw? "raw/": "processed/") + getFolderForType(folderName) + "/*.json");

        File folder = new File(RESULT_PATH_OLD + (raw? "raw/": "processed/") + getFolderForType(folderName));
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile() && !nameContainsStr.isEmpty() && listOfFiles[i].toString().contains(nameContainsStr)) {
                String filename = listOfFiles[i].getPath().toString();
                if (filename.endsWith(".json")) {
                    resultFiles.add(filename);
                }
            }
        }

        return resultFiles;
    }

    public static List<String> getResultFiles_SerSize(String nameContainsStr) {
        List<String> resultFiles = new LinkedList<>();

//        System.out.println("Finding files in...: " + RESULT_PATH_SERSIZE);

        File folder = new File(RESULT_PATH_SERSIZE);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile() && !nameContainsStr.isEmpty() && listOfFiles[i].toString().contains(nameContainsStr)) {
                String filename = listOfFiles[i].getPath().toString();
                if (filename.endsWith(".json")) {
                    resultFiles.add(filename);
                }
            }
        }

        return resultFiles;
    }

    public static String getStringFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String         line = null;
        StringBuilder  stringBuilder = new StringBuilder();
        String         ls = System.getProperty("line.separator");

        try {
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            return stringBuilder.toString().trim();
        } finally {
            reader.close();
        }
    }

    public static void writeResultToFile(String filename, JsonObject result) throws FileNotFoundException, UnsupportedEncodingException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(result);
        PrintWriter writer = new PrintWriter(filename, "UTF-8");
        writer.write(json);
        writer.close();
    }

    private static String getFolderForType(String typeInName) {
        return typeInName;
    }

    public static String getFolderNameForSerProtocol(SER_PROTOCOL serprotocol) {
        String foldername = "";
        switch (serprotocol) {
            case JAVA:
                foldername = "java";
                break;
            case PROTOBUF:
                foldername = "proto";
                break;
            case AVRO:
                foldername = "avro";
                break;
            case AVRO_JSON:
                foldername = "avro-json";
                break;
            case FST:
                foldername = "fst-binary";
                break;
            case KRYO:
                foldername = "kryo-binary";
                break;
            case HESSIAN:
                foldername = "hessian-binary";
                break;
            case UNKNOWN:
                return "";
        }
        return foldername;
    }
}
