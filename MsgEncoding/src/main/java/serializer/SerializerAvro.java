package serializer;

import frameworks.avro.generated.randobjects.Avro_DoubleList;
import frameworks.avro.generated.randobjects.Avro_IntList;
import frameworks.avro.generated.randobjects.Avro_Msg;
import frameworks.avro.generated.randobjects.Avro_Msg_rw01;
import frameworks.avro.generated.randobjects.Avro_Msg_rw02;
import frameworks.avro.generated.randobjects.Avro_Msg_rw03;
import frameworks.avro.generated.randobjects.Avro_Msg_rw04;
import frameworks.avro.generated.randobjects.Avro_Msg_rw05;
import frameworks.avro.generated.randobjects.Avro_StringList;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import messages.realworld.Msg;
import messages.realworld.Msg_rw01;
import messages.realworld.Msg_rw02;
import messages.realworld.Msg_rw03;
import messages.realworld.Msg_rw04;
import messages.realworld.Msg_rw05;
import messages.typelists.DoubleList;
import messages.typelists.IntList;
import messages.typelists.StringList;


public class SerializerAvro {

    public enum Type {
        JSON("json"), BINARY("");

        public String name;

        Type(String name){
            this.name = name;
        }
    }

    private SerializerAvro() { }

// ------------ StringList ------------

// ------ Serialize:

    public static byte[] ser_StringList(StringList msg, Type type) {
        Avro_StringList avroMsg = msgToAvro_StringList(msg);
        byte[] serialized;
        if(type.equals(Type.BINARY)) {
            serialized = serAvro_binary_StringList(avroMsg);
        }
        else {
            serialized = serAvro_json_StringList(avroMsg);
        }
        return serialized;
    }

    public static byte[] serAvro_json_StringList(Avro_StringList avroObj) {
        DatumWriter<Avro_StringList> writer = new SpecificDatumWriter<Avro_StringList>(Avro_StringList.class);
        byte[] data = new byte[0];
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Encoder jsonEncoder = null;
        try {
            jsonEncoder = EncoderFactory.get().jsonEncoder(
                    Avro_StringList.getClassSchema(), stream);
            writer.write(avroObj, jsonEncoder);
            jsonEncoder.flush();
            data = stream.toByteArray();
        } catch (IOException e) {
            System.out.println("Serialization error:" + e.getMessage());
        }
        return data;
    }

    public static byte[] serAvro_binary_StringList(Avro_StringList avroObj) {
        DatumWriter<Avro_StringList> writer = new SpecificDatumWriter<Avro_StringList>(Avro_StringList.class);
        byte[] data = new byte[0];
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Encoder binaryEncoder = null;
        try {
            binaryEncoder = EncoderFactory.get().binaryEncoder(stream, null);
            writer.write(avroObj, binaryEncoder);
            binaryEncoder.flush();
            data = stream.toByteArray();
        } catch (IOException e) {
            System.out.println("Serialization error:" + e.getMessage());
        }
        return data;
    }

// ------ Deserialize:

    public static Object deser_StringList(byte[] msg, Type type) throws IOException {
        Avro_StringList avroMsg;
        if(type.equals(Type.BINARY)) {
            avroMsg = deserToAvro_binary_StringList(msg);
        }
        else {
            avroMsg = deserToAvro_json_StringList(msg);
        }
        Object deserialized = avroToMsg_StringList(avroMsg);
        return deserialized;
    }

    public static Avro_StringList deserToAvro_json_StringList(byte[] encodedMsg) throws IOException {
        Avro_StringList decodedAvro = null;

        DatumReader<Avro_StringList> reader = new SpecificDatumReader<Avro_StringList>(Avro_StringList.class);
        Decoder decoder = null;

        decoder = DecoderFactory.get().jsonDecoder(
                Avro_StringList.getClassSchema(), new String(encodedMsg));
        return reader.read(null, decoder);
    }

    public static Avro_StringList deserToAvro_binary_StringList(byte[] encodedMsg) throws IOException {
        Avro_Msg_rw05 decodedAvro = null;

        DatumReader<Avro_StringList> reader = new SpecificDatumReader<Avro_StringList>(Avro_StringList.class);
        Decoder decoder = null;

        decoder = DecoderFactory.get().binaryDecoder(encodedMsg, null);
        return reader.read(null, decoder);
    }

// ------------ DoubleList ------------

// ------ Serialize:

    public static byte[] ser_DoubleList(DoubleList msg, Type type) {
        Avro_DoubleList avroMsg = msgToAvro_DoubleList(msg);
        byte[] serialized;
        if(type.equals(Type.BINARY)) {
            serialized = serAvro_binary_DoubleList(avroMsg);
        }
        else {
            serialized = serAvro_json_DoubleList(avroMsg);
        }
        return serialized;
    }

    public static byte[] serAvro_json_DoubleList(Avro_DoubleList avroObj) {
        DatumWriter<Avro_DoubleList> writer = new SpecificDatumWriter<Avro_DoubleList>(Avro_DoubleList.class);
        byte[] data = new byte[0];
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Encoder jsonEncoder = null;
        try {
            jsonEncoder = EncoderFactory.get().jsonEncoder(
                    Avro_DoubleList.getClassSchema(), stream);
            writer.write(avroObj, jsonEncoder);
            jsonEncoder.flush();
            data = stream.toByteArray();
        } catch (IOException e) {
            System.out.println("Serialization error:" + e.getMessage());
        }
        return data;
    }

    public static byte[] serAvro_binary_DoubleList(Avro_DoubleList avroObj) {
        DatumWriter<Avro_DoubleList> writer = new SpecificDatumWriter<Avro_DoubleList>(Avro_DoubleList.class);
        byte[] data = new byte[0];
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Encoder binaryEncoder = null;
        try {
            binaryEncoder = EncoderFactory.get().binaryEncoder(stream, null);
            writer.write(avroObj, binaryEncoder);
            binaryEncoder.flush();
            data = stream.toByteArray();
        } catch (IOException e) {
            System.out.println("Serialization error:" + e.getMessage());
        }
        return data;
    }

// ------ Deserialize:

