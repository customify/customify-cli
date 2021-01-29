
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

  public  void init() {
      try{
          connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?characterEncoding=utf8","root","code");
          statement = connection.createStatement();
      }catch (SQLException e){
          System.out.println(e.getMessage());
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
