package ua.com.alevel.task2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) {
        TempClass tempClass = new TempClass();
        findAnnotation(tempClass);
        System.out.println(tempClass.getA() + " " + tempClass.getStr() + " " + " " + tempClass.getB());

    }

    private static void findAnnotation(TempClass tempClass) {
        Class<?> cl = TempClass.class;
        Field[] fields = cl.getDeclaredFields();
        for (Field currentField :
                fields) {
            Value value = currentField.getAnnotation(Value.class);
            Annotation[] annotations = currentField.getAnnotations();
            annotationsProcess(tempClass, currentField, value, annotations);
        }
    }

    private static void annotationsProcess(TempClass tempClass, Field currentField, Value value, Annotation[] annotations) {
        for (Annotation cAnnotation :
                annotations) {
            dataSetting(tempClass, currentField, value, cAnnotation);
        }
    }

    private static void dataSetting(TempClass tempClass, Field currentField, Value value, Annotation cAnnotation) {
        if (cAnnotation.annotationType().getSimpleName().equalsIgnoreCase("Value")) {
            currentField.setAccessible(true);
            try {
                String str = currentField.getType().getSimpleName();
                if (str.equalsIgnoreCase("int")) {
                    currentField.setInt(tempClass, Integer.parseInt(value.value()));
                } else {
                    currentField.set(tempClass, value.value());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }
}
