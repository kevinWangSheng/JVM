package com.kevin.demo.JVM.classpath.impl;

import com.kevin.demo.JVM.classpath.Entry;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * @author wang
 * @create 2023-11-17-20:01
 */
public class WildcardEntry extends CompositeEntry{
    public WildcardEntry(String pathList) {
        super(toPathList(pathList));
    }


    private static String toPathList(String wildcardPath){
        String baseDir = wildcardPath.replace("*", "");

        try {
            return Files.walk(Paths.get(baseDir))
                    .filter(Files::isRegularFile)
                    .map(Path::toString)
                    .filter(p->p.endsWith(".jar")||p.endsWith(".JAR"))
                    .collect(Collectors.joining(File.pathSeparator));
        } catch (IOException e) {
            return "";
        }
    }
}
