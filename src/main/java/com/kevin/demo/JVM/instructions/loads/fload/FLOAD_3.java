package com.kevin.demo.JVM.instructions.loads.fload;

import com.kevin.demo.JVM.instructions.base.InstructionNoOperands;
import com.kevin.demo.JVM.rtda.Frame;

public class FLOAD_3 extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        Float val = frame.localVars().getFloat(3);
        frame.operandStack().pushFloat(val);
    }

}

