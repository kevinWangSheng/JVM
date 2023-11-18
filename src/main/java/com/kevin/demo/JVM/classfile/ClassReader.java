package com.kevin.demo.JVM.classfile;

import java.math.BigInteger;

/**
 * java虚拟机定义了u1、u2、u4三种数据类型来表示；1字节、2字节、4字节，无符号整数。
 *  在如下实现中，用增位方式表示无符号类型：
 *  u1、u2可以用int类型存储，因为int类型是4字节
 *  u4 需要用long类型存储，因为long类型是8字节
 * @author wang
 * @create 2023-11-18-20:45
 */
public class ClassReader {
    private byte[] data;

    public ClassReader(byte[] data) {
        this.data = data;
    }
    // u1
    public int readUint8(){
        byte[] val = readByte(1);
        return byte2int(val);
    }

    public int readUint16(){

        byte[] value = readByte(2);
        return byte2int(value);
    }

    public long readUint32(){
        byte[] value = readByte(4);
        String str_hex = new BigInteger(1, value).toString(16);
        return Long.parseLong(str_hex, 16);
    }

    public int readUint32TInteger(){
        byte[] val = readByte(4);
        return new BigInteger(1, val).intValue();
    }

    public float readUint64TFloat(){
        byte[] value = readByte(8);
        return new BigInteger(1,value).floatValue();
    }

    public long readUint64TLong(){
        byte[] value = readByte(8);
        return new BigInteger(1,value).longValue();
    }

    public double readUint64TDouble(){
        byte[] value = readByte(8);
        return new BigInteger(1,value).doubleValue();
    }

    public int[] readUint16s(){
        int n = readUint16();
        int[] res = new int[n];
        for(int i = 0;i<n;i++){
            res[i] = readUint16();
        }
        return res;
    }

    public byte[] readBytes(int n){
        return readByte(n);
    }

    public byte[] readByte(int length){
        byte[] copy = new byte[length];
        System.arraycopy(data, 0, copy, 0, length);
        System.arraycopy(data, length, data, 0, data.length - length);
        return copy;
    }

    private int byte2int(byte[] val) {
        String str_hex = new BigInteger(1, val).toString(16);
        return Integer.parseInt(str_hex, 16);
    }


}
