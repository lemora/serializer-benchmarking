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
import serializer.SerializerProtostuff;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(value = 2)
@Warmup(iterations = 4, time = 10, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 100, timeUnit = TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class ProtoStuffContainerBenchmarker {

    @State(Scope.Thread)
    public static class IntListObjects {

        @Param({"10", "100", "500", "1000", "10000", "100000"})
        public int valueCount;

        public IntList intList;
        public byte[] smsg_intList;

        @Setup(Level.Trial)
        public void doSetup() throws IOException {
            intList = new IntList(Helper.getIntListFromCSV(valueCount));
            smsg_intList = SerializerProtostuff.ser_IntList(intList);
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
            smsg_doubleList = SerializerProtostuff.ser_DoubleList(doubleList);
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
            smsg_stringList = SerializerProtostuff.ser_StringList(stringList);
        }
    }

// ------ StringList ------

    @Benchmark
    public byte[] proto_StringList_ser(StringListObjects stringList) {
        return SerializerProtostuff.ser_StringList(stringList.stringList);
    }

    @Benchmark
    public Object proto_StringList_deser(StringListObjects stringList) {
        return SerializerProtostuff.deser_StringList(stringList.smsg_stringList);
    }

// ------ DoubleList ------

    @Benchmark
    public byte[] proto_DoubleList_ser(DoubleListObjects doubleList) {
        return SerializerProtostuff.ser_DoubleList(doubleList.doubleList);
    }

    @Benchmark
    public Object proto_DoubleList_deser(DoubleListObjects doubleList) {
        return SerializerProtostuff.deser_DoubleList(doubleList.smsg_doubleList);
    }

// ------ IntList ------

    @Benchmark
    public byte[] proto_IntList_ser(IntListObjects intList) {
        return SerializerProtostuff.ser_IntList(intList.intList);
    }

    @Benchmark
    public Object proto_IntList_deser(IntListObjects intList) {
        return SerializerProtostuff.deser_IntList(intList.smsg_intList);
    }

}
