package com.kevin.demo.JVM.instructions.loads.iload;

import com.kevin.demo.JVM.instructions.base.InstructionNoOperands;
import com.kevin.demo.JVM.rtda.Frame;

public class ILOAD_0 extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        int val = frame.localVars().getInt(0);
        frame.operandStack().pushInt(val);
    }
}
