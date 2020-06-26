import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

import messages.dcachey.PoolCostInfo;
import messages.dcachey.PoolManagerPoolUpMessage;
import messages.dcachey.PoolV2Mode;
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
import serializer.SerializerFst;

import static junit.framework.TestCase.assertTrue;

public class TestFstSerialization
{

    @BeforeClass
    public static void setup() {
        System.out.println("Setup -- registering Kryo classes");
        // testobj
        SerializerFst.registerClass( Msg.class );
        SerializerFst.registerClass( Msg_rw01.class );
        SerializerFst.registerClass( Msg_rw02.class );
        SerializerFst.registerClass( Msg_rw03.class );
        SerializerFst.registerClass( Msg_rw04.class );
        SerializerFst.registerClass( Msg_rw05.class );
        // containers
        SerializerFst.registerClass( HashMap.class );
        SerializerFst.registerClass( LinkedList.class );
        // dcachey
        SerializerFst.registerClass( PoolManagerPoolUpMessage.class );
        SerializerFst.registerClass(PoolV2Mode.class );
        SerializerFst.registerClass(PoolCostInfo.class );
        SerializerFst.registerClass(PoolCostInfo.PoolSpaceInfo.class );
    }

// -------------------------- TESTS: --------------------------------------------------

// ------------ dCachey Types ------------

    @Test
    public void ser_PMPUM() {
        PoolManagerPoolUpMessage msg = null;
        msg = PoolManagerPoolUpMessage.createTestInstance();
        byte[] serialized = SerializerFst.serialize( msg );
        PoolManagerPoolUpMessage deserialized = null;
        deserialized = (PoolManagerPoolUpMessage) SerializerFst.deserialize( serialized );
        byte[] serialized_second = SerializerFst.serialize( deserialized );

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
        byte[] serialized = SerializerFst.serialize( msg );
        StringList deserialized = null;
        deserialized = (StringList) SerializerFst.deserialize( serialized );
        byte[] serialized_second = SerializerFst.serialize( deserialized );

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
        byte[] serialized = SerializerFst.serialize( msg );
        IntList deserialized = null;
        deserialized = (IntList) SerializerFst.deserialize( serialized );
        byte[] serialized_second = SerializerFst.serialize( deserialized );

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
        byte[] serialized = SerializerFst.serialize( msg );
        DoubleList deserialized = null;
        deserialized = (DoubleList) SerializerFst.deserialize( serialized );
        byte[] serialized_second = SerializerFst.serialize( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }


// ------------ Testobj Types ------------

    @Test
    public void ser_Msg() {
        Msg msg = new Msg();
        byte[] serialized = SerializerFst.serialize( msg );
        Msg deserialized = null;
        deserialized = (Msg) SerializerFst.deserialize( serialized );
        byte[] serialized_second = SerializerFst.serialize( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_MsgRw01() {
        Msg_rw01 msg = new Msg_rw01();
        byte[] serialized = SerializerFst.serialize( msg );
        Msg_rw01 deserialized = null;
        deserialized = (Msg_rw01) SerializerFst.deserialize( serialized );
        byte[] serialized_second = SerializerFst.serialize( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_MsgRw02() {
        Msg_rw02 msg = new Msg_rw02();
        byte[] serialized = SerializerFst.serialize( msg );
        Msg_rw02 deserialized = null;
        deserialized = (Msg_rw02) SerializerFst.deserialize( serialized );
        byte[] serialized_second = SerializerFst.serialize( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_MsgRw03() {
        Msg_rw03 msg = new Msg_rw03();
        byte[] serialized = SerializerFst.serialize( msg );
        Msg_rw03 deserialized = null;
        deserialized = (Msg_rw03) SerializerFst.deserialize( serialized );
        byte[] serialized_second = SerializerFst.serialize( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_MsgRw04() {
        Msg_rw04 msg = Msg_rw04.createTestInstance();
        byte[] serialized = SerializerFst.serialize( msg );

        Msg_rw04 deserialized = null;
        deserialized = (Msg_rw04) SerializerFst.deserialize( serialized );
        byte[] serialized_second = SerializerFst.serialize( deserialized );

//        assertTrue( Arrays.equals(serialized, serialized_second) ); // Fails: Fst changes order of map items!
        assertTrue( msg.get_stringList01().equals(deserialized.get_stringList01()));
        assertTrue( msg.get_intStrMap01().equals(deserialized.get_intStrMap01()));
    }

    @Test
    public void ser_MsgRw05() {
        Msg_rw05 msg = new Msg_rw05();
        byte[] serialized = SerializerFst.serialize( msg );
        Msg_rw05 deserialized = null;
        deserialized = (Msg_rw05) SerializerFst.deserialize( serialized );
        byte[] serialized_second = SerializerFst.serialize( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

}