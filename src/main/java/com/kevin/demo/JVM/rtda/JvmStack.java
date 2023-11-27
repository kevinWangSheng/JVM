package com.kevin.demo.JVM.rtda;

/** jvm虚拟机栈
 * @author wang
 * @create 2023-11-19-22:58
 */
public class JvmStack {
    private int maxSize;
    private int size;
    private Frame _top;

    public JvmStack(int maxSize) {
        this.maxSize = maxSize;
    }

    public void push(Frame frame) {
        if (this.size > this.maxSize) {
            throw new StackOverflowError();
        }

        if (this._top != null) {
            frame.lower = this._top;
        }

        this._top = frame;
        this.size++;
    }

    public Frame pop() {
        if (this._top == null) {
            throw new RuntimeException("jvm stack is empty!");
        }
        Frame top = this._top;
        this._top = top.lower;
        top.lower = null;
        this.size--;

        return top;
    }

    public Frame top(){
        if (this._top == null){
            throw new RuntimeException("jvm stack is empty!");
        }
        return this._top;
    }

    public boolean isEmpty(){
        return this._top == null;
    }

    public Frame[] getFrames() {
        Frame[] frames = new Frame[this.size];
        int i = 0;
        for (Frame frame = this._top; frame != null; frame = frame.lower) {
            frames[i++] = frame;
        }
        return frames;
    }

    public void clear(){
        if (!this.isEmpty()){
            this.pop();
        }
    }
}
