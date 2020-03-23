//This file consists of all the variables/web elements used for Home page of IMDB

package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import basePackage.TestBase;

public class TitlePageImdb {
	
	public String strTopRatedPageTitle = "IMDb Top 250 - IMDb";
	
	//Initialize objects in the Page
	public TitlePageImdb(WebDriver driver){
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@aria-label='Open Navigation Drawer'][2]//div")
	public WebElement MenuLink;
	
	@FindBy(xpath = "//a[@href='/chart/top/?ref_=nv_mv_250']")
	public WebElement TopRatedLink;
	
	public void navigateToTop250() {
		TestBase.waitFor(3);
		MenuLink.click();
		System.out.println("Menu Link is clicked !!!");
		TestBase.waitFor(3);
	    Actions action = new Actions(TestBase.driver);
	    action.moveToElement(TopRatedLink).build().perform();
		TopRatedLink.click();
		System.out.println("Top Rated Movies Link is clicked !!!");
		TestBase.waitFor(3);
		Assert.assertEquals(TestBase.driver.getTitle(), strTopRatedPageTitle);
	}

}
