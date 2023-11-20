package com.kevin.demo.JVM;

import com.kevin.demo.JVM.classfile.ClassFile;
import com.kevin.demo.JVM.classfile.MemberInfo;
import com.kevin.demo.JVM.classpath.Classpath;
import com.kevin.demo.JVM.rtda.Frame;
import com.kevin.demo.JVM.rtda.LocalVars;
import com.kevin.demo.JVM.rtda.OperandStack;

/**
 * @author wang
 * @create 2023-11-17-19:16
 */
public class Main {
    public static void main(String[] args) {
        Cmd cmd = Cmd.parse(args);
        if(!cmd.ok || cmd.helpFlag){
            System.out.println("Usage: <main class> [-options] class [args...]");
            return;
        }
        if(cmd.versionFlag){
            System.out.println("java version \"1.8.0\"");
            return;
        }
        startJVM(cmd);
    }


    /**
     * 测试字节码文件解析
     */
//    private static void startJVM(Cmd cmd) {
//        Classpath classpath = new Classpath(cmd.jre,cmd.classpath);
//        System.out.printf("classpath：%s class：%s args：%s\n", classpath, cmd.getMainClass(), cmd.getAppArgs());
//        String className = cmd.getMainClass().replace(".", "/");
//        ClassFile classFile = loadClass(className, classpath);
//        assert classFile != null;
//        printClassInfo(classFile);
//    }
//
//    private static ClassFile loadClass(String className, Classpath classpath) {
//        try {
//            byte[] classData = classpath.readClass(className);
//            return new ClassFile(classData);
//        } catch (Exception e) {
//            System.out.println("Could not find or load main class " + className);
//            return null;
//        }
//    }
//
//    private static void printClassInfo(ClassFile cf) {
//        System.out.println("version: " + cf.majorVersion() + "." + cf.minorVersion());
//        System.out.println("constants count：" + cf.constantPool().getSiz());
//        System.out.format("access flags：0x%x\n", cf.accessFlags());
//        System.out.println("this class：" + cf.className());
//        System.out.println("super class：" + cf.superClassName());
//        System.out.println("interfaces：" + Arrays.toString(cf.interfaceNames()));
//        System.out.println("fields count：" + cf.fields().length);
//        for (MemberInfo memberInfo : cf.fields()) {
//            System.out.format("%s \t\t %s\n", memberInfo.name(), memberInfo.descriptor());
//        }
//
//        System.out.println("methods count: " + cf.methods().length);
//        for (MemberInfo memberInfo : cf.methods()) {
//            System.out.format("%s \t\t %s\n", memberInfo.name(), memberInfo.descriptor());
//        }
//    }

    /**
     * test the load running time data
     */

//    private static void startJVM(Cmd args) {
//        Frame frame = new Frame(100, 100);
//        test_localVars(frame.localVars());
//        test_operandStack(frame.operandStack());
//    }
//
//    private static void test_localVars(LocalVars vars){
//        vars.setInt(0,100);
//        vars.setInt(1,-100);
//        System.out.println(vars.getInt(0));
//        System.out.println(vars.getInt(1));
//    }
//
//    private static void test_operandStack(OperandStack ops){
//        ops.pushInt(100);
//        ops.pushInt(-100);
//        ops.pushRef(null);
//        System.out.println(ops.popRef());
//        System.out.println(ops.popInt());
//    }


    private static void startJVM(Cmd cmd) {
        Classpath classpath = new Classpath(cmd.jre, cmd.classpath);
        System.out.printf("classpath:%s class:%s args:%s\n", classpath, cmd.getMainClass(), cmd.getAppArgs());
        String className = cmd.getMainClass().replace(".", "/");
        ClassFile classFile = loadClass(className, classpath);
        MemberInfo mainMethod = getMainMethod(classFile);
        if (null == mainMethod) {
            System.out.println("Main method not found in class " + cmd.classpath);
            return;
        }
        new Interpret(mainMethod);
    }

    private static ClassFile loadClass(String className, Classpath cp) {
        try {
            byte[] classData = cp.readClass(className);
            return new ClassFile(classData);
        } catch (Exception e) {
            System.out.println("Could not find or load main class " + className);
            e.printStackTrace();
        }
        return null;
    }

    //找到主函数入口方法
    private static MemberInfo getMainMethod(ClassFile cf) {
        if (null == cf) return null;
        MemberInfo[] methods = cf.methods();
        for (MemberInfo m : methods) {
            if ("main".equals(m.name()) && "([Ljava/lang/String;)V".equals(m.descriptor())) {
                return m;
            }
        }
        return null;
    }
}
