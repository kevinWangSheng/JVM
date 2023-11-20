package com.kevin.demo.JVM.instructions.base;

import com.kevin.demo.JVM.rtda.Frame;

/**
 * @author wang
 * @create 2023-11-19-23:28
 */
public interface Instruction {
    void fetchOperands(BytecodeReader reader);

    void execute(Frame frame);

    static void branch(Frame frame, int offset) {
        int pc = frame.thread().pc();
        int nextPC = pc + offset;
        frame.setNextPC(nextPC);
    }
}
