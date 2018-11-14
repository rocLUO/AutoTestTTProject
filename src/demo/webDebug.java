/*
 * WebTest
 * author: luopeng
 * Date:
 * Time:
 *
 * Description:
 */
package demo;

import baseFile.WebBase;
import config.WebInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;


public class webDebug extends WebInfo {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        System.setProperty(Chrome, ChromePath);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test(description = "debug file case")
    public void test() throws InterruptedException {
        try {

            //WebBase.launchAdress(driver, releaseLink);

            WebBase.windowMaxSize(driver);


        } catch (Error e) {
            e.printStackTrace();
            WebBase.screenSnap(driver);
        }
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}