import java.io.IOException;
import java.util.Arrays;
import org.junit.Test;

import messages.realworld.Msg;
import messages.realworld.Msg_rw01;
import messages.realworld.Msg_rw02;
import messages.realworld.Msg_rw03;
import messages.realworld.Msg_rw04;
import messages.realworld.Msg_rw05;
import messages.typelists.DoubleList;
import messages.typelists.IntList;
import messages.typelists.StringList;
import serializer.Helper;
import serializer.SerializerProtobuf;

import static junit.framework.TestCase.assertTrue;

public class TestProtoSerialization
{

// -------------------------- TESTS: --------------------------------------------------

// ------------ Container Types ------------

    @Test
    public void ser_StringList() {
        StringList msg = null;
        try {
            msg = new StringList(Helper.getStringListFromCSV(10));
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] serialized = SerializerProtobuf.ser_StringList( msg );
        StringList deserialized = null;
        deserialized = (StringList) SerializerProtobuf.deser_StringList( serialized );
        byte[] serialized_second = SerializerProtobuf.ser_StringList( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }


    @Test
    public void ser_IntList() {
        IntList msg = null;
        try {
            msg = new IntList(Helper.getIntListFromCSV(10));
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] serialized = SerializerProtobuf.ser_IntList( msg );
        IntList deserialized = null;
        deserialized = (IntList) SerializerProtobuf.deser_IntList( serialized );
        byte[] serialized_second = SerializerProtobuf.ser_IntList( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_DoubleList() {
        DoubleList msg = null;
        try {
            msg = new DoubleList(Helper.getDoubleListFromCSV(10));
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] serialized = SerializerProtobuf.ser_DoubleList( msg );
        DoubleList deserialized = null;
        deserialized = (DoubleList) SerializerProtobuf.deser_DoubleList( serialized );
        byte[] serialized_second = SerializerProtobuf.ser_DoubleList( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

// ------------ Testobj Types ------------

    @Test
    public void ser_Msg() {
        Msg msg = new Msg();
        byte[] serialized = SerializerProtobuf.ser_Msg( msg );
        Msg deserialized = null;
        deserialized = (Msg) SerializerProtobuf.deser_Msg( serialized );
        byte[] serialized_second = SerializerProtobuf.ser_Msg( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_MsgRw01() {
        Msg_rw01 msg = new Msg_rw01();
        byte[] serialized = SerializerProtobuf.ser_MsgRw01( msg );
        Msg_rw01 deserialized = null;
        deserialized = (Msg_rw01) SerializerProtobuf.deser_MsgRw01( serialized );
        byte[] serialized_second = SerializerProtobuf.ser_MsgRw01( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_MsgRw02() {
        Msg_rw02 msg = new Msg_rw02();
        byte[] serialized = SerializerProtobuf.ser_MsgRw02( msg );
        Msg_rw02 deserialized = null;
        deserialized = (Msg_rw02) SerializerProtobuf.deser_MsgRw02( serialized );
        byte[] serialized_second = SerializerProtobuf.ser_MsgRw02( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_MsgRw03() {
        Msg_rw03 msg = new Msg_rw03();
        byte[] serialized = SerializerProtobuf.ser_MsgRw03( msg );
        Msg_rw03 deserialized = null;
        deserialized = (Msg_rw03) SerializerProtobuf.deser_MsgRw03( serialized );
        byte[] serialized_second = SerializerProtobuf.ser_MsgRw03( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_MsgRw04() {
        Msg_rw04 msg = Msg_rw04.createTestInstance();
        byte[] serialized = SerializerProtobuf.ser_MsgRw04( msg );
        Msg_rw04 deserialized = null;
        deserialized = (Msg_rw04) SerializerProtobuf.deser_MsgRw04( serialized );
        byte[] serialized_second = SerializerProtobuf.ser_MsgRw04( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_MsgRw05() {
        Msg_rw05 msg = new Msg_rw05();
        byte[] serialized = SerializerProtobuf.ser_MsgRw05( msg );
        Msg_rw05 deserialized = null;
        deserialized = (Msg_rw05) SerializerProtobuf.deser_MsgRw05( serialized );
        byte[] serialized_second = SerializerProtobuf.ser_MsgRw05( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

}