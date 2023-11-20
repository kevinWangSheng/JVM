package com.kevin.demo.JVM.instructions.loads.dload;

import com.kevin.demo.JVM.instructions.base.InstructionIndex8;
import com.kevin.demo.JVM.rtda.Frame;

public class DLOAD extends InstructionIndex8 {

    @Override
    public void execute(Frame frame) {
        double val = frame.localVars().getDouble(this.idx);
        frame.operandStack().pushRef(val);
    }

}