package ua.com.alevel.task1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        A a = null;
        Class<?> aClass = A.class;
        try {
            Constructor<?> constructor = aClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            try {
                a = (A) constructor.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Field[] fields = aClass.getDeclaredFields();

        fields[0].setAccessible(true);
        fields[1].setAccessible(true);
        try {
            fields[0].set(a, "new id value");
            fields[1].set(a, "new name value");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        for (Field currentField:
             fields) {
            try {
                System.out.println(currentField.get(a));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
