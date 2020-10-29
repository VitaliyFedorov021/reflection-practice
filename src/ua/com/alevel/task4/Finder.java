package ua.com.alevel.task4;

import java.io.File;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class Finder {
    public static List<Class<?>> findClasses(File file) {
        List<Class<?>> clazz = new ArrayList<>();
        for (File currentFile :
                file.listFiles()) {
            if (currentFile.isDirectory()) {
                findClasses(currentFile);
            } else {
                try {
                    char[] arr = currentFile.getPath().toCharArray();
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 4; i < arr.length - 5; ++i) {
                        stringBuilder.append(arr[i]);
                    }

                    clazz.add(Class.forName(stringBuilder.toString()));
                } catch (ClassNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return findAnnotations(clazz);
    }

    private static List<Class<?>> findAnnotations(List<Class<?>> classes) {
        List<Class<?>> classesWithAnnotations = new ArrayList<>();
        for (Class<?> currentClass :
                classes) {
            Annotation[] annotations = currentClass.getAnnotations();
            for (Annotation currentAnnotation :
                    annotations) {
                if (currentAnnotation.annotationType().getSimpleName().equalsIgnoreCase("Service")) {
                    classesWithAnnotations.add(currentClass);
                }
            }
        }
        return classesWithAnnotations;
    }
}
