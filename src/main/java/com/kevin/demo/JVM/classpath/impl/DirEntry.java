package com.kevin.demo.JVM.classpath.impl;

import com.kevin.demo.JVM.classpath.Entry;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author wang
 * @create 2023-11-17-20:00
 */
public class DirEntry implements Entry {
    private Path absolutePath;

    /**
     * 获取全路径
     * @param path
     */
    public DirEntry(String path){
        this.absolutePath = Path.of(path).toAbsolutePath();
    }
    @Override
    public byte[] readClass(String className) throws IOException {
        return Files.readAllBytes(absolutePath.resolve(className));
    }

    @Override
    public String toString() {
        return this.absolutePath.toString();
    }
}
