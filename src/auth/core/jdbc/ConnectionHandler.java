package auth.core.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.*;

import auth.core.util.ConfigProperties;

public class ConnectionHandler {

   static Connection con;
   private static String hostAddress;
   private static String userName;
   private static String passWord;
   private static String databaseName;
  
   private void DBConnectionPool() {
		ConfigProperties.initialize("C:\\Users\\chanonong\\workspace\\JDBCAuthentication\\src\\util\\DB.properties");
		//ConfigProperties.initialize("/Users/chanonkhamronyitha/Dropbox/JDBCAuthentication/src/util/DB.properties");
	   Properties propObj =ConfigProperties.getInstance();
		hostAddress = propObj.getProperty("DBHostAddress"); 
		userName = propObj.getProperty("UserName");
		passWord = propObj.getProperty("Password");
		databaseName = propObj.getProperty("DatabaseName");
	}
   public static Connection getConnection() {
	   
	   ConnectionHandler conObj = new ConnectionHandler();
	   conObj.DBConnectionPool();
 
      try
      {
         String url = "jdbc:mysql://"+hostAddress+"/"+databaseName+"?autoReconnect=true";
         Class.forName("com.mysql.jdbc.Driver");
         try
         {            	
            con = DriverManager.getConnection(url,userName,passWord); 
         }         
         catch (SQLException ex)
         {
            ex.printStackTrace();
         }
      }
      catch(ClassNotFoundException e)
      {
         System.out.println(e);
      }
    return con;
   }
}

