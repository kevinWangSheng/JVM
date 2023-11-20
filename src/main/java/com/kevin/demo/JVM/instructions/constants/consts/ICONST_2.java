package com.kevin.demo.JVM.instructions.constants.consts;

import com.kevin.demo.JVM.instructions.base.InstructionNoOperands;
import com.kevin.demo.JVM.rtda.Frame;

public class ICONST_2 extends InstructionNoOperands {
    @Override
    public void execute(Frame frame) {
        frame.operandStack().pushInt(2);
    }
}
