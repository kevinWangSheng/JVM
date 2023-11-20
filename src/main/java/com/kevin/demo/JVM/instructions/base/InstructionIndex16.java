package com.kevin.demo.JVM.instructions.base;


import com.kevin.demo.JVM.rtda.Frame;

/**
 * @author wang
 * @create 2023-11-19-23:47
 */
public class InstructionIndex16 implements Instruction {
    protected int idx;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.idx = reader.readShort();
    }

    @Override
    public void  execute(Frame frame) {

    }

}
