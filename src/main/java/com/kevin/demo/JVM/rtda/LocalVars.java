package com.kevin.demo.JVM.rtda;

import com.kevin.demo.JVM.rtda.heap.methodarea.Object;

/** 局部变量
 * @author wang
 * @create 2023-11-19-22:57
 */
public class LocalVars {
    // 局部变量表
    private Slot[] slots;

    public LocalVars(int maxLocals) {
        if (maxLocals > 0) {
            slots = new Slot[maxLocals];
            for (int i = 0; i < maxLocals; i++) {
                slots[i] = new Slot();
            }
        }
    }

    public void setInt(int idx, int val) {
        this.slots[idx].num = val;
    }

    public int getInt(int idx) {
        return slots[idx].num;
    }

    public void setFloat(int idx, float val) {
        this.slots[idx].num = (Float.valueOf(val)).intValue();
    }

    public Object getThis(){
        return this.getRef(0);
    }

    public Float getFloat(int idx) {
        int num = this.slots[idx].num;
        return (float) num;
    }

    public void setLong(int idx, long val) {
        this.slots[idx].num = (int) val;
        this.slots[idx + 1].num = (int) (val >> 32);
    }

    public Long getLong(int idx) {
        int low = this.slots[idx].num;
        int high = this.slots[idx + 1].num;
        return ((long) high << 32) | (long) low;
    }

    public void setDouble(int idx, double val) {
        setLong(idx, (long) val);
    }

    public Double getDouble(int idx) {
        return Double.valueOf(getLong(idx));
    }

    public void setRef(int idx, Object ref) {
        slots[idx].ref = ref;
    }

    public Object getRef(int idx) {
        return slots[idx].ref;
    }

    public Slot[] getSlots() {
        return slots;
    }

    public void setSlots(Slot[] slots) {
        this.slots = slots;
    }

    public void setSlot(int idx, Slot slot) {
        this.slots[idx] = slot;
    }
}
