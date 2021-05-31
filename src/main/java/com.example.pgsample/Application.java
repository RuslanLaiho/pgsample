package com.example.pgsample;

import com.example.pgsample.controllers.MenuController;
import com.example.pgsample.models.Rooster;
import com.example.pgsample.workers.InputFun;
import com.example.pgsample.workers.SQLWork;
import com.example.pgsample.controllers.MenuController;
import com.example.pgsample.DBHelper.DBHelper;
import org.postgresql.ds.PGPoolingDataSource;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Application
{
    public static void main(String[] args) throws SQLException
    {
            DataSource dataSource = DBHelper.getDataSource();
            try (Connection connection = dataSource.getConnection()) {

            SQLWork sqlWork = new SQLWork(connection);
            MenuController.menu(sqlWork);
            }
    }
}

