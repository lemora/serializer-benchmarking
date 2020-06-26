package benchmarking.size;

import com.google.gson.JsonObject;
import messages.realworld.Msg;
import messages.realworld.*;
import messages.typelists.DoubleList;
import messages.typelists.IntList;
import messages.typelists.StringList;
import results.BenchmarkResult;
import results.BmResultsContainer;
import serializer.Helper;
import serializer.SerializerProtobuf;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import static benchmarking.size.SerSizer.createResultJsonObject;
import static results.ResultFileIO.containerVals;
import static results.ResultFileIO.writeResultToFile;

public class ProtoSerSizer {

    private static final String BENCHMARKER = "ProtoSerSizer";
    private static final String SERTYPE = "proto";

    public static void evaluateSerSizes() throws FileNotFoundException, UnsupportedEncodingException {
        List<BenchmarkResult> resultList = new LinkedList<>();
        resultList.add( new BenchmarkResult(SerializerProtobuf.ser_Msg(new Msg()).length, 0, SERTYPE + "_msg00_ser") );
        resultList.add( new BenchmarkResult(SerializerProtobuf.ser_MsgRw01(new Msg_rw01()).length, 0, SERTYPE + "_msg01_ser") );
        resultList.add( new BenchmarkResult(SerializerProtobuf.ser_MsgRw02(new Msg_rw02()).length, 0, SERTYPE + "_msg02_ser") );
        resultList.add( new BenchmarkResult(SerializerProtobuf.ser_MsgRw03(new Msg_rw03()).length, 0, SERTYPE + "_msg03_ser") );
        resultList.add( new BenchmarkResult(SerializerProtobuf.ser_MsgRw04(Msg_rw04.createTestInstance()).length, 0, SERTYPE + "_msg04_ser") );
        resultList.add( new BenchmarkResult(SerializerProtobuf.ser_MsgRw05(new Msg_rw05()).length, 0, SERTYPE + "_msg05_ser") );

        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        String date = sdf.format( Calendar.getInstance().getTime() );
        JsonObject resultJsonObject = createResultJsonObject(BENCHMARKER, SERTYPE, resultList);
        writeResultToFile("jmh-results/sersizes/" + date + "-" + SERTYPE + "-sersizes.json", resultJsonObject);
    }

    public static void evaluateSerSizesTestobj() throws FileNotFoundException, UnsupportedEncodingException {
        List<BenchmarkResult> resultList = new LinkedList<>();
        resultList.add( new BenchmarkResult(SerializerProtobuf.ser_Msg(new Msg()).length, 0, SERTYPE + "_msg00_ser") );
        resultList.add( new BenchmarkResult(SerializerProtobuf.ser_MsgRw01(new Msg_rw01()).length, 0, SERTYPE + "_msg01_ser") );
        resultList.add( new BenchmarkResult(SerializerProtobuf.ser_MsgRw02(new Msg_rw02()).length, 0, SERTYPE + "_msg02_ser") );
        resultList.add( new BenchmarkResult(SerializerProtobuf.ser_MsgRw03(new Msg_rw03()).length, 0, SERTYPE + "_msg03_ser") );
        resultList.add( new BenchmarkResult(SerializerProtobuf.ser_MsgRw04(Msg_rw04.createTestInstance()).length, 0, SERTYPE + "_msg04_ser") );
        resultList.add( new BenchmarkResult(SerializerProtobuf.ser_MsgRw05(new Msg_rw05()).length, 0, SERTYPE + "_msg05_ser") );

        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        String date = sdf.format( Calendar.getInstance().getTime() );
        JsonObject resultJsonObject = createResultJsonObject(BENCHMARKER, SERTYPE, resultList);
        writeResultToFile("jmh-results/sersizes/" + date + "-" + SERTYPE + "-testobj-sersizes.json", resultJsonObject);
    }

    public static void evaluateSerSizesContainer(BmResultsContainer.List_Type containerType) throws IOException {
        List<BenchmarkResult> resultList = new LinkedList<>();

        for(int i = 0; i < containerVals.length; i++) {
            int valCount = containerVals[i];
            switch (containerType) {
                case STRING_LIST:
                    StringList sl = new StringList(Helper.getStringListFromCSV(valCount));
                    resultList.add( new BenchmarkResult(SerializerProtobuf.ser_StringList(sl).length,  "sersize_stringlist_" + valCount, valCount) );
                    break;
                case INT_LIST:
                    IntList il = new IntList(Helper.getIntListFromCSV(valCount));
                    resultList.add( new BenchmarkResult(SerializerProtobuf.ser_IntList(il).length,  "sersize_intlist_" + valCount, valCount) );
                    break;
                case DOUBLE_LIST:
                    DoubleList dl = new DoubleList(Helper.getDoubleListFromCSV(valCount));
                    resultList.add( new BenchmarkResult(SerializerProtobuf.ser_DoubleList(dl).length, "sersize_doublelist_" + valCount, valCount) );
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

}
