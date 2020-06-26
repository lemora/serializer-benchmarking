package serializer;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.NotSerializableException;

public class SerializerHessian {

    private SerializerHessian() {}

// ------------ Serialization methods ------------

    public static byte[] serialize(Object obj) {
        int initialBufferSize = 256;
        ByteArrayOutputStream serialized = new ByteArrayOutputStream(initialBufferSize);
        Hessian2Output out = new Hessian2Output(serialized);
        
        try {
            out.startMessage();
            out.writeObject(obj);
            out.completeMessage();
            out.close();
        } catch (InvalidClassException e) {
            System.out.println("Failed to serialize object: " + e + "(this is usually a bug)");
            e.printStackTrace();
        } catch (NotSerializableException e) {
            System.out.println("Failed to serialize object because the object is not serializable (this is usually a bug)");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Failed to serialize object: " + e);
            e.printStackTrace();
        }

        return serialized.toByteArray();
    }

    public static Object deserialize(byte[] serialized) {
        Object deserialized = null;
        ByteArrayInputStream bais = new ByteArrayInputStream(serialized);
        try {
            Hessian2Input in = new Hessian2Input(bais);
            in.startMessage();
            deserialized = in.readObject();
            in.completeMessage();
            in.close();
        } catch (IOException e) {
            System.out.println("Failed to deserialize object: ");
            e.printStackTrace();
        }

        return deserialized;
    }

}