package benchmarking.runtime;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import messages.realworld.Msg;
import serializer.SerializerJava;

import java.util.concurrent.TimeUnit;


@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(value = 1)
@Warmup(iterations = 0, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 1, time = 5, timeUnit = TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class DummyBenchmarker {

    Msg msg;

    @Setup
    public void setUp() {
        msg = new Msg();
    }


// ------ Msg ------

    @Benchmark
    public void msg_int_ser(Blackhole blackhole) {
        byte[] serialized = SerializerJava.serialize(msg);
        blackhole.consume(serialized);
    }

}
