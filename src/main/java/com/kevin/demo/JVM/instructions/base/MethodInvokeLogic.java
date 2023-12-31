package com.kevin.demo.JVM.instructions.base;

import com.kevin.demo.JVM.rtda.Frame;
import com.kevin.demo.JVM.rtda.Slot;
import com.kevin.demo.JVM.rtda.Thread;
import com.kevin.demo.JVM.rtda.heap.methodarea.Method;

/**
 * http://www.itstack.org
 * create by fuzhengwei on 2019/4/28
 */
public class MethodInvokeLogic {

    public static void invokeMethod(Frame invokerFrame, Method method) {
        Thread thread = invokerFrame.thread();
        Frame newFrame = thread.newFrame(method);
        thread.pushFrame(newFrame);

        int argSlotCount = method.argSlotCount();
        if (argSlotCount > 0) {
            for (int i = argSlotCount - 1; i >= 0; i--) {
                Slot slot = invokerFrame.operandStack().popSlot();
                newFrame.localVars().setSlot(i, slot);
            }
        }

        //hack
        if (method.isNative()) {
            if ("registerNatives".equals(method.name())) {
                thread.popFrame();
            } else {
                throw new RuntimeException("native method " + method.name());
            }
        }
    }

}
