package com.kevin.demo.JVM.rtda;

import com.kevin.demo.JVM.rtda.heap.methodarea.Method;

/**线程
 * @author wang
 * @create 2023-11-19-22:58
 */
public class Thread {
    //Program Counter 寄存器
    private int pc;

    //虚拟机栈
    private JvmStack stack;

    public Thread() {
        this.stack = new JvmStack(1024);
    }

    public int pc() {
        return this.pc;
    }

    public void setPC(int pc) {
        this.pc = pc;
    }

    public void pushFrame(Frame frame) {
        this.stack.push(frame);
    }

    public Frame popFrame() {
        return this.stack.pop();
    }

    public Frame currentFrame() {
        return this.stack.top();
    }

    public Frame topFrame(){
        return this.stack.top();
    }

    public boolean isStackEmpty(){
        return this.stack.isEmpty();
    }

    public Frame newFrame(Method method) {
        return new Frame(this, method);
    }
}
