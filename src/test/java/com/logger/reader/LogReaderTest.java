package com.logger.reader;

import com.logger.Parser;

import java.util.List;

public class LogReaderTest extends LogReader{
    public LogReaderTest(String filePath, Parser parser) {
        super(filePath,parser);
    }
    public List<String> readFile(){
        return getAllEvents();
    }
}
