package benchmarking.size;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import results.BenchmarkResult;
import results.ResultFileIO;
import serializer.SerializerAvro;

import static results.BmResultsContainer.List_Type;

public class SerSizer {

    public static void main(String[] args) throws IOException {
        // Java
        JavaSerSizer.evaluateSerSizesTestobj();
        JavaSerSizer.evaluateSerSizesContainer(List_Type.INT_LIST);
        JavaSerSizer.evaluateSerSizesContainer(List_Type.DOUBLE_LIST);
        JavaSerSizer.evaluateSerSizesContainer(List_Type.STRING_LIST);
        JavaSerSizer.evaluateSerSizesDcachey();

        // Avro
        for(SerializerAvro.Type mode : SerializerAvro.Type.values()) {
            AvroSerSizer.evaluateSerSizesTestobj(mode);
            AvroSerSizer.evaluateSerSizesContainer(mode, List_Type.INT_LIST);
            AvroSerSizer.evaluateSerSizesContainer(mode, List_Type.DOUBLE_LIST);
            AvroSerSizer.evaluateSerSizesContainer(mode, List_Type.STRING_LIST);
        }

        // Proto
        ProtoSerSizer.evaluateSerSizesTestobj();
        ProtoSerSizer.evaluateSerSizesContainer(List_Type.INT_LIST);
        ProtoSerSizer.evaluateSerSizesContainer(List_Type.DOUBLE_LIST);
        ProtoSerSizer.evaluateSerSizesContainer(List_Type.STRING_LIST);

        // Protostuff
        ProtostuffSerSizer.evaluateSerSizesTestobj();
        ProtostuffSerSizer.evaluateSerSizesContainer(List_Type.INT_LIST);
        ProtostuffSerSizer.evaluateSerSizesContainer(List_Type.DOUBLE_LIST);
        ProtostuffSerSizer.evaluateSerSizesContainer(List_Type.STRING_LIST);
        ProtostuffSerSizer.evaluateSerSizesDcachey();

        // Fst
        FstSerSizer.evaluateSerSizesTestobj();
        FstSerSizer.evaluateSerSizesContainer(List_Type.INT_LIST);
        FstSerSizer.evaluateSerSizesContainer(List_Type.DOUBLE_LIST);
        FstSerSizer.evaluateSerSizesContainer(List_Type.STRING_LIST);
        FstSerSizer.evaluateSerSizesDcachey();

        // Kryo
        KryoSerSizer.evaluateSerSizesTestobj();
        KryoSerSizer.evaluateSerSizesContainer(List_Type.INT_LIST);
        KryoSerSizer.evaluateSerSizesContainer(List_Type.DOUBLE_LIST);
        KryoSerSizer.evaluateSerSizesContainer(List_Type.STRING_LIST);
        KryoSerSizer.evaluateSerSizesDcachey();

        // Hessian
        HessianSerSizer.evaluateSerSizesTestobj();
        HessianSerSizer.evaluateSerSizesContainer(List_Type.INT_LIST);
        HessianSerSizer.evaluateSerSizesContainer(List_Type.DOUBLE_LIST);
        HessianSerSizer.evaluateSerSizesContainer(List_Type.STRING_LIST);
        HessianSerSizer.evaluateSerSizesDcachey();
    }

    public static JsonObject createResultJsonObject(String benchmarkerName, String type, List<BenchmarkResult> resultList) {
        // create JSON Object
        JsonObject minimalResult = new JsonObject();

        JsonObject environment = new JsonObject();
        environment.addProperty("jdkVersion", "");
        environment.addProperty("vmName", "");
        environment.addProperty("vmVersion","");
        environment.addProperty("machineName", ResultFileIO.Machine.ZITPCX22820.name);
        environment.addProperty("physicalCores", ResultFileIO.Machine.ZITPCX22820.physCores);
        environment.addProperty("virtualCores", ResultFileIO.Machine.ZITPCX22820.virtCores);
        environment.addProperty("numberOfParallelBenchmarkers", 1);

        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        String date = sdf.format( Calendar.getInstance().getTime() );
        environment.add("date", new JsonPrimitive(date));
        minimalResult.add("environment", environment);

        JsonObject jmh = new JsonObject();
        jmh.add("scoreUnit", new JsonPrimitive("bytes/object"));
        minimalResult.add("jmh", jmh);

        JsonObject benchmarker = new JsonObject();
        benchmarker.add("name", new JsonPrimitive( benchmarkerName ));
        benchmarker.add("type", new JsonPrimitive( benchmarkerName.replace("SerSizer", "").toLowerCase() ));
        minimalResult.add("benchmarker", benchmarker);

        JsonArray resultValues = new JsonArray();

        for (BenchmarkResult res : resultList) {
            JsonObject resultValueObject = new JsonObject();
            resultValueObject.add( "benchmark", new JsonPrimitive(res.getBenchmarkName()) );
            resultValueObject.add( "object", new JsonPrimitive( res.getSerializedStructName()) );
//            resultValueObject.add( "mode", new JsonPrimitive("") );
            resultValueObject.add( "score", new JsonPrimitive(res.getScore()));
            resultValueObject.add("valueCount", new JsonPrimitive(res.getValueCount()));
            resultValues.add(resultValueObject);
        }
        minimalResult.add("values", resultValues);

        return minimalResult;
    }
}
