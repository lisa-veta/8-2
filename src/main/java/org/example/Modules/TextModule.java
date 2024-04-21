package org.example.Modules;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
public class TextModule implements Module{

    private final String firstDescription = "Подсчет и вывод количества строк";
    private final String secondDescription = "Вывод частоты вхождения каждого символа";
    private final String thirdDescription = "Подсчет и вывод количества слов";
    @Override
    public boolean isRightFormat(String path) {
        return path.endsWith(".txt") || path.endsWith(".docx");
    }

    @Override
    public void description(String path) {
        System.out.println("\nРабота с текстовыми файлами:");
        System.out.println("0 - Выход");
        System.out.println("1 - " + firstDescription);
        System.out.println("2 - " + secondDescription);
        System.out.println("3 - " + thirdDescription);
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
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            Map<Character, Integer> map = new HashMap<>();
            int character;
            while ((character = reader.read()) != -1) {
                char current = (char) character;
                if (map.containsKey(current)) {
                    map.put(current, map.get(current) + 1);
                } else {
                    map.put(current, 1);
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
        System.out.print("\n"+thirdDescription + ": ");
        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            int wordCount = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                wordCount += words.length;
            }
            System.out.println("\nКоличество слов в файле: " + wordCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
