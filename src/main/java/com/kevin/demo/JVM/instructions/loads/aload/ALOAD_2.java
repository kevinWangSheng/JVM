package com.kevin.demo.JVM.instructions.loads.aload;

import com.kevin.demo.JVM.instructions.base.InstructionNoOperands;
import com.kevin.demo.JVM.rtda.Frame;
import com.kevin.demo.JVM.rtda.heap.methodarea.Object;

public class ALOAD_2 extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        Object ref = frame.localVars().getRef(2);
        frame.operandStack().pushRef(ref);
    }

}

