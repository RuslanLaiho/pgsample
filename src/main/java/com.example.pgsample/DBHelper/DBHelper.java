package com.example.pgsample.DBHelper;

import org.postgresql.ds.PGPoolingDataSource;

public class DBHelper {

    private static PGPoolingDataSource dataSource;

    public static PGPoolingDataSource getDataSource() {

        if (dataSource == null) {

            dataSource = new PGPoolingDataSource();
            dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
            dataSource.setUser("postgres");
            dataSource.setPassword("1488228");

        }
        return dataSource;
    }
}
