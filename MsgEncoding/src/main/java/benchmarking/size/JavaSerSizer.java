package benchmarking.size;

import com.google.gson.JsonObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import messages.dcachey.PoolManagerPoolUpMessage;
import messages.realworld.Msg;
import messages.realworld.Msg_rw01;
import messages.realworld.Msg_rw02;
import messages.realworld.Msg_rw03;
import messages.realworld.Msg_rw04;
import messages.realworld.Msg_rw05;
import messages.typelists.DoubleList;
import messages.typelists.IntList;
import messages.typelists.StringList;
import results.BenchmarkResult;
import serializer.Helper;
import serializer.SerializerJava;

import static benchmarking.size.SerSizer.createResultJsonObject;
import static results.ResultFileIO.writeResultToFile;
import static results.ResultFileIO.containerVals;
import static results.BmResultsContainer.List_Type;

public class JavaSerSizer {

    private static final String BENCHMARKER = "JavaSerSizer";
    private static final String SERTYPE = "java";

    public static void evaluateSerSizesTestobj() throws FileNotFoundException, UnsupportedEncodingException {
        List<BenchmarkResult> resultList = new LinkedList<>();
        resultList.add( new BenchmarkResult(SerializerJava.serialize(new Msg()).length, 0, SERTYPE + "_msg00_ser") );
        resultList.add( new BenchmarkResult(SerializerJava.serialize(new Msg_rw01()).length, 0, SERTYPE + "_msg01_ser") );
        resultList.add( new BenchmarkResult(SerializerJava.serialize(new Msg_rw02()).length, 0, SERTYPE + "_msg02_ser") );
        resultList.add( new BenchmarkResult(SerializerJava.serialize(new Msg_rw03()).length, 0, SERTYPE + "_msg03_ser") );
        resultList.add( new BenchmarkResult(SerializerJava.serialize(Msg_rw04.createTestInstance()).length, 0, SERTYPE + "_msg04_ser") );
        resultList.add( new BenchmarkResult(SerializerJava.serialize(new Msg_rw05()).length, 0, SERTYPE + "_msg05_ser") );

        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        String date = sdf.format( Calendar.getInstance().getTime() );
        JsonObject resultJsonObject = createResultJsonObject(BENCHMARKER, SERTYPE, resultList);
        writeResultToFile("jmh-results/sersizes/" + date + "-" + SERTYPE + "-testobj-sersizes.json", resultJsonObject);
    }

    public static void evaluateSerSizesContainer(List_Type containerType) throws IOException {
        List<BenchmarkResult> resultList = new LinkedList<>();

        for(int i = 0; i < containerVals.length; i++) {
            int valCount = containerVals[i];
            switch (containerType) {
                case STRING_LIST:
                    StringList sl = new StringList(Helper.getStringListFromCSV(valCount));
                    resultList.add( new BenchmarkResult(SerializerJava.serialize(sl).length, "sersize_stringlist_" + valCount, valCount) );
                    break;
                case INT_LIST:
                    IntList il = new IntList(Helper.getIntListFromCSV(valCount));
                    resultList.add( new BenchmarkResult(SerializerJava.serialize(il).length, "sersize_intlist_" + valCount, valCount) );
                    break;
                case DOUBLE_LIST:
                    DoubleList dl = new DoubleList(Helper.getDoubleListFromCSV(valCount));
                    resultList.add( new BenchmarkResult(SerializerJava.serialize(dl).length, "sersize_doublelist_" + valCount, valCount) );
                    break;
                case ANY:
                    break;
            }
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        String date = sdf.format( Calendar.getInstance().getTime() );
        JsonObject resultJsonObject = createResultJsonObject(BENCHMARKER, SERTYPE, resultList);
        writeResultToFile("jmh-results/sersizes/" + date + "-" + SERTYPE + "-" + containerType.name + "-container-sersizes.json", resultJsonObject);
    }

    public static void evaluateSerSizesDcachey() throws IOException {
        List<BenchmarkResult> resultList = new LinkedList<>();
        resultList.add( new BenchmarkResult(SerializerJava.serialize(PoolManagerPoolUpMessage.createTestInstance()).length, 0, SERTYPE + "_pmpum_ser") );

        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        String date = sdf.format( Calendar.getInstance().getTime() );
        JsonObject resultJsonObject = createResultJsonObject(BENCHMARKER, SERTYPE, resultList);
        writeResultToFile("jmh-results/sersizes/" + date + "-" + SERTYPE + "-dcachey-sersizes.json", resultJsonObject);
    }
}
