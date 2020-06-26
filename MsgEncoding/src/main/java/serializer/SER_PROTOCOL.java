package serializer;

import java.util.Arrays;

public enum SER_PROTOCOL {
    UNKNOWN(new byte[]{}, "unknown", "x"),
    JAVA(new byte[]{}, "java", "jos"), // same as UNKNOWN, has no prefix
    PROTOBUF(new byte[]{0x00, 0x02, 0x00, 0x01}, "proto", "pb"),
    PROTOSTUFF(new byte[]{0x00, 0x02, 0x00, 0x01}, "protostuff", "psr"),
    AVRO(new byte[]{0x00, 0x03, 0x01, 0x01}, "avro", "av"),
    AVRO_JSON(new byte[]{0x00, 0x03, 0x02, 0x01}, "avrojson", "avj"),
    FST(new byte[]{0x00, 0x05, 0x00, 0x01}, "fst", "fst"),
    KRYO(new byte[]{0x00, 0x06, 0x00, 0x01}, "kryo", "kry"),
    HESSIAN(new byte[]{0x00, 0x07, 0x00, 0x01}, "hessian", "hes");

    public final byte[] prefixID;
    public final String shortName;
    public final String abbrev;

    public byte[] getPrefixID() {
        return prefixID;
    }

    public String getShortName() {
        return shortName;
    }

    public boolean equalsSerType(byte[] prefix) {
        return Arrays.equals(prefixID, prefix);
    }

    SER_PROTOCOL(byte[] prefixID, String shortName, String abbrev) {
        this.prefixID = prefixID;
        this.shortName = shortName;
        this.abbrev = abbrev.toUpperCase();
    }
}