    public static Object deser_DoubleList(byte[] msg, Type type) throws IOException {
        Avro_DoubleList avroMsg;
        if(type.equals(Type.BINARY)) {
            avroMsg = deserToAvro_binary_DoubleList(msg);
        }
        else {
            avroMsg = deserToAvro_json_DoubleList(msg);
        }
        Object deserialized = avroToMsg_DoubleList(avroMsg);
        return deserialized;
    }

    public static Avro_DoubleList deserToAvro_json_DoubleList(byte[] encodedMsg) throws IOException {
        Avro_DoubleList decodedAvro = null;

        DatumReader<Avro_DoubleList> reader = new SpecificDatumReader<Avro_DoubleList>(Avro_DoubleList.class);
        Decoder decoder = null;

        decoder = DecoderFactory.get().jsonDecoder(
                Avro_DoubleList.getClassSchema(), new String(encodedMsg));
        return reader.read(null, decoder);
    }

    public static Avro_DoubleList deserToAvro_binary_DoubleList(byte[] encodedMsg) throws IOException {
        Avro_Msg_rw05 decodedAvro = null;

        DatumReader<Avro_DoubleList> reader = new SpecificDatumReader<Avro_DoubleList>(Avro_DoubleList.class);
        Decoder decoder = null;

        decoder = DecoderFactory.get().binaryDecoder(encodedMsg, null);
        return reader.read(null, decoder);
    }

// ------------ IntList ------------

// ------ Serialize:

    public static byte[] ser_IntList(IntList msg, Type type) {
        Avro_IntList avroMsg = msgToAvro_IntList(msg);
        byte[] serialized;
        if(type.equals(Type.BINARY)) {
            serialized = serAvro_binary_IntList(avroMsg);
        }
        else {
            serialized = serAvro_json_IntList(avroMsg);
        }
        return serialized;
    }

    public static byte[] serAvro_json_IntList(Avro_IntList avroObj) {
        DatumWriter<Avro_IntList> writer = new SpecificDatumWriter<Avro_IntList>(Avro_IntList.class);
        byte[] data = new byte[0];
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Encoder jsonEncoder = null;
        try {
            jsonEncoder = EncoderFactory.get().jsonEncoder(
                    Avro_IntList.getClassSchema(), stream);
            writer.write(avroObj, jsonEncoder);
            jsonEncoder.flush();
            data = stream.toByteArray();
        } catch (IOException e) {
            System.out.println("Serialization error:" + e.getMessage());
        }
        return data;
    }

    public static byte[] serAvro_binary_IntList(Avro_IntList avroObj) {
        DatumWriter<Avro_IntList> writer = new SpecificDatumWriter<Avro_IntList>(Avro_IntList.class);
        byte[] data = new byte[0];
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Encoder binaryEncoder = null;
        try {
            binaryEncoder = EncoderFactory.get().binaryEncoder(stream, null);
            writer.write(avroObj, binaryEncoder);
            binaryEncoder.flush();
            data = stream.toByteArray();
        } catch (IOException e) {
            System.out.println("Serialization error:" + e.getMessage());
        }
        return data;
    }

// ------ Deserialize:

    public static Object deser_IntList(byte[] msg, Type type) throws IOException {
        Avro_IntList avroMsg;
        if(type.equals(Type.BINARY)) {
            avroMsg = deserToAvro_binary_IntList(msg);
        }
        else {
            avroMsg = deserToAvro_json_IntList(msg);
        }
        Object deserialized = avroToMsg_IntList(avroMsg);
        return deserialized;
    }

    public static Avro_IntList deserToAvro_json_IntList(byte[] encodedMsg) throws IOException {
        Avro_IntList decodedAvro = null;

        DatumReader<Avro_IntList> reader = new SpecificDatumReader<Avro_IntList>(Avro_IntList.class);
        Decoder decoder = null;

        decoder = DecoderFactory.get().jsonDecoder(
                Avro_IntList.getClassSchema(), new String(encodedMsg));
        return reader.read(null, decoder);
    }

    public static Avro_IntList deserToAvro_binary_IntList(byte[] encodedMsg) throws IOException {
        Avro_Msg_rw05 decodedAvro = null;

        DatumReader<Avro_IntList> reader = new SpecificDatumReader<Avro_IntList>(Avro_IntList.class);
        Decoder decoder = null;

        decoder = DecoderFactory.get().binaryDecoder(encodedMsg, null);
        return reader.read(null, decoder);
    }

// ------------ Msg_rw05 ------------

// ------ Serialize:

    public static byte[] ser_MsgRw05(Msg_rw05 msg, Type type) {
        Avro_Msg_rw05 avroMsg = msgToAvro_MsgRw05(msg);
        byte[] serialized;
        if(type.equals(Type.BINARY)) {
            serialized = serAvro_binary_MsgRw05(avroMsg);
        }
        else {
            serialized = serAvro_json_MsgRw05(avroMsg);
        }
        return serialized;
    }

    public static byte[] serAvro_json_MsgRw05(Avro_Msg_rw05 avroObj) {
        DatumWriter<Avro_Msg_rw05> writer = new SpecificDatumWriter<Avro_Msg_rw05>(Avro_Msg_rw05.class);
        byte[] data = new byte[0];
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Encoder jsonEncoder = null;
        try {
            jsonEncoder = EncoderFactory.get().jsonEncoder(
                    Avro_Msg_rw05.getClassSchema(), stream);
            writer.write(avroObj, jsonEncoder);
            jsonEncoder.flush();
            data = stream.toByteArray();
        } catch (IOException e) {
            System.out.println("Serialization error:" + e.getMessage());
        }
        return data;
    }

