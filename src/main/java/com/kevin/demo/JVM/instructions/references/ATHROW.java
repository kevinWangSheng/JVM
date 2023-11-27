package com.kevin.demo.JVM.instructions.references;

import com.kevin.demo.JVM._native.java._Throwable;
import com.kevin.demo.JVM.instructions.base.InstructionNoOperands;
import com.kevin.demo.JVM.rtda.Frame;
import com.kevin.demo.JVM.rtda.OperandStack;
import com.kevin.demo.JVM.rtda.Thread;
import com.kevin.demo.JVM.rtda.heap.methodarea.Object;
import com.kevin.demo.JVM.rtda.heap.methodarea.StringPool;

/**
 * http://www.itstack.org
 * create by fuzhengwei on 2019/5/2
 */
public class ATHROW extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        Object ex = frame.operandStack().popRef();
        if (ex == null) {
            throw new NullPointerException();
        }

        Thread thread = frame.thread();
        if (!findAndGotoExceptionHandler(thread, ex)) {
            handleUncaughtException(thread, ex);
        }
    }


    private boolean findAndGotoExceptionHandler(Thread thread, Object ex) {
        do {
            Frame frame = thread.currentFrame();
            int pc = frame.nextPC() - 1;

            int handlerPc = frame.method().findExceptionHandler(ex.clazz(), pc);
            if (handlerPc > 0) {
                OperandStack stack = frame.operandStack();
                stack.clear();
                stack.pushRef(ex);
                frame.setNextPC(handlerPc);
                return true;
            }

            thread.popFrame();
        } while (!thread.isStackEmpty());
        return false;
    }



    private void handleUncaughtException(Thread thread, Object ex) {
        thread.clearStack();

        Object jMsg = ex.getRefVar("detailMessage", "Ljava/lang/String;");

        String msg = StringPool.goString(jMsg);

        System.out.println(ex.clazz().javaName() + "：" + msg);

        java.lang.Object extra = ex.extra();

        _Throwable[] throwables = (_Throwable[]) extra;
        for (_Throwable t : throwables) {
            System.out.println(t.string());
        }

    }

}
