package serializer;

import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.InvalidProtocolBufferException;

import frameworks.protobuf.generated.protos.ProtosContainer;
import frameworks.protobuf.generated.protos.ProtosTestobj;

import messages.realworld.Msg;
import messages.realworld.Msg_rw01;
import messages.realworld.Msg_rw02;
import messages.realworld.Msg_rw03;
import messages.realworld.Msg_rw04;
import messages.realworld.Msg_rw05;
import messages.typelists.DoubleList;
import messages.typelists.IntList;
import messages.typelists.StringList;

import java.util.List;
import java.util.Map;

public class SerializerProtobuf {

    private SerializerProtobuf() { }

// ------------ StringList ------------

// ------ Serialize:

    public static byte[] ser_StringList(StringList msg) {
        ProtosContainer.Proto_StringList protoMsg = msgToProto_StringList(msg);
        byte[] serialized = serProto_StringList(protoMsg);
        return serialized;
    }

    public static byte[] serProto_StringList(ProtosContainer.Proto_StringList protoObj) {
        return protoObj.toByteArray();
    }

// ------ Deserialize:

    public static Object deser_StringList(byte[] msg) {
        ProtosContainer.Proto_StringList protoMsg = deserToProto_StringList(msg);
        Object deserialized = protoToMsg_StringList(protoMsg);
        return deserialized;
    }

    public static ProtosContainer.Proto_StringList deserToProto_StringList(byte[] encodedMsg) {
        ProtosContainer.Proto_StringList decodedProto = null;

        try {
            decodedProto = ProtosContainer.Proto_StringList.parseFrom(encodedMsg);
        }
        catch (InvalidProtocolBufferException e) {
            throw new IllegalArgumentException("Serialized message cannot be deserialized using frameworks.protobuf!");
        }

        return decodedProto;
    }

// ------------ IntList ------------

// ------ Serialize:

    public static byte[] ser_IntList(IntList msg) {
        ProtosContainer.Proto_IntList protoMsg = msgToProto_IntList(msg);
        byte[] serialized = serProto_IntList(protoMsg);
        return serialized;
    }

    public static byte[] serProto_IntList(ProtosContainer.Proto_IntList protoObj) {
        return protoObj.toByteArray();
    }

// ------ Deserialize:

    public static Object deser_IntList(byte[] msg) {
        ProtosContainer.Proto_IntList protoMsg = deserToProto_IntList(msg);
        Object deserialized = protoToMsg_IntList(protoMsg);
        return deserialized;
    }

    public static ProtosContainer.Proto_IntList deserToProto_IntList(byte[] encodedMsg) {
        ProtosContainer.Proto_IntList decodedProto = null;

        try {
            decodedProto = ProtosContainer.Proto_IntList.parseFrom(encodedMsg);
        }
        catch (InvalidProtocolBufferException e) {
            throw new IllegalArgumentException("Serialized message cannot be deserialized using frameworks.protobuf!");
        }

        return decodedProto;
    }

// ------------ DoubleList ------------

// ------ Serialize:

    public static byte[] ser_DoubleList(DoubleList msg) {
        ProtosContainer.Proto_DoubleList protoMsg = msgToProto_DoubleList(msg);
        byte[] serialized = serProto_DoubleList(protoMsg);
        return serialized;
    }

    public static byte[] serProto_DoubleList(ProtosContainer.Proto_DoubleList protoObj) {
        return protoObj.toByteArray();
    }

// ------ Deserialize:

    public static Object deser_DoubleList(byte[] msg) {
        ProtosContainer.Proto_DoubleList protoMsg = deserToProto_DoubleList(msg);
        Object deserialized = protoToMsg_DoubleList(protoMsg);
        return deserialized;
    }

