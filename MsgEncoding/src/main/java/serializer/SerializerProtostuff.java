package serializer;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import messages.dcachey.PoolManagerPoolUpMessage;
import messages.realworld.Msg;
import messages.realworld.Msg_rw01;
import messages.realworld.Msg_rw02;
import messages.realworld.Msg_rw03;
import messages.realworld.Msg_rw04;
import messages.realworld.Msg_rw05;
import messages.typelists.DoubleList;
import messages.typelists.IntList;
import messages.typelists.StringList;

public class SerializerProtostuff {

    private SerializerProtostuff() { }

// ------------ PoolManagerPoolUpMessage ------------

    public static byte[] ser_PMPUM(PoolManagerPoolUpMessage msg) {
        Schema<PoolManagerPoolUpMessage> schema = RuntimeSchema.getSchema( PoolManagerPoolUpMessage.class );
        LinkedBuffer buffer = LinkedBuffer.allocate(512);

        // ser
        final byte[] serialized;
        try {
            serialized = ProtostuffIOUtil.toByteArray(msg, schema, buffer);
        }
        finally {
            buffer.clear();
        }
        return serialized;
    }

    public static PoolManagerPoolUpMessage deser_PMPUM(byte[] msg) {
        Schema<PoolManagerPoolUpMessage> schema = RuntimeSchema.getSchema( PoolManagerPoolUpMessage.class );
        PoolManagerPoolUpMessage deserialized = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(msg, deserialized, schema);
        return deserialized;
    }


// ------------ StringList ------------

// ------ Serialize:

    public static byte[] ser_StringList(StringList msg) {
        Schema<StringList> schema = RuntimeSchema.getSchema( StringList.class );
        LinkedBuffer buffer = LinkedBuffer.allocate(512);

        // ser
        final byte[] serialized;
        try {
            serialized = ProtostuffIOUtil.toByteArray(msg, schema, buffer);
        }
        finally {
            buffer.clear();
        }
        return serialized;
    }

// ------ Deserialize:

    public static Object deser_StringList(byte[] msg) {
        Schema<StringList> schema = RuntimeSchema.getSchema( StringList.class );
        StringList deserialized = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(msg, deserialized, schema);
        return deserialized;
    }

// ------------ IntList ------------

// ------ Serialize:

    public static byte[] ser_IntList(IntList msg) {
        Schema<IntList> schema = RuntimeSchema.getSchema( IntList.class );
        LinkedBuffer buffer = LinkedBuffer.allocate(512);

        // ser
        final byte[] serialized;
        try {
            serialized = ProtostuffIOUtil.toByteArray(msg, schema, buffer);
        }
        finally {
            buffer.clear();
        }
        return serialized;
    }

// ------ Deserialize:

    public static Object deser_IntList(byte[] msg) {
        Schema<IntList> schema = RuntimeSchema.getSchema( IntList.class );
        IntList deserialized = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(msg, deserialized, schema);
        return deserialized;
    }

// ------------ DoubleList ------------

// ------ Serialize:

    public static byte[] ser_DoubleList(DoubleList msg) {
        Schema<DoubleList> schema = RuntimeSchema.getSchema( DoubleList.class );
        LinkedBuffer buffer = LinkedBuffer.allocate(512);

        // ser
        final byte[] serialized;
        try {
            serialized = ProtostuffIOUtil.toByteArray(msg, schema, buffer);
        }
        finally {
            buffer.clear();
        }
        return serialized;
    }

// ------ Deserialize:

    public static Object deser_DoubleList(byte[] msg) {
        Schema<DoubleList> schema = RuntimeSchema.getSchema( DoubleList.class );
        DoubleList deserialized = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(msg, deserialized, schema);
        return deserialized;
    }

// ------------ MsgRw05 ------------

// ------ Serialize:

    public static byte[] ser_MsgRw05(Msg_rw05 msg) {
        Schema<Msg_rw05> schema = RuntimeSchema.getSchema( Msg_rw05.class );
        LinkedBuffer buffer = LinkedBuffer.allocate(512);

        // ser
        final byte[] serialized;
        try {
            serialized = ProtostuffIOUtil.toByteArray(msg, schema, buffer);
        }
        finally {
            buffer.clear();
        }
        return serialized;
    }


// ------ Deserialize:

    public static Object deser_MsgRw05(byte[] msg) {
        Schema<Msg_rw05> schema = RuntimeSchema.getSchema( Msg_rw05.class );
        Msg_rw05 deserialized = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(msg, deserialized, schema);
        return deserialized;
    }


// ------------ MsgRw04 ------------

// ------ Serialize:

    public static byte[] ser_MsgRw04(Msg_rw04 msg) {
        Schema<Msg_rw04> schema = RuntimeSchema.getSchema( Msg_rw04.class );
        LinkedBuffer buffer = LinkedBuffer.allocate(512);

        // ser
        final byte[] serialized;
        try {
            serialized = ProtostuffIOUtil.toByteArray(msg, schema, buffer);
        }
        finally {
            buffer.clear();
        }
        return serialized;
    }

// ------ Deserialize:

    public static Object deser_MsgRw04(byte[] msg) {
        Schema<Msg_rw04> schema = RuntimeSchema.getSchema( Msg_rw04.class );
        Msg_rw04 deserialized = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(msg, deserialized, schema);
        return deserialized;
    }

// ------------ MsgRw03 ------------

// ------ Serialize:

    public static byte[] ser_MsgRw03(Msg_rw03 msg) {
        Schema<Msg_rw03> schema = RuntimeSchema.getSchema( Msg_rw03.class );
        LinkedBuffer buffer = LinkedBuffer.allocate(512);

        // ser
        final byte[] serialized;
        try {
            serialized = ProtostuffIOUtil.toByteArray(msg, schema, buffer);
        }
        finally {
            buffer.clear();
        }
        return serialized;
    }

// ------ Deserialize:

    public static Object deser_MsgRw03(byte[] msg) {
        Schema<Msg_rw03> schema = RuntimeSchema.getSchema( Msg_rw03.class );
        Msg_rw03 deserialized = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(msg, deserialized, schema);
        return deserialized;
    }


// ------------ MsgRw02 ------------

// ------ Serialize:

    public static byte[] ser_MsgRw02(Msg_rw02 msg) {
        Schema<Msg_rw02> schema = RuntimeSchema.getSchema( Msg_rw02.class );
        LinkedBuffer buffer = LinkedBuffer.allocate(512);

        // ser
        final byte[] serialized;
        try {
            serialized = ProtostuffIOUtil.toByteArray(msg, schema, buffer);
        }
        finally {
            buffer.clear();
        }
        return serialized;
    }

// ------ Deserialize:

    public static Object deser_MsgRw02(byte[] msg) {
        Schema<Msg_rw02> schema = RuntimeSchema.getSchema( Msg_rw02.class );
        Msg_rw02 deserialized = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(msg, deserialized, schema);
        return deserialized;
    }


// ------------ MsgRw01 ------------

// ------ Serialize:

    public static byte[] ser_MsgRw01(Msg_rw01 msg) {
        Schema<Msg_rw01> schema = RuntimeSchema.getSchema( Msg_rw01.class );
        LinkedBuffer buffer = LinkedBuffer.allocate(512);

        // ser
        final byte[] serialized;
        try {
            serialized = ProtostuffIOUtil.toByteArray(msg, schema, buffer);
        }
        finally {
            buffer.clear();
        }
        return serialized;
    }

// ------ Deserialize:

    public static Object deser_MsgRw01(byte[] msg) {
        Schema<Msg_rw01> schema = RuntimeSchema.getSchema( Msg_rw01.class );
        Msg_rw01 deserialized = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(msg, deserialized, schema);
        return deserialized;
    }


// ------------ Msg ------------

// ------ Serialize:

    public static byte[] ser_Msg(Msg msg) {
        Schema<Msg> schema = RuntimeSchema.getSchema( Msg.class );
        LinkedBuffer buffer = LinkedBuffer.allocate(512);

        // ser
        final byte[] serialized;
        try {
            serialized = ProtostuffIOUtil.toByteArray(msg, schema, buffer);
        }
        finally {
            buffer.clear();
        }
        return serialized;
    }

// ------ Deserialize:

    public static Object deser_Msg(byte[] msg) {
        Schema<Msg> schema = RuntimeSchema.getSchema( Msg.class );
        Msg deserialized = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(msg, deserialized, schema);
        return deserialized;
    }

}
