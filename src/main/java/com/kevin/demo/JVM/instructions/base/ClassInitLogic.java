package com.kevin.demo.JVM.instructions.base;

import com.kevin.demo.JVM.rtda.Frame;
import com.kevin.demo.JVM.rtda.Thread;
import com.kevin.demo.JVM.rtda.heap.methodarea.Class;
import com.kevin.demo.JVM.rtda.heap.methodarea.Method;

/**
 * http://www.itstack.org
 * create by fuzhengwei on 2019/4/28
 */
public class ClassInitLogic {

    public static void initClass(Thread thread, Class clazz) {
        clazz.startInit();
        scheduleClinit(thread, clazz);
        initSuperClass(thread, clazz);
    }

    private static void scheduleClinit(Thread thread, Class clazz) {
        Method clinit = clazz.getClinitMethod();
        if (null == clinit) return;
        Frame newFrame = thread.newFrame(clinit);
        thread.pushFrame(newFrame);
    }

    private static void initSuperClass(Thread thread, Class clazz) {
        if (clazz.isInterface()) return;
        Class superClass = clazz.superClass();
        if (null != superClass && !superClass.initStarted()) {
            initClass(thread, superClass);
        }
    }

}
