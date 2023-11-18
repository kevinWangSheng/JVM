package com.kevin.demo.JVM.classpath;

import com.kevin.demo.JVM.classpath.impl.WildcardEntry;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author wang
 * @create 2023-11-17-20:19
 */
public class Classpath {
    private Entry bootstrapClasspath;  //启动类路径
    private Entry extensionClasspath;  //扩展类路径
    private Entry userClasspath;       //用户类路径

    public Classpath(String jreOption,String cpOption){
        bootstrapAndExtensionClasspath(jreOption);

        parseUserClasspath(cpOption);
    }

    private void bootstrapAndExtensionClasspath(String jreOption){
        String jreDir = getJreDir(jreOption);
        //..jre/lib/*
        String jreLibPath = Paths.get(jreDir, "lib") + File.separator + "*";
        bootstrapClasspath = new WildcardEntry(jreLibPath);

        //..jre/lib/ext/*
        String jreExtPath = Paths.get(jreDir, "lib", "ext") + File.separator + "*";
        extensionClasspath = new WildcardEntry(jreExtPath);
    }

    public static String getJreDir(String jreOption){
        if(jreOption!= null && Files.exists(Paths.get(jreOption))){
            return jreOption;
        }

        if(Files.exists(Paths.get("./jre"))){
            return "./jre";
        }

        String jh = System.getenv("JAVA_HOME");
        if(jh != null){
            return Paths.get(jh, "jre").toString();
        }
        throw new RuntimeException("Can not find JRE folder!");
    }

    private void parseUserClasspath(String cpOption){
        if(cpOption == null){
            cpOption = ".";
        }
        userClasspath = Entry.create(cpOption);
    }

    public byte[] readClass(String className) throws IOException {
        className = className+".class";
        try {
            return bootstrapClasspath.readClass(className);
        } catch (Exception ignored) {
            //ignored
        }

        //[readClass]扩展类路径
        try {
            return extensionClasspath.readClass(className);
        } catch (Exception ignored) {
            //ignored
        }

        // 用户类路径
        return userClasspath.readClass(className);
    }
}
