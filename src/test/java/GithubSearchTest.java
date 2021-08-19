import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.GithubMain;

public class GithubSearchTest {

    GithubMain githubMainPage=new GithubMain();
    static ExtentReports report;
    static ExtentTest test;

    @BeforeClass
    public void openBrowser(){
        report = new ExtentReports("GithubSearchTestResults.html");
        test = report.startTest("GithubSearchTest");
        githubMainPage.start();
    }

    @Test
    public  void  searchGithub(){
        githubMainPage.openMainPage();
        githubMainPage.searchText();
        githubMainPage.advancedSearch();
        if(githubMainPage.verifyAdvancedSearchResults())
                test.log(LogStatus.PASS,"Search result : found only '1 repository result'");
        else
         test.log(LogStatus.FAIL,"Search result : '1 repository result' not found");
        if(githubMainPage.verifyAdvancedSearchRepo())
            test.log(LogStatus.PASS,"Repo : 'mvoloskov/decider' found");
        else
            test.log(LogStatus.FAIL,"Repo: 'mvoloskov/decider' not found");
        githubMainPage.navigateToRepo();
        githubMainPage.printReadMeFile();
    }

    @AfterClass
    public void cleaner(){
        githubMainPage.quit();
        report.endTest(test);
        report.flush();
    }


}
