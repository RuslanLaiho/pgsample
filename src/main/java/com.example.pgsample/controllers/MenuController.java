package com.example.pgsample.controllers;

import com.example.pgsample.models.Rooster;
import com.example.pgsample.workers.InputFun;
import com.example.pgsample.workers.SQLWork;

import java.io.IOException;
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

        boolean flagForExit = true;
        while (flagForExit) {
            input = cin.getUserInput("Ввод: ");

            switch (Integer.parseInt(input)) {

                case (1):
                    ArrayList<Rooster> receivedRoosters = new ArrayList<Rooster>();
                    receivedRoosters = sqlWork.selectAll();
                    System.out.println(receivedRoosters);
                    break;

                case (2):
                    Rooster inputRoosters = new Rooster();
                    inputRoosters.name = cin.getUserInput("Введите имя петуха: ");
                    inputRoosters.describleRooster = cin.getUserInput("Введите описание петуха: ");
                    sqlWork.add(inputRoosters);
                    break;

                case (3):
                    input = cin.getUserInput("Введите имя петуха: ");
                    Rooster rooster = sqlWork.select(input);
                    System.out.println(rooster);
                    input = "3";
                    break;

                case (4):
                    try {
                        ArrayList<Rooster> roostersList = sqlWork.selectAll();
                        sqlWork.JsonSer(roostersList);

                    } catch (IOException throwables) {
                        System.out.println("Не удалось создать файл");
                    }
                    break;

                case (5):
                    try {
                        sqlWork.JsonDeser();
                    } catch (IOException throwables) {
                        System.out.println("Не удалось прочесть файл");
                    }
                    break;

                case (0):
                    flagForExit = false;
                    break;

            }
        }
    }
}
