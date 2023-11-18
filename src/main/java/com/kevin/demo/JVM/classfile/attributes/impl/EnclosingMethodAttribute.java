package com.kevin.demo.JVM.classfile.attributes.impl;

import com.kevin.demo.JVM.classfile.ClassReader;
import com.kevin.demo.JVM.classfile.constant.ConstantPool;
import com.kevin.demo.JVM.classfile.attributes.AttributeInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wang
 * @create 2023-11-18-21:55
 */
public class EnclosingMethodAttribute implements AttributeInfo {
    private ConstantPool constantPool;
    private int classIdx;

    private int methodIdx;
    @Override
    public void readInfo(ClassReader reader) {
        this.classIdx = reader.readUint16();
        this.methodIdx = reader.readUint16();
    }

    public EnclosingMethodAttribute(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public String className() {
        return constantPool.getClassName(classIdx);
    }

    public Map<String,String> methodNameAndDescriptor(){
        if(methodIdx<=0) return new HashMap<>();
        return constantPool.getNameAndType(methodIdx);
    }
}
