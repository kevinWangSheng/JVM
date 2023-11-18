package com.kevin.demo.JVM.classfile.attributes.impl;

import com.kevin.demo.JVM.classfile.ClassReader;
import com.kevin.demo.JVM.classfile.attributes.AttributeInfo;

/**
 * @author wang
 * @create 2023-11-18-21:58
 */
public class ExceptionsAttribute implements AttributeInfo {
    private int[] exceptionIndexTable;
    @Override
    public void readInfo(ClassReader reader) {
        this.exceptionIndexTable = reader.readUint16s();
    }
    public int[] exceptionIndexTable(){
        return exceptionIndexTable;
    }
}
