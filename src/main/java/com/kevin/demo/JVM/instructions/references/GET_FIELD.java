package com.kevin.demo.JVM.instructions.references;

import com.kevin.demo.JVM.instructions.base.InstructionIndex16;
import com.kevin.demo.JVM.rtda.Frame;
import com.kevin.demo.JVM.rtda.OperandStack;
import com.kevin.demo.JVM.rtda.heap.constantpool.FieldRef;
import com.kevin.demo.JVM.rtda.heap.constantpool.RunTimeConstantPool;
import com.kevin.demo.JVM.rtda.heap.methodarea.Field;
import com.kevin.demo.JVM.rtda.heap.methodarea.Object;
import com.kevin.demo.JVM.rtda.heap.methodarea.Slots;

public class GET_FIELD extends InstructionIndex16 {

    @Override
    public void execute(Frame frame) {
        RunTimeConstantPool runTimeConstantPool = frame.method().clazz().constantPool();
        FieldRef fieldRef = (FieldRef) runTimeConstantPool.getConstants(this.idx);
        Field field = fieldRef.resolvedField();
        //java.lang.IncompatibleClassChangeError
        if (field.isStatic()){
            throw new IncompatibleClassChangeError();
        }
        OperandStack stack = frame.operandStack();
        Object ref = stack.popRef();
        //java.lang.NullPointerException
        if (null == ref) {
            throw new NullPointerException();
        }
        String descriptor = field.descriptor();
        int slotId = field.slotId();
        Slots slots = ref.fields();

        switch (descriptor.substring(0, 1)) {
            case "Z":
            case "B":
            case "C":
            case "S":
            case "I":
                stack.pushInt(slots.getInt(slotId));
                break;
            case "F":
                stack.pushFloat(slots.getFloat(slotId));
                break;
            case "J":
                stack.pushLong(slots.getLong(slotId));
                break;
            case "D":
                stack.pushDouble(slots.getDouble(slotId));
                break;
            case "L":
            case "[":
                stack.pushRef(slots.getRef(slotId));
                break;
            default:
                break;
        }
    }

}