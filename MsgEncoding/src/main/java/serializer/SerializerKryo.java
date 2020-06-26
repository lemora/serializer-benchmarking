package serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.ByteArrayOutputStream;

public class SerializerKryo {

    private static Kryo kryo = new Kryo();

    private SerializerKryo() {}

    public static void registerClass(Class c) {
        kryo.register(c);
    }

// ------------ Serialization methods ------------

    public static byte[] serialize(Object obj) {
        int initialBufferSize = 256;
        ByteArrayOutputStream serialized = new ByteArrayOutputStream(initialBufferSize);

        Output output = new Output(serialized);
        kryo.writeObject(output, obj);
        output.close();

        return serialized.toByteArray();
    }

    public static Object deserialize(byte[] serialized, Class c) {
        Object result = null;

        Input input = new Input(serialized);
        result = kryo.readObject(input, c);
        input.close();

        return result;
    }

}