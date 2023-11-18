package com.kevin.demo.JVM.classfile.constant.impl;



import com.kevin.demo.JVM.classfile.ClassReader;
import com.kevin.demo.JVM.classfile.constant.ConstantInfo;

/**
 * http://www.itstack.org
 * create by fuzhengwei on 2019/4/26
 */
public class ConstantMethodHandleInfo implements ConstantInfo {

    private int referenceKind;
    private int referenceIndex;

    @Override
    public void readInfo(ClassReader reader) {
        this.referenceKind = reader.readUint8();
        this.referenceIndex = reader.readUint16();
    }

    @Override
    public int tag() {
        return this.CONSTANT_TAG_METHODHANDLE;
    }
}
