package com.logger.reader;

import java.util.List;

public class LogReaderTest extends LogReader{
    public LogReaderTest(String filePath) {
        super(filePath);
    }
    public List<String> readFile(){
        return getAllEvents();
    }

    public void mapToKill(String killLine) {
        mapKill(killLine);
    }
}
