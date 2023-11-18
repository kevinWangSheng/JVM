package com.kevin.demo.JVM.classfile.constant.impl;


import com.kevin.demo.JVM.classfile.ClassReader;
import com.kevin.demo.JVM.classfile.constant.ConstantInfo;

/**
 * http://www.itstack.org
 * create by fuzhengwei on 2019/4/26
 */
public class ConstantFloatInfo implements ConstantInfo {

    private float val;

    @Override
    public void readInfo(ClassReader reader) {
       this.val = reader.readUint64TFloat();
    }

    @Override
    public int tag() {
        return this.CONSTANT_TAG_FLOAT;
    }

    public float value(){
        return this.val;
    }

}
