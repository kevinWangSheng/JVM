package com.kevin.demo.JVM.classfile.attributes.impl;

import com.kevin.demo.JVM.classfile.ClassReader;
import com.kevin.demo.JVM.classfile.constant.ConstantPool;
import com.kevin.demo.JVM.classfile.attributes.AttributeInfo;

/**
 * @author wang
 * @create 2023-11-18-22:03
 */
public class SignatureAttribute implements AttributeInfo {
    private ConstantPool constantPool;
    private int signatureIdx;

    public SignatureAttribute(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.signatureIdx = reader.readUint16();
    }

    public String signature(){
        return this.constantPool.getUTF8(this.signatureIdx);
    }
}
