package com.kevin.demo.JVM.rtda;

import com.kevin.demo.JVM.rtda.heap.methodarea.Method;

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
    private Method method;

    public Frame(Thread thread, Method method) {
        this.thread = thread;
        this.method = method;
        this.localVars = new LocalVars(method.maxLocals);
        this.operandStack = new OperandStack(method.maxStack);
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

    public Method method(){
        return this.method;
    }

    public int nextPC() {
        return this.nextPC;
    }

    public void setNextPC(int nextPC) {
        this.nextPC = nextPC;
    }

    public void revertNextPC(){
        this.nextPC = this.thread.pc();
    }



}
