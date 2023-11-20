package com.kevin.demo.JVM.instructions.comparisons.comparisons.if_acmp;


import com.kevin.demo.JVM.instructions.base.Instruction;
import com.kevin.demo.JVM.instructions.base.InstructionBranch;
import com.kevin.demo.JVM.rtda.Frame;

public class IF_ACMPNE extends InstructionBranch {

    @Override
    public void execute(Frame frame) {
        if (!_acmp(frame)) {
            Instruction.branch(frame, this.offset);
        }
    }

}