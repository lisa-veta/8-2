package org.example.Modules;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

@Component
public class DirectoryModule implements Module{
    private final String firstDescription = "Вывод списка файлов в каталоге";
    private final String secondDescription = "Подсчет размера всех файлов в каталоге";
    private final String thirdDescription = "Подсчет количество папок и файлов";
    @Override
    public boolean isRightFormat(String path) {
        File folder = new File(path);
        return folder.isDirectory();
    }

    @Override
    public void description(String path) {
        System.out.println("\nРабота с папками:");
        System.out.println("0 - Выход");
        System.out.println("1 - " + firstDescription);
        System.out.println("2 - " + secondDescription);
        System.out.println("3 - " + thirdDescription);
    }

    @Override
    public void firstFunction(String path) {
        System.out.println("\n"+firstDescription + ":");
        File folder = new File(path);
        File[] files = folder.listFiles();
        if(files != null){
            for(File file : files){
                System.out.println(file.getName()+"\n");
            }
        }
        else{
            System.out.printf("Папка %s пуста%n", folder.getName());
        }
    }

    @Override
    public void secondFunction(String path) {
        System.out.println("\n"+secondDescription + ":");
        File folder = new File(path);
        File[] files = folder.listFiles();
        long count = 0;
        if(files != null){
            for(File file : files){
                long fileSize = file.length();
                System.out.println(file.getName() + " " + fileSize + "байт" + "\n");
                count += fileSize;
            }
            System.out.println("Всего занято пространства: " + count + " байт" + "\n");
        }
        else{
            System.out.printf("Папка %s пуста%n", folder.getName());
        }
    }

    @Override
    public void thirdFunction(String path) {
        System.out.println("\n"+thirdDescription + ":");
        File folder = new File(path);
        File[] files = folder.listFiles(File::isFile);
        File[] folders = folder.listFiles(File::isDirectory);
        if(files != null){
            System.out.println("Количество файлов : " + files.length);
        }
        if(folders != null){
            System.out.println("Количество папок : " + folders.length);
        }
    }
}