    public static ProtosContainer.Proto_DoubleList deserToProto_DoubleList(byte[] encodedMsg) {
        ProtosContainer.Proto_DoubleList decodedProto = null;

        try {
            decodedProto = ProtosContainer.Proto_DoubleList.parseFrom(encodedMsg);
        }
        catch (InvalidProtocolBufferException e) {
            throw new IllegalArgumentException("Serialized message cannot be deserialized using frameworks.protobuf!");
        }

        return decodedProto;
    }

// ------------ MsgRw05 ------------

// ------ Serialize:

    public static byte[] ser_MsgRw05(Msg_rw05 msg) {
        ProtosTestobj.Proto_Msg_rw05 protoMsg = msgToProto_MsgRw05(msg);
        byte[] serialized = serProto_MsgRw05(protoMsg);
        return serialized;
    }

    public static byte[] serProto_MsgRw05(ProtosTestobj.Proto_Msg_rw05 protoObj) {
        return protoObj.toByteArray();
    }

// ------ Deserialize:

    public static Object deser_MsgRw05(byte[] msg) {
        ProtosTestobj.Proto_Msg_rw05 protoMsg = deserToProto_MsgRw05(msg);
        Object deserialized = protoToMsg_MsgRw05(protoMsg);
        return deserialized;
    }

    public static ProtosTestobj.Proto_Msg_rw05 deserToProto_MsgRw05(byte[] encodedMsg) {
        ProtosTestobj.Proto_Msg_rw05 decodedProto = null;

        try {
            decodedProto = ProtosTestobj.Proto_Msg_rw05.parseFrom(encodedMsg);
        }
        catch (InvalidProtocolBufferException e) {
            throw new IllegalArgumentException("Serialized message cannot be deserialized using frameworks.protobuf!");
        }

        return decodedProto;
    }

// ------------ MsgRw04 ------------

// ------ Serialize:

    public static byte[] ser_MsgRw04(Msg_rw04 msg) {
        ProtosTestobj.Proto_Msg_rw04 protoMsg = msgToProto_MsgRw04(msg);
        byte[] serialized = serProto_MsgRw04(protoMsg);
        return serialized;
    }

    public static byte[] serProto_MsgRw04(ProtosTestobj.Proto_Msg_rw04 protoObj) {
        return protoObj.toByteArray();
    }

// ------ Deserialize:

    public static Object deser_MsgRw04(byte[] msg) {
        ProtosTestobj.Proto_Msg_rw04 protoMsg = deserToProto_MsgRw04(msg);
        Object deserialized = protoToMsg_MsgRw04(protoMsg);
        return deserialized;
    }

    public static ProtosTestobj.Proto_Msg_rw04 deserToProto_MsgRw04(byte[] encodedMsg) {
        ProtosTestobj.Proto_Msg_rw04 decodedProto = null;

        try {
            decodedProto = ProtosTestobj.Proto_Msg_rw04.parseFrom(encodedMsg);
        }
        catch (InvalidProtocolBufferException e) {
            throw new IllegalArgumentException("Serialized message cannot be deserialized using frameworks.protobuf!");
        }

        return decodedProto;
    }

// ------------ MsgRw03 ------------

// ------ Serialize:

    public static byte[] ser_MsgRw03(Msg_rw03 msg) {
        ProtosTestobj.Proto_Msg_rw03 protoMsg = msgToProto_MsgRw03(msg);
        byte[] serialized = serProto_MsgRw03(protoMsg);
        return serialized;
    }

    public static byte[] serProto_MsgRw03(ProtosTestobj.Proto_Msg_rw03 protoObj) {
        return protoObj.toByteArray();
    }

// ------ Deserialize:

    public static Object deser_MsgRw03(byte[] msg) {
        ProtosTestobj.Proto_Msg_rw03 protoMsg = deserToProto_MsgRw03(msg);
        Object deserialized = protoToMsg_MsgRw03(protoMsg);
        return deserialized;
    }

