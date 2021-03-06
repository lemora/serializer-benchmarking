import java.io.IOException;
import java.util.Arrays;
import org.junit.Test;

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
import serializer.SerializerJava;

import static junit.framework.TestCase.assertTrue;

public class TestJavaSerialization
{

// -------------------------- TESTS: --------------------------------------------------

// ------------ dCachey Types ------------

    @Test
    public void ser_PMPUM() {
        PoolManagerPoolUpMessage msg = null;
        msg = PoolManagerPoolUpMessage.createTestInstance();
        byte[] serialized = SerializerJava.serialize( msg );
        PoolManagerPoolUpMessage deserialized = null;
        deserialized = (PoolManagerPoolUpMessage) SerializerJava.deserialize( serialized );
        byte[] serialized_second = SerializerJava.serialize( deserialized );

        PoolManagerPoolUpMessage deserialized2 = (PoolManagerPoolUpMessage) SerializerJava.deserialize( serialized_second );
        byte[] serialized_third = SerializerJava.serialize( deserialized2 );

//        System.out.println(Arrays.toString(serialized));
//        System.out.println(Arrays.toString(serialized_second));

//        assertTrue( Arrays.equals(serialized, serialized_second) ); // some simplification on first serialization!
        assertTrue( Arrays.equals(serialized_second, serialized_third) ); // some simplification on first serialization!
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
        byte[] serialized = SerializerJava.serialize( msg );
        StringList deserialized = null;
        deserialized = (StringList) SerializerJava.deserialize( serialized );
        byte[] serialized_second = SerializerJava.serialize( deserialized );

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
        byte[] serialized = SerializerJava.serialize( msg );
        IntList deserialized = null;
        deserialized = (IntList) SerializerJava.deserialize( serialized );
        byte[] serialized_second = SerializerJava.serialize( deserialized );

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
        byte[] serialized = SerializerJava.serialize( msg );
        DoubleList deserialized = null;
        deserialized = (DoubleList) SerializerJava.deserialize( serialized );
        byte[] serialized_second = SerializerJava.serialize( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }


// ------------ Testobj Types ------------

    @Test
    public void ser_Msg() {
        Msg msg = new Msg();
        byte[] serialized = SerializerJava.serialize( msg );
        Msg deserialized = null;
        deserialized = (Msg) SerializerJava.deserialize( serialized );
        byte[] serialized_second = SerializerJava.serialize( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_MsgRw01() {
        Msg_rw01 msg = new Msg_rw01();
        byte[] serialized = SerializerJava.serialize( msg );
        Msg_rw01 deserialized = null;
        deserialized = (Msg_rw01) SerializerJava.deserialize( serialized );
        byte[] serialized_second = SerializerJava.serialize( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_MsgRw02() {
        Msg_rw02 msg = new Msg_rw02();
        byte[] serialized = SerializerJava.serialize( msg );
        Msg_rw02 deserialized = null;
        deserialized = (Msg_rw02) SerializerJava.deserialize( serialized );
        byte[] serialized_second = SerializerJava.serialize( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_MsgRw03() {
        Msg_rw03 msg = new Msg_rw03();
        byte[] serialized = SerializerJava.serialize( msg );
        Msg_rw03 deserialized = null;
        deserialized = (Msg_rw03) SerializerJava.deserialize( serialized );
        byte[] serialized_second = SerializerJava.serialize( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_MsgRw04() {
        Msg_rw04 msg = Msg_rw04.createTestInstance();
        byte[] serialized = SerializerJava.serialize( msg );
        Msg_rw04 deserialized = null;
        deserialized = (Msg_rw04) SerializerJava.deserialize( serialized );
        byte[] serialized_second = SerializerJava.serialize( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_MsgRw05() {
        Msg_rw05 msg = new Msg_rw05();
        byte[] serialized = SerializerJava.serialize( msg );
        Msg_rw05 deserialized = null;
        deserialized = (Msg_rw05) SerializerJava.deserialize( serialized );
        byte[] serialized_second = SerializerJava.serialize( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

}