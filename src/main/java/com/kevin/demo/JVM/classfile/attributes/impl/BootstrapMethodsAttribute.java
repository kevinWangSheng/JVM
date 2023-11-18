package com.kevin.demo.JVM.classfile.attributes.impl;

import com.kevin.demo.JVM.classfile.ClassReader;
import com.kevin.demo.JVM.classfile.attributes.AttributeInfo;

/**
 * @author wang
 * @create 2023-11-18-21:46
 */
public class BootstrapMethodsAttribute implements AttributeInfo {
    BootstrapMethod[] bootstrapMethods;

    @Override
    public void readInfo(ClassReader reader) {
        int bootstrapMethodNum = reader.readUint16();
        bootstrapMethods = new BootstrapMethod[bootstrapMethodNum];
        for (int i = 0; i < bootstrapMethodNum; i++) {
            bootstrapMethods[i] = new BootstrapMethod(reader.readUint16(), reader.readUint16s());
        }
    }

    static class BootstrapMethod {
        int bootstrapMethodRef;
        int[] bootstrapArguments;

        BootstrapMethod(int bootstrapMethodRef, int[] bootstrapArguments) {
            this.bootstrapMethodRef = bootstrapMethodRef;
            this.bootstrapArguments = bootstrapArguments;
        }
    }
}
