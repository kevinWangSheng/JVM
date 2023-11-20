package com.kevin.demo.JVM.instructions.conversions.f2x;

import com.kevin.demo.JVM.instructions.base.InstructionNoOperands;
import com.kevin.demo.JVM.rtda.Frame;
import com.kevin.demo.JVM.rtda.OperandStack;

// convert float to double
public class F2D extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.operandStack();
        float f = stack.popFloat();
        double d = f;
        stack.pushDouble(d);
    }

}
