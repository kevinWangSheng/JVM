package com.kevin.demo.JVM.rtda;

/**栈帧
 * @author wang
 * @create 2023-11-19-22:56
 */
public class Frame {
    // 栈帧
    Frame lower;

    // 局部变量表
    LocalVars localVars;


    // 操作数栈
    OperandStack operandStack;

    public Frame(int maxLocals, int maxStack) {
        this.localVars = new LocalVars(maxLocals);
        this.operandStack = new OperandStack(maxStack);
    }

    public LocalVars localVars(){
        return localVars;
    }

    public OperandStack operandStack(){
        return operandStack;
    }

}
