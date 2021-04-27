package com.example.pgsample;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLWork {

     private ObjectMapper mapper = new ObjectMapper();
     private Connection connection;

    public SQLWork(Connection connect) {
        connection = connect;

    }

    void delete() throws SQLException {


        PreparedStatement statement =
                connection.prepareStatement("DELETE FROM test_table;");
        statement.execute();
    }

     void insert() throws SQLException {

        for (int i=1; i<10; i++) {
            PreparedStatement statement1 =
                    connection.prepareStatement("INSERT INTO test_table(id,name) VALUES( "+ i + ", " + "'Name "+ i + "');");

            statement1.execute();
        }
    }

     ArrayList<Rooster> selectAll() throws SQLException{

        PreparedStatement statement2 =
                connection.prepareStatement("SELECT * FROM list_of_roosters;");
        ResultSet rs2 = statement2.executeQuery();

         ArrayList<Rooster> roostersList = new  ArrayList<Rooster>();


        while (rs2.next() )
        {
            Rooster timeRoosters = new Rooster();
            timeRoosters.roosters_id = rs2.getInt(1);
            timeRoosters.name = rs2.getString(2);
            timeRoosters.describle_r = rs2.getString(3);

            roostersList.add(timeRoosters);
        }

        return roostersList;
    }

     public Rooster select(String namef) throws SQLException{

        PreparedStatement statement2 =
                connection.prepareStatement("SELECT *  FROM list_of_roosters WHERE name = '"+ namef + "';");
        ResultSet rs6 = statement2.executeQuery();

        Rooster foundRooster = new Rooster();
        while (rs6.next() )
        {
            foundRooster.roosters_id = rs6.getInt(1);
            foundRooster.name= rs6.getString(2);
            foundRooster.describle_r= rs6.getString(3);
        }
        return foundRooster;

    }

     void add(Rooster inputRooster) throws SQLException{
        

        PreparedStatement statement3 =
                connection.prepareStatement("SELECT COUNT(roosters_id) FROM list_of_roosters;");
        ResultSet rs4 = statement3.executeQuery();
        int num=0;
        while (rs4.next() )
        {
            num = rs4.getInt(1);
        }
         
        PreparedStatement statement4 =
                connection.prepareStatement("INSERT INTO list_of_roosters(roosters_id, name, describe_r) VALUES("+ num
                        +", '"+ inputRooster.name + "' , '" + inputRooster.describle_r + "' );");
        statement4.execute();



    }

     void JsonSer(ArrayList<Rooster> roostersList) throws  SQLException, IOException {

            File file = new File("roosters.json");
            file.createNewFile();
            FileWriter writer = new FileWriter(file);

            mapper.writeValue(writer, roostersList);
    }

     void JsonDeser() throws  SQLException, IOException {


        InputFun cin = new InputFun();
        String fileName = cin.getUserInput("Введите имя файла: ");
        fileName = fileName+ ".json";

         ArrayList<Rooster> roosterArrayList = mapper.readValue(new File(fileName),
                 mapper.getTypeFactory().constructCollectionType(List.class, Rooster.class));


         for (Rooster r: roosterArrayList ) {
             add(r);
         }

        System.out.println("Петухи успешно занесены в БД");

    }
}
