package com.kevin.demo.JVM.rtda;

/**操作数栈
 * @author wang
 * @create 2023-11-19-22:58
 */
public class OperandStack {
    private int size;

    private Slot[] slots;

    public OperandStack(int maxStack) {
        if (maxStack > 0) {
            slots = new Slot[maxStack];
            for (int i = 0; i < maxStack; i++) {
                slots[i] = new Slot();
            }
        }
    }

    public void pushInt(int val) {
        slots[size].num = val;
        size++;
    }

    public int popInt(){
        size --;
        return slots[size].num;
    }

    public void pushRef(Object ref){
        slots[size].ref = ref;
        size++;
    }

    public Object popRef(){
        size --;
        Object ref = slots[size].ref;
        slots[size].ref = null;
        return ref;
    }
}