    public static ProtosTestobj.Proto_Msg_rw03 deserToProto_MsgRw03(byte[] encodedMsg) {
        ProtosTestobj.Proto_Msg_rw03 decodedProto = null;

        try {
            decodedProto = ProtosTestobj.Proto_Msg_rw03.parseFrom(encodedMsg);
        }
        catch (InvalidProtocolBufferException e) {
            throw new IllegalArgumentException("Serialized message cannot be deserialized using frameworks.protobuf!");
        }

        return decodedProto;
    }


// ------------ MsgRw02 ------------

// ------ Serialize:

    public static byte[] ser_MsgRw02(Msg_rw02 msg) {
        ProtosTestobj.Proto_Msg_rw02 protoMsg = msgToProto_MsgRw02(msg);
        byte[] serialized = serProto_MsgRw02(protoMsg);
        return serialized;
    }

    public static byte[] serProto_MsgRw02(ProtosTestobj.Proto_Msg_rw02 protoObj) {
        return protoObj.toByteArray();
    }

// ------ Deserialize:

    public static Object deser_MsgRw02(byte[] msg) {
        ProtosTestobj.Proto_Msg_rw02 protoMsg = deserToProto_MsgRw02(msg);
        Object deserialized = protoToMsg_MsgRw02(protoMsg);
        return deserialized;
    }

    public static ProtosTestobj.Proto_Msg_rw02 deserToProto_MsgRw02(byte[] encodedMsg) {
        ProtosTestobj.Proto_Msg_rw02 decodedProto = null;

        try {
            decodedProto = ProtosTestobj.Proto_Msg_rw02.parseFrom(encodedMsg);
        }
        catch (InvalidProtocolBufferException e) {
            throw new IllegalArgumentException("Serialized message cannot be deserialized using frameworks.protobuf!");
        }

        return decodedProto;
    }

// ------------ MsgRw01 ------------

// ------ Serialize:

    public static byte[] ser_MsgRw01(Msg_rw01 msg) {
        ProtosTestobj.Proto_Msg_rw01 protoMsg = msgToProto_MsgRw01(msg);
        byte[] serialized = serProto_MsgRw01(protoMsg);
        return serialized;
    }

    public static byte[] serProto_MsgRw01(ProtosTestobj.Proto_Msg_rw01 protoObj) {
        return protoObj.toByteArray();
    }

// ------ Deserialize:

    public static Object deser_MsgRw01(byte[] msg) {
        ProtosTestobj.Proto_Msg_rw01 protoMsg = deserToProto_MsgRw01(msg);
        Object deserialized = protoToMsg_MsgRw01(protoMsg);
        return deserialized;
    }

    public static ProtosTestobj.Proto_Msg_rw01 deserToProto_MsgRw01(byte[] encodedMsg) {
        ProtosTestobj.Proto_Msg_rw01 decodedProto = null;

        try {
            decodedProto = ProtosTestobj.Proto_Msg_rw01.parseFrom(encodedMsg);
        }
        catch (InvalidProtocolBufferException e) {
            throw new IllegalArgumentException("Serialized message cannot be deserialized using frameworks.protobuf!");
        }

        return decodedProto;
    }


// ------------ Msg ------------

// ------ Serialize:

    public static byte[] ser_Msg(Msg msg) {
        ProtosTestobj.Proto_Msg protoMsg = msgToProto_Msg(msg);
        byte[] serialized = serProto_Msg(protoMsg);
        return serialized;
    }

    public static byte[] serProto_Msg(ProtosTestobj.Proto_Msg protoObj) {
        return protoObj.toByteArray();
    }

// ------ Deserialize:

    public static Object deser_Msg(byte[] msg) {
        ProtosTestobj.Proto_Msg protoMsg = deserToProto_Msg(msg);
        Object deserialized = protoToMsg_Msg(protoMsg);
        return deserialized;
    }

