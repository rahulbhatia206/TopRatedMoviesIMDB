//This file consists of all the variables/web elements used for Top 250 page of IMDB

package pageObjects;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import basePackage.DBFunctions;
import basePackage.TestBase;

public class TopRatedPageImdb {
	
	DBFunctions objDBConnect = new DBFunctions();
	TestBase objBase = new TestBase();
	
	//Initialize Top Rated Page objects
	public TopRatedPageImdb(WebDriver driver){
		PageFactory.initElements(driver, this);
	}

	public String movieName,movieYear,movieRating;
	public int moviecount;
	
	@FindBy(xpath = "//*[@id='main']/div")
	public WebElement moviesList;	
	
	public void Get250TopMovieList() throws SQLException {
		
		objDBConnect.Connect();
		System.out.println("Creating database ..... ");
		
	  	System.out.println("\n");
	  	System.out.println("IMDB Top Rated 250 Movies are listed below: ");
	  	System.out.println("\n");
		
		//Find required the child objects
	      for (moviecount = 0;moviecount< 250;moviecount++){
	    	  		    	   
	    	  	movieName = moviesList.findElements(By.xpath("//td[@class='titleColumn']//a")).get(moviecount).getText();
	    	  	
	    	  	If(movieName.contains("'"));{
	    	  		movieName = movieName.replaceAll("'", "''");
	    	  	}
	    	  	System.out.println("Name of Movie Ranked - "+(moviecount+1)+" is "+movieName);
	    	  
	    	  	//Find Movie Year and remove the brackets
	    	  	movieYear = moviesList.findElements(By.xpath("//span[@class='secondaryInfo']")).get(moviecount).getText();
	    	  	movieYear=movieYear.substring(1, 5);
	    	  	
	    	  	System.out.println("Year of Movie Ranked - "+(moviecount+1)+" is "+movieYear);
	    	  	
	    	  	//Find the Movie Ratings
	    	  	movieRating = moviesList.findElements(By.xpath("//td[@class='ratingColumn imdbRating']//strong")).get(moviecount).getText();
	    	  	System.out.println("Rating of Movie Ranked - "+(moviecount+1)+" is "+movieRating);
	    	  	
	    	  	
	    	  	//Update the SQLite database with the all the results
				objBase.strQuery = "INSERT INTO IMDB_Top250 (Sr_No,Movie_Name,Movie_Year,Movie_Rating) " +
		                             "VALUES ("+(moviecount+1)+",'"+movieName+"','"+movieYear+"','"+movieRating+"')"; 
	    		
				objDBConnect.execSql(objBase.strQuery);
				System.out.println("\n");	
		
	      }
	
	}

	private void If(boolean contains) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void printTop250inExcel() throws SQLException{
		
		try {
			System.out.println("Database Created Successfully");
			FileWriter objFilewriter = new FileWriter(objBase.strFilePath);
			BufferedWriter objBuffWriter = new BufferedWriter(objFilewriter);
			objDBConnect.Connect();
			objBase.conn = DriverManager.getConnection(objBase.strDBPath);
			objBase.stmnt = objBase.conn.createStatement();
			objBase.strQuery = "select * from IMDB_Top250";
			objBase.rs = objBase.stmnt.executeQuery(objBase.strQuery);
			ResultSetMetaData col = objBase.rs.getMetaData();
			objBuffWriter.write(col.getColumnName(1)+","+col.getColumnName(2)+","+col.getColumnName(3)+","+col.getColumnName(4));
			objBuffWriter.newLine();
			while (objBase.rs.next()){
				objBuffWriter.write(objBase.rs.getInt("Sr_No")+","+objBase.rs.getString("Movie_Name")+","+objBase.rs.getString("Movie_Year")+","+objBase.rs.getString("Movie_Rating"));
				objBuffWriter.newLine();
			}
			objBuffWriter.close();
			objBase.rs.close();
			objBase.stmnt.close();
			objBase.conn.close();
			System.out.println("File is created successfully and placed at "+objBase.strFilePath);
		} catch (IOException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    System.exit(0);
		}
	}
	
}

