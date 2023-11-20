package com.kevin.demo.JVM.instructions.comparisons.comparisons.ifcond;

import com.kevin.demo.JVM.instructions.base.Instruction;
import com.kevin.demo.JVM.instructions.base.InstructionBranch;
import com.kevin.demo.JVM.rtda.Frame;

public class IFNE extends InstructionBranch {

    @Override
    public void execute(Frame frame) {
        int val = frame.operandStack().popInt();
        if (val != 0) {
            Instruction.branch(frame, this.offset);
        }
    }
}