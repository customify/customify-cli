
/*
*
*  created by veritem 29/01/2021
*
*  */

package customify.server.Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Db {
  private static  Connection connection = null;
  private static Statement statement = null;
  private static final String dbUrl = new String("");
  private static final String user = new String("");
  private static final String password = new String("");


    public static void init() {
      System.out.println("Db is connecting...........");


      try{
          connection = DriverManager.getConnection(dbUrl,user,password);
          statement = connection.createStatement();


          System.out.println("Db connected......");

      }catch (SQLException e){
          System.out.println("Db error: "+e.getMessage());
      }
  }


    public static Connection getConnection() {
        return connection;
    }

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
