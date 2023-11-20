package com.kevin.demo.JVM.instructions.extended.ifnull;

import com.kevin.demo.JVM.instructions.base.Instruction;
import com.kevin.demo.JVM.instructions.base.InstructionBranch;
import com.kevin.demo.JVM.rtda.Frame;

//branch if reference is null
public class IFNULL extends InstructionBranch {

    @Override
    public void execute(Frame frame) {
        Object ref = frame.operandStack().popRef();
        if (null == ref) {
            Instruction.branch(frame, this.offset);
        }
    }
}
