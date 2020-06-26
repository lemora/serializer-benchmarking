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

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import messages.typelists.DoubleList;
import messages.typelists.IntList;
import messages.typelists.StringList;
import serializer.Helper;
import serializer.SerializerAvro;


@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(value = 2)
@Warmup(iterations = 4, time = 10, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 100, timeUnit = TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class AvroContainerBenchmarker {

    @State(Scope.Thread)
    public static class IntListObjects {

        @Param({"10", "100", "500", "1000", "10000", "100000"})
        public int valueCount;

        public Avro_IntList avro_intList;
        public byte[] smsg_binary_intList;

        @Setup(Level.Trial)
        public void doSetup() throws IOException {
            IntList list = new IntList(Helper.getIntListFromCSV(valueCount));
            avro_intList = SerializerAvro.msgToAvro_IntList(list);
            smsg_binary_intList = SerializerAvro.serAvro_binary_IntList(avro_intList);
        }
    }

    @State(Scope.Thread)
    public static class DoubleListObjects {

        @Param({"10", "100", "500", "1000", "10000", "100000"})
        public int valueCount;

        public Avro_DoubleList avro_doubleList;
        public byte[] smsg_binary_doubleList;

        @Setup(Level.Trial)
        public void doSetup() throws IOException {
            DoubleList list = new DoubleList(Helper.getDoubleListFromCSV(valueCount));
            avro_doubleList = SerializerAvro.msgToAvro_DoubleList(list);
            smsg_binary_doubleList = SerializerAvro.serAvro_binary_DoubleList(avro_doubleList);
        }
    }

    @State(Scope.Thread)
    public static class StringListObjects {

        @Param({"10", "100", "500", "1000", "10000", "100000"})
        public int valueCount;

        public Avro_StringList avro_stringList;
        public byte[] smsg_binary_stringList;

        @Setup(Level.Trial)
        public void doSetup() throws IOException {
            StringList list = new StringList(Helper.getStringListFromCSV(valueCount));
            avro_stringList = SerializerAvro.msgToAvro_StringList(list);
            smsg_binary_stringList = SerializerAvro.serAvro_binary_StringList(avro_stringList);
        }
    }

// ------ StringList ------

    @Benchmark
    public byte[] avro_StringList_ser(StringListObjects stringList) {
        return SerializerAvro.serAvro_binary_StringList(stringList.avro_stringList);
    }

    @Benchmark
    public Object avro_StringList_deser(StringListObjects stringList) {
        try {
            return SerializerAvro.deserToAvro_binary_StringList(stringList.smsg_binary_stringList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


// ------ DoubleList ------

    @Benchmark
    public byte[] avro_DoubleList_ser(DoubleListObjects doubleList) {
        return SerializerAvro.serAvro_binary_DoubleList(doubleList.avro_doubleList);
    }

    @Benchmark
    public Object avro_DoubleList_deser(DoubleListObjects doubleList) {
        try {
            return SerializerAvro.deserToAvro_binary_DoubleList(doubleList.smsg_binary_doubleList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

// ------ IntList ------

    @Benchmark
    public byte[] avro_IntList_ser(IntListObjects intList) {
        return SerializerAvro.serAvro_binary_IntList(intList.avro_intList);
    }

    @Benchmark
    public Object avro_IntList_deser(IntListObjects intList) {
        try {
            return SerializerAvro.deserToAvro_binary_IntList(intList.smsg_binary_intList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
