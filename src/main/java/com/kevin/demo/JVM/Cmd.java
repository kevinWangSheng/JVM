package com.kevin.demo.JVM;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.util.List;

/** 命令行
 * @author wang
 * @create 2023-11-17-17:11
 */
public class Cmd {
    @Parameter(names = {"-?", "-help"}, description = "print help message", order = 3, help = true)
    boolean helpFlag;

    @Parameter(names = {"-Xms"}, description = "init heap size")
    String Xms;

    @Parameter(names = {"-Xmx"}, description = "max heap size")
    String Xmx;

    @Parameter(names = "-version", description = "print version and exit", order = 2)
    boolean versionFlag = false;

    @Parameter(names = "verbose", description = "enable verbose output", order = 5)
    boolean verboseClassFlag = false;
    @Parameter(names = {"-cp", "-classpath"}, description = "classpath", order = 1)
    String classpath;

    @Parameter(description = "main class and args")
    List<String> mainClassAndArgs;

    boolean ok;

    @Parameter(names = "-Xjre", description = "path to jre", order = 4)
    String jre;
    String getMainClass() {
        return mainClassAndArgs != null && !mainClassAndArgs.isEmpty() ? mainClassAndArgs.get(0) : null;
    }

    List<String> getAppArgs(){
        return mainClassAndArgs != null && mainClassAndArgs.size() > 1 ? mainClassAndArgs.subList(1, mainClassAndArgs.size()) : null;
    }

    static Cmd parse(String[] args) {
        Cmd cmd = new Cmd();
        JCommander build = JCommander.newBuilder().addObject(cmd).build();
        build.parse(args);
        cmd.ok = true;
        return cmd;
    }
}
