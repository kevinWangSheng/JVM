package com.kevin.demo.JVM.classfile.attributes.impl;

import com.kevin.demo.JVM.classfile.ClassReader;
import com.kevin.demo.JVM.classfile.attributes.AttributeInfo;

/**
 * @author wang
 * @create 2023-11-18-22:05
 */
public class UnparsedAttribute implements AttributeInfo {
    private String name;
    private int length;
    private byte[] info;

    public UnparsedAttribute(String attrName, int attrLen) {
        this.name = attrName;
        this.length = attrLen;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.info = reader.readBytes(this.length);
    }

    public byte[] info(){
        return this.info;
    }
}
