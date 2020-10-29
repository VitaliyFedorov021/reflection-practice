package ua.com.alevel.task4;

import ua.com.alevel.task2.TempClass;
import ua.com.alevel.task2.Value;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Creator {

    public static Map<String, Object> addToMap(List<Class<?>> classesWithAnnotations) {
        Map<String, Object> resultMap = new HashMap<>();

        for (Class<?> currentClass : classesWithAnnotations) {
            Constructor<?> constructor = null;
            try {
                constructor = currentClass.getConstructor();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            try {
                Object variable = constructor.newInstance();
                findAnnotationValue(currentClass, variable);
                findAnnotationInit(currentClass, variable);
                resultMap.put(currentClass.getSimpleName(), variable);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return resultMap;
    }

    public static void findAnnotationValue(Class<?> clazz, Object var) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field currentField :
                fields) {
            Value value = currentField.getAnnotation(Value.class);
            Annotation[] annotations = currentField.getAnnotations();
            annotationsProcess(var, currentField, value, annotations);
        }
    }

    private static void annotationsProcess(Object var, Field currentField, Value value, Annotation[] annotations) {
        for (Annotation cAnnotation :
                annotations) {
            dataSetting(var, currentField, value, cAnnotation);
        }
    }

    private static void dataSetting(Object var, Field currentField, Value value, Annotation cAnnotation) {
        if (cAnnotation.annotationType().getSimpleName().equalsIgnoreCase("Value")) {
            currentField.setAccessible(true);
            try {
                String str = currentField.getType().getSimpleName();
                if (str.equalsIgnoreCase("int")) {
                    currentField.setInt(var, Integer.parseInt(value.value()));
                } else {
                    currentField.set(var, value.value());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static void findAnnotationInit(Class<?> clazz, Object var) {
        Method[] methods = clazz.getMethods();
        for (Method currentMethod :
                methods) {
            Annotation[] annotations = currentMethod.getAnnotations();
            annotationProcessing(annotations, currentMethod, var);
        }
    }
    private static void annotationProcessing(Annotation[] annotations, Method method, Object var) {
        for (Annotation currentAnnotation:
             annotations) {
            if (currentAnnotation.annotationType().getSimpleName().equalsIgnoreCase("Init")) {
                try {
                    method.invoke(var);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
