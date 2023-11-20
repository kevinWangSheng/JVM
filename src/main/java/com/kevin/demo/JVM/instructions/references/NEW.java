package com.kevin.demo.JVM.instructions.references;


import com.kevin.demo.JVM.instructions.base.ClassInitLogic;
import com.kevin.demo.JVM.instructions.base.InstructionIndex16;
import com.kevin.demo.JVM.rtda.Frame;
import com.kevin.demo.JVM.rtda.heap.constantpool.ClassRef;
import com.kevin.demo.JVM.rtda.heap.constantpool.RunTimeConstantPool;
import com.kevin.demo.JVM.rtda.heap.methodarea.Class;
import com.kevin.demo.JVM.rtda.heap.methodarea.Object;

public class NEW extends InstructionIndex16 {

    @Override
    public void execute(Frame frame) {
        RunTimeConstantPool cp = frame.method().clazz().constantPool();
        ClassRef classRef = (ClassRef) cp.getConstants(this.idx);
        Class clazz = classRef.resolvedClass();
        if (!clazz.initStarted()) {
            frame.revertNextPC();
            ClassInitLogic.initClass(frame.thread(), clazz);
            return;
        }

        if (clazz.isInterface() || clazz.isAbstract()) {
            throw new InstantiationError();
        }
        
        Object ref = clazz.newObject();
        frame.operandStack().pushRef(ref);
    }

}