package serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InvalidClassException;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializerJava {

    private SerializerJava() {}

// ------------ Serialization methods ------------

    public static byte[] serialize(Object obj) {
        int initialBufferSize = 256;
        ByteArrayOutputStream serialized = new ByteArrayOutputStream(initialBufferSize);
        
        try (ObjectOutputStream out = new ObjectOutputStream(serialized)) {
            out.writeObject(obj);
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
        try (ObjectInputStream stream = new ObjectInputStream(new ByteArrayInputStream(serialized))) {
            return stream.readObject();
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to deser_Msg object: The class could not be found. Is there a software version mismatch in your installation?");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Failed to deser_Msg object: ");
            e.printStackTrace();
        }

        return new Object();
    }

}