package com.ctn.util;

/**
 * Created by hp on 2015/3/13.
 */
public class HexUtil {

    public static byte[] hexStr2Byte(String hexStr) {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return bytes;
    }

    public static String bytes2StrNum(byte[] bs){
        StringBuffer temp= new StringBuffer("");
        for(int i=bs.length-1;i>=0;i--){
            temp.append(bs[i]);
        }
        return temp.toString();
    }

    /**将byte[]转化成string**/
    public static String bytes2string(byte[] bs,boolean reverse){
        String temp="";
        if(reverse){
            for(int i=bs.length-1;i>=0;i--){
                temp+=(char)bs[i];
            }
        }else{
            for(int i=0;i<bs.length;i++){
                temp+=(char)bs[i];
            }
        }
        return temp;
    }

    /**截取byte[]的某段值**/
    public static byte[] getSubBytes(byte[] bs,int begin,int length,boolean reverse){
        byte[] temps=new byte[length];
        if(reverse){
            for(int i=begin+length-1;i>=begin;i--){
                temps[begin+length-1-i]=bs[i];
            }
            return temps;
        }else{
            for(int i=begin;i<begin+length;i++){
                temps[i-begin]=bs[i];
            }
            return temps;
        }
    }

    /** byte 的 unsigned int 值 **/
    public static int getUnsignByte(byte b) {
        if (b < 0) {
            return b & 0x7F + 128;
        } else {
            return (int) b;
        }
    }

    public static byte hexStr2ByteSingle(String hexStr) {
        return hexStr2Byte(hexStr)[0];
    }

}
