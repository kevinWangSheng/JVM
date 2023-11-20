package com.kevin.demo.JVM.instructions.loads.aload;

import com.kevin.demo.JVM.instructions.base.InstructionIndex8;
import com.kevin.demo.JVM.rtda.Frame;

//load reference from local variable
public class ALOAD extends InstructionIndex8 {

    @Override
    public void execute(Frame frame) {
        Object ref = frame.localVars().getRef(this.idx);
        frame.operandStack().pushRef(ref);
    }

}