    public static byte[] serAvro_binary_MsgRw05(Avro_Msg_rw05 avroObj) {
        DatumWriter<Avro_Msg_rw05> writer = new SpecificDatumWriter<Avro_Msg_rw05>(Avro_Msg_rw05.class);
        byte[] data = new byte[0];
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Encoder binaryEncoder = null;
        try {
            binaryEncoder = EncoderFactory.get().binaryEncoder(stream, null);
            writer.write(avroObj, binaryEncoder);
            binaryEncoder.flush();
            data = stream.toByteArray();
        } catch (IOException e) {
            System.out.println("Serialization error:" + e.getMessage());
        }
        return data;
    }

// ------ Deserialize:

    public static Object deser_MsgRw05(byte[] msg, Type type) throws IOException {
        Avro_Msg_rw05 avroMsg;
        if(type.equals(Type.BINARY)) {
            avroMsg = deserToAvro_binary_MsgRw05(msg);
        }
        else {
            avroMsg = deserToAvro_json_MsgRw05(msg);
        }
        Object deserialized = avroToMsg_MsgRw05(avroMsg);
        return deserialized;
    }

    public static Avro_Msg_rw05 deserToAvro_json_MsgRw05(byte[] encodedMsg) throws IOException {
        Avro_Msg_rw05 decodedAvro = null;

        DatumReader<Avro_Msg_rw05> reader = new SpecificDatumReader<Avro_Msg_rw05>(Avro_Msg_rw05.class);
        Decoder decoder = null;

        decoder = DecoderFactory.get().jsonDecoder(
                Avro_Msg_rw05.getClassSchema(), new String(encodedMsg));
        return reader.read(null, decoder);
    }

    public static Avro_Msg_rw05 deserToAvro_binary_MsgRw05(byte[] encodedMsg) throws IOException {
        Avro_Msg_rw05 decodedAvro = null;

        DatumReader<Avro_Msg_rw05> reader = new SpecificDatumReader<Avro_Msg_rw05>(Avro_Msg_rw05.class);
        Decoder decoder = null;

        decoder = DecoderFactory.get().binaryDecoder(encodedMsg, null);
        return reader.read(null, decoder);
    }

// ------------ Msg_rw04 ------------

// ------ Serialize:

    public static byte[] ser_MsgRw04(Msg_rw04 msg, Type type) {
        Avro_Msg_rw04 avroMsg = msgToAvro_MsgRw04(msg);
        byte[] serialized;
        if (type.equals(Type.BINARY)) {
            serialized = serAvro_binary_MsgRw04(avroMsg);
        }
        else {
            serialized = serAvro_json_MsgRw04(avroMsg);
        }
        return serialized;
    }

    public static byte[] serAvro_json_MsgRw04(Avro_Msg_rw04 avroObj) {
        DatumWriter<Avro_Msg_rw04> writer = new SpecificDatumWriter<Avro_Msg_rw04>(Avro_Msg_rw04.class);
        byte[] data = new byte[0];
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Encoder jsonEncoder = null;
        try {
            jsonEncoder = EncoderFactory.get().jsonEncoder(
                    Avro_Msg_rw04.getClassSchema(), stream);
            writer.write(avroObj, jsonEncoder);
            jsonEncoder.flush();
            data = stream.toByteArray();
        } catch (IOException e) {
            System.out.println("Serialization error:" + e.getMessage());
        }
        return data;
    }

    public static byte[] serAvro_binary_MsgRw04(Avro_Msg_rw04 avroObj) {
        DatumWriter<Avro_Msg_rw04> writer = new SpecificDatumWriter<Avro_Msg_rw04>(Avro_Msg_rw04.class);
        byte[] data = new byte[0];
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Encoder binaryEncoder = null;
        try {
            binaryEncoder = EncoderFactory.get().binaryEncoder(stream, null);
            writer.write(avroObj, binaryEncoder);
            binaryEncoder.flush();
            data = stream.toByteArray();
        } catch (IOException e) {
            System.out.println("Serialization error:" + e.getMessage());
        }
        return data;
    }

// ------ Deserialize:

    public static Object deser_MsgRw04(byte[] msg, Type type) throws IOException {
        Avro_Msg_rw04 avroMsg;
        if (type.equals(Type.BINARY)) {
            avroMsg = deserToAvro_binary_MsgRw04(msg);
        }
        else {
            avroMsg = deserToAvro_json_MsgRw04(msg);
        }
        Object deserialized = avroToMsg_MsgRw04(avroMsg);
        return deserialized;
    }

    public static Avro_Msg_rw04 deserToAvro_json_MsgRw04(byte[] encodedMsg) throws IOException {
        Avro_Msg_rw04 decodedAvro = null;

        DatumReader<Avro_Msg_rw04> reader = new SpecificDatumReader<Avro_Msg_rw04>(Avro_Msg_rw04.class);
        Decoder decoder = null;

        decoder = DecoderFactory.get().jsonDecoder(
                Avro_Msg_rw04.getClassSchema(), new String(encodedMsg));
        return reader.read(null, decoder);
    }

    public static Avro_Msg_rw04 deserToAvro_binary_MsgRw04(byte[] encodedMsg) throws IOException {
        Avro_Msg_rw04 decodedAvro = null;

        DatumReader<Avro_Msg_rw04> reader = new SpecificDatumReader<Avro_Msg_rw04>(Avro_Msg_rw04.class);
        Decoder decoder = null;

        decoder = DecoderFactory.get().binaryDecoder(encodedMsg, null);
        return reader.read(null, decoder);
    }

// ------------ Msg_rw03 ------------

// ------ Serialize:

