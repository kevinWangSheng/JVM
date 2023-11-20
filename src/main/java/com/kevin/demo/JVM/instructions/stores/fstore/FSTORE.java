package com.kevin.demo.JVM.instructions.stores.fstore;

import com.kevin.demo.JVM.instructions.base.InstructionIndex8;
import com.kevin.demo.JVM.rtda.Frame;

public class FSTORE extends InstructionIndex8 {

    @Override
    public void execute(Frame frame) {
        _fstore(frame, this.idx);
    }

}
