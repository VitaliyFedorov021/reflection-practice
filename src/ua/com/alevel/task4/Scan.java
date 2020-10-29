package ua.com.alevel.task4;

import ua.com.alevel.task1.A;

import java.io.File;
import java.util.Map;

public class Scan {
    private static final Map<String, Object> MAP;
    static {
        MAP = Creator.addToMap(Finder.findClasses(new File("src/ua/com/alevel/task4")));
    }

    public static Map<String, Object> getMAP() {
        return MAP;
    }
}
