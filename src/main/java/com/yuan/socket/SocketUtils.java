package com.yuan.socket;


import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

public class SocketUtils {

    public static String getCLA(byte[] data){
        String ret = new String(Arrays.copyOfRange(data,1,3));
        return ret.toLowerCase();
    }

    public static String getCRC16(byte[] bytes) {
        int CRC = 0x0000ffff;
        int POLYNOMIAL = 0x0000a001;
        int i, j;
        for (i = 0; i < bytes.length; i++) {
            CRC ^= ((int) bytes[i] & 0x000000ff);
            for (j = 0; j < 8; j++) {
                if ((CRC & 0x00000001) != 0) {
                    CRC >>= 1;
                    CRC ^= POLYNOMIAL;
                } else {
                    CRC >>= 1;
                }
            }
        }
        return Integer.toHexString(CRC);
    }
    public static String BinaryToHexString(byte[] bytes) {
        String hexStr = "0123456789ABCDEF";
        String result = "";
        String hex = "";
        for (byte b : bytes) {
            hex = String.valueOf(hexStr.charAt((b & 0xF0) >> 4));
            hex += String.valueOf(hexStr.charAt(b & 0x0F));
            result += hex;
        }
        return result;
    }
    public static byte[] getData(byte[] data){
        return Arrays.copyOfRange(data,3,data.length-3);
    }
    public static int byte2int(byte[] res) {
        int targets = (res[0] & 0xff) | ((res[1] << 8) & 0xff00)
                | ((res[2] << 24) >>> 8) | (res[3] << 24);
        return targets;
    }
    public static String string2Hex(String string) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            int ch = (int) string.charAt(i);
            String strHex = Integer.toHexString(ch);
            sb.append(strHex);
        }
        return sb.toString();
    }
    public static byte[] intToByteArray(int i) {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(buf);
        try {
            out.writeInt(i);
            byte[] b = buf.toByteArray();
            out.close();
            buf.close();
            return b;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static byte[] linkBytes(byte[] bytes1,byte[] bytes2){
        byte[] bytes = new byte[bytes1.length+bytes2.length];
        int i = 0;
        for (;i<bytes1.length;i++){
            bytes[i] = bytes1[i];
        }
        for (i=0;i<bytes2.length;i++){
            bytes[bytes1.length+i] = bytes2[i];
        }
        return bytes;
    }
    public static boolean sendString(String message, OutputStream outputStream){
        Boolean ret=true;
        try {
            BufferedOutputStream writer = new BufferedOutputStream(outputStream);
            writer.write(message.getBytes());
            writer.flush();
        } catch (IOException e) {
            ret = false;
        }
        return ret;
    }
    public static boolean sendBytes(byte[] bytes, OutputStream outputStream){
        Boolean ret=true;
        try {
            BufferedOutputStream writer = new BufferedOutputStream(outputStream);
            writer.write(bytes);
            writer.flush();
        } catch (IOException e) {
            ret = false;
        }
        return ret;
    }
}