    public static byte[] ser_MsgRw03(Msg_rw03 msg, Type type) {
        Avro_Msg_rw03 avroMsg = msgToAvro_MsgRw03(msg);
        byte[] serialized;
        if (type.equals(Type.BINARY)) {
            serialized = serAvro_binary_MsgRw03(avroMsg);
        }
        else {
            serialized = serAvro_json_MsgRw03(avroMsg);
        }
        return serialized;
    }

    public static byte[] serAvro_json_MsgRw03(Avro_Msg_rw03 avroObj) {
        DatumWriter<Avro_Msg_rw03> writer = new SpecificDatumWriter<Avro_Msg_rw03>(Avro_Msg_rw03.class);
        byte[] data = new byte[0];
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Encoder jsonEncoder = null;
        try {
            jsonEncoder = EncoderFactory.get().jsonEncoder(
                    Avro_Msg_rw03.getClassSchema(), stream);
            writer.write(avroObj, jsonEncoder);
            jsonEncoder.flush();
            data = stream.toByteArray();
        } catch (IOException e) {
            System.out.println("Serialization error:" + e.getMessage());
        }
        return data;
    }

    public static byte[] serAvro_binary_MsgRw03(Avro_Msg_rw03 avroObj) {
        DatumWriter<Avro_Msg_rw03> writer = new SpecificDatumWriter<Avro_Msg_rw03>(Avro_Msg_rw03.class);
        byte[] data = new byte[0];
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Encoder encoder = null;
        try {
            encoder = EncoderFactory.get().binaryEncoder(stream, null);
            writer.write(avroObj, encoder);
            encoder.flush();
            data = stream.toByteArray();
        } catch (IOException e) {
            System.out.println("Serialization error:" + e.getMessage());
        }
        return data;
    }

// ------ Deserialize:

    public static Object deser_MsgRw03(byte[] msg, Type type) throws IOException {
        Avro_Msg_rw03 avroMsg;
        if (type.equals(Type.BINARY)) {
            avroMsg = deserToAvro_binary_MsgRw03(msg);
        }
        else {
            avroMsg = deserToAvro_json_MsgRw03(msg);
        }
        Object deserialized = avroToMsg_MsgRw03(avroMsg);
        return deserialized;
    }

    public static Avro_Msg_rw03 deserToAvro_json_MsgRw03(byte[] encodedMsg) throws IOException {
        Avro_Msg_rw03 decodedAvro = null;

        DatumReader<Avro_Msg_rw03> reader = new SpecificDatumReader<Avro_Msg_rw03>(Avro_Msg_rw03.class);
        Decoder decoder = null;

        decoder = DecoderFactory.get().jsonDecoder(
                Avro_Msg_rw03.getClassSchema(), new String(encodedMsg));
        return reader.read(null, decoder);
    }

    public static Avro_Msg_rw03 deserToAvro_binary_MsgRw03(byte[] encodedMsg) throws IOException {
        Avro_Msg_rw03 decodedAvro = null;

        DatumReader<Avro_Msg_rw03> reader = new SpecificDatumReader<Avro_Msg_rw03>(Avro_Msg_rw03.class);
        Decoder decoder = null;

        decoder = DecoderFactory.get().binaryDecoder(encodedMsg, null);
        return reader.read(null, decoder);
    }


// ------------ Msg_rw02 ------------

// ------ Serialize:

    public static byte[] ser_MsgRw02(Msg_rw02 msg, Type type) {
        Avro_Msg_rw02 avroMsg = msgToAvro_MsgRw02(msg);
        byte[] serialized;
        if (type.equals(Type.BINARY)) {
            serialized = serAvro_binary_MsgRw02(avroMsg);
        }
        else {
            serialized = serAvro_json_MsgRw02(avroMsg);
        }
        return serialized;
    }

    public static byte[] serAvro_json_MsgRw02(Avro_Msg_rw02 avroObj) {
        DatumWriter<Avro_Msg_rw02> writer = new SpecificDatumWriter<Avro_Msg_rw02>(Avro_Msg_rw02.class);
        byte[] data = new byte[0];
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Encoder jsonEncoder = null;
        try {
            jsonEncoder = EncoderFactory.get().jsonEncoder(
                    Avro_Msg_rw02.getClassSchema(), stream);
            writer.write(avroObj, jsonEncoder);
            jsonEncoder.flush();
            data = stream.toByteArray();
        } catch (IOException e) {
            System.out.println("Serialization error:" + e.getMessage());
        }
        return data;
    }

    public static byte[] serAvro_binary_MsgRw02(Avro_Msg_rw02 avroObj) {
        DatumWriter<Avro_Msg_rw02> writer = new SpecificDatumWriter<Avro_Msg_rw02>(Avro_Msg_rw02.class);
        byte[] data = new byte[0];
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Encoder encoder = null;
        try {
            encoder = EncoderFactory.get().binaryEncoder(stream, null);
            writer.write(avroObj, encoder);
            encoder.flush();
            data = stream.toByteArray();
        } catch (IOException e) {
            System.out.println("Serialization error:" + e.getMessage());
        }
        return data;
    }

// ------ Deserialize:

    public static Object deser_MsgRw02(byte[] msg, Type type) throws IOException {
        Avro_Msg_rw02 avroMsg;
        if (type.equals(Type.BINARY)) {
            avroMsg = deserToAvro_binary_MsgRw02(msg);
        }
        else {
            avroMsg = deserToAvro_json_MsgRw02(msg);
        }
        Object deserialized = avroToMsg_MsgRw02(avroMsg);
        return deserialized;
    }

    public static Avro_Msg_rw02 deserToAvro_json_MsgRw02(byte[] encodedMsg) throws IOException {
        Avro_Msg_rw02 decodedAvro = null;

        DatumReader<Avro_Msg_rw02> reader = new SpecificDatumReader<Avro_Msg_rw02>(Avro_Msg_rw02.class);
        Decoder decoder = null;

        decoder = DecoderFactory.get().jsonDecoder(
                Avro_Msg_rw02.getClassSchema(), new String(encodedMsg));
        return reader.read(null, decoder);
    }