    public static ProtosTestobj.Proto_Msg deserToProto_Msg(byte[] encodedMsg) {
        ProtosTestobj.Proto_Msg decodedProto = null;

        try {
            decodedProto = ProtosTestobj.Proto_Msg.parseFrom(encodedMsg);
        }
        catch (InvalidProtocolBufferException e) {
            throw new IllegalArgumentException("Serialized message cannot be deserialized using frameworks.protobuf!");
        }

        return decodedProto;
    }


// ----------------------------------------------------------------------
// ------------------- CONVERTERS: Msg <-> Proto Object ----------------
// ----------------------------------------------------------------------

// ------------ StringList ------------

    public static ProtosContainer.Proto_StringList msgToProto_StringList(StringList msg) {
        ProtosContainer.Proto_StringList.Builder protoMsg = ProtosContainer.Proto_StringList.newBuilder();

        // Transscribing fields
        List<String> _tmpStrList = msg.getValues();
        protoMsg.addAllVals( _tmpStrList );

        return protoMsg.build();
    }


    public static StringList protoToMsg_StringList(ProtosContainer.Proto_StringList protoObj) {
        List<String> strings = protoObj.getValsList();
        StringList msg = new StringList(strings);
        return msg;
    }

// ------------ IntList ------------

    public static ProtosContainer.Proto_IntList msgToProto_IntList(IntList msg) {
        ProtosContainer.Proto_IntList.Builder protoMsg = ProtosContainer.Proto_IntList.newBuilder();

        // Transscribing fields
        List<Integer> _tmpStrList = msg.getValues();
        protoMsg.addAllVals( _tmpStrList );

        return protoMsg.build();
    }


    public static IntList protoToMsg_IntList(ProtosContainer.Proto_IntList protoObj) {
        List<Integer> ints = protoObj.getValsList();
        IntList msg = new IntList(ints);
        return msg;
    }

// ------------ DoubleList ------------

    public static ProtosContainer.Proto_DoubleList msgToProto_DoubleList(DoubleList msg) {
        ProtosContainer.Proto_DoubleList.Builder protoMsg = ProtosContainer.Proto_DoubleList.newBuilder();

        // Transscribing fields
        List<Double> _tmpStrList = msg.getValues();
        protoMsg.addAllVals( _tmpStrList );

        return protoMsg.build();
    }


    public static DoubleList protoToMsg_DoubleList(ProtosContainer.Proto_DoubleList protoObj) {
        List<Double> doubles = protoObj.getValsList();
        DoubleList msg = new DoubleList(doubles);
        return msg;
    }

// ------------ MsgRw05 ------------

    public static ProtosTestobj.Proto_Msg_rw05 msgToProto_MsgRw05(Msg_rw05 msg) {
        ProtosTestobj.Proto_Msg_rw05.Builder protoMsg = ProtosTestobj.Proto_Msg_rw05.newBuilder();

        // Transscribing fields
        protoMsg.setByte01( msg.get_byte01() );
        protoMsg.setBoolean01( msg.get_boolean01() );

        protoMsg.setShort01( msg.get_short01() );
        protoMsg.setInt01( msg.get_int01() );
        protoMsg.setLong01( msg.get_long01() );

        protoMsg.setFloat01( msg.get_float01() );
        protoMsg.setDouble01( msg.get_double01() );

        protoMsg.setString01( msg.get_string01() );

        return protoMsg.build();
    }


    public static Msg_rw05 protoToMsg_MsgRw05(ProtosTestobj.Proto_Msg_rw05 pm) {
        Msg_rw05 msg = new Msg_rw05();

        msg.set_byte01( (byte) pm.getByte01() );
        msg.set_boolean01( pm.getBoolean01() );

        msg.set_short01( (short) pm.getShort01() );
        msg.set_int01( pm.getInt01() );
        msg.set_long01( pm.getLong01() );

        msg.set_float01( pm.getFloat01() );
        msg.set_double01( pm.getDouble01() );

        msg.set_string01( pm.getString01() );

        return msg;
    }

// ------------ MsgRw04 ------------

