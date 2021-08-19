package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import utils.ResourceUtil;
import org.testng.Assert;


public class GithubMain {

    WebDriver driver;
    By searchTextBox=By.xpath("//input[@placeholder='Search GitHub']");
    By advancedSearch=By.xpath("//a[@href='/search/advanced?q=react&type=Repositories']");
    By languageDropdown=By.id("search_language");
    By withStarsInput=By.id("search_stars");
    By withFollowersInput=By.id("search_followers");
    By licenseDropdown =By.id("search_license");
    By advancedSearchButton=By.xpath("//button[text()='Search'][1]");
    By repositoryCountText=By.xpath("//div[contains(@class,'codesearch-results')]/div/div/h3");
    By repoListText=By.xpath("//ul[@class]//a[text()='mvoloskov/decider']");
    By readMeFileContextContainer=By.xpath("//div[@data-target='readme-toc.content']");
    public void start(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().window().maximize();
    }

    public void quit(){
        driver.quit();
    }

    public void openMainPage(){
        driver.navigate().to(ResourceUtil.properties().getProperty("appurl"));
    }
    public void searchText(){
        driver.findElement(searchTextBox).sendKeys(ResourceUtil.properties().getProperty("searchText"));
        driver.findElement(searchTextBox).sendKeys(Keys.ENTER);
    }
    public void advancedSearch(){
        driver.findElement(advancedSearch).click();
        new Select(driver.findElement(languageDropdown)).selectByVisibleText(ResourceUtil.properties().getProperty("language"));
        driver.findElement(withStarsInput).sendKeys(ResourceUtil.properties().getProperty("stars"));
        driver.findElement(withFollowersInput).sendKeys(ResourceUtil.properties().getProperty("followers"));
        new Select(driver.findElement(licenseDropdown)).selectByVisibleText(ResourceUtil.properties().getProperty("license"));
        driver.findElement(advancedSearchButton).click();
    }

    public boolean verifyAdvancedSearchResults(){
        try {
            Assert.assertEquals(driver.findElement(repositoryCountText).getText(), ResourceUtil.properties().getProperty("verificationText"));
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean verifyAdvancedSearchRepo(){
        try {
            Assert.assertTrue(driver.findElement(repoListText).isDisplayed());
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    public void navigateToRepo(){
        driver.findElement(repoListText).click();
    }
    public void printReadMeFile(){
        String readmeText=driver.findElement(readMeFileContextContainer).getText();
        System.out.println(readmeText.substring(0,300));
    }

}