    public static Avro_Msg_rw02 deserToAvro_binary_MsgRw02(byte[] encodedMsg) throws IOException {
        Avro_Msg_rw02 decodedAvro = null;

        DatumReader<Avro_Msg_rw02> reader = new SpecificDatumReader<Avro_Msg_rw02>(Avro_Msg_rw02.class);
        Decoder decoder = null;

        decoder = DecoderFactory.get().binaryDecoder(encodedMsg, null);
        return reader.read(null, decoder);
    }


// ------------ Msg_rw01 ------------

// ------ Serialize:

    public static byte[] ser_MsgRw01(Msg_rw01 msg, Type type) {
        Avro_Msg_rw01 avroMsg = msgToAvro_MsgRw01(msg);
        byte[] serialized;
        if (type.equals(Type.BINARY)) {
            serialized = serAvro_binary_MsgRw01(avroMsg);
        }
        else {
            serialized = serAvro_json_MsgRw01(avroMsg);
        }
        return serialized;
    }

    public static byte[] serAvro_json_MsgRw01(Avro_Msg_rw01 avroObj) {
        DatumWriter<Avro_Msg_rw01> writer = new SpecificDatumWriter<Avro_Msg_rw01>(Avro_Msg_rw01.class);
        byte[] data = new byte[0];
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Encoder jsonEncoder = null;
        try {
            jsonEncoder = EncoderFactory.get().jsonEncoder(
                    Avro_Msg_rw01.getClassSchema(), stream);
            writer.write(avroObj, jsonEncoder);
            jsonEncoder.flush();
            data = stream.toByteArray();
        } catch (IOException e) {
            System.out.println("Serialization error:" + e.getMessage());
        }
        return data;
    }

    public static byte[] serAvro_binary_MsgRw01(Avro_Msg_rw01 avroObj) {
        DatumWriter<Avro_Msg_rw01> writer = new SpecificDatumWriter<Avro_Msg_rw01>(Avro_Msg_rw01.class);
        byte[] data = new byte[0];
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Encoder encoder = null;
        try {
            encoder = EncoderFactory.get().binaryEncoder(stream, null);
            writer.write(avroObj, encoder);
            encoder.flush();
            data = stream.toByteArray();
        } catch (IOException e) {
            System.out.println("Serialization error:" + e.getMessage());
        }
        return data;
    }

// ------ Deserialize:

    public static Object deser_MsgRw01(byte[] msg, Type type) throws IOException {
        Avro_Msg_rw01 avroMsg;
        if (type.equals(Type.BINARY)) {
            avroMsg = deserToAvro_binary_MsgRw01(msg);
        }
        else {
            avroMsg = deserToAvro_json_MsgRw01(msg);
        }
        Object deserialized = avroToMsg_MsgRw01(avroMsg);
        return deserialized;
    }

    public static Avro_Msg_rw01 deserToAvro_json_MsgRw01(byte[] encodedMsg) throws IOException {
        Avro_Msg_rw01 decodedAvro = null;

        DatumReader<Avro_Msg_rw01> reader = new SpecificDatumReader<Avro_Msg_rw01>(Avro_Msg_rw01.class);
        Decoder decoder = null;

        decoder = DecoderFactory.get().jsonDecoder(
                Avro_Msg_rw01.getClassSchema(), new String(encodedMsg));
        return reader.read(null, decoder);
    }

    public static Avro_Msg_rw01 deserToAvro_binary_MsgRw01(byte[] encodedMsg) throws IOException {
        Avro_Msg_rw01 decodedAvro = null;

        DatumReader<Avro_Msg_rw01> reader = new SpecificDatumReader<Avro_Msg_rw01>(Avro_Msg_rw01.class);
        Decoder decoder = null;

        decoder = DecoderFactory.get().binaryDecoder(encodedMsg, null);
        return reader.read(null, decoder);
    }

// ------------ Msg ------------

// ------ Serialize:

    public static byte[] ser_Msg(Msg msg, Type type) {
        Avro_Msg avroMsg = msgToAvro_Msg(msg);
        byte[] serialized;
        if (type.equals(Type.BINARY)) {
            serialized = serAvro_binary_Msg(avroMsg);
        }
        else {
            serialized = serAvro_json_Msg(avroMsg);
        }
        return serialized;
    }

    public static byte[] serAvro_json_Msg(Avro_Msg avroObj) {
        DatumWriter<Avro_Msg> writer = new SpecificDatumWriter<Avro_Msg>(Avro_Msg.class);
        byte[] data = new byte[0];
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Encoder jsonEncoder = null;
        try {
            jsonEncoder = EncoderFactory.get().jsonEncoder(
                    Avro_Msg.getClassSchema(), stream);
            writer.write(avroObj, jsonEncoder);
            jsonEncoder.flush();
            data = stream.toByteArray();
        } catch (IOException e) {
            System.out.println("Serialization error:" + e.getMessage());
        }
        return data;
    }

    public static byte[] serAvro_binary_Msg(Avro_Msg avroObj) {
        DatumWriter<Avro_Msg> writer = new SpecificDatumWriter<Avro_Msg>(Avro_Msg.class);
        byte[] data = new byte[0];
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Encoder encoder = null;
        try {
            encoder = EncoderFactory.get().binaryEncoder(stream, null);
            writer.write(avroObj, encoder);
            encoder.flush();
            data = stream.toByteArray();
        } catch (IOException e) {
            System.out.println("Serialization error:" + e.getMessage());
        }
        return data;
    }

// ------ Deserialize:

    public static Object deser_Msg(byte[] msg, Type type) throws IOException {
        Avro_Msg avroMsg;
        if(type.equals(Type.BINARY)) {
            avroMsg = deserToAvro_binary_Msg(msg);
        }
        else {
            avroMsg = deserToAvro_json_Msg(msg);
        }
        Object deserialized = avroToMsg_Msg(avroMsg);;
        return deserialized;
    }

    public static Avro_Msg deserToAvro_json_Msg(byte[] encodedMsg) throws IOException {
        Avro_Msg decodedAvro = null;

        DatumReader<Avro_Msg> reader = new SpecificDatumReader<Avro_Msg>(Avro_Msg.class);
        Decoder decoder = null;

        decoder = DecoderFactory.get().jsonDecoder(
                Avro_Msg.getClassSchema(), new String(encodedMsg));
        return reader.read(null, decoder);
    }

    public static Avro_Msg deserToAvro_binary_Msg(byte[] encodedMsg) throws IOException {
        Avro_Msg decodedAvro = null;

        DatumReader<Avro_Msg> reader = new SpecificDatumReader<Avro_Msg>(Avro_Msg.class);
        Decoder decoder = null;

        decoder = DecoderFactory.get().binaryDecoder(encodedMsg, null);
        return reader.read(null, decoder);
    }


// ----------------------------------------------------------------------
// ------------------- CONVERTERSS: Msg <-> Avro Object ----------------
// ----------------------------------------------------------------------


// ------------ StringList ------------

    public static Avro_StringList msgToAvro_StringList(StringList msg) {
        Avro_StringList avro_msg = new Avro_StringList();

        // Transscribing fields
        avro_msg.setValues(new LinkedList<>(msg.getValues()));

        return avro_msg;
    }

    public static StringList avroToMsg_StringList(Avro_StringList avroObj) {
        List<java.lang.String> slist = new LinkedList<>();
        avroObj.getValues().stream().map(CharSequence::toString).forEach(slist::add);
        StringList msg = new StringList(slist);

        return msg;
    }

// ------------ DoubleList ------------

    public static Avro_DoubleList msgToAvro_DoubleList(DoubleList msg) {
        Avro_DoubleList avro_msg = new Avro_DoubleList();

        // Transscribing fields
        avro_msg.setValues(msg.getValues());

        return avro_msg;
    }

    public static DoubleList avroToMsg_DoubleList(Avro_DoubleList avroObj) {
        DoubleList msg = new DoubleList(avroObj.getValues());

        return msg;
    }

// ------------ IntList ------------

    public static Avro_IntList msgToAvro_IntList(IntList msg) {
        Avro_IntList avro_msg = new Avro_IntList();

        // Transscribing fields
        avro_msg.setValues(msg.getValues());

        return avro_msg;
    }

    public static IntList avroToMsg_IntList(Avro_IntList avroObj) {
        IntList msg = new IntList(avroObj.getValues());

        return msg;
    }

// ------------ Msg_rw05 ------------

    public static Avro_Msg_rw05 msgToAvro_MsgRw05(Msg_rw05 msg) {
        Avro_Msg_rw05 avro_msg = new Avro_Msg_rw05();

        // Transscribing fields
        ByteBuffer buffer = ByteBuffer.wrap( new byte[] {(byte) msg.get_byte01()} );
        avro_msg.setByte01$1( buffer );

        avro_msg.setBoolean01$1( msg.get_boolean01() );

        avro_msg.setShort01$1( msg.get_short01() );
        avro_msg.setInt01$1( msg.get_int01() );
        avro_msg.setLong01$1( msg.get_long01() );

        avro_msg.setFloat01$1( msg.get_float01() );
        avro_msg.setDouble01$1( msg.get_double01() );

        avro_msg.setString01$1( msg.get_string01() );

        return avro_msg;
    }

    public static Msg_rw05 avroToMsg_MsgRw05(Avro_Msg_rw05 avroObj) {
        Msg_rw05 msg = new Msg_rw05();

        msg.set_byte01( avroObj.getByte01$1().get(0) );
        msg.set_boolean01( avroObj.getBoolean01$1() );

        msg.set_short01((short) avroObj.getShort01$1());
        msg.set_int01( avroObj.getInt01$1() );
        msg.set_long01( avroObj.getLong01$1() );

        msg.set_float01( avroObj.getFloat01$1() );
        msg.set_double01( avroObj.getDouble01$1() );

        msg.set_string01( avroObj.getString01$1().toString() );

        return msg;
    }

// ------------ Msg_rw04 ------------

    public static Avro_Msg_rw04 msgToAvro_MsgRw04(Msg_rw04 msg) {

        // Transscribing String-list fields
        List<CharSequence> str_list = new LinkedList<>( msg.get_stringList01() );

        // Transscribing int-String-map fields
        // TODO
        Map<Integer, String> intStr_map = msg.get_intStrMap01();
        Map<CharSequence, CharSequence> charseq_map = new HashMap<>();
        for (Integer key : intStr_map.keySet()) {
            charseq_map.put(key.toString(), intStr_map.get(key));
        }

        // Transscribing outer fields
        Avro_Msg_rw04 avro_msg = new Avro_Msg_rw04();
        avro_msg.setStringList01$1( str_list );
        avro_msg.setIntStrMap01$1( charseq_map );

        return avro_msg;
    }

    public static Msg_rw04 avroToMsg_MsgRw04(Avro_Msg_rw04 avroObj) {

        // Transscribing String-list fields
        List<String> str_list = new LinkedList<>();
        for (int i = 0; i < avroObj.getStringList01$1().size(); i++) {
            str_list.add(i, avroObj.getStringList01$1().get(i).toString() );
        }

        // Transscribing int-String-map fields
        Map<CharSequence, CharSequence> charseq_map = avroObj.getIntStrMap01$1();
        Map<Integer, String> intStr_map = new HashMap<>();
        for (CharSequence key : charseq_map.keySet()) {
            intStr_map.put(Integer.parseInt(key.toString()), charseq_map.get(key).toString());
        }

        // Transscribing outer fields
        Msg_rw04 msg = Msg_rw04.createTestInstance();
        msg.set_stringList01( str_list );
        msg.set_intStrMap01( intStr_map);

        return msg;
    }

// ------------ Msg_rw03 ------------

