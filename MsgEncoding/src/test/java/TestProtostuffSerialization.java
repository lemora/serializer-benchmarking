import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

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
import serializer.Helper;
import serializer.SerializerProtostuff;

import static junit.framework.TestCase.assertTrue;

public class TestProtostuffSerialization
{

// -------------------------- TESTS: --------------------------------------------------

// ------------ dCachey Types ------------

    @Test
    public void ser_PMPUM() {
        PoolManagerPoolUpMessage msg = PoolManagerPoolUpMessage.createTestInstance();
        byte[] serialized = SerializerProtostuff.ser_PMPUM( msg );
        PoolManagerPoolUpMessage deserialized = null;
        deserialized = (PoolManagerPoolUpMessage) SerializerProtostuff.deser_PMPUM( serialized );
        byte[] serialized_second = SerializerProtostuff.ser_PMPUM( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }


// ------------ Container Types ------------

    @Test
    public void ser_StringList() {
        StringList msg = null;
        try {
            msg = new StringList(Helper.getStringListFromCSV(10));
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] serialized = SerializerProtostuff.ser_StringList( msg );
        StringList deserialized = null;
        deserialized = (StringList) SerializerProtostuff.deser_StringList( serialized );
        byte[] serialized_second = SerializerProtostuff.ser_StringList( deserialized );

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
        byte[] serialized = SerializerProtostuff.ser_IntList( msg );
        IntList deserialized = null;
        deserialized = (IntList) SerializerProtostuff.deser_IntList( serialized );
        byte[] serialized_second = SerializerProtostuff.ser_IntList( deserialized );

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
        byte[] serialized = SerializerProtostuff.ser_DoubleList( msg );
        DoubleList deserialized = null;
        deserialized = (DoubleList) SerializerProtostuff.deser_DoubleList( serialized );
        byte[] serialized_second = SerializerProtostuff.ser_DoubleList( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

// ------------ Testobj Types ------------

    @Test
    public void ser_Msg() {
        Msg msg = new Msg();
        byte[] serialized = SerializerProtostuff.ser_Msg( msg );
        Msg deserialized = null;
        deserialized = (Msg) SerializerProtostuff.deser_Msg( serialized );
        byte[] serialized_second = SerializerProtostuff.ser_Msg( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_MsgRw01() {
        Msg_rw01 msg = new Msg_rw01();
        byte[] serialized = SerializerProtostuff.ser_MsgRw01( msg );
        Msg_rw01 deserialized = null;
        deserialized = (Msg_rw01) SerializerProtostuff.deser_MsgRw01( serialized );
        byte[] serialized_second = SerializerProtostuff.ser_MsgRw01( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_MsgRw02() {
        Msg_rw02 msg = new Msg_rw02();
        byte[] serialized = SerializerProtostuff.ser_MsgRw02( msg );
        Msg_rw02 deserialized = null;
        deserialized = (Msg_rw02) SerializerProtostuff.deser_MsgRw02( serialized );
        byte[] serialized_second = SerializerProtostuff.ser_MsgRw02( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_MsgRw03() {
        Msg_rw03 msg = new Msg_rw03();
        byte[] serialized = SerializerProtostuff.ser_MsgRw03( msg );
        Msg_rw03 deserialized = null;
        deserialized = (Msg_rw03) SerializerProtostuff.deser_MsgRw03( serialized );
        byte[] serialized_second = SerializerProtostuff.ser_MsgRw03( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_MsgRw04() {
        Msg_rw04 msg = Msg_rw04.createTestInstance();
        byte[] serialized = SerializerProtostuff.ser_MsgRw04( msg );
        Msg_rw04 deserialized = null;
        deserialized = (Msg_rw04) SerializerProtostuff.deser_MsgRw04( serialized );
        byte[] serialized_second = SerializerProtostuff.ser_MsgRw04( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) ); // Fails: Protostuff changes order of map items!
//        assertTrue( msg.get_stringList01().equals(deserialized.get_stringList01()));
//        assertTrue( msg.get_intStrMap01().equals(deserialized.get_intStrMap01()));
    }

    @Test
    public void ser_MsgRw05() {
        Msg_rw05 msg = new Msg_rw05();
        byte[] serialized = SerializerProtostuff.ser_MsgRw05( msg );
        Msg_rw05 deserialized = null;
        deserialized = (Msg_rw05) SerializerProtostuff.deser_MsgRw05( serialized );
        byte[] serialized_second = SerializerProtostuff.ser_MsgRw05( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

}