package com.reader.reader;

import org.junit.jupiter.api.Test;

public class LogReaderTester {
    //TODO: colocar filepath num arquivo de propriedades
    String filePath = "games.log";
    LogReader logReader = new LogReader(filePath);

    @Test
    void readFile(){
        logReader.read();
    }

}
