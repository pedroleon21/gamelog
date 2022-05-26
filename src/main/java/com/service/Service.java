package com.service;

import com.logger.reader.LoggerReader;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class Service {

    @Inject
    LoggerReader reader;
    public String query() {
        return reader.readeFile();
    }
}
