package org.example.Modules;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;

@Component
public class DirectoryModule implements Module{
    private String firstDescription = "Вывод размера изображения";
    private String secondDescription = "Вывод информации exif ";
    @Override
    public boolean isRightFormat(String path) {
        return path.endsWith(".txt");
    }

    @Override
    public void description(String path) {
        System.out.println("1 - " + firstDescription);
        System.out.println("2 - " + secondDescription);
    }

    @Override
    public void firstFunction(String path) {

    }

    @Override
    public void secondFunction(String path) {

    }

    @Override
    public void thirdFunction(String path) {

    }
}
