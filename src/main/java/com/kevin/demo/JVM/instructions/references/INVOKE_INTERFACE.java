package com.kevin.demo.JVM.instructions.references;

import com.kevin.demo.JVM.instructions.base.BytecodeReader;
import com.kevin.demo.JVM.instructions.base.Instruction;
import com.kevin.demo.JVM.instructions.base.MethodInvokeLogic;
import com.kevin.demo.JVM.rtda.Frame;
import com.kevin.demo.JVM.rtda.heap.constantpool.InterfaceMethodRef;
import com.kevin.demo.JVM.rtda.heap.constantpool.RunTimeConstantPool;
import com.kevin.demo.JVM.rtda.heap.methodarea.Method;
import com.kevin.demo.JVM.rtda.heap.methodarea.MethodLookup;
import com.kevin.demo.JVM.rtda.heap.methodarea.Object;

/**
 * http://www.itstack.org
 * create by fuzhengwei on 2019/4/28
 * invokeinterface调用接口方法
 */
public class INVOKE_INTERFACE implements Instruction {

    private int idx;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.idx = reader.readShort();
        reader.readByte();
        reader.readByte();
    }

    @Override
    public void execute(Frame frame) {
        RunTimeConstantPool runTimeConstantPool = frame.method().clazz().constantPool();
        InterfaceMethodRef methodRef = (InterfaceMethodRef) runTimeConstantPool.getConstants(this.idx);
        Method resolvedMethod = methodRef.resolvedInterfaceMethod();
        if (resolvedMethod.isStatic() || resolvedMethod.isPrivate()) {
            throw new IncompatibleClassChangeError();
        }
        Object ref = frame.operandStack().getRefFromTop(resolvedMethod.argSlotCount() - 1);
        if (null == ref) {
            throw new NullPointerException();
        }
        if (!ref.clazz().isImplements(methodRef.resolvedClass())) {
            throw new IncompatibleClassChangeError();
        }
        Method methodToBeInvoked = MethodLookup.lookupMethodInClass(ref.clazz(), methodRef.name(), methodRef.descriptor());
        if (null == methodToBeInvoked || methodToBeInvoked.isAbstract()) {
            throw new AbstractMethodError();
        }
        if (!methodToBeInvoked.isPublic()) {
            throw new IllegalAccessError();
        }

        MethodInvokeLogic.invokeMethod(frame, methodToBeInvoked);

    }

}
