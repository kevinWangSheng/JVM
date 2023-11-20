package com.kevin.demo.JVM.rtda.heap.methodarea;

import com.kevin.demo.JVM.rtda.heap.ClassLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * http://www.itstack.org
 * create by fuzhengwei on 2019/4/29
 */
public class StringPool {

    private static Map<String, Object> internedStrs = new HashMap<>();

    public static java.lang.Object jString(ClassLoader loader, String goStr) {
        java.lang.Object internedStr = internedStrs.get(goStr);
        if (null != internedStr) return internedStr;

        char[] chars = goStr.toCharArray();
        Object jChars = new Object(loader.loadClass("[C"), chars);
        
        Object jStr = loader.loadClass("java/lang/String").newObject();
        jStr.setRefVal("value", "[C", jChars);

        internedStrs.put(goStr, jStr);
        return jStr;
    }

    public static String goString(Object jStr) {
        Object charArr = jStr.getRefVar("value", "[C");
        return new String(charArr.chars());
    }

}