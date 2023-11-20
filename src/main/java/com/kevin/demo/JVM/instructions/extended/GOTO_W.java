package com.kevin.demo.JVM.instructions.extended;

import com.kevin.demo.JVM.instructions.base.BytecodeReader;
import com.kevin.demo.JVM.instructions.base.Instruction;
import com.kevin.demo.JVM.rtda.Frame;

//branch always(wide index)
public class GOTO_W implements Instruction {

    private int offset;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.offset = reader.readInt();
    }

    @Override
    public void execute(Frame frame) {
        Instruction.branch(frame, this.offset);
    }

}
