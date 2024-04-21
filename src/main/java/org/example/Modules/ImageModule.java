package org.example.Modules;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import org.springframework.stereotype.Component;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class ImageModule implements Module{
    private final String firstDescription = "Вывод размера изображения";
    private final String secondDescription = "Вывод информации exif ";
    private final String thirdDescription = "Вывод ориентации изображения";
    @Override
    public boolean isRightFormat(String path) {
        return path.endsWith(".png") || path.endsWith(".jpeg") || path.endsWith(".jpg") ;
    }

    @Override
    public void description(String path) {
        System.out.println("\nРабота с изображениями:");
        System.out.println("0 - Выход");
        System.out.println("1 - " + firstDescription);
        System.out.println("2 - " + secondDescription);
        System.out.println("3 - " + thirdDescription);
    }

    @Override
    public void firstFunction(String path) throws IOException {
        System.out.println("\n"+firstDescription + ":");
        BufferedImage image = ImageIO.read(new File(path));
        int height = image.getHeight();
        int width = image.getWidth();
        System.out.println("Высота изображения: " + height + "px" +
                "\nШирина изображения: " + width + "px");
    }

    @Override
    public void secondFunction(String path) {
        System.out.println("\n"+secondDescription + ":");
        try(InputStream inputStream = new FileInputStream(path)) {

            Metadata metadata = ImageMetadataReader.readMetadata(inputStream);
            ExifSubIFDDirectory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);

            if (directory != null) {
                for(Tag tag : directory.getTags()){
                    System.out.println(tag.getTagName() + " : " + tag.getDescription());
                }
            } else {
                System.out.println("Нет EXIF информации");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void thirdFunction(String path) throws IOException {
        System.out.println("\n"+thirdDescription + ":");
        BufferedImage image = ImageIO.read(new File(path));
        int height = image.getHeight();
        int width = image.getWidth();
        if(height > width){
            System.out.println("Изображение горизонтальное");
        }
        else if(height < width){
            System.out.println("Изображение вертикальное");
        }
        else {
            System.out.println("Изображение квадратное");
        }
    }
}
