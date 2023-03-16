package shop.mtcoding.reflection_study;
import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.springframework.stereotype.Controller;

import shop.mtcoding.reflection_study.anno.RequestMapping;

public class MyRefApp {
    private static Set<Class> ComponentScan(String pkg) throws Exception{
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Set<Class> classes = new HashSet<>(); // initial set

        URL pacakageUrl = classLoader.getResource(pkg);
        File packageDirectory = new File(pacakageUrl.toURI());
        for (File file : packageDirectory.listFiles()) {
            if (file.getName().endsWith(".class")){
                String className = pkg + "." + file.getName().replace("class","");
                Class cls = Class.forName(className);
                classes.add(cls); // list 에 클래스 추가 
            }
        }

        return classes;
    }

    public static void findUri(Set<Class> classes, String uri) throws Exception{
        boolean isFind = false; // initial set
        // finding classes, methods with the annotation
        for (Class cls : classes) {
            if (cls.isAnnotationPresent(Controller.class)){
               Object instance = cls.newInstance();
               Method[] methods = cls.getDeclaredMethods();

               for (Method mt : methods) {
                   Annotation anno = mt.getDeclaredAnnotation(RequestMapping.class);
                   RequestMapping rm = (RequestMapping) anno;
                   if(rm.uri().equals(uri)){
                       isFind = true;
                       mt.invoke(instance);
            }
        }
        if (isFind == false){
            System.out.println("404 not found");
        }

                
            }
        }
    }
    public static void main(String[] args) throws Exception {
       Scanner sc = new Scanner(System.in); 
       String uri = sc.nextLine();
        Set<Class> classes = ComponentScan("reflection_study");
       findUri(classes, uri);
    }
}
