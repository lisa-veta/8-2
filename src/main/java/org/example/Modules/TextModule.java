package org.example.Modules;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Component
public class TextModule implements Module{

    private final String firstDescription = "Подсчет и вывод количества строк";
    private final String secondDescription = "Вывод частоты вхождения каждого символа";
    @Override
    public boolean isRightFormat(String path) {
        return path.endsWith(".txt") || path.endsWith(".docx");
    }

    @Override
    public void description(String path) {
        System.out.println("Работа с текстовыми файлами:");
        System.out.println("0 - Выход");
        System.out.println("1 - " + firstDescription);
        System.out.println("2 - " + secondDescription);
    }

    @Override
    public void firstFunction(String path) {
        System.out.println("\n"+firstDescription + ":");
        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            int lineCount = 0;
            while (reader.readLine() != null) {
                lineCount++;
            }
            System.out.println("Количество строк в файле: " + lineCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void secondFunction(String path) {
        System.out.println("\n"+secondDescription + ":");
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))){
            Map<Character, Integer> map = new HashMap<>();
            int character;
            while ((character = reader.read()) != -1) {
                char currentChar = (char)character;
                if (map.containsKey(currentChar)) {
                    map.put(currentChar, map.get(currentChar) + 1);
                } else {
                    map.put(currentChar, 1);
                }
            }
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                System.out.println("Символ: " + entry.getKey() + " Частота появления: " + entry.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void thirdFunction(String path) {

    }
}
