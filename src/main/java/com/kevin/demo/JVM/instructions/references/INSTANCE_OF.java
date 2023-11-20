package com.kevin.demo.JVM.instructions.references;

import com.kevin.demo.JVM.instructions.base.InstructionIndex16;
import com.kevin.demo.JVM.rtda.Frame;
import com.kevin.demo.JVM.rtda.OperandStack;
import com.kevin.demo.JVM.rtda.heap.constantpool.ClassRef;
import com.kevin.demo.JVM.rtda.heap.constantpool.RunTimeConstantPool;
import com.kevin.demo.JVM.rtda.heap.methodarea.Class;
import com.kevin.demo.JVM.rtda.heap.methodarea.Object;

public class INSTANCE_OF extends InstructionIndex16 {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.operandStack();
        Object ref = stack.popRef();
        if (null == ref){
            stack.pushInt(0);
            return;
        }
        RunTimeConstantPool cp = frame.method().clazz().constantPool();
        ClassRef classRef = (ClassRef) cp.getConstants(this.idx);
        Class clazz = classRef.resolvedClass();
        if (ref.isInstanceOf(clazz)){
            stack.pushInt(1);
        } else {
            stack.pushInt(0);
        }
    }

}
