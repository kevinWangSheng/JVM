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
    private OperandStack operandStack;

    private Thread thread;

    private int nextPC;

    public Frame(Thread thread, int maxLocals, int maxStack) {
        this.thread = thread;
        this.localVars = new LocalVars(maxLocals);
        this.operandStack = new OperandStack(maxStack);
    }

    public Frame(int maxLocals,int maxStack) {
        Frame frame = new Frame(thread, maxLocals, maxStack);
        frame.lower = this;
    }

    public LocalVars localVars() {
        return localVars;
    }

    public OperandStack operandStack() {
        return operandStack;
    }

    public Thread thread() {
        return this.thread;
    }

    public int nextPC() {
        return this.nextPC;
    }

    public void setNextPC(int nextPC) {
        this.nextPC = nextPC;
    }

}
