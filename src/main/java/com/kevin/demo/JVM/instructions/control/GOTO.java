package com.kevin.demo.JVM.instructions.control;

import com.kevin.demo.JVM.instructions.base.Instruction;
import com.kevin.demo.JVM.instructions.base.InstructionBranch;
import com.kevin.demo.JVM.rtda.Frame;

//branch always
public class GOTO extends InstructionBranch {

    @Override
    public void execute(Frame frame) {
        Instruction.branch(frame, this.offset);
    }
}
