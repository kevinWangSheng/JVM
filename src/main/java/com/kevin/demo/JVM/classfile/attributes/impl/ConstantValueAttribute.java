package com.kevin.demo.JVM.classfile.attributes.impl;

import com.kevin.demo.JVM.classfile.ClassReader;
import com.kevin.demo.JVM.classfile.attributes.AttributeInfo;

/**
 * @author wang
 * @create 2023-11-18-21:50
 */
public class ConstantValueAttribute implements AttributeInfo {
    private int constantValueIdx;
    @Override
    public void readInfo(ClassReader reader) {
        this.constantValueIdx = reader.readUint16();
    }

    public int getConstantValueIdx() {
        return this.constantValueIdx;
    }
}
