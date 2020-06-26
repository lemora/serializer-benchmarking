package benchmarking.runtime;

import frameworks.protobuf.generated.protos.ProtosContainer;
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
import serializer.SerializerProtobuf;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(value = 2)
@Warmup(iterations = 4, time = 10, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 100, timeUnit = TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class ProtoContainerBenchmarker {

    @State(Scope.Thread)
    public static class IntListObjects {

        @Param({"10", "100", "500", "1000", "10000", "100000"})
        public int valueCount;

        public ProtosContainer.Proto_IntList proto_intList;
        public byte[] smsg_intList;

        @Setup(Level.Trial)
        public void doSetup() throws IOException {
            IntList list = new IntList(Helper.getIntListFromCSV(valueCount));
            proto_intList = SerializerProtobuf.msgToProto_IntList(list);
            smsg_intList = SerializerProtobuf.serProto_IntList(proto_intList);
        }
    }

    @State(Scope.Thread)
    public static class DoubleListObjects {

        @Param({"10", "100", "500", "1000", "10000", "100000"})
        public int valueCount;

        public ProtosContainer.Proto_DoubleList proto_doubleList;
        public byte[] smsg_doubleList;

        @Setup(Level.Trial)
        public void doSetup() throws IOException {
            DoubleList list = new DoubleList(Helper.getDoubleListFromCSV(valueCount));
            proto_doubleList = SerializerProtobuf.msgToProto_DoubleList(list);
            smsg_doubleList = SerializerProtobuf.serProto_DoubleList(proto_doubleList);
        }
    }

    @State(Scope.Thread)
    public static class StringListObjects {

        @Param({"10", "100", "500", "1000", "10000", "100000"})
        public int valueCount;

        public ProtosContainer.Proto_StringList proto_stringList;
        public byte[] smsg_stringList;

        @Setup(Level.Trial)
        public void doSetup() throws IOException {
            StringList list = new StringList(Helper.getStringListFromCSV(valueCount));
            proto_stringList = SerializerProtobuf.msgToProto_StringList(list);
            smsg_stringList = SerializerProtobuf.serProto_StringList(proto_stringList);
        }
    }

// ------ StringList ------

    @Benchmark
    public byte[] proto_StringList_ser(StringListObjects stringList) {
        return SerializerProtobuf.serProto_StringList(stringList.proto_stringList);
    }

    @Benchmark
    public Object proto_StringList_deser(StringListObjects stringList) {
        return SerializerProtobuf.deserToProto_StringList(stringList.smsg_stringList);
    }


// ------ DoubleList ------

    @Benchmark
    public byte[] proto_DoubleList_ser(DoubleListObjects doubleList) {
        return SerializerProtobuf.serProto_DoubleList(doubleList.proto_doubleList);
    }

    @Benchmark
    public Object proto_DoubleList_deser(DoubleListObjects doubleList) {
        return SerializerProtobuf.deserToProto_DoubleList(doubleList.smsg_doubleList);
    }

// ------ IntList ------

    @Benchmark
    public byte[] proto_IntList_ser(IntListObjects intList) {
        return SerializerProtobuf.serProto_IntList(intList.proto_intList);
    }

    @Benchmark
    public Object proto_IntList_deser(IntListObjects intList) {
        return SerializerProtobuf.deserToProto_IntList(intList.smsg_intList);
    }

}
