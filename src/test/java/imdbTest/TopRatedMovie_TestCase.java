/*
 * Author - RahulB
 * Date - 23rd March, 2020
 * Purpose - This file contains our main test case.
 */

package imdbTest;


import java.sql.SQLException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import basePackage.DBFunctions;
import basePackage.TestBase;
import pageObjects.TitlePageImdb;
import pageObjects.TopRatedPageImdb;

@Test
public class TopRatedMovie_TestCase {

	// Class Variables
	TestBase objBase = new TestBase();
	TitlePageImdb objTitlePage = new TitlePageImdb(TestBase.driver);
	TopRatedPageImdb objTopRated = new TopRatedPageImdb(TestBase.driver);
	DBFunctions objDBConnect = new DBFunctions();

	@BeforeTest
	public void BasicDBFunctions() throws SQLException {

		System.out.println("\n");
		System.err.println("**************** Test Case Started **************");
		System.out.println("\n");

		objDBConnect.ClearDB();
	}

	// Open the website IMDB
	@Test
	public void OpenBrowser() {

		objBase.OpenIMDB();
	}

	// Navigate to Top Rated Page
	@Test(dependsOnMethods = { "OpenBrowser" })
	public void NavigateToTopRated() throws InterruptedException {

		objTitlePage.navigateToTop250();

	}

	// Get Top 250 Rated Movies and Store in DB
	@Test(dependsOnMethods = { "OpenBrowser", "NavigateToTopRated" })
	public void FetchData() throws SQLException {

		objTopRated.Get250TopMovieList();

	}

	//Create the Excel file of Top Rated Movies from DB
	@Test(dependsOnMethods = { "OpenBrowser", "NavigateToTopRated", "FetchData" })
	public void PrintResultInExcel() throws SQLException {

		objTopRated.printTop250inExcel();

		System.out.println("\n");
		System.err.println("**************** Test Case Ended **************");
		System.out.println("\n");
	}

}
