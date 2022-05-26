package com.logger.reader;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.RequestScoped;
import java.nio.file.Files;
import java.nio.file.Path;

@RequestScoped
public class LoggerReader {

    @ConfigProperty(name = "log.file.path")
    String filePath;
    public String readeFile() {
        try{
            return Files.readString(Path.of(filePath));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
