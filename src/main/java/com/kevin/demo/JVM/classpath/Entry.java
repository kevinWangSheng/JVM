package com.kevin.demo.JVM.classpath;

import com.kevin.demo.JVM.classpath.impl.CompositeEntry;
import com.kevin.demo.JVM.classpath.impl.DirEntry;
import com.kevin.demo.JVM.classpath.impl.WildcardEntry;
import com.kevin.demo.JVM.classpath.impl.ZipEntry;

import java.io.File;
import java.io.IOException;

/**
 * @author wang
 * @create 2023-11-17-19:41
 */
public interface Entry {
    byte[] readClass(String className) throws IOException;

    static  Entry create(String path){
        if(path.contains(File.pathSeparator)){
            return new CompositeEntry(path);
        }

        if(path.endsWith("*")){
            return new WildcardEntry(path);
        }

        if (path.endsWith(".jar") || path.endsWith(".JAR") ||
                path.endsWith(".zip") || path.endsWith(".ZIP")) {
            return new ZipEntry(path);
        }

        return new DirEntry(path);
    }
}
