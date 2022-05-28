package com.service;

import com.dao.DAO;
import com.logger.reader.LogReader;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Hashtable;
import java.util.List;

@RequestScoped
public class Service {
    @Inject
    DAO dao;
    @Inject
    LogReader reader;
    public Hashtable<String, List<String>> query() {
        return reader.readGames();
    }
}
