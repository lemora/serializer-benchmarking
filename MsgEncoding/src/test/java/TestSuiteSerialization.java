import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        TestAvroSerialization.class,
        TestJavaSerialization.class,
        TestProtoSerialization.class,
        TestProtostuffSerialization.class,
        TestKryoSerialization.class,
        TestFstSerialization.class,
        TestHessianSerialization.class
})

public class TestSuiteSerialization {
}  	