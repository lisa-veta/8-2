package org.example.Modules;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.probe.FFmpegFormat;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class MP3Module implements Module{
    private final String firstDescription = "Вывод названия трека из тегов";
    private final String secondDescription = "Вывод длительности в секундах";
    private final String thirdDescription = "Вывод год создания, автора и жанра";
    @Override
    public boolean isRightFormat(String path) {
        return path.endsWith(".mp3");
    }

    @Override
    public void description(String path) {
        System.out.println("\nРабота с аудио файлами:");
        System.out.println("0 - Выход");
        System.out.println("1 - " + firstDescription);
        System.out.println("2 - " + secondDescription);
        System.out.println("3 - " + thirdDescription);
    }

    @Override
    public void firstFunction(String path) throws InvalidDataException, UnsupportedTagException, IOException {
        System.out.println("\n"+firstDescription + ":");
        Mp3File mp3file = new Mp3File(new File(path));
        if (mp3file.hasId3v1Tag()) {
            ID3v1 tag = mp3file.getId3v1Tag();
            System.out.println("Название трека : " + tag.getTitle() );
        }
    }


    @Override
    public void secondFunction(String path) throws InvalidDataException, UnsupportedTagException, IOException {
        System.out.println("\n"+secondDescription + ":");
        Mp3File mp3file = new Mp3File(new File(path));
        if (mp3file.hasId3v1Tag()) {
            long count = mp3file.getLengthInSeconds();
            System.out.println("Продолжительность трека : " + count + " сек");
        }
    }

    @Override
    public void thirdFunction(String path) throws InvalidDataException, UnsupportedTagException, IOException {
        System.out.println("\n"+thirdDescription + ":");
        Mp3File mp3file = new Mp3File(new File(path));
        if (mp3file.hasId3v1Tag()) {
            ID3v1 tag = mp3file.getId3v1Tag();
            System.out.println("Год создания трека : " + tag.getYear() +
                    "\nЖанр трека : " + tag.getGenreDescription() +
                    "\nАвтор трека : " + tag.getArtist());
        }
    }
}
