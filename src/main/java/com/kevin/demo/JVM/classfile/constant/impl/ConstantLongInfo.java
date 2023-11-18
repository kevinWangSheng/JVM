package com.kevin.demo.JVM.classfile.constant.impl;


import com.kevin.demo.JVM.classfile.ClassReader;
import com.kevin.demo.JVM.classfile.constant.ConstantInfo;

/**
 * http://www.itstack.org
 * create by fuzhengwei on 2019/4/26
 */
public class ConstantLongInfo implements ConstantInfo {

    private long val;

    @Override
    public void readInfo(ClassReader reader) {
        this.val = reader.readUint64TLong();
    }

    @Override
    public int tag() {
        return this.CONSTANT_TAG_LONG;
    }

    public long value(){
        return this.val;
    }

}
