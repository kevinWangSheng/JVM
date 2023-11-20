package com.kevin.demo.JVM.instructions.constants.ipush;


import com.kevin.demo.JVM.instructions.base.BytecodeReader;
import com.kevin.demo.JVM.instructions.base.Instruction;
import com.kevin.demo.JVM.rtda.Frame;

//sipush指令从操作数中获取一个short型整数，扩展成int型，然后推入栈顶。
public class SIPUSH implements Instruction {

    private short val;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.val = reader.readShort();
    }

    @Override
    public void execute(Frame frame) {
         frame.operandStack().pushInt(val);
    }
}
