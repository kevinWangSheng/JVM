package com.kevin.demo.JVM.rtda.heap.constantpool;
import com.kevin.demo.JVM.classfile.constant.impl.ConstantInterfaceMethodRefInfo;
import com.kevin.demo.JVM.rtda.heap.methodarea.Method;

public class InterfaceMethodRef extends MemberRef {

    private Method method;

    public static InterfaceMethodRef newInterfaceMethodRef(RunTimeConstantPool runTimeConstantPool, ConstantInterfaceMethodRefInfo refInfo) {
        InterfaceMethodRef ref = new InterfaceMethodRef();
        ref.runTimeConstantPool = runTimeConstantPool;
        ref.copyMemberRefInfo(refInfo);
        return ref;
    }

    public Method resolvedInterfaceMethod() {
        if (this.method == null){
            this.resolveInterfaceMethodRef();
        }
        return this.method;
    }

    public void resolveInterfaceMethodRef(){

    }


}
