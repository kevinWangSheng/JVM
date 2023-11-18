package com.kevin.demo.JVM.classpath.impl;

import com.kevin.demo.JVM.classpath.Entry;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.Stack;

/**
 * @author wang
 * @create 2023-11-17-20:11
 */
public class ZipEntry implements Entry {
    private Path absolutePath;

    public ZipEntry(String path){

        this.absolutePath = Paths.get(path).toAbsolutePath();
    }
    @Override
    public byte[] readClass(String className) throws IOException {
        try(FileSystem zipFs = FileSystems.newFileSystem(absolutePath, (ClassLoader) null)){
            return Files.readAllBytes(zipFs.getPath(className));
        }
    }

    @Override
    public String toString() {
        return this.absolutePath.toString();
    }
}
