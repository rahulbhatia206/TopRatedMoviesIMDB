	/*
	 * Author - RahulB
	 * Date - 23rd March, 2020
	 * Purpose - This File will be used for connection with the database and executing queries
	 */

package basePackage;

import java.sql.*;

//Connect to the sqlite database
public class DBFunctions
{
	TestBase objBase = new TestBase();
	public void Connect(){
    try {
      Class.forName(objBase.strSQLite);
      objBase.conn = DriverManager.getConnection(objBase.strDBPath);
      objBase.conn.setAutoCommit(false);
      System.out.println("Opened database successfully");
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
  }
	
	//This method will execute the sql queries to fetch/update data.
    public void execSql(String query) throws SQLException{
    	objBase.stmnt = null;
	  	objBase.stmnt = objBase.conn.createStatement();
    	objBase.stmnt.executeUpdate(query);
		objBase.stmnt.close();
		objBase.conn.commit();
  }
    
    
    public void ClearDB() throws SQLException {
    	
    	objBase.strQuery = "DELETE FROM IMDB_Top250";
		Connect();
		execSql(objBase.strQuery);
		System.out.println("Old Database Records are cleaned");
    }
    
}