    public static Avro_Msg_rw03 msgToAvro_MsgRw03(Msg_rw03 msg) {

        // Transscribing inner Msg fields
        Msg m_msg = msg.get_msg();
        Avro_Msg a_msg = new Avro_Msg();
        // Nothing to transcribe: Msg has no fields!

        // Transscribing inner Msg_rw01 fields
        Msg_rw01 m_msg_rw01_00 = msg.get_msg_rw01_00();
        Avro_Msg_rw01 a_msg_rw01_00 = new Avro_Msg_rw01();

//        ByteBuffer buffer = ByteBuffer.wrap( new byte[] {(byte) m_msg_rw01_00.get_char01()} );
        a_msg_rw01_00.setChar01$1( m_msg_rw01_00.get_char01() );

//        buffer = ByteBuffer.wrap( new byte[] {(byte) m_msg_rw01_00.get_byte01()} );
        a_msg_rw01_00.setByte01$1( m_msg_rw01_00.get_byte01() );

        a_msg_rw01_00.setBoolean01$1( m_msg_rw01_00.is_boolean01() );

        a_msg_rw01_00.setShort01$1( m_msg_rw01_00.get_short01() );
        a_msg_rw01_00.setInt01$1( m_msg_rw01_00.get_int01() );
        a_msg_rw01_00.setLong01$1( m_msg_rw01_00.get_long01() );

        a_msg_rw01_00.setFloat01$1( m_msg_rw01_00.get_float01() );
        a_msg_rw01_00.setDouble01$1( m_msg_rw01_00.get_double01() );

        // Transscribing inner Msg_rw01 fields
        Msg_rw01 m_msg_rw01_01 = msg.get_msg_rw01_01();
        Avro_Msg_rw01 a_msg_rw01_01 = new Avro_Msg_rw01();

//        buffer = ByteBuffer.wrap( new byte[] {(byte) m_msg_rw01_01.get_char01()} );
        a_msg_rw01_01.setChar01$1( m_msg_rw01_01.get_char01() );

//        buffer = ByteBuffer.wrap( new byte[] {(byte) m_msg_rw01_01.get_byte01()} );
        a_msg_rw01_01.setByte01$1( m_msg_rw01_01.get_byte01() );

        a_msg_rw01_01.setBoolean01$1( m_msg_rw01_01.is_boolean01() );

        a_msg_rw01_01.setShort01$1( m_msg_rw01_01.get_short01() );
        a_msg_rw01_01.setInt01$1( m_msg_rw01_01.get_int01() );
        a_msg_rw01_01.setLong01$1( m_msg_rw01_01.get_long01() );

        a_msg_rw01_01.setFloat01$1( m_msg_rw01_01.get_float01() );
        a_msg_rw01_01.setDouble01$1( m_msg_rw01_01.get_double01() );

        // Transscribing outer Msg_rw03
        Avro_Msg_rw03 a_msg_rw03 = new Avro_Msg_rw03();
        a_msg_rw03.setMsg$1( a_msg );
        a_msg_rw03.setMsgRw0100$1( a_msg_rw01_00 );
        a_msg_rw03.setMsgRw0101$1( a_msg_rw01_01 );

        return a_msg_rw03;
    }

    public static Msg_rw03 avroToMsg_MsgRw03(Avro_Msg_rw03 avroObj) {

        // Transscribing inner Msg fields
        Avro_Msg a_msg = avroObj.getMsg$1();
        Msg m_msg = new Msg();
        // Nothing to transcribe: Msg has no fields!

        // Transscribing inner Msg_rw01 fields
        Avro_Msg_rw01 a_msg_rw01_00 = avroObj.getMsgRw0100$1();
        Msg_rw01 m_msg_rw01_00 = new Msg_rw01();

        m_msg_rw01_00.set_char01( (char) a_msg_rw01_00.getChar01$1() );
        m_msg_rw01_00.set_byte01( (byte) a_msg_rw01_00.getByte01$1() );
        m_msg_rw01_00.set_boolean01( a_msg_rw01_00.getBoolean01$1() );

        m_msg_rw01_00.set_short01((short) a_msg_rw01_00.getShort01$1());
        m_msg_rw01_00.set_int01( a_msg_rw01_00.getInt01$1() );
        m_msg_rw01_00.set_long01( a_msg_rw01_00.getLong01$1() );

        m_msg_rw01_00.set_float01( a_msg_rw01_00.getFloat01$1() );
        m_msg_rw01_00.set_double01( a_msg_rw01_00.getDouble01$1() );

        // Transscribing inner Msg_rw01 fields
        Avro_Msg_rw01 a_msg_rw01_01 = avroObj.getMsgRw0101$1();
        Msg_rw01 m_msg_rw01_01 = new Msg_rw01();

        m_msg_rw01_01.set_char01( (char) a_msg_rw01_01.getChar01$1() );
        m_msg_rw01_01.set_byte01( (byte) a_msg_rw01_01.getByte01$1() );
        m_msg_rw01_01.set_boolean01( a_msg_rw01_01.getBoolean01$1() );

        m_msg_rw01_01.set_short01((short) a_msg_rw01_01.getShort01$1());
        m_msg_rw01_01.set_int01( a_msg_rw01_01.getInt01$1() );
        m_msg_rw01_01.set_long01( a_msg_rw01_01.getLong01$1() );

        m_msg_rw01_01.set_float01( a_msg_rw01_01.getFloat01$1() );
        m_msg_rw01_01.set_double01( a_msg_rw01_01.getDouble01$1() );

        // Transscribing outer Msg_rw03
        Msg_rw03 m_msg_rw03 = new Msg_rw03();
        m_msg_rw03.set_msg( m_msg );
        m_msg_rw03.set_msg_rw01_00( m_msg_rw01_00 );
        m_msg_rw03.set_msg_rw01_01( m_msg_rw01_01 );

        return m_msg_rw03;
    }


// ------------ Msg_rw02 ------------

