package customify.server.Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Db {
  private  Connection connection = null;
  private  Statement statement = null;

  public  void init() {
      try{
          this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?characterEncoding=utf8","root","code");
          this.statement = this.connection.createStatement();
      }catch (SQLException e){
          System.out.println(e.getMessage());
      }
  }


    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public  void closeConnection(){
      try{
          this.connection.close();
      }catch (SQLException e){
          e.printStackTrace();
      }
  }
}