    public static ProtosTestobj.Proto_Msg_rw04 msgToProto_MsgRw04(Msg_rw04 msg) {
        ProtosTestobj.Proto_Msg_rw04.Builder protoMsg = ProtosTestobj.Proto_Msg_rw04.newBuilder();

        // StringList
        List<String> _tmpStrList = msg.get_stringList01();
        protoMsg.addAllStringList01( _tmpStrList );

        // IntStrMap
        Map<Integer, String> _tmpIntStrMap = msg.get_intStrMap01();
        protoMsg.putAllIntStrMap01(_tmpIntStrMap);

        return protoMsg.build();
    }


    public static Msg_rw04 protoToMsg_MsgRw04(ProtosTestobj.Proto_Msg_rw04 pm) {
        Msg_rw04 msg = Msg_rw04.createTestInstance();

        // StringList
        ProtocolStringList _tmpStrList = pm.getStringList01List();
        msg.set_stringList01( (List<String>) _tmpStrList );

        // IntStrMap
        Map<Integer, String> _tmpIntStrMap = pm.getIntStrMap01Map();
        msg.set_intStrMap01(_tmpIntStrMap);

        return msg;
    }

// ------------ MsgRw03 ------------

    public static ProtosTestobj.Proto_Msg_rw03 msgToProto_MsgRw03(Msg_rw03 msg) {

        // Inner struct Msg
        ProtosTestobj.Proto_Msg.Builder protoMsg_Msg = ProtosTestobj.Proto_Msg.newBuilder();

        // Inner struct MsgRw01_00
        ProtosTestobj.Proto_Msg_rw01.Builder protoMsg_MsgRw01_00 = ProtosTestobj.Proto_Msg_rw01.newBuilder();

        protoMsg_MsgRw01_00.setChar01( msg.get_msg_rw01_00().get_char01() );
        protoMsg_MsgRw01_00.setByte01( msg.get_msg_rw01_00().get_byte01() );
        protoMsg_MsgRw01_00.setBoolean01( msg.get_msg_rw01_00().is_boolean01() );

        protoMsg_MsgRw01_00.setShort01( msg.get_msg_rw01_00().get_short01() );
        protoMsg_MsgRw01_00.setInt01( msg.get_msg_rw01_00().get_int01() );
        protoMsg_MsgRw01_00.setLong01( msg.get_msg_rw01_00().get_long01() );

        protoMsg_MsgRw01_00.setFloat01( msg.get_msg_rw01_00().get_float01() );
        protoMsg_MsgRw01_00.setDouble01( msg.get_msg_rw01_00().get_double01() );

        // Inner struct MsgRw01_00
        ProtosTestobj.Proto_Msg_rw01.Builder protoMsg_MsgRw01_01 = ProtosTestobj.Proto_Msg_rw01.newBuilder();

        protoMsg_MsgRw01_01.setChar01( msg.get_msg_rw01_01().get_char01() );
        protoMsg_MsgRw01_01.setByte01( msg.get_msg_rw01_01().get_byte01() );
        protoMsg_MsgRw01_01.setBoolean01( msg.get_msg_rw01_01().is_boolean01() );

        protoMsg_MsgRw01_01.setShort01( msg.get_msg_rw01_01().get_short01() );
        protoMsg_MsgRw01_01.setInt01( msg.get_msg_rw01_01().get_int01() );
        protoMsg_MsgRw01_01.setLong01( msg.get_msg_rw01_01().get_long01() );

        protoMsg_MsgRw01_01.setFloat01( msg.get_msg_rw01_01().get_float01() );
        protoMsg_MsgRw01_01.setDouble01( msg.get_msg_rw01_01().get_double01() );

        // Outer message MsgRw03
        ProtosTestobj.Proto_Msg_rw03.Builder protoMsg = ProtosTestobj.Proto_Msg_rw03.newBuilder();

        protoMsg.setMsg(protoMsg_Msg);
        protoMsg.setMsgRw0100(protoMsg_MsgRw01_00);
        protoMsg.setMsgRw0101(protoMsg_MsgRw01_01);

        return protoMsg.build();
    }


