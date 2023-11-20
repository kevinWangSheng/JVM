package com.kevin.demo.JVM.instructions.references;

import com.kevin.demo.JVM.instructions.base.ClassInitLogic;
import com.kevin.demo.JVM.instructions.base.InstructionIndex16;
import com.kevin.demo.JVM.instructions.base.MethodInvokeLogic;
import com.kevin.demo.JVM.rtda.Frame;
import com.kevin.demo.JVM.rtda.heap.constantpool.MethodRef;
import com.kevin.demo.JVM.rtda.heap.constantpool.RunTimeConstantPool;
import com.kevin.demo.JVM.rtda.heap.methodarea.Class;
import com.kevin.demo.JVM.rtda.heap.methodarea.Method;

/**
 * http://www.itstack.org
 * create by fuzhengwei on 2019/4/28
 * invokestatic调用static方法
 */
public class INVOKE_STATIC extends InstructionIndex16 {

    @Override
    public void execute(Frame frame) {
        RunTimeConstantPool runTimeConstantPool = frame.method().clazz().constantPool();
        MethodRef methodRef = (MethodRef) runTimeConstantPool.getConstants(this.idx);
        Method resolvedMethod = methodRef.ResolvedMethod();

        if (!resolvedMethod.isStatic()) {
            throw new IncompatibleClassChangeError();
        }

        Class clazz = resolvedMethod.clazz();
        if (!clazz.initStarted()) {
            frame.revertNextPC();
            ClassInitLogic.initClass(frame.thread(), clazz);
            return;
        }

        MethodInvokeLogic.invokeMethod(frame, resolvedMethod);
    }
}
