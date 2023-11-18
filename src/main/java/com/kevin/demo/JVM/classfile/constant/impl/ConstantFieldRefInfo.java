package com.kevin.demo.JVM.classfile.constant.impl;


import com.kevin.demo.JVM.classfile.constant.ConstantPool;

/**
 * http://www.itstack.org
 * create by fuzhengwei on 2019/4/26
 */
public class ConstantFieldRefInfo extends ConstantMemberRefInfo {

    public ConstantFieldRefInfo(ConstantPool constantPool) {
        super(constantPool);
    }

    @Override
    public int tag() {
        return this.CONSTANT_TAG_FIELDREF;
    }

}
