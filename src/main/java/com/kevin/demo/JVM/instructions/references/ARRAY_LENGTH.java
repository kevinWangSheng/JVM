package com.kevin.demo.JVM.instructions.references;


import com.kevin.demo.JVM.instructions.base.InstructionNoOperands;
import com.kevin.demo.JVM.rtda.Frame;
import com.kevin.demo.JVM.rtda.OperandStack;
import com.kevin.demo.JVM.rtda.heap.methodarea.Object;

/**
 * http://www.itstack.org
 * create by fuzhengwei on 2019/4/29
 * get length of array
 */
public class ARRAY_LENGTH extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {

        OperandStack stack = frame.operandStack();
        Object arrRef = stack.popRef();
        if (null == arrRef){
            throw new NullPointerException();
        }

        int arrLen = arrRef.arrayLength();
        stack.pushInt(arrLen);
    }

}
