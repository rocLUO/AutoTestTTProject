package dataEngine;

import baseFile.WebBase;
import config.WebInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ActionsKeywords extends WebInfo{
    /**
     * @param:
     *
     * @auther: luopeng
     *
     * @date: 17:10 2018/11/5
     *
     * @description : 关键字驱动 - 关键字对应执行操作封装
    */

        public static WebDriver driver;

        /**
         * 以下方法，我们针对dataEngine.xlsx文件中的action_keyword这列的关键字都进行封装
         * 等关键字框架快设计完了，我们再来调整，读取配置文件去启动不同测试浏览器和测试地址
         * 这样就不会代码写死这两个参数。
         */
        public static void openBrowser() {

            // 这里，我们暂时都写死用chrome来进行自动化测试
            System.setProperty("webdriver.chrome.driver", ChromePath);
            driver = new ChromeDriver();
        }

        public static void openUrl() {
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.get("https://www.baidu.com");

        }

        public static void input(String ElementObeject) throws InterruptedException {
            WebBase.inPutByXpath(driver, ElementObeject, "selenium");
        }

        public static void click(String ElementObeject) throws InterruptedException {
            WebBase.clickXpath(driver,ElementObeject);
        }

        // 关闭浏览器并退出
        public static void closeBrowser() throws InterruptedException {
            driver.quit();
        }

    }
