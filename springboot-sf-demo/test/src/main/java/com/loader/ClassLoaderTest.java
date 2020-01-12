package com.loader;

import javax.jnlp.ServiceManager;
import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTest {
    public static void main(String[] args) throws Exception {

        ClassLoader c = ClassLoaderTest.class.getClassLoader();
        while (c != null) {
            System.out.println(c);
            c = c.getParent();
        }

        //系统类加载器
        System.out.println("系统类加载器：" + ClassLoader.getSystemClassLoader());

    }

    public static void testBootStrapClassLoader() {

    }

    public static void cumsumerClassLoader() throws Exception {
        //自定义类加载器实现
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {

                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Object obj = classLoader.loadClass("com.loader.ClassLoaderTest").newInstance();
        System.out.println(obj.getClass()); //class com.loader.ClassLoaderTest
        System.out.println(obj instanceof com.loader.ClassLoaderTest);  //false
    }
}


