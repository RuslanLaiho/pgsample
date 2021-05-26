package com.example.pgsample.controllers;

import com.example.pgsample.DBHelper.DBHelper;
import com.example.pgsample.models.Rooster;
import com.example.pgsample.workers.InputFun;
import com.example.pgsample.workers.SQLWork;
import org.postgresql.ds.PGPoolingDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuController {



public static void menu(SQLWork sqlWork) throws SQLException {
    String input;
    InputFun cin = new InputFun();

    System.out.println("----------- Добро пожаловать -----------");
    System.out.println("------ в обработчик списка петухов ------");
    System.out.println("Введите: 1, что-бы вывести таблицу петухов.");
    System.out.println("Введите: 2, что бы ввести нового петуха в таблицу.");
    System.out.println("Введите: 3, для поиска петуха.");
    System.out.println("Введите: 4, для JSON сериаллизации, что бы это не значило.");
    System.out.println("Введите: 5, для считывания петухов из JSON и добавления в БД.");
    System.out.println("Введите: 0, Для выхода.");



        while (true) {
        input = cin.getUserInput("Ввод: ");
        System.out.println(input);

        if (Integer.parseInt(input) == 1) {
            ArrayList<Rooster> receivedRoosters = new ArrayList<Rooster>();
            receivedRoosters = sqlWork.selectAll();
            System.out.println(receivedRoosters);
        }

        if (Integer.parseInt(input) == 2) {

            Rooster inputRoosters = new Rooster();
            inputRoosters.name = cin.getUserInput("Введите имя петуха: ");
            inputRoosters.describle_r = cin.getUserInput("Введите описание петуха: ");
            sqlWork.add(inputRoosters);
            input = "2";
        }

        if (Integer.parseInt(input) == 3) {
            input = cin.getUserInput("Введите имя петуха: ");
            Rooster rooster = sqlWork.select(input);
            System.out.println(rooster);
            input = "3";
        }

        try {
            if (Integer.parseInt(input) == 4) {
                ArrayList<Rooster> roostersList = sqlWork.selectAll();
                sqlWork.JsonSer(roostersList);
            }
        } catch (IOException throwables) {
            System.out.println("Не удалось создать файл");
        }

        try {
            if (Integer.parseInt(input) == 5) {
                sqlWork.JsonDeser();
            }
        } catch (IOException throwables) {
            System.out.println("Не удалось прочесть файл");
        }

        if (Integer.parseInt(input) == 0) {
            break;
        }
    }



}
}
