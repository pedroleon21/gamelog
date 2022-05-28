package com.reader;

import com.entries.Game;
import com.entries.GameScore;
import com.reader.Parser;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.RequestScoped;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@RequestScoped
public class LogReader {

    @ConfigProperty(name = "log.file.path")
    String filePath;


    public LogReader() {
    }

    public LogReader(String filePath) {
        this.filePath = filePath;
    }
    //talvez mudar para streaming de arquivo??
    public String read() {
        try {
            return Files.readString(Path.of(filePath));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }







}
