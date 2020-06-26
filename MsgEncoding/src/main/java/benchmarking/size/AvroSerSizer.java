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
import serializer.SerializerAvro;

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

public class AvroSerSizer {

    private static final String BENCHMARKER = "SerSizerAvro";
    private static final String SERTYPE = "avro";
    
    public static void evaluateSerSizesTestobj(SerializerAvro.Type type) throws FileNotFoundException, UnsupportedEncodingException {
        String sertype = SERTYPE + type.name;
        List<BenchmarkResult> resultList = new LinkedList<>();
        resultList.add( new BenchmarkResult(SerializerAvro.ser_Msg(new Msg(), type).length, 0, sertype + "_msg00_ser") );
        resultList.add( new BenchmarkResult(SerializerAvro.ser_MsgRw01(new Msg_rw01(), type).length, 0, sertype + "_msg01_ser") );
        resultList.add( new BenchmarkResult(SerializerAvro.ser_MsgRw02(new Msg_rw02(), type).length, 0, sertype + "_msg02_ser") );
        resultList.add( new BenchmarkResult(SerializerAvro.ser_MsgRw03(new Msg_rw03(), type).length, 0, sertype + "_msg03_ser") );
        resultList.add( new BenchmarkResult(SerializerAvro.ser_MsgRw04(Msg_rw04.createTestInstance(), type).length, 0, sertype + "_msg04_ser") );
        resultList.add( new BenchmarkResult(SerializerAvro.ser_MsgRw05(new Msg_rw05(), type).length, 0, sertype + "_msg05_ser") );

        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        String date = sdf.format( Calendar.getInstance().getTime() );
        JsonObject resultJsonObject = createResultJsonObject(BENCHMARKER, SERTYPE, resultList);
        writeResultToFile("jmh-results/sersizes/" + date + "-" + sertype + "-testobj-sersizes.json", resultJsonObject);
    }

    public static void evaluateSerSizesContainer(SerializerAvro.Type type, BmResultsContainer.List_Type containerType) throws IOException {
        String sertype = SERTYPE + type.name;
        List<BenchmarkResult> resultList = new LinkedList<>();

        for(int i = 0; i < containerVals.length; i++) {
            int valCount = containerVals[i];
            switch (containerType) {
                case STRING_LIST:
                    StringList sl = new StringList(Helper.getStringListFromCSV(valCount));
                    resultList.add( new BenchmarkResult(SerializerAvro.ser_StringList(sl, type).length, "sersize_stringlist_" + valCount, valCount) );
                    break;
                case INT_LIST:
                    IntList il = new IntList(Helper.getIntListFromCSV(valCount));
                    resultList.add( new BenchmarkResult(SerializerAvro.ser_IntList(il, type).length, "sersize_intlist_" + valCount, valCount) );
                    break;
                case DOUBLE_LIST:
                    DoubleList dl = new DoubleList(Helper.getDoubleListFromCSV(valCount));
                    resultList.add( new BenchmarkResult(SerializerAvro.ser_DoubleList(dl, type).length, "sersize_doublelist_" + valCount, valCount) );
                    break;
                case ANY:
                    break;
            }
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        String date = sdf.format( Calendar.getInstance().getTime() );
        JsonObject resultJsonObject = createResultJsonObject(BENCHMARKER+type.name.toUpperCase(), SERTYPE, resultList);
        writeResultToFile("jmh-results/sersizes/" + date + "-" + sertype + "-" + containerType.name + "-container-sersizes.json", resultJsonObject);
    }
}
