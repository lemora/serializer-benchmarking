package serializer;

import org.nustaq.serialization.FSTConfiguration;

public class SerializerFst {

    private SerializerFst() {}

    private static FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();

    public static void registerClass(Class c) {
        conf.registerClass(c);
    }

// ------------ Serialization methods ------------

    public static byte[] serialize(Object obj) {
        return conf.asByteArray(obj);
    }

    public static Object deserialize(byte[] serialized) {
        return conf.asObject(serialized);
    }

}