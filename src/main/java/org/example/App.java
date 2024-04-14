package org.example;
import org.example.Modules.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class App {
    private static List<Module> moduls;
    @Autowired
    public App(List<Module> moduls){
        this.moduls = moduls;
    }

    public static void main(String[] args) throws IOException {
        SpringApplication.run(App.class, args);
        while (true){
            System.out.println("Введите путь к файлу\n(для завершения работы введите 0)");
            Scanner scanner = new Scanner(System.in);
            String filePath = scanner.nextLine();
            if (filePath.equals("0")) {
                return;
            }
            Module module = null;
            for(Module mod : moduls){
                if(mod.isRightFormat(filePath)){
                    module = mod;
                }
            }
            startModule(module, filePath);
        }
    }

    private static void startModule(Module module, String path) throws IOException {
        Scanner scanner = new Scanner(System.in);
        if(module != null){
            while (true){
                module.description(path);
                String choice = scanner.nextLine();
                switch (choice){
                    case "0":
                        return;
                    case "1":
                        module.firstFunction(path);
                    case "2":
                        module.thirdFunction(path);
                    case "3":
                        module.thirdFunction(path);
                    default:
                        System.out.println("Выберите из предложенного списка");
                        break;
                }
            }
        }
        else{
            System.out.println("Нет подходящего модуля для обратотки данного типа");
        }
    }
}