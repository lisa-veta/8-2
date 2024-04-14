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
    private String firstDescription = "Вывод размера изображения";
    private String secondDescription = "Вывод информации exif ";
    @Override
    public boolean isRightFormat(String path) {
        return path.endsWith(".png");
    }

    @Override
    public void description(String path) {
        System.out.println("1 - " + firstDescription);
        System.out.println("2 - " + secondDescription);
    }

    @Override
    public void firstFunction(String path) throws IOException {
        System.out.println(firstDescription + ":\n");
        BufferedImage image = ImageIO.read(new File(path));
        int height = image.getHeight();
        int width = image.getWidth();
        System.out.println("Высота изображения: " + height +
                "\nШирина изображения: " + width);
    }

    @Override
    public void secondFunction(String path) {
        System.out.println(secondDescription + ":\n");
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
    public void thirdFunction(String path) {

    }
}
