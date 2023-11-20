package com.kevin.demo.JVM.instructions.loads.lload;

import com.kevin.demo.JVM.instructions.base.InstructionIndex8;
import com.kevin.demo.JVM.rtda.Frame;

public class LLOAD extends InstructionIndex8 {

    @Override
    public void execute(Frame frame) {
        Long val = frame.localVars().getLong(this.idx);
        frame.operandStack().pushLong(val);
    }

}
