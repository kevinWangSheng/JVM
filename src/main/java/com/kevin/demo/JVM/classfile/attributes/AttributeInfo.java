package com.kevin.demo.JVM.classfile.attributes;

import com.kevin.demo.JVM.classfile.ClassReader;
import com.kevin.demo.JVM.classfile.constant.ConstantPool;
import com.kevin.demo.JVM.classfile.attributes.impl.*;

/**
 * @author wang
 * @create 2023-11-18-20:44
 */
public interface AttributeInfo {
    void readInfo(ClassReader reader);

    static AttributeInfo[] readAttributes(ClassReader reader, ConstantPool constantPool) {
        int attributesCount = reader.readUint16();
        AttributeInfo[] attributes = new AttributeInfo[attributesCount];
        for(int i = 0;i<attributesCount;i++){
            attributes[i] = readAttribute(reader, constantPool);
        }
        return attributes;
    }

    static AttributeInfo readAttribute(ClassReader reader,ConstantPool constantPool){
        int attrNameId = reader.readUint16();
        String attrName = constantPool.getUTF8(attrNameId);
        int attrLen = reader.readUint32TInteger();
        AttributeInfo attrInfo = newAttributeInfo(attrName, attrLen,constantPool);
        attrInfo.readInfo(reader);
        return attrInfo;
    }

    static AttributeInfo newAttributeInfo(String attrName, int attrLen, ConstantPool constantPool){
        switch (attrName) {
            case "BootstrapMethods":
                return new BootstrapMethodsAttribute();
            case "Code":
                return new CodeAttribute(constantPool);
            case "ConstantValue":
                return new ConstantValueAttribute();
            case "Deprecated":
                return new DeprecatedAttribute();
            case "EnclosingMethod":
                return new EnclosingMethodAttribute(constantPool);
            case "Exceptions":
                return new ExceptionsAttribute();
            case "InnerClasses":
                return new InnerClassesAttribute();
            case "LineNumberTable":
                return new LineNumberTableAttribute();
            case "LocalVariableTable":
                return new LocalVariableTableAttribute();
            case "LocalVariableTypeTable":
                return new LocalVariableTypeTableAttribute();
            // case "MethodParameters":
            // case "RuntimeInvisibleAnnotations":
            // case "RuntimeInvisibleParameterAnnotations":
            // case "RuntimeInvisibleTypeAnnotations":
            // case "RuntimeVisibleAnnotations":
            // case "RuntimeVisibleParameterAnnotations":
            // case "RuntimeVisibleTypeAnnotations":
            case "Signature":
                return new SignatureAttribute(constantPool);
            case "SourceFile":
                return new SourceFileAttribute(constantPool);
            // case "SourceDebugExtension":
            // case "StackMapTable":
            case "Synthetic":
                return new SyntheticAttribute();
            default:
                return new UnparsedAttribute(attrName, attrLen);
        }
    }
}
