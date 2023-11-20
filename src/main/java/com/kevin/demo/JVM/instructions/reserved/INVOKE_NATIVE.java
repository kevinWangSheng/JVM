package com.kevin.demo.JVM.instructions.reserved;

import com.kevin.demo.JVM._native.NativeMethod;
import com.kevin.demo.JVM._native.Registry;
import com.kevin.demo.JVM.instructions.base.InstructionNoOperands;
import com.kevin.demo.JVM.rtda.Frame;
import com.kevin.demo.JVM.rtda.heap.methodarea.Method;

/**
 * http://www.itstack.org
 * create by fuzhengwei on 2019/5/2
 */
public class INVOKE_NATIVE extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        Method method = frame.method();
        String className = method.clazz().name();
        String methodName = method.name();
        String methodDescriptor = method.descriptor();

        NativeMethod nativeMethod = Registry.findNativeMethod(className, methodName, methodDescriptor);
        if (null == nativeMethod) {
            String methodInfo = className + "." + methodName + methodDescriptor;
            throw new UnsatisfiedLinkError(methodInfo);
        }

        nativeMethod.invoke(frame);

    }

}
