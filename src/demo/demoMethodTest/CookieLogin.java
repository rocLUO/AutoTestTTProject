/*
 * WebTest - com.testcase
 * author: luopeng
 * Date: 2018 2018/8/13
 * Time: 14:33
 *
 * Description:
 */
package demo.demoMethodTest;

import baseFile.WebBase;
import config.WebInfo;
import method.webMethod.CookieAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.os.WindowsUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class CookieLogin extends WebInfo {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() throws IOException {
        System.setProperty(Chrome, ChromePath);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

//        CookieAction.saveCookie(driver);
    }


    @Test(description = "获取 登录 cookie")
    public void getLoginCookie() throws InterruptedException, IOException {
        try {

            //WindowsUtils.tryToKillByName("chrome.exe");
            WindowsUtils.getProgramFilesPath();

            WebBase.launchAdress(driver, "http://release.qycloud.com.cn");

            CookieAction.readCookieFile(driver);

            WebBase.windowMaxSize(driver);

        } catch (Error e) {
            Thread.sleep(1000);
            WebBase.screenSnap(driver);
        }

        WebBase.launchAdress(driver, "https://release.qycloud.com.cn/user/workbench");

    }

    @AfterClass
    public void afterClass() {
        //driver.quit();
    }
}
