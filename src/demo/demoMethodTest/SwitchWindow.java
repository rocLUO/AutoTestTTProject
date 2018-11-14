/*
 * WebTest -
 * author: luopeng
 * Date: 2018/9/29
 * Time: 11:09
 *
 * Description:
 */
package demo.demoMethodTest;

import baseFile.WebBase;
import config.WebInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;


public class SwitchWindow extends WebInfo {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        System.setProperty(Chrome, ChromePath);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test(description = "切换 窗口 操作(handle")
    public void swtchHandle() throws InterruptedException {

        try {
            //WebBase.launchAdress(driver, baseDataLink);

            WebBase.windowMaxSize(driver);

            //Account.loginAccount(driver, release_luo1_id, release_password, "骆1");

            //点击 工作列表 入口
            //WorkEdit.openEntrance(driver,3);

            String now = driver.getWindowHandle();
            System.out.println("当前" +now);

            Set<String> all = driver.getWindowHandles();
            System.out.println("所有" +all);

            WebBase.switchHandle(driver);

            String now2 = driver.getWindowHandle();
            System.out.println(now2);
            //WebBase.inPutByXpath(driver,AppList_Search_input_Xpath,"列表搜索");

            WebBase.getTextByXpath(driver,"//*[@id='u1']/a[1]");



        } catch (Error e) {
            Thread.sleep(1000);
            WebBase.screenSnap(driver);
        }
    }

    @AfterClass
    public void afterClass() {
        //driver.quit();
    }
}
