package benchmarking.runtime;

import frameworks.avro.generated.randobjects.Avro_DoubleList;
import frameworks.avro.generated.randobjects.Avro_IntList;
import frameworks.avro.generated.randobjects.Avro_StringList;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import messages.typelists.DoubleList;
import messages.typelists.IntList;
import messages.typelists.StringList;
import serializer.Helper;
import serializer.SerializerAvro;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(value = 2)
@Warmup(iterations = 4, time = 10, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 100, timeUnit = TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class AvroJsonContainerBenchmarker {


    @State(Scope.Thread)
    public static class IntListObjects {

        @Param({"10", "100", "500", "1000", "10000", "100000"})
        public int valueCount;

        public Avro_IntList avrojson_intList;
        public byte[] smsg_json_intList;

        @Setup(Level.Trial)
        public void doSetup() throws IOException {
            IntList list = new IntList(Helper.getIntListFromCSV(valueCount));
            avrojson_intList = SerializerAvro.msgToAvro_IntList(list);
            smsg_json_intList = SerializerAvro.serAvro_json_IntList(avrojson_intList);
        }
    }

    @State(Scope.Thread)
    public static class DoubleListObjects {

        @Param({"10", "100", "500", "1000", "10000", "100000"})
        public int valueCount;

        public Avro_DoubleList avrojson_doubleList;
        public byte[] smsg_json_doubleList;

        @Setup(Level.Trial)
        public void doSetup() throws IOException {
            DoubleList list = new DoubleList(Helper.getDoubleListFromCSV(valueCount));
            avrojson_doubleList = SerializerAvro.msgToAvro_DoubleList(list);
            smsg_json_doubleList = SerializerAvro.serAvro_json_DoubleList(avrojson_doubleList);
        }
    }

    @State(Scope.Thread)
    public static class StringListObjects {

        @Param({"10", "100", "500", "1000", "10000", "100000"})
        public int valueCount;

        public Avro_StringList avrojson_stringList;
        public byte[] smsg_json_stringList;

        @Setup(Level.Trial)
        public void doSetup() throws IOException {
            StringList list = new StringList(Helper.getStringListFromCSV(valueCount));
            avrojson_stringList = SerializerAvro.msgToAvro_StringList(list);
            smsg_json_stringList = SerializerAvro.serAvro_json_StringList(avrojson_stringList);
        }
    }

// ------ StringList ------

    @Benchmark
    public byte[] avrojson_StringList_ser(StringListObjects stringList) {
        return SerializerAvro.serAvro_json_StringList(stringList.avrojson_stringList);
    }

    @Benchmark
    public Object avrojson_StringList_deser(StringListObjects stringList) {
        try {
            return SerializerAvro.deserToAvro_json_StringList(stringList.smsg_json_stringList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


// ------ DoubleList ------

    @Benchmark
    public byte[] avrojson_DoubleList_ser(DoubleListObjects doubleList) {
        return SerializerAvro.serAvro_json_DoubleList(doubleList.avrojson_doubleList);
    }

    @Benchmark
    public Object avrojson_DoubleList_deser(DoubleListObjects doubleList) {
        try {
            return SerializerAvro.deserToAvro_json_DoubleList(doubleList.smsg_json_doubleList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

// ------ IntList ------

    @Benchmark
    public byte[] avrojson_IntList_ser(IntListObjects intList) {
        return SerializerAvro.serAvro_json_IntList(intList.avrojson_intList);
    }

    @Benchmark
    public Object avrojson_IntList_deser(IntListObjects intList) {
        try {
            return SerializerAvro.deserToAvro_json_IntList(intList.smsg_json_intList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
