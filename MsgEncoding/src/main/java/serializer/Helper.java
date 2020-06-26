package serializer;

import com.google.common.primitives.Longs;
import results.ResultFileIO;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

public class Helper {

    private static final String PATH_TO_DATA = "src/main/java/messages/data/";
    public static boolean debug = true;

    public static SER_PROTOCOL stringToSerProtocol(String serProtocol) {
        switch (serProtocol.toLowerCase()) {
            case "jos" :
            case "java" :
                return SER_PROTOCOL.JAVA;
            case "proto" :
            case "protobuf":
                return SER_PROTOCOL.PROTOBUF;
            case "protostuff" :
            case "psr" :
                return SER_PROTOCOL.PROTOSTUFF;
            case "avro" :
                return SER_PROTOCOL.AVRO;
            case "avrojson" :
                return SER_PROTOCOL.AVRO_JSON;
            case "fst" :
                return SER_PROTOCOL.FST;
            case "kryo" :
                return SER_PROTOCOL.KRYO;
            case "hessian" :
                return SER_PROTOCOL.HESSIAN;
            default:
                throw new UnsupportedOperationException("Serialization protocol '" + serProtocol + "' not known.");
        }
    }

    public static ResultFileIO.Machine stringToMachine(String machineName) {
        ResultFileIO.Machine machine = ResultFileIO.Machine.ZITPCX22820;
        switch (machineName.toLowerCase()) {
            case "zitpcx22820":
                machine = ResultFileIO.Machine.ZITPCX22820;
                break;
            case "dot1":
            case "dcache-dot1":
                machine = ResultFileIO.Machine.DCACHE_DOT1;
                break;
            case "dot13":
                machine = ResultFileIO.Machine.DCACHE_DOT13;
                break;
            case "dcache-dot13":
            case "dot13cpulock":
                machine = ResultFileIO.Machine.DCACHE_DOT13_CPULOCK;
                break;
            case "dca03":
            case "dcache-core-atlas03":
                machine = ResultFileIO.Machine.DCACHE_CORE_ATLAS03;
                break;
        }
        return machine;
    }

    public static long bytesToLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.put(bytes);
        buffer.flip();
        return buffer.getLong();
    }

    public static byte[] longToBytearr(long x) {
        return Longs.toByteArray(x);
    }

    public static byte[] concatByteArrays(byte[] arr1, byte[] arr2) {
        ByteArrayOutputStream array = new ByteArrayOutputStream(256);
        array.write(arr1, 0 ,arr1.length);
        array.write(arr2, 0, arr2.length);

        return array.toByteArray();
    }

    public static String byteArrayToString(byte[] byteArray) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < byteArray.length; i++) {
            sb.append(byteArray[i] + " ");
            if(i != 0 && i%15 == 0) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public static void debugPrint(String debugOutput) {
        if (debug) {
            System.out.println(debugOutput);
        }
    }

// ------------ List from CSV

    public static List<Integer> getIntListFromCSV(int count) throws IOException {
//        Helper.debugPrint("Generate Int-List from CSV file with: " + count + " ints");
        String filestring = "int" + count + ".csv";
        String csvString = "";
        try {
            csvString = ResultFileIO.getStringFromFile(PATH_TO_DATA + filestring);
        } catch (IOException e) {
            InputStream stream = Helper.class.getResourceAsStream("/" + filestring);
            if(stream != null) {
                Scanner sc = new java.util.Scanner(stream).useDelimiter("\\A");
                csvString = sc.hasNext() ? sc.next() : "";
            }
        }

        if (csvString.isEmpty()) {
            throw new IOException("Could not read CSV file " + filestring);
        }

        List<Integer> intlist = new LinkedList<>();
        Arrays.stream(csvString.split(",")).mapToInt(Integer::parseInt).forEach(intlist::add);
        return intlist;
    }

    public static List<Double> getDoubleListFromCSV(int count) throws IOException {
//        Helper.debugPrint("Generate Double-List from CSV file with: " + count + " ints");
        String filestring = "double" + count + ".csv";
        String csvString = "";
        try {
            csvString = ResultFileIO.getStringFromFile(PATH_TO_DATA + filestring);
        } catch (IOException e) {
            InputStream stream = Helper.class.getResourceAsStream("/"  + filestring);
            if(stream != null) {
                Scanner sc = new java.util.Scanner(stream).useDelimiter("\\A");
                csvString = sc.hasNext() ? sc.next() : "";
            }
        }
        if (csvString.isEmpty()) {
            throw new IOException("Could not read CSV file " + filestring);
        }

        List<Double> doublelist = new LinkedList<>();
        Arrays.stream(csvString.split(",")).mapToDouble(Double::parseDouble).forEach(doublelist::add);
        return doublelist;
    }

    public static List<String> getStringListFromCSV(int count) throws IOException {
//        Helper.debugPrint("Generate String-List from CSV file with: " + count + " ints");
        String filestring = "string" + count + ".csv";
        String csvString = "";
        try {
            csvString = ResultFileIO.getStringFromFile(PATH_TO_DATA + filestring);
        } catch (IOException e) {
            InputStream stream = Helper.class.getResourceAsStream("/" + filestring);
            if(stream != null) {
                Scanner sc = new java.util.Scanner(stream).useDelimiter("\\A");
                csvString = sc.hasNext() ? sc.next() : "";
            }
        }
        if (csvString.isEmpty()) {
            throw new IOException("Could not read CSV file " + filestring);
        }
            List<String> stringList = new LinkedList<>();
        Arrays.stream(csvString.split(",")).forEach(stringList::add);
        return stringList;
    }

    public static void generateRandomValuesFile(String type, int count) throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("Generating value csv file with: " + count + " " + type);

        StringBuilder sb = new StringBuilder();
        if(type.equals("int")) {
            new Random().ints(count).boxed().forEach(v -> sb.append(v + ","));
            sb.deleteCharAt(sb.length()-1);
        }
        else if (type.equals("double")) {
            new Random().doubles().limit(count).forEach(v -> sb.append(v + ","));
            sb.deleteCharAt(sb.length()-1);
        }
        else if (type.equals("string")) {
            for(int i = 0; i < count; i++) {
                sb.append(UUID.randomUUID().toString()).append(",");
            }
            sb.deleteCharAt(sb.length()-1);
        }
        String resultFile = PATH_TO_DATA + type + count + ".csv";
        PrintWriter writer = new PrintWriter(resultFile, "UTF-8");
        writer.write(sb.toString());
        writer.close();
    }
}
