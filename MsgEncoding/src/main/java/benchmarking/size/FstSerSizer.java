package benchmarking.size;

import com.google.gson.JsonObject;
import messages.dcachey.PoolCostInfo;
import messages.dcachey.PoolManagerPoolUpMessage;
import messages.dcachey.PoolV2Mode;
import messages.realworld.Msg;
import messages.realworld.*;
import messages.typelists.DoubleList;
import messages.typelists.IntList;
import messages.typelists.StringList;
import results.BenchmarkResult;
import results.BmResultsContainer;
import serializer.Helper;
import serializer.SerializerFst;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static benchmarking.size.SerSizer.createResultJsonObject;
import static results.ResultFileIO.*;

public class FstSerSizer {

    private static final String BENCHMARKER = "FstSerSizer";
    private static final String SERTYPE = "fst";

    public static void evaluateSerSizesTestobj() throws FileNotFoundException, UnsupportedEncodingException {
        setup();
        List<BenchmarkResult> resultList = new LinkedList<>();
        resultList.add( new BenchmarkResult(SerializerFst.serialize(new Msg()).length, 0, SERTYPE + "_msg00_ser") );
        resultList.add( new BenchmarkResult(SerializerFst.serialize(new Msg_rw01()).length, 0, SERTYPE + "_msg01_ser") );
        resultList.add( new BenchmarkResult(SerializerFst.serialize(new Msg_rw02()).length, 0, SERTYPE + "_msg02_ser") );
        resultList.add( new BenchmarkResult(SerializerFst.serialize(new Msg_rw03()).length, 0, SERTYPE + "_msg03_ser") );
        resultList.add( new BenchmarkResult(SerializerFst.serialize(Msg_rw04.createTestInstance()).length, 0, SERTYPE + "_msg04_ser") );
        resultList.add( new BenchmarkResult(SerializerFst.serialize(new Msg_rw05()).length, 0, SERTYPE + "_msg05_ser") );

        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        String date = sdf.format( Calendar.getInstance().getTime() );
        JsonObject resultJsonObject = createResultJsonObject(BENCHMARKER, SERTYPE, resultList);
        writeResultToFile("jmh-results/sersizes/" + date + "-" + SERTYPE + "-testobj-sersizes.json", resultJsonObject);
    }

    public static void evaluateSerSizesContainer(BmResultsContainer.List_Type containerType) throws IOException {
        setup();
        List<BenchmarkResult> resultList = new LinkedList<>();

        for(int i = 0; i < containerVals.length; i++) {
            int valCount = containerVals[i];
            switch (containerType) {
                case STRING_LIST:
                    StringList sl = new StringList(Helper.getStringListFromCSV(valCount));
                    resultList.add( new BenchmarkResult(SerializerFst.serialize(sl).length, "sersize_stringlist_" + valCount, valCount) );
                    break;
                case INT_LIST:
                    IntList il = new IntList(Helper.getIntListFromCSV(valCount));
                    resultList.add( new BenchmarkResult(SerializerFst.serialize(il).length, "sersize_intlist_" + valCount, valCount) );
                    break;
                case DOUBLE_LIST:
                    DoubleList dl = new DoubleList(Helper.getDoubleListFromCSV(valCount));
                    resultList.add( new BenchmarkResult(SerializerFst.serialize(dl).length, "sersize_doublelist_" + valCount, valCount) );
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
        resultList.add( new BenchmarkResult(SerializerFst.serialize(PoolManagerPoolUpMessage.createTestInstance()).length, 0, SERTYPE + "_pmpum_ser") );

        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        String date = sdf.format( Calendar.getInstance().getTime() );
        JsonObject resultJsonObject = createResultJsonObject(BENCHMARKER, SERTYPE, resultList);
        writeResultToFile("jmh-results/sersizes/" + date + "-" + SERTYPE + "-dcachey-sersizes.json", resultJsonObject);
    }

    public static void setup() {
        System.out.println("Setup -- registering Fst classes");
        // testobj
        SerializerFst.registerClass( Msg.class );
        SerializerFst.registerClass( Msg_rw01.class );
        SerializerFst.registerClass( Msg_rw02.class );
        SerializerFst.registerClass( Msg_rw03.class );
        SerializerFst.registerClass( Msg_rw04.class );
        SerializerFst.registerClass( Msg_rw05.class );
        // containers
        SerializerFst.registerClass( HashMap.class );
        SerializerFst.registerClass( LinkedList.class );
        // dcachey
        SerializerFst.registerClass( PoolManagerPoolUpMessage.class );
        SerializerFst.registerClass(PoolV2Mode.class );
        SerializerFst.registerClass(PoolCostInfo.class );
        SerializerFst.registerClass(PoolCostInfo.PoolSpaceInfo.class );
    }
}