    public static Msg_rw03 protoToMsg_MsgRw03(ProtosTestobj.Proto_Msg_rw03 pm) {
        Msg_rw03 msg = new Msg_rw03();

        // Inner struct Msg
        ProtosTestobj.Proto_Msg inner_msg = pm.getMsg();
        Msg msg_msg = new Msg();
        // empty struct: nothing to transcribe

        // Inner struct MsgRw01_00
        ProtosTestobj.Proto_Msg_rw01 inner_msgRw01_00 = pm.getMsgRw0101();

        Msg_rw01 msg_rw01_00 = new Msg_rw01();

        msg_rw01_00.set_char01( (char) inner_msgRw01_00.getChar01() );
        msg_rw01_00.set_byte01( (byte) inner_msgRw01_00.getByte01() );
        msg_rw01_00.set_boolean01( inner_msgRw01_00.getBoolean01() );

        msg_rw01_00.set_short01( (short) inner_msgRw01_00.getShort01() );
        msg_rw01_00.set_int01( inner_msgRw01_00.getInt01() );
        msg_rw01_00.set_long01( inner_msgRw01_00.getLong01() );

        msg_rw01_00.set_float01( inner_msgRw01_00.getFloat01() );
        msg_rw01_00.set_double01( inner_msgRw01_00.getDouble01() );

        // Inner struct MsgRw01_01
        ProtosTestobj.Proto_Msg_rw01 inner_msgRw01_01 = pm.getMsgRw0101();

        Msg_rw01 msg_rw01_01 = new Msg_rw01();

        msg_rw01_01.set_char01( (char) inner_msgRw01_01.getChar01() );
        msg_rw01_01.set_byte01( (byte) inner_msgRw01_01.getByte01() );
        msg_rw01_01.set_boolean01( inner_msgRw01_01.getBoolean01() );

        msg_rw01_01.set_short01( (short) inner_msgRw01_01.getShort01() );
        msg_rw01_01.set_int01( inner_msgRw01_01.getInt01() );
        msg_rw01_01.set_long01( inner_msgRw01_01.getLong01() );

        msg_rw01_01.set_float01( inner_msgRw01_01.getFloat01() );
        msg_rw01_01.set_double01( inner_msgRw01_01.getDouble01() );

        // Outer message MsgRw03
        msg.set_msg(msg_msg);
        msg.set_msg_rw01_00(msg_rw01_00);
        msg.set_msg_rw01_01(msg_rw01_01);

        return msg;
    }


// ------------ MsgRw02 ------------

    public static ProtosTestobj.Proto_Msg_rw02 msgToProto_MsgRw02(Msg_rw02 msg) {

        // Inner struct Msg
        ProtosTestobj.Proto_Msg.Builder protoMsg_Msg = ProtosTestobj.Proto_Msg.newBuilder();

        // Inner struct MsgRw01
        ProtosTestobj.Proto_Msg_rw01.Builder protoMsg_MsgRw01 = ProtosTestobj.Proto_Msg_rw01.newBuilder();

        protoMsg_MsgRw01.setChar01( msg.get_msg_rw01().get_char01() );
        protoMsg_MsgRw01.setByte01( msg.get_msg_rw01().get_byte01() );
        protoMsg_MsgRw01.setBoolean01( msg.get_msg_rw01().is_boolean01() );

        protoMsg_MsgRw01.setShort01( msg.get_msg_rw01().get_short01() );
        protoMsg_MsgRw01.setInt01( msg.get_msg_rw01().get_int01() );
        protoMsg_MsgRw01.setLong01( msg.get_msg_rw01().get_long01() );

        protoMsg_MsgRw01.setFloat01( msg.get_msg_rw01().get_float01() );
        protoMsg_MsgRw01.setDouble01( msg.get_msg_rw01().get_double01() );

        // Outer message MsgRw02
        ProtosTestobj.Proto_Msg_rw02.Builder protoMsg = ProtosTestobj.Proto_Msg_rw02.newBuilder();

        protoMsg.setMsg(protoMsg_Msg);
        protoMsg.setMsgRw01(protoMsg_MsgRw01);

        return protoMsg.build();
    }


