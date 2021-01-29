
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


    // To login into the shell do

    //
    // jaW3mRUAAwzTAiOTVkZu

  private static  Connection connection = null;
  private static Statement statement = null;
  private static final String dbUrl = "jdbc:mysql://uqq6c1ewt1hkbzwd:jaW3mRUAAwzTAiOTVkZu@bsnlibok3ll8qs778xbx-mysql.services.clever-cloud.com:3306/bsnlibok3ll8qs778xbx";
  private static final String user = "uqq6c1ewt1hkbzwd";
  private static final String password = "jaW3mRUAAwzTAiOTVkZu";


    /**
     * for initializing the connections to the database
     */
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
