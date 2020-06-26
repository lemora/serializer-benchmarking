import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

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
import serializer.SerializerAvro;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.fail;

public class TestAvroSerialization
{
    
// -------------------------- TESTS: --------------------------------------------------

// ------------ Container Types ------------

    @Test
    public void ser_StringList_Binary() {
        StringList msg = null;
        try {
            msg = new StringList(Helper.getStringListFromCSV(10));
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] serialized = SerializerAvro.ser_StringList( msg, SerializerAvro.Type.BINARY );
        StringList deserialized = null;
        try {
            deserialized = (StringList) SerializerAvro.deser_StringList( serialized, SerializerAvro.Type.BINARY );
        } catch (IOException e) {
            fail( "Avro deser method threw exception." );
        }
        byte[] serialized_second = SerializerAvro.ser_StringList( deserialized, SerializerAvro.Type.BINARY );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_StringList_Json() {
        StringList msg = null;
        try {
            msg = new StringList(Helper.getStringListFromCSV(10));
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] serialized = SerializerAvro.ser_StringList( msg, SerializerAvro.Type.JSON );
        StringList deserialized = null;
        try {
            deserialized = (StringList) SerializerAvro.deser_StringList( serialized, SerializerAvro.Type.JSON );
        } catch (IOException e) {
            fail( "Avro deser method threw exception." );
        }
        byte[] serialized_second = SerializerAvro.ser_StringList( deserialized, SerializerAvro.Type.JSON );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_DoubleList_Binary() {
        DoubleList msg = null;
        try {
            msg = new DoubleList(Helper.getDoubleListFromCSV(10));
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] serialized = SerializerAvro.ser_DoubleList( msg, SerializerAvro.Type.BINARY );
        DoubleList deserialized = null;
        try {
            deserialized = (DoubleList) SerializerAvro.deser_DoubleList( serialized, SerializerAvro.Type.BINARY );
        } catch (IOException e) {
            fail( "Avro deser method threw exception." );
        }
        byte[] serialized_second = SerializerAvro.ser_DoubleList( deserialized, SerializerAvro.Type.BINARY );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_DoubleList_Json() {
        DoubleList msg = null;
        try {
            msg = new DoubleList(Helper.getDoubleListFromCSV(10));
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] serialized = SerializerAvro.ser_DoubleList( msg, SerializerAvro.Type.JSON );
        DoubleList deserialized = null;
        try {
            deserialized = (DoubleList) SerializerAvro.deser_DoubleList( serialized, SerializerAvro.Type.JSON );
        } catch (IOException e) {
            fail( "Avro deser method threw exception." );
        }
        byte[] serialized_second = SerializerAvro.ser_DoubleList( deserialized, SerializerAvro.Type.JSON );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_IntList_Binary() {
        IntList msg = null;
        try {
            msg = new IntList(Helper.getIntListFromCSV(10));
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] serialized = SerializerAvro.ser_IntList( msg, SerializerAvro.Type.BINARY );
        IntList deserialized = null;
        try {
            deserialized = (IntList) SerializerAvro.deser_IntList( serialized, SerializerAvro.Type.BINARY );
        } catch (IOException e) {
            fail( "Avro deser method threw exception." );
        }
        byte[] serialized_second = SerializerAvro.ser_IntList( deserialized, SerializerAvro.Type.BINARY );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_IntList_Json() {
        IntList msg = null;
        try {
            msg = new IntList(Helper.getIntListFromCSV(10));
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] serialized = SerializerAvro.ser_IntList( msg, SerializerAvro.Type.JSON );
        IntList deserialized = null;
        try {
            deserialized = (IntList) SerializerAvro.deser_IntList( serialized, SerializerAvro.Type.JSON );
        } catch (IOException e) {
            fail( "Avro deser method threw exception." );
        }
        byte[] serialized_second = SerializerAvro.ser_IntList( deserialized, SerializerAvro.Type.JSON );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_Msg_Json() {
        Msg msg = new Msg();
        byte[] serialized = SerializerAvro.ser_Msg( msg, SerializerAvro.Type.JSON );
        Msg deserialized = null;
        try {
            deserialized = (Msg) SerializerAvro.deser_Msg( serialized, SerializerAvro.Type.JSON );
        } catch (IOException e) {
            fail( "Avro deser method threw exception." );
        }
        byte[] serialized_second = SerializerAvro.ser_Msg( deserialized, SerializerAvro.Type.JSON );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_Msg_Binary() {
        Msg msg = new Msg();
        byte[] serialized = SerializerAvro.ser_Msg( msg, SerializerAvro.Type.BINARY );
        Msg deserialized = null;
        try {
            deserialized = (Msg) SerializerAvro.deser_Msg( serialized, SerializerAvro.Type.BINARY );
        } catch (IOException e) {
            fail( "Avro deser method threw exception." );
        }
        byte[] serialized_second = SerializerAvro.ser_Msg( deserialized, SerializerAvro.Type.BINARY );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_MsgRw01_Json() {
        Msg_rw01 msg = new Msg_rw01();
        byte[] serialized = SerializerAvro.ser_MsgRw01( msg, SerializerAvro.Type.JSON );
        Msg_rw01 deserialized = null;
        try {
            deserialized = (Msg_rw01) SerializerAvro.deser_MsgRw01( serialized, SerializerAvro.Type.JSON );
        } catch (IOException e) {
            fail( "Avro deser method threw exception." );
        }
        byte[] serialized_second = SerializerAvro.ser_MsgRw01( deserialized, SerializerAvro.Type.JSON );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_MsgRw01_Binary() {
        Msg_rw01 msg = new Msg_rw01();
        byte[] serialized = SerializerAvro.ser_MsgRw01( msg, SerializerAvro.Type.BINARY );
        Msg_rw01 deserialized = null;
        try {
            deserialized = (Msg_rw01) SerializerAvro.deser_MsgRw01( serialized, SerializerAvro.Type.BINARY );
        } catch (IOException e) {
            fail( "Avro deser method threw exception." );
        }
        byte[] serialized_second = SerializerAvro.ser_MsgRw01( deserialized, SerializerAvro.Type.BINARY );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_MsgRw02_Json() {
        Msg_rw02 msg = new Msg_rw02();
        byte[] serialized = SerializerAvro.ser_MsgRw02( msg, SerializerAvro.Type.JSON );
        Msg_rw02 deserialized = null;
        try {
            deserialized = (Msg_rw02) SerializerAvro.deser_MsgRw02( serialized, SerializerAvro.Type.JSON );
        } catch (IOException e) {
            fail( "Avro deser method threw exception." );
        }
        byte[] serialized_second = SerializerAvro.ser_MsgRw02( deserialized, SerializerAvro.Type.JSON );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_MsgRw02_Binary() {
        Msg_rw02 msg = new Msg_rw02();
        byte[] serialized = SerializerAvro.ser_MsgRw02( msg, SerializerAvro.Type.BINARY );
        Msg_rw02 deserialized = null;
        try {
            deserialized = (Msg_rw02) SerializerAvro.deser_MsgRw02( serialized, SerializerAvro.Type.BINARY );
        } catch (IOException e) {
            fail( "Avro deser method threw exception." );
        }
        byte[] serialized_second = SerializerAvro.ser_MsgRw02( deserialized, SerializerAvro.Type.BINARY );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_MsgRw03_Json() {
        Msg_rw03 msg = new Msg_rw03();
        byte[] serialized = SerializerAvro.ser_MsgRw03( msg, SerializerAvro.Type.JSON );
        Msg_rw03 deserialized = null;
        try {
            deserialized = (Msg_rw03) SerializerAvro.deser_MsgRw03( serialized, SerializerAvro.Type.JSON );
        } catch (IOException e) {
            fail( "Avro deser method threw exception." );
        }
        byte[] serialized_second = SerializerAvro.ser_MsgRw03( deserialized, SerializerAvro.Type.JSON );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_MsgRw03_Binary() {
        Msg_rw03 msg = new Msg_rw03();
        byte[] serialized = SerializerAvro.ser_MsgRw03( msg, SerializerAvro.Type.BINARY );
        Msg_rw03 deserialized = null;
        try {
            deserialized = (Msg_rw03) SerializerAvro.deser_MsgRw03( serialized, SerializerAvro.Type.BINARY );
        } catch (IOException e) {
            fail( "Avro deser method threw exception." );
        }
        byte[] serialized_second = SerializerAvro.ser_MsgRw03( deserialized, SerializerAvro.Type.BINARY );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_MsgRw04_Json() {
        Msg_rw04 msg = Msg_rw04.createTestInstance();
        byte[] serialized = SerializerAvro.ser_MsgRw04( msg, SerializerAvro.Type.JSON );
        Msg_rw04 deserialized = null;
        try {
            deserialized = (Msg_rw04) SerializerAvro.deser_MsgRw04( serialized, SerializerAvro.Type.JSON );
        } catch (IOException e) {
            fail( "Avro deser method threw exception." );
        }
        byte[] serialized_second = SerializerAvro.ser_MsgRw04( deserialized, SerializerAvro.Type.JSON );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_MsgRw04_Binary() {
        Msg_rw04 msg = Msg_rw04.createTestInstance();
        byte[] serialized = SerializerAvro.ser_MsgRw04( msg, SerializerAvro.Type.BINARY );
        Msg_rw04 deserialized = null;
        try {
            deserialized = (Msg_rw04) SerializerAvro.deser_MsgRw04( serialized, SerializerAvro.Type.BINARY );
        } catch (IOException e) {
            fail( "Avro deser method threw exception." );
        }
        byte[] serialized_second = SerializerAvro.ser_MsgRw04( deserialized, SerializerAvro.Type.BINARY );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_MsgRw05_Json() {
        Msg_rw05 msg = new Msg_rw05();
        byte[] serialized = SerializerAvro.ser_MsgRw05( msg, SerializerAvro.Type.JSON );
        Msg_rw05 deserialized = null;
        try {
            deserialized = (Msg_rw05) SerializerAvro.deser_MsgRw05( serialized, SerializerAvro.Type.JSON );
        } catch (IOException e) {
            fail( "Avro deser method threw exception." );
        }
        byte[] serialized_second = SerializerAvro.ser_MsgRw05( deserialized, SerializerAvro.Type.JSON );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

    @Test
    public void ser_MsgRw05_Binary() {
        Msg_rw05 msg = new Msg_rw05();
        byte[] serialized = SerializerAvro.ser_MsgRw05( msg, SerializerAvro.Type.BINARY );
        Msg_rw05 deserialized = null;
        try {
            deserialized = (Msg_rw05) SerializerAvro.deser_MsgRw05( serialized, SerializerAvro.Type.BINARY );
        } catch (IOException e) {
            fail( "Avro deser method threw exception." );
        }
        byte[] serialized_second = SerializerAvro.ser_MsgRw05( deserialized, SerializerAvro.Type.BINARY );

        assertTrue( Arrays.equals(serialized, serialized_second) );
    }

}