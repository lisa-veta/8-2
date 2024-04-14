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
    private String firstDescription = "Вывод названия трека из тегов";
    private String secondDescription = "Вывод длительности в секундах";
    @Override
    public boolean isRightFormat(String path) {
        return path.endsWith(".mp3");
    }

    @Override
    public void description(String path) {
        System.out.println("1 - " + firstDescription);
        System.out.println("2 - " + secondDescription);
    }

    @Override
    public void firstFunction(String path) throws InvalidDataException, UnsupportedTagException, IOException {
        System.out.println(firstDescription + ":\n");
        Mp3File mp3file = new Mp3File(new File(path));
        if (mp3file.hasId3v1Tag()) {
            ID3v1 id3v1Tag = mp3file.getId3v1Tag();
            System.out.println("Название трека : " + id3v1Tag.getTitle());
        }
    }

    @Override
    public void secondFunction(String path) {
        System.out.println(secondDescription + ":\n");

    }

    @Override
    public void thirdFunction(String path) {

    }
}
