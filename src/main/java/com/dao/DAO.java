package com.dao;

import com.logger.reader.LogReader;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class DAO {
    @Inject
    LogReader reader;

}