    public static Msg_rw02 protoToMsg_MsgRw02(ProtosTestobj.Proto_Msg_rw02 pm) {
        Msg_rw02 msg = new Msg_rw02();

        // Inner struct Msg
        ProtosTestobj.Proto_Msg inner_msg = pm.getMsg();
        Msg msg_msg = new Msg();
        // empty struct: nothing to transcribe

        // Inner struct MsgRw01
        ProtosTestobj.Proto_Msg_rw01 inner_msgRw01 = pm.getMsgRw01();

        Msg_rw01 msg_rw01 = new Msg_rw01();

        msg_rw01.set_char01( (char) inner_msgRw01.getChar01() );
        msg_rw01.set_byte01( (byte) inner_msgRw01.getByte01() );
        msg_rw01.set_boolean01( inner_msgRw01.getBoolean01() );

        msg_rw01.set_short01( (short) inner_msgRw01.getShort01() );
        msg_rw01.set_int01( inner_msgRw01.getInt01() );
        msg_rw01.set_long01( inner_msgRw01.getLong01() );

        msg_rw01.set_float01( inner_msgRw01.getFloat01() );
        msg_rw01.set_double01( inner_msgRw01.getDouble01() );

        // Outer message MsgRw02
        msg.set_msg(msg_msg);
        msg.set_msg_rw01(msg_rw01);

        return msg;
    }


// ------------ MsgRw01 ------------

    public static ProtosTestobj.Proto_Msg_rw01 msgToProto_MsgRw01(Msg_rw01 msg) {
        ProtosTestobj.Proto_Msg_rw01.Builder protoMsg = ProtosTestobj.Proto_Msg_rw01.newBuilder();

        // Transscribing fields
        protoMsg.setChar01( msg.get_char01() );
        protoMsg.setByte01( msg.get_byte01() );
        protoMsg.setBoolean01( msg.is_boolean01() );

        protoMsg.setShort01( msg.get_short01() );
        protoMsg.setInt01( msg.get_int01() );
        protoMsg.setLong01( msg.get_long01() );

        protoMsg.setFloat01( msg.get_float01() );
        protoMsg.setDouble01( msg.get_double01() );

        return protoMsg.build();
    }


    public static Msg_rw01 protoToMsg_MsgRw01(ProtosTestobj.Proto_Msg_rw01 pm) {
        Msg_rw01 msg = new Msg_rw01();

        msg.set_char01( (char) pm.getChar01() );
        msg.set_byte01( (byte) pm.getByte01() );
        msg.set_boolean01( pm.getBoolean01() );

        msg.set_short01( (short) pm.getShort01() );
        msg.set_int01( pm.getInt01() );
        msg.set_long01( pm.getLong01() );

        msg.set_float01( pm.getFloat01() );
        msg.set_double01( pm.getDouble01() );

        return msg;
    }


// ------------ Msg ------------

    public static ProtosTestobj.Proto_Msg msgToProto_Msg(Msg msg) {
        ProtosTestobj.Proto_Msg.Builder protoMsg = ProtosTestobj.Proto_Msg.newBuilder();
        return protoMsg.build();
    }

    public static Msg protoToMsg_Msg(ProtosTestobj.Proto_Msg protoObj) {
        Msg msg = new Msg();
        return msg;
    }

}
