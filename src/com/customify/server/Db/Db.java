
/*
*
*  created by veritem 29/01/2021
*
*  */

package com.customify.server.Db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Db {

  private static  Connection connection = null;
  private static Statement statement = null;

    /**
     * for initializing the connections to the database
     */
    public static void init() {
      System.out.println("Db is connecting...........");


        Properties prop = new Properties();
        String fileName = "config.properties";
        InputStream is = null;

        try {
            is = new FileInputStream(fileName);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            prop.load(is);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


      try{
          connection = DriverManager.getConnection(prop.getProperty("dbUrl"),prop.getProperty("user"),prop.getProperty("password"));
          statement = connection.createStatement();

          System.out.println("Db connected......");

      }catch (SQLException e){
          System.out.println("Db error: "+e.getMessage());
      }
  }


  /*
  * Getting connection
  * */
    public static Connection getConnection() {
        return connection;
    }

    /*
    * Statement to perform query on
    * */
    public static Statement getStatement() {
        return statement;
    }

    public static void closeConnection(){
      try{
          connection.close();
      }catch (SQLException e){
          e.printStackTrace();
      }
  }
}
