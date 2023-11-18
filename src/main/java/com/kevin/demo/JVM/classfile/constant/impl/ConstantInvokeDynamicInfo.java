package com.kevin.demo.JVM.classfile.constant.impl;


import com.kevin.demo.JVM.classfile.ClassReader;
import com.kevin.demo.JVM.classfile.constant.ConstantInfo;

/**
 * http://www.itstack.org
 * create by fuzhengwei on 2019/4/26
 */
public class ConstantInvokeDynamicInfo implements ConstantInfo {

    private int bootstrapMethodAttrIdx;
    private int nameAndTypeIdx;

    @Override
    public void readInfo(ClassReader reader) {
        this.bootstrapMethodAttrIdx = reader.readUint16();
        this.nameAndTypeIdx = reader.readUint16();
    }

    @Override
    public int tag() {
        return this.CONSTANT_TAG_INVOKEDYNAMIC;
    }
}
