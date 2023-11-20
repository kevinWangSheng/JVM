package com.kevin.demo.JVM._native.sun;

import com.kevin.demo.JVM.instructions.base.MethodInvokeLogic;
import com.kevin.demo.JVM.rtda.Frame;
import com.kevin.demo.JVM.rtda.heap.methodarea.Class;
import com.kevin.demo.JVM.rtda.heap.methodarea.Method;
import com.kevin.demo.JVM.rtda.heap.methodarea.Object;
import com.kevin.demo.JVM.rtda.heap.methodarea.StringPool;

/**
 * http://www.itstack.org
 * create by fuzhengwei on 2019/4/30
 */
public class VM {

    public void initialize(Frame frame) {
        Class vmClass = frame.method().clazz();
        Object savedProps = vmClass.getRefVar("savedProps", "Ljava/util/Properties;");
        Object key = StringPool.jString(vmClass.loader(), "foo");
        Object val = StringPool.jString(vmClass.loader(), "bar");

        frame.operandStack().pushRef(savedProps);
        frame.operandStack().pushRef(key);
        frame.operandStack().pushRef(val);

        Class propsClass = vmClass.loader().loadClass("java/util/Properties");
        Method setPropMethod = propsClass.getInstanceMethod("setProperty",
                "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;");
        MethodInvokeLogic.invokeMethod(frame, setPropMethod);
    }

}
