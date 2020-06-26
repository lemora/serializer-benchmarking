package benchmarking.runtime;

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
import serializer.SerializerJava;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(value = 2)
@Warmup(iterations = 4, time = 10, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 100, timeUnit = TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class JavaContainerBenchmarker {

    @State(Scope.Thread)
    public static class IntListObjects {

        @Param({"10", "100", "500", "1000", "10000", "100000"})
        public int valueCount;

        public IntList intList;
        public byte[] smsg_intList;

        @Setup(Level.Trial)
        public void doSetup() throws IOException {
            intList = new IntList(Helper.getIntListFromCSV(valueCount));
            smsg_intList = SerializerJava.serialize(intList);
        }
    }

    @State(Scope.Thread)
    public static class DoubleListObjects {

        @Param({"10", "100", "500", "1000", "10000", "100000"})
        public int valueCount;

        public DoubleList doubleList;
        public byte[] smsg_doubleList;

        @Setup(Level.Trial)
        public void doSetup() throws IOException {
            doubleList = new DoubleList(Helper.getDoubleListFromCSV(valueCount));
            smsg_doubleList = SerializerJava.serialize(doubleList);
        }
    }

    @State(Scope.Thread)
    public static class StringListObjects {

        @Param({"10", "100", "500", "1000", "10000", "100000"})
        public int valueCount;

        public StringList stringList;
        public byte[] smsg_stringList;

        @Setup(Level.Trial)
        public void doSetup() throws IOException {
            stringList = new StringList(Helper.getStringListFromCSV(valueCount));
            smsg_stringList = SerializerJava.serialize(stringList);
        }
    }


// ------ StringList ------

    @Benchmark
    public byte[] java_StringList_ser(StringListObjects stringList) {
        return SerializerJava.serialize(stringList.stringList);
    }

    @Benchmark
    public Object java_StringList_deser(StringListObjects stringList) {
        return SerializerJava.deserialize(stringList.smsg_stringList);
    }


// ------ DoubleList ------

    @Benchmark
    public byte[] java_DoubleList_ser(DoubleListObjects doubleList) {
        return SerializerJava.serialize(doubleList.doubleList);
    }

    @Benchmark
    public Object java_DoubleList_deser(DoubleListObjects doubleList) {
        return SerializerJava.deserialize(doubleList.smsg_doubleList);
    }

// ------ IntList ------

    @Benchmark
    public byte[] java_IntList_ser(IntListObjects intList) {
        return SerializerJava.serialize(intList.intList);
    }

    @Benchmark
    public Object java_IntList_deser(IntListObjects intList) {
        return SerializerJava.deserialize(intList.smsg_intList);
    }

}