    public static Avro_Msg_rw02 msgToAvro_MsgRw02(Msg_rw02 msg) {

        // Transscribing inner Msg fields
        Msg m_msg = msg.get_msg();
        Avro_Msg a_msg = new Avro_Msg();
        // Nothing to transcribe: Msg has no fields!

        // Transscribing inner Msg_rw01 fields
        Msg_rw01 m_msg_rw01 = msg.get_msg_rw01();
        Avro_Msg_rw01 a_msg_rw01 = new Avro_Msg_rw01();

//        ByteBuffer buffer = ByteBuffer.wrap( new byte[] {(byte) m_msg_rw01.get_char01()} );
        a_msg_rw01.setChar01$1( m_msg_rw01.get_char01() );

//        buffer = ByteBuffer.wrap( new byte[] {(byte) m_msg_rw01.get_byte01()} );
        a_msg_rw01.setByte01$1( m_msg_rw01.get_byte01() );

        a_msg_rw01.setBoolean01$1( m_msg_rw01.is_boolean01() );

        a_msg_rw01.setShort01$1( m_msg_rw01.get_short01() );
        a_msg_rw01.setInt01$1( m_msg_rw01.get_int01() );
        a_msg_rw01.setLong01$1( m_msg_rw01.get_long01() );

        a_msg_rw01.setFloat01$1( m_msg_rw01.get_float01() );
        a_msg_rw01.setDouble01$1( m_msg_rw01.get_double01() );

        // Transscribing outer Msg_rw02
        Avro_Msg_rw02 a_msg_rw02 = new Avro_Msg_rw02();
        a_msg_rw02.setMsg$1( a_msg );
        a_msg_rw02.setMsgRw01$1( a_msg_rw01 );

        return a_msg_rw02;
    }

    public static Msg_rw02 avroToMsg_MsgRw02(Avro_Msg_rw02 avroObj) {

        // Transscribing inner Msg fields
        Avro_Msg a_msg = avroObj.getMsg$1();
        Msg m_msg = new Msg();
        // Nothing to transcribe: Msg has no fields!

        // Transscribing inner Msg_rw01 fields
        Avro_Msg_rw01 a_msg_rw01 = avroObj.getMsgRw01$1();
        Msg_rw01 m_msg_rw01 = new Msg_rw01();

        m_msg_rw01.set_char01( (char) a_msg_rw01.getChar01$1() );
        m_msg_rw01.set_byte01( (byte) a_msg_rw01.getByte01$1() );
        m_msg_rw01.set_boolean01( a_msg_rw01.getBoolean01$1() );

        m_msg_rw01.set_short01((short) a_msg_rw01.getShort01$1());
        m_msg_rw01.set_int01( a_msg_rw01.getInt01$1() );
        m_msg_rw01.set_long01( a_msg_rw01.getLong01$1() );

        m_msg_rw01.set_float01( a_msg_rw01.getFloat01$1() );
        m_msg_rw01.set_double01( a_msg_rw01.getDouble01$1() );

        // Transscribing outer Msg_rw02
        Msg_rw02 m_msg_rw02 = new Msg_rw02();
        m_msg_rw02.set_msg( m_msg );
        m_msg_rw02.set_msg_rw01( m_msg_rw01 );

        return m_msg_rw02;
    }


// ------------ Msg_rw01 ------------

    public static Avro_Msg_rw01 msgToAvro_MsgRw01(Msg_rw01 msg) {
        Avro_Msg_rw01 avro_msg = new Avro_Msg_rw01();

        // Transscribing fields
//        ByteBuffer buffer = ByteBuffer.wrap( new byte[] {(byte) msg.get_char01()} );
        avro_msg.setChar01$1( msg.get_char01() );

//        buffer = ByteBuffer.wrap( new byte[] {(byte) msg.get_byte01()} );
        avro_msg.setByte01$1( msg.get_byte01() );

        avro_msg.setBoolean01$1( msg.is_boolean01() );

        avro_msg.setShort01$1( msg.get_short01() );
        avro_msg.setInt01$1( msg.get_int01() );
        avro_msg.setLong01$1( msg.get_long01() );

        avro_msg.setFloat01$1( msg.get_float01() );
        avro_msg.setDouble01$1( msg.get_double01() );

//        avro_msg.put( "_double01", msg.get_double01() );

        return avro_msg;
    }

    public static Msg_rw01 avroToMsg_MsgRw01(Avro_Msg_rw01 avroObj) {
        Msg_rw01 msg = new Msg_rw01();

        msg.set_char01( (char) avroObj.getChar01$1());
        msg.set_byte01( (byte) avroObj.getByte01$1() );
        msg.set_boolean01( avroObj.getBoolean01$1() );

        msg.set_short01((short) avroObj.getShort01$1());
        msg.set_int01( avroObj.getInt01$1() );
        msg.set_long01( avroObj.getLong01$1() );

        msg.set_float01( avroObj.getFloat01$1() );
        msg.set_double01( avroObj.getDouble01$1() );

        return msg;
    }


// ------------ Msg ------------

    public static Avro_Msg msgToAvro_Msg(Msg msg) {
        Avro_Msg avro_msg = new Avro_Msg();
        return avro_msg;
    }

    public static Msg avroToMsg_Msg(Avro_Msg avroObj) {
        Msg msg = new Msg();
        // nothing to extract: Msg has no fields
        return msg;
    }

}
