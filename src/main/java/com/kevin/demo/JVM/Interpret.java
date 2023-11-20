package com.kevin.demo.JVM;

import com.alibaba.fastjson2.JSON;
import com.kevin.demo.JVM.classfile.MemberInfo;
import com.kevin.demo.JVM.classfile.attributes.impl.CodeAttribute;
import com.kevin.demo.JVM.instructions.Factory;
import com.kevin.demo.JVM.instructions.base.BytecodeReader;
import com.kevin.demo.JVM.instructions.base.Instruction;
import com.kevin.demo.JVM.rtda.Frame;
import com.kevin.demo.JVM.rtda.Thread;



//指令集解释器
class Interpret {

    Interpret(MemberInfo m) {
        CodeAttribute codeAttr = m.codeAttribute();
        int maxLocals = codeAttr.maxLocals();
        int maxStack = codeAttr.maxStack();
        byte[] byteCode = codeAttr.data();
        Thread thread = new Thread();
        Frame frame = thread.newFrame(maxLocals, maxStack);
        thread.pushFrame(frame);
        loop(thread, byteCode);
    }

    private void loop(Thread thread, byte[] byteCode) {
        Frame frame = thread.popFrame();
        BytecodeReader reader = new BytecodeReader();

        while (true) {
            //循环
            int pc = frame.nextPC();
            thread.setPC(pc);
            //decode
            reader.reset(byteCode, pc);
            byte opcode = reader.readByte();
            Instruction inst = Factory.newInstruction(opcode);
            if (null == inst) {
                System.out.println("寄存器(指令)尚未实现 " + byteToHexString(new byte[]{opcode}));
                break;
            }
            inst.fetchOperands(reader);
            frame.setNextPC(reader.pc());
            System.out.println("寄存器(指令)：" + byteToHexString(new byte[]{opcode}) + " -> " + inst.getClass().getSimpleName() + " => 局部变量表：" + JSON.toJSONString(frame.localVars().getSlots()) + " 操作数栈：" + JSON.toJSONString(frame.operandStack().getSlots()));            //exec
            inst.execute(frame);
        }

    }

    private static String byteToHexString(byte[] codes) {
        StringBuilder sb = new StringBuilder();
        sb.append("0x");
        for (byte b : codes) {
            int value = b & 0xFF;
            String strHex = Integer.toHexString(value);
            if (strHex.length() < 2) {
                strHex = "0" + strHex;
            }
            sb.append(strHex);
        }
        return sb.toString();
    }

}
