package io.agora.media;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

public class Utils {
    public static final long HMAC_SHA256_LENGTH = 32;
    public static final int VERSION_LENGTH = 3;
    public static final int APP_ID_LENGTH = 32;

    public static byte[] hmacSign(String keyString, byte[] msg){
        SecretKeySpec keySpec = new SecretKeySpec(keyString.getBytes(), "HmacSHA256");
        Mac mac = null;
        try {
            mac = Mac.getInstance("HmacSHA256");
            mac.init(keySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return mac.doFinal(msg);
    }

    public static byte[] pack(PackableEx packableEx) {
        ByteBuf buffer = new ByteBuf();
        packableEx.marshal(buffer);
        return buffer.asBytes();
    }

    public static void unpack(byte[] data, PackableEx packableEx) {
        ByteBuf buffer = new ByteBuf();
        buffer.put(data);
        packableEx.unmarshal(buffer);
    }

    public static String base64Encode(byte[] data) {
        byte[] encodedBytes = Base64.getEncoder().encode(data);
        return new String(encodedBytes);
    }

    public static long crc32(String input) {
        // get bytes from string
        byte bytes[] = input.getBytes();
        Checksum checksum = new CRC32();
        // update the current checksum with the specified array of bytes
        checksum.update(bytes, 0, bytes.length);
        // get the current checksum value
        long checksumValue = checksum.getValue();
        return checksumValue;
    }
}
