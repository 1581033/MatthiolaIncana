package com.matth.utils.javassist;

import org.apache.ibatis.javassist.*;
import org.w3c.dom.Document;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @author Matthiola incana
 * @create 2022/6/22 15:46
 */
public class ModifyUtils {

    private final ClassPool classPool;

    private CtClass ctClass;

    private CtMethod ctMethod;

    private CtField ctField;

    public ModifyUtils(){
        classPool = ClassPool.getDefault();
    }

    public ModifyUtils(Class<?> clazz) throws NotFoundException {
        classPool = ClassPool.getDefault();
        ctClass = classPool.getCtClass(clazz.getName());
    }

    public ModifyUtils(String path) throws NotFoundException {
        classPool = ClassPool.getDefault();
        classPool.insertClassPath(path);
    }

    public void setPath(String path) throws NotFoundException {
        classPool.insertClassPath(path);
    }

    public void modify(String clazz, String method) throws Exception {
        ctClass = classPool.getCtClass(clazz);
        ctMethod = ctClass.getDeclaredMethod(method);
    }

    public void modify(String clazz, String method, List<Class<?>> classes) throws Exception {
        ctClass = classPool.getCtClass(clazz);
        CtClass[] classList = classes.stream().map(s -> classPool.makeClass(s.getName())).toArray(CtClass[]::new);
        ctMethod = ctClass.getDeclaredMethod(method,classList);
    }

    public void modifyNested(String clazz, String clazs, String method, List<Class<?>> classes) throws Exception {
        ctClass = classPool.getCtClass(clazz);
        //CtClass[] classList = classes.stream().map(s -> classPool.makeClass(s.getName())).toArray(CtClass[]::new);
        for (CtClass nestedClass : ctClass.getNestedClasses()) {
            if (clazs.equals(nestedClass.getName())){
                System.out.println("64 ----------->" + nestedClass.getName());
                CtMethod declaredMethod = nestedClass.getDeclaredMethod(method);
                System.out.println("66 ----------->" + declaredMethod.getName());
                declaredMethod.setBody("{return false;}");
                System.out.println(declaredMethod.getReturnType());
                nestedClass.writeFile("C:\\Users\\十六夜\\Desktop\\");
            }
        }
        ctClass.writeFile("C:\\Users\\十六夜\\Desktop\\");
    }

    public void modifyField(String clazz, String field) throws Exception {
        ctClass = classPool.getCtClass(clazz);
        ctField = ctClass.getDeclaredField(field);
    }

    public void addField(String field) throws CannotCompileException {
        ctClass.addField(CtField.make(field,ctClass));
    }

    public CtField[] getField() throws NotFoundException {
        return ctClass.getFields();
    }

    public void addMethod(String method) throws CannotCompileException {
        ctClass.addMethod(CtMethod.make(method,ctClass));
    }

    public CtMethod[] getMethod(String method) throws NotFoundException {
        return ctClass.getDeclaredMethods(method);
    }

    public void setValue(String body) throws CannotCompileException {
        //ctField.setAttribute();
    }

    public void setBody(String body) throws CannotCompileException {
        ctMethod.setBody(body);
    }

    public void writeFile(String path) throws CannotCompileException, IOException {
        ctClass.writeFile(path);
    }

    public void rest(String path) throws NotFoundException, IOException, CannotCompileException {
        writeFile(path);
        System.out.println("写入" + path);
        //classPool.getCtClass(ctClass.getName());
    }

    public static void modifyWordsJar() throws Exception {
        String path = "C:\\Users\\十六夜\\Desktop\\aspose-words-22.6-jdk17.jar";
        ModifyUtils modifyUtils = new ModifyUtils(path);
        modifyUtils.modify("com.aspose.words.zzcf","zzaM", Arrays.asList(InputStream.class));
        modifyUtils.setBody("{this.zzYLF = new java.util.Date(Long.MAX_VALUE);this.zzWzN = com.aspose.words.zzYh2.zzXTF;zzMc = this;}");
        modifyUtils.setPath("C:\\Users\\十六夜\\Desktop\\");

        modifyUtils.modify("com.aspose.words.zzZIg","zzWUc");
        modifyUtils.setBody("{return 256;}");
        modifyUtils.setPath("C:\\Users\\十六夜\\Desktop\\");
    }

    public static void modifyCellsJar() throws Exception {
        String path = "C:\\Users\\十六夜\\Desktop\\aspose-cells-22.6.jar";
        ModifyUtils modifyUtils = new ModifyUtils(path);
        modifyUtils.modify("com.aspose.cells.k65","a", Arrays.asList(Document.class));
        modifyUtils.setBody("{com.aspose.cells.License.a = \"\";a = this;com.aspose.cells.z7x.a();}");
        modifyUtils.setPath("C:\\Users\\十六夜\\Desktop\\");
    }

    public static void modifyBrowserJar() throws Exception {
        String path = "C:\\Users\\十六夜\\Desktop\\jxbrowser-7.12.2.jar";
        ModifyUtils modifyUtils = new ModifyUtils(path);
        modifyUtils.modifyNested("com.teamdev.jxbrowser.internal.rpc.transport.ConnectionServer", "com.teamdev.jxbrowser.internal.rpc.transport.ConnectionServer$AcceptConnections","validateLicence", Arrays.asList(Void.class));
        //modifyUtils.setBody("{return false;}");
        //modifyUtils.setPath("C:\\Users\\十六夜\\Desktop\\jxbrowser-7.12.3");
        //modifyUtils.writeFile("C:\\Users\\十六夜\\Desktop\\");
    }


}
