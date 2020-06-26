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
import serializer.SerializerKryo;

import static junit.framework.TestCase.assertTrue;

public class TestKryoSerialization
{

    @BeforeClass
    public static void setup() {
        System.out.println("Setup -- registering Kryo classes");
        // testobj
        SerializerKryo.registerClass( Msg.class );
        SerializerKryo.registerClass( Msg_rw01.class );
        SerializerKryo.registerClass( Msg_rw02.class );
        SerializerKryo.registerClass( Msg_rw03.class );
        SerializerKryo.registerClass( Msg_rw04.class );
        SerializerKryo.registerClass( Msg_rw05.class );
        SerializerKryo.registerClass( HashMap.class );
        // container
        SerializerKryo.registerClass( StringList.class );
        SerializerKryo.registerClass( IntList.class );
        SerializerKryo.registerClass( DoubleList.class );
        SerializerKryo.registerClass( LinkedList.class );
        // dcachey
        SerializerKryo.registerClass( PoolManagerPoolUpMessage.class );
        SerializerKryo.registerClass( PoolV2Mode.class );
        SerializerKryo.registerClass( PoolCostInfo.class );
        SerializerKryo.registerClass( PoolCostInfo.PoolSpaceInfo.class );
        SerializerKryo.registerClass( javax.security.auth.Subject.class );
        SerializerKryo.registerClass( java.util.HashSet.class );
    }

// -------------------------- TESTS: --------------------------------------------------

// ------------ dCachey Types ------------

    @Test
    public void ser_PMPUM() {
        PoolManagerPoolUpMessage msg = null;
        msg = PoolManagerPoolUpMessage.createTestInstance();
        byte[] serialized = SerializerKryo.serialize( msg );
        PoolManagerPoolUpMessage deserialized = null;
        deserialized = (PoolManagerPoolUpMessage) SerializerKryo.deserialize( serialized, PoolManagerPoolUpMessage.class );
        byte[] serialized_second = SerializerKryo.serialize( deserialized );

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
        byte[] serialized = SerializerKryo.serialize( msg );
        StringList deserialized = null;
        deserialized = (StringList) SerializerKryo.deserialize( serialized, StringList.class );
        byte[] serialized_second = SerializerKryo.serialize( deserialized );

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
        byte[] serialized = SerializerKryo.serialize( msg );
        IntList deserialized = null;
        deserialized = (IntList) SerializerKryo.deserialize( serialized, IntList.class );
        byte[] serialized_second = SerializerKryo.serialize( deserialized );

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
        byte[] serialized = SerializerKryo.serialize( msg );
        DoubleList deserialized = null;
        deserialized = (DoubleList) SerializerKryo.deserialize( serialized, DoubleList.class );
        byte[] serialized_second = SerializerKryo.serialize( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

// ------------ Testobj Types ------------

    @Test
    public void ser_Msg() {
        Msg msg = new Msg();
        byte[] serialized = SerializerKryo.serialize( msg );
        Msg deserialized = null;
        deserialized = (Msg) SerializerKryo.deserialize( serialized, Msg.class );
        byte[] serialized_second = SerializerKryo.serialize( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_MsgRw01() {
        Msg_rw01 msg = new Msg_rw01();
        byte[] serialized = SerializerKryo.serialize( msg );
        Msg_rw01 deserialized = null;
        deserialized = (Msg_rw01) SerializerKryo.deserialize( serialized, Msg_rw01.class );
        byte[] serialized_second = SerializerKryo.serialize( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_MsgRw02() {
        Msg_rw02 msg = new Msg_rw02();
        byte[] serialized = SerializerKryo.serialize( msg );
        Msg_rw02 deserialized = null;
        deserialized = (Msg_rw02) SerializerKryo.deserialize( serialized, Msg_rw02.class );
        byte[] serialized_second = SerializerKryo.serialize( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_MsgRw03() {
        Msg_rw03 msg = new Msg_rw03();
        byte[] serialized = SerializerKryo.serialize( msg );
        Msg_rw03 deserialized = null;
        deserialized = (Msg_rw03) SerializerKryo.deserialize( serialized, Msg_rw03.class );
        byte[] serialized_second = SerializerKryo.serialize( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_MsgRw04() {
        Msg_rw04 msg = Msg_rw04.createTestInstance();
        byte[] serialized = SerializerKryo.serialize( msg );
        Msg_rw04 deserialized = null;
        deserialized = (Msg_rw04) SerializerKryo.deserialize( serialized, Msg_rw04.class );
        byte[] serialized_second = SerializerKryo.serialize( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_MsgRw05() {
        Msg_rw05 msg = new Msg_rw05();
        byte[] serialized = SerializerKryo.serialize( msg );
        Msg_rw05 deserialized = null;
        deserialized = (Msg_rw05) SerializerKryo.deserialize( serialized, Msg_rw05.class );
        byte[] serialized_second = SerializerKryo.serialize( deserialized );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

}