	/*
	 * Author - RahulB
	 * Date - 23rd March, 2020
	 * Purpose - This file consists of common functions and variables used throughout the project
	 */

package basePackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class TestBase {
	public static WebDriver driver = new FirefoxDriver();
	public Statement stmnt;
	public Connection conn;
	public ResultSet rs;
	
	public String strImdbURL = "https://www.imdb.com";
	public String strHomePageTitle = "IMDb: Ratings, Reviews, and Where to Watch the Best Movies & TV Shows";
	
	
	public String strSQLite = "org.sqlite.JDBC";
	public String strDBPath = "jdbc:sqlite:C:\\Selenium QA Automation\\UAGitRepos\\QAAutomationFrameworks\\Top250-movies-imdb\\Top250Movie.sqlite";
	public String strQuery;
	public String strFilePath = "C:\\Selenium QA Automation\\UAGitRepos\\QAAutomationFrameworks\\Top250-movies-imdb\\TopRatedMovieListIMDB.csv";

	
	
	
	public static boolean waitFor(int iSeconds) {
		try {
			Thread.sleep(iSeconds * 1000);
		} catch (Exception e) {
			System.out.println("Not able to Wait --- " + e.getMessage());
			return false;
		}
		return true;
	}
	
	public void OpenIMDB() {
		driver.manage().window().maximize();
		driver.get(strImdbURL);
		Assert.assertEquals(driver.getTitle(), strHomePageTitle);		
	}

}
