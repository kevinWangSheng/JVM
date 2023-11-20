package com.kevin.demo.JVM.instructions.loads.iload;

import com.kevin.demo.JVM.instructions.base.InstructionIndex8;
import com.kevin.demo.JVM.rtda.Frame;

//load int from local variable
public class ILOAD extends InstructionIndex8 {

    @Override
    public void execute(Frame frame) {
        int val = frame.localVars().getInt(this.idx);
        frame.operandStack().pushInt(val);
    }
}

