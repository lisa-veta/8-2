package org.example.Modules;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.IOException;

public interface Module {
    boolean isRightFormat(String path);
    void description(String path);
    void firstFunction(String path) throws IOException, InvalidDataException, UnsupportedTagException;
    void secondFunction(String path) throws InvalidDataException, UnsupportedTagException, IOException;
    void thirdFunction(String path);
}
