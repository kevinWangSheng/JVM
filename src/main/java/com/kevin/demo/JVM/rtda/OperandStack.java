package com.kevin.demo.JVM.rtda;


import com.kevin.demo.JVM.rtda.heap.methodarea.Object;

/**操作数栈
 * @author wang
 * @create 2023-11-19-22:58
 */
public class OperandStack {
    private int size = 0;
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
        this.slots[size].num = val;
        this.size++;
    }

    public int popInt() {
        this.size--;
        return this.slots[size].num;
    }

    public void pushFloat(float val) {
        this.slots[this.size].num = (int) val;
        this.size++;
    }

    public float popFloat() {
        this.size--;
        return this.slots[this.size].num;
    }

    public void pushLong(long val) {
        this.slots[this.size].num = (int) val;
        this.slots[this.size + 1].num = (int) (val >> 32);
        this.size += 2;
    }

    public long popLong() {
        this.size -= 2;
        int low = this.slots[this.size].num;
        int high = this.slots[this.size + 1].num;
        return (long) (high) << 32 | (long) (low);
    }

    public void pushDouble(double val) {
        this.pushLong((long) val);
    }

    public double popDouble() {
        return this.popLong();
    }

    public void pushRef(Object ref) {
        this.slots[this.size].ref = ref;
        this.size++;
    }

    public Object popRef() {
        this.size--;
        Object ref = this.slots[size].ref;
        this.slots[this.size].ref = null;
        return ref;
    }

    public void pushSlot(Slot slot) {
        this.slots[this.size] = slot;
        this.size++;
    }

    public Slot popSlot() {
        this.size--;
        return this.slots[this.size];
    }

    public Object getRefFromTop(int n) {
        return this.slots[this.size - 1 - n].ref;
    }

    public Slot[] getSlots() {
        return slots;
    }

    public void pushBoolean(boolean val) {
        if (val) {
            this.pushInt(1);
        } else {
            this.pushInt(0);
        }
    }

    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.slots.length; i++) {
            this.slots[i].ref = null;
        }
    }
}
