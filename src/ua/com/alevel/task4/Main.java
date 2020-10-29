package ua.com.alevel.task4;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Scan scan = new Scan();
        Map<String, Object> map = Scan.getMAP();

        for (Map.Entry<String, Object> currentEntry:
             map.entrySet()) {
            System.out.println(currentEntry.getKey());
        }
    }
}
