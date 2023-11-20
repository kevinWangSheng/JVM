package com.kevin.demo.JVM.instructions.comparisons.comparisons.if_icmp;

import com.kevin.demo.JVM.instructions.base.Instruction;
import com.kevin.demo.JVM.instructions.base.InstructionBranch;
import com.kevin.demo.JVM.rtda.Frame;
import com.kevin.demo.JVM.rtda.OperandStack;

public class IF_ICMPLT extends InstructionBranch {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.operandStack();
        int val2 = stack.popInt();
        int val1 = stack.popInt();
        if (val1 < val2) {
            Instruction.branch(frame, this.offset);
        }
    }

}