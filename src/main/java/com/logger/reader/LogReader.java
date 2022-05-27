package com.logger.reader;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.RequestScoped;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@RequestScoped
public class LoggerReader {

    @ConfigProperty(name = "log.file.path")
    String filePath;
    public List<String> readeFile() {
        String file = read();
        String[] eventos = file.split("\\n");
        return Arrays.asList(eventos);
    }

    private String read() {
        try{
            return Files.readString(Path.of(filePath));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
