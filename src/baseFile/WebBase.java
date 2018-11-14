package baseFile;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import config.WebInfo;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;

public class WebBase extends WebInfo {

    // intput text
    public static void textPrint(String Text) {
        Reporter.log(getCurrentSystemTime() + "...:" + Text + " ");
        System.out.println(getCurrentSystemTime() + "...:" + Text + " ");
    }

    // get time
    public static String getCurrentSystemTime() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String time = dateFormat.format(now);
        return time;
    }

    public static int checkTime() {
        /**
         * @param: []
         *
         * @auther: luopeng
         *
         * @date: 17:39 2018/8/7
         *
         * @description : 输出当前 时间 整数- >几点
         */
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH");
        String time = dateFormat.format(now);
        return Integer.parseInt(time);
    }

    public static void sleep(int scend) throws InterruptedException {
        int time = scend * 1000;
        Thread.sleep(time);
        textPrint("wait for :" + scend + " scends");
    }

    //	public static void screenSnap(WebDriver Driver) {
    //		try {
    //			// ScreenShot(WebDriver dr, String dir)
    //			textPrint("already catch screen shot!");
    //			screenSnapF.ScreenShotA(Driver, "C:\\selenium\\testShotPic\\");
    //			if (!(new File("C:\\selenium\\testShotPic\\").isDirectory())) { // 判断是否存在该目录
    //				new File("C:\\selenium\\testShotPic\\").mkdir(); // 如果不存在则新建一个目录
    //			}
    //		} catch (Error e) {
    //			e.printStackTrace();
    //			// 杈撳嚭褰撳墠椤甸潰鐨則itle
    //			textPrint("Page title is: " + Driver.getTitle());
    //			// 鍏抽棴鎵�鏈墂ebDriver杩涚▼锛岄��鍑�
    //			Driver.quit();
    //		}
    //	}
    public static void snapshot(WebDriver Driver, TakesScreenshot drivername, String filename) {
        // this method will take screen shot ,require two parameters ,one is
        // driver name, another is file name
        String currentPath = System.getProperty("user.dir"); // get current work
        // folder
        File scrFile = drivername.getScreenshotAs(OutputType.FILE);

        // Now you can do whatever you need to do with it, for example copy
        // somewhere
        try {
            textPrint("save snapshot path is:" + currentPath + "/" + filename);
            FileUtils.copyFile(scrFile, new File(currentPath + "\\screen\\" + filename));
        } catch (IOException e) {
            textPrint("Can‘t save screenshot");
            e.printStackTrace();
        } finally {
            textPrint("screen shot finished, it‘s in " + currentPath + " folder");
        }
    }

    public static void screenSnap(WebDriver Driver) {
        snapshot(Driver, (TakesScreenshot) Driver, WebBase.getCurrentSystemTime() + ".png");
    }

    public void checkPageName() throws InterruptedException {
        WebBase.textPrint("点击文稿!!");
        ;
        inputTime();
    }

    // public void useLogin(String loginPage, String useName, String PASSW,
    // WebDriver Driver) throws InterruptedException {
    // try {
    // Driver.get(loginPage);
    // WebBase.textPrint("打开--> " + loginPage);
    // WebBase.sleep(2);

    // // Page 1
    // handleA();
    // // waitForTitle();
    // clickId("userid");
    // WebBase.sleep(2);
    //
    // clearWordId("userid");
    // WebBase.sleep(2);
    //
    // InPutWordId("userid", useName);
    // WebBase.sleep(2);
    //
    // clearWordId("password");
    // WebBase.sleep(2);
    //
    // InPutWordId("password", PASSW);
    // WebBase.sleep(2);
    // WebBase.textPrint("---->please input the vroteWord!<----");
    // WebBase.sleep(2);
    // inputTime();
    // //验证码输入
    // // Driver.findElement(By.id("veryCode")).click();
    // // Driver.findElement(By.id("veryCode")).clear();
    // // Thread.sleep(2000);
    // // Driver.findElement(By.id("veryCode")).sendKeys("4474");
    // clickId("goLogin");
    // WebBase.textPrint("登录账号");
    // WebBase.sleep(6);
    // } catch(Error e) {
    // e.printStackTrace();
    // WebBase.screenSnap(Driver);
    // WebBase.textPrint("Use-> " + useName + " Login -->Fail!");
    // Driver.quit();
    // }
    // }

    public static void checkForTitle(WebDriver Driver, String Title) throws InterruptedException {
        if (Title == null)
            try {
                assertEquals(Title, Driver.getTitle());
                WebBase.textPrint("search Title-->" + Title + "  :sucesses");
                WebBase.sleep(2);

            } catch (Error e) {
                e.printStackTrace();
                WebBase.screenSnap(Driver);
            }
    }

    public void checkForIdAtt(WebDriver Driver, String Name, String Id, String V) throws InterruptedException {
		/*
		if (Name == null && Id == null) {
			WebBase.textPrint("参数为null ");
		} else {
			WebBase.textPrint("字符参数为： " + Name);
			WebBase.textPrint("Id参数为： " + Id);
			WebBase.textPrint("数组参数为: " + V);
		}
		*/
        try {
            assertEquals(Name, Driver.findElement(By.id(Id)).getAttribute(V));
            WebBase.sleep(2);
        } catch (NotFoundException e) {
            e.printStackTrace();
            WebBase.screenSnap(Driver);
            WebBase.textPrint("search textName-> " + Name + "  :Fail!");
            WebBase.textPrint("search IdName-> " + Id + "  :Fail!");
            WebBase.textPrint("search Attribute-> " + V + "  :Fail!");
            Driver.quit();
        }
    }

    // 根据ID验证元素存在
    public void checkForId(WebDriver Driver, String Name, String Id) throws InterruptedException {
		/*
		if (Name == null && Id == null) {
			WebBase.textPrint("参数为null");
		} else {
			WebBase.textPrint("字符参数为： " + Name);
			WebBase.textPrint("Id参数为： " + Id);
		}
		*/
        try {
            assertEquals(Name, Driver.findElement(By.id(Id)).getText());
            WebBase.sleep(2);

        } catch (NotFoundException e) {
            e.printStackTrace();
            WebBase.screenSnap(Driver);
        }
    }

    // 验证CSS定位元素
    public void checkForCSS(WebDriver Driver, String TextName, String CssButtonName) throws InterruptedException {
		/*
		if (TextName == null && CssButtonName == null) {
			WebBase.textPrint("参数为null");
		} else {
			WebBase.textPrint("ID参数为： " + TextName);
			WebBase.textPrint("定位参数为： " + CssButtonName);
		}
		*/
        try {
            assertEquals(TextName, Driver.findElement(By.cssSelector(CssButtonName)).getText());
            WebBase.textPrint("search Css-->  " + TextName + "  :sucesses!");
            WebBase.sleep(2);

        } catch (NotFoundException e) {
            e.printStackTrace();
            WebBase.screenSnap(Driver);

            WebBase.textPrint("search Css-> " + TextName + "  :Fail!");
            Driver.quit();
        }
    }

    // 验证linktext元素
    public void checkForLinkText(WebDriver Driver, String Linktext) throws InterruptedException {
		/*
		if (Linktext == null) {
			WebBase.textPrint("参数为null");
		} else {
			WebBase.textPrint("ID参数为： " + Linktext);
		}
		*/
        try {
            assertEquals(Linktext, Driver.findElement(By.linkText(Linktext)).getText());
            WebBase.textPrint("search LinkText-->  " + Linktext + "  :sucesses");
            WebBase.sleep(2);

        } catch (NotFoundException e) {
            e.printStackTrace();

            WebBase.textPrint("search LinkText-->  " + Linktext + "  :Fail!");
            Driver.quit();
        }
    }

    // 验证Xpath定位元素
    public static void checkForXpath(WebDriver Driver, String Name, String Xpath) throws InterruptedException {
		/*
		if (Name == null && Xpath == null) {
			WebBase.textPrint("参数为null");
		} else {
			WebBase.textPrint("ID参数为： " + Name);
			WebBase.textPrint("定位参数为： " + Xpath);
		}
		*/
        try {
            assertEquals(Driver.findElement(By.xpath(Xpath)).getText(), Name);
            WebBase.textPrint("search Xpath-->  " + Name + "  :sucesses!");
            WebBase.sleep(2);

        } catch (Error e) {
            e.printStackTrace();

            WebBase.textPrint("search Xpath-->  " + Name + "  :Fail!");
            Driver.quit();
        }
    }

    // 检查页面title
    public void checkTitle(WebDriver Driver, String title) throws InterruptedException {
        try {
            assertEquals(title, Driver.getTitle());
            WebBase.textPrint("search Title-->" + title + "  :sucesses");
            WebBase.sleep(2);

        } catch (NotFoundException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
            WebBase.screenSnap(Driver);

            WebBase.textPrint("search Title-->" + title + "  :Fail!");
            WebBase.textPrint("this Page title is: " + Driver.getTitle());
            Driver.quit();
        }
    }

    // 点击Id元素
    public static void clickId(WebDriver Driver, String idName) throws InterruptedException {
        try {
            Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            Driver.findElement(By.id(idName)).click();
            WebBase.textPrint("click Id button-->" + idName + "  :sucesses!");
            WebBase.sleep(2);

        } catch (Error e) {
            e.printStackTrace();
            WebBase.screenSnap(Driver);

            WebBase.textPrint("click Id button--> " + idName + "  :Fail!");
            Driver.quit();
        }
    }

    // click Css button
    public void clickCSS(WebDriver Driver, String Css) throws InterruptedException {
        if (Css == null) {
            WebBase.textPrint("参数为null");
        } else {
            WebBase.textPrint("参数为： " + Css);
        }
        try {
            Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            Driver.findElement(By.cssSelector(Css)).click();
            WebBase.textPrint("click CssButton--> " + Css + "  :sucesses!");
            WebBase.sleep(2);

        } catch (Error e) {
            e.printStackTrace();
            WebBase.screenSnap(Driver);

            WebBase.textPrint("click CssButton--> " + Css + "  :Fail!");
            Driver.quit();
        }
    }

    public void clickTitle(WebDriver Driver, String Title) throws InterruptedException {

        try {
            Driver.findElement(By.name(Title)).click();
        } catch (Error e) {
            e.printStackTrace();
            WebBase.screenSnap(Driver);
        }
    }

    // click linktext
    public static void clickLinkText(WebDriver Driver, String text) throws InterruptedException {
        try {
            Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            WebElement contactLink = Driver.findElement(By.linkText(text));
            WebBase.textPrint("search linkText--> " + text + "  :sucesses!");
            contactLink.click();
            WebBase.textPrint("click linkText--> " + text + "  :sucesses!");
            WebBase.sleep(2);

        } catch (Error e) {
            e.printStackTrace();
            WebBase.screenSnap(Driver);

            WebBase.textPrint("click linkText--> " + text + "  :Fail!");
            Driver.quit();
        }
    }

    public void clickAbotLinkText(WebDriver Driver, String text) throws InterruptedException {
        try {
            Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            WebElement aboutLink = Driver.findElement(By.partialLinkText(text));
            aboutLink.click();
            WebBase.sleep(2);

        } catch (Error e) {
            e.printStackTrace();
            WebBase.screenSnap(Driver);

            WebBase.textPrint("click linkText--> " + text + "  :Fail!");
            Driver.quit();
        }
    }

    public static void clickXpath(WebDriver Driver, String Xpath) throws InterruptedException {
        try {
            Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            Driver.findElement(By.xpath(Xpath)).click();
            WebBase.textPrint("click Xpath--> " + Xpath + "  :sucesses!");
            WebBase.sleep(2);

        } catch (Error e) {
            e.printStackTrace();
            WebBase.screenSnap(Driver);

            WebBase.textPrint("click Xpath--> " + Xpath + "  :Fail!");
            Driver.quit();
        }
    }

    public static void clickName(WebDriver Driver, String Name) throws InterruptedException {
        try {
            Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            Driver.findElement(By.name(Name)).click();
            WebBase.textPrint("click Name--> " + Name + "  :sucesses!");
            WebBase.sleep(2);

        } catch (Error e) {
            e.printStackTrace();
            WebBase.screenSnap(Driver);

            WebBase.textPrint("click Name--> " + Name + "  :Fail!");
            Driver.quit();
        }
    }

    public static void clickClassName(WebDriver Driver, String ClassName) throws InterruptedException {
        try {
            Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            Driver.findElement(By.className(ClassName)).click();
            WebBase.textPrint("click ClassName--> " + ClassName + "  :success!");
            WebBase.sleep(2);

        } catch (Error e) {
            e.printStackTrace();
            WebBase.screenSnap(Driver);
        }
    }

    // clear inputWord
    public static void clearTextById(WebDriver Driver, String idName) throws InterruptedException {
        try {
            Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            Driver.findElement(By.id(idName)).clear();
            WebBase.textPrint(idName + " <--clear" + "  :sucesses!");
            WebBase.sleep(2);

        } catch (Error e) {
            e.printStackTrace();
            WebBase.screenSnap(Driver);

            WebBase.textPrint(idName + " <--clear" + "  :Fail!");
            Driver.quit();
        }
    }

    public static void clearTextByName(WebDriver Driver, String Name) throws InterruptedException {
        try {
            Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            Driver.findElement(By.name(Name)).clear();
            WebBase.textPrint(Name + " <--clear" + "  :sucesses!");
            WebBase.sleep(2);

        } catch (Error e) {
            e.printStackTrace();
            WebBase.screenSnap(Driver);

            WebBase.textPrint(Name + " <--clear" + "  :Fail!");
            Driver.quit();
        }
    }

    public static void clearWordCss(WebDriver Driver, String CSS) throws InterruptedException {
        try {
            Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            Driver.findElement(By.cssSelector(CSS)).clear();
            WebBase.textPrint(CSS + " <--clear" + "  :sucesses!");
            WebBase.sleep(2);

        } catch (Error e) {
            e.printStackTrace();
            WebBase.screenSnap(Driver);

            WebBase.textPrint(CSS + " <--clear" + "  :Fail!");
            Driver.quit();
        }
    }

    // 双击指定ID元素
    public void clickDoubleId(WebDriver Driver, String idName) throws InterruptedException {
        try {
            Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            Actions action = new Actions(Driver);
            action.doubleClick(Driver.findElement(By.id(idName)));
            WebBase.sleep(2);
            WebBase.textPrint("# Double click Id button-->" + idName + "  :sucesses!");
            WebBase.sleep(2);

        } catch (Error e) {
            e.printStackTrace();
            WebBase.screenSnap(Driver);

            WebBase.textPrint("# Double click Id button--> " + idName + "  :Fail!");
            Driver.quit();
        }
    }

    public static void waitForPage(WebDriver Driver) {
        /**
         * @param: [Driver]
         *
         * @auther: luopeng
         *
         * @date: 17:20 2018/8/8
         *
         * @description :  等待页面加载 -> 15秒超时
         */
        try {
            Driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        } catch (NotFoundException e) {
            e.printStackTrace();
            screenSnap(Driver);
        }
    }

    // 等待title页面
    public static void waitForTitle(WebDriver Driver, String text) throws InterruptedException {
        for (int second = 0; ; second++) {
            if (second >= 60) fail("timeout");

            try {
                if (text.equals(Driver.getTitle())) break;
                WebBase.textPrint("Page--> " + Driver.getTitle() + "  :loding sucesses!");
                WebBase.textPrint("等待了  :" + second + "  秒!");
                WebBase.sleep(2);

            } catch (NotFoundException e) {
                Thread.sleep(1000);
                e.printStackTrace();
                WebBase.screenSnap(Driver);

                WebBase.textPrint("Page--> " + Driver.getTitle() + "  :loding error");
                Driver.quit();
            }
        }
    }

    // wait css button exits
    public void waitForCss(WebDriver Driver, String text, String Css) throws InterruptedException {
        for (int second = 0; ; second++) {
            if (second >= 60) fail("timeout");
            try {
                WebBase.textPrint("等待了  :" + second + "  秒!");
                if ("text".equals(Driver.findElement(By.cssSelector(Css)).getText())) break;
                WebBase.sleep(2);

            } catch (NotFoundException e) {
                Thread.sleep(1000);
                e.printStackTrace();
                WebBase.screenSnap(Driver);

                WebBase.textPrint("Page--> " + Driver.getTitle() + "  :loding error");
                Driver.quit();
            }
            Thread.sleep(1000);
            WebBase.textPrint("CssButton--> " + text + "  :cannot found!");
        }
    }

    public static void waitForXpath(WebDriver Driver, String Name, String Xpath) throws InterruptedException {
        for (int second = 0; ; second++) {
            if (second >= 60) fail("timeout");
            try {
                if (Name.equals(Driver.findElement(By.xpath(Xpath)).getText())) break;
                WebBase.sleep(2);

            } catch (NotFoundException e) {
                Thread.sleep(1000);
                e.printStackTrace();
                WebBase.screenSnap(Driver);

                WebBase.textPrint("Page--> " + Driver.getTitle() + "  :loding error");
                Driver.quit();
            }
            Thread.sleep(1000);
            WebBase.textPrint("XpathButton--> " + Name + "  :cannot found!");
        }
    }

    public void waitForId(WebDriver Driver, String Name, String Id) throws InterruptedException {
        for (int second = 0; ; second++) {
            if (second >= 60) fail("timeout");
            try {
                if (Name.equals(Driver.findElement(By.id(Id)).getText())) break;
                WebBase.sleep(2);

            } catch (NotFoundException e) {
                Thread.sleep(1000);
                e.printStackTrace();
                WebBase.screenSnap(Driver);

                WebBase.textPrint("Page--> " + Driver.getTitle() + "  :loding error");
                Driver.quit();
            }
            Thread.sleep(1000);
            WebBase.textPrint("CssButton--> " + Name + "  :cannot found!");
        }
    }

    public static void windowMaxSize(WebDriver Driver) throws InterruptedException {
        Driver.manage().window().maximize();
        WebBase.sleep(2);
        textPrint("browser window Size -> MAX");
    }

    // open new tab
    public void newTab(WebDriver Driver) throws InterruptedException {
        try {
            Actions OpenNewTab = new Actions(Driver);
            OpenNewTab.keyDown(Keys.CONTROL).sendKeys("t").keyUp(Keys.CONTROL).perform();
            WebBase.textPrint("#### Open New Tab Page! ####");
            WebBase.sleep(2);

        } catch (Error e) {
            e.printStackTrace();
            WebBase.screenSnap(Driver);

            WebBase.textPrint("*/*/* Open New Page is  :Fail! */*/*");
            Driver.quit();
        }
    }

    // 对Id元素输入字符
    public static void inPutById(WebDriver Driver, String idName, String Word) throws InterruptedException {

        try {
            Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            Driver.findElement(By.id(idName)).sendKeys(Word);
            WebBase.textPrint(idName + "  <--input text-->  " + Word + "  :sucesses!");
            WebBase.sleep(2);

        } catch (Error e) {
            e.printStackTrace();
            WebBase.screenSnap(Driver);

            WebBase.textPrint(idName + " <--input text--> " + Word + "  :Fail!");
            Driver.quit();
        }
    }

    // 对Xpath定位元素输入字符
    public static void inPutByXpath(WebDriver Driver, String Xpath, String Word) throws InterruptedException {

        try {
            Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            Driver.findElement(By.xpath(Xpath)).sendKeys(Word);
            WebBase.textPrint(Xpath + "  <--input text-->  " + Word + "  :sucesses!");
            WebBase.sleep(2);

        } catch (Error e) {
            e.printStackTrace();
            WebBase.screenSnap(Driver);

            WebBase.textPrint(Xpath + " <--input text--> " + Word + "  :Fail!");
            Driver.quit();
        }
    }

    // 对Css定位元素输入字符
    public void inputByCss(WebDriver Driver, String Css, String Word) throws InterruptedException {

        try {
            Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            Driver.findElement(By.cssSelector(Css)).sendKeys(Word);
            WebBase.textPrint(Css + "  <--input text-->  " + Word + "  :sucesses!");
            WebBase.sleep(2);

        } catch (Error e) {
            e.printStackTrace();
            WebBase.screenSnap(Driver);

            WebBase.textPrint(Css + " <--input text--> " + Word + "  :Fail!");
            Driver.quit();
        }
    }

    public static void inputByName(WebDriver Driver, String Name, String Word) throws InterruptedException {

        try {
            Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            Driver.findElement(By.name(Name)).sendKeys(Word);
            WebBase.textPrint(Name + "  <--input text-->  " + Word + "  :sucesses!");
            WebBase.sleep(2);

        } catch (Error e) {
            e.printStackTrace();
            WebBase.screenSnap(Driver);

            WebBase.textPrint(Name + " <--input text--> " + Word + "  :Fail!");
            Driver.quit();
        }
    }

    public static void sendKeyBoardAction(WebDriver Driver, String KeyAction) throws InterruptedException {
        /**
         * @param: [Driver, KeyAction]
         *
         * @auther: luopeng
         *
         * @date: 11:44 2018/8/10
         *
         * @description : 发送键盘事件
         */
        try {
            Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


            Actions action = new Actions(Driver);

            switch (KeyAction) {
                case "TAB":
                    action.sendKeys(Keys.TAB);
                    textPrint("send keyboard action --> TAB");
                    break;

                case "Enter":
                    action.sendKeys(Keys.ENTER);
                    textPrint("send keyboard action --> ENTER");
                    break;

                case "PageDown":
                    action.sendKeys(Keys.ARROW_DOWN);
                    textPrint("send keyboard action --> PageDown");
                    break;

                case "PageUp":
                    action.sendKeys(Keys.ARROW_UP);
                    textPrint("send keyboard action --> PageUp");
                    break;

                case "Ctrl":
                    action.sendKeys(Keys.CONTROL);
                    textPrint("send keyboard action --> Ctrl");
                    break;

                case "Space":
                    action.sendKeys(Keys.SPACE);
                    textPrint("send keyboard action --> Space");
                    break;

                case "Shift":
                    action.sendKeys(Keys.SHIFT);
                    textPrint("send keyboard action --> Shift");
                    break;
            }

            //释放
            action.sendKeys(Keys.NULL);

        } catch (Error e) {
            e.printStackTrace();
            screenSnap(Driver);
        }
    }

    public void inputTime() throws InterruptedException {
        WebBase.textPrint("you just have 5 second input votetext!");
        WebBase.sleep(2);
        WebBase.textPrint("only 5 s!");
        WebBase.sleep(1);
        WebBase.textPrint("only 4 s!");
        WebBase.sleep(1);
        WebBase.textPrint("only 3 s!");
        WebBase.sleep(1);
        WebBase.textPrint("only 2 s!");
        WebBase.sleep(1);
        WebBase.textPrint("only 1 s!");
        WebBase.sleep(1);
    }

    public static void handleA(WebDriver Driver) throws InterruptedException {
        String now_handle = Driver.getWindowHandle();
        WebBase.textPrint("now page title is -->" + Driver.getTitle() + "-->  handle A is : " + now_handle);
        WebBase.sleep(2);

    }

    public void handleB(WebDriver Driver) throws InterruptedException {
        String sec_handle = Driver.getWindowHandle();
        WebBase.textPrint("now page title is -->" + Driver.getTitle() + "-->  handle B is : " + sec_handle);
        WebBase.sleep(2);

    }

    protected void setTextOnUEditor(WebDriver Driver, String editorId, String text) {
        boolean isSucceed = false;
        try {
            String javascript = "UE.getEditor('" + editorId + "').setContent('" + text + "');";
            WebBase.sleep(2);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
            WebBase.screenSnap(Driver);

            WebBase.textPrint("switch handle-->  :Fail!");
            WebBase.textPrint("this Page title is: " + Driver.getTitle());
            Driver.quit();
        }
    }

    // 切换页面句柄
    public static void switchHandle(WebDriver Driver) throws InterruptedException {
        // 事实证明，newTab不需要切换handle也能在newPage 找到元素
        try {
            //获取当前页 句柄
            String orgContext = Driver.getWindowHandle();

            textPrint("now page handle is -->" + orgContext);

            //获取当前所有页面句柄,并循环判断是不是当前的句柄
            for (String context : Driver.getWindowHandles()) {
                if (context.equals(orgContext)) {
                    continue;//如果当前句柄与循环判断一致
                }
                Driver.switchTo().window(context);
            }
            WebBase.textPrint("switch handle-->  :success!");
            WebBase.textPrint("this Page title is: " + Driver.getTitle());
            WebBase.sleep(2);

        } catch (Error e) {
            e.printStackTrace();
            Assert.assertTrue(false);
            WebBase.screenSnap(Driver);

            WebBase.textPrint("switch handle-->  :Fail!");
            WebBase.textPrint("this Page title is: " + Driver.getTitle());
            Driver.quit();
        }
    }

    //根据 窗口 打开 顺序 切换窗口
    public static void switchHandleBy(WebDriver Driver, int i) throws InterruptedException {
        /**
         * @param: [Driver, handleNum]
         *
         * @auther: luopeng
         *
         * @date: 13:56 2018/9/13
         *
         * @description : 根据 窗口打开顺序的句柄 切换窗口
         */
        try {
            Set<String> AllHandles = Driver.getWindowHandles();
//            textPrint("now get all handles : " + AllHandles());

//            Driver.switchTo().window(AllHandles);
//            textPrint("switch handle to --> +" + AllHandles[0]);

        } catch (Error e) {
            e.printStackTrace();
            screenSnap(Driver);
        }
    }


    public void switchFrameById(WebDriver Driver, String frameId) throws InterruptedException {
        Driver.switchTo().frame(frameId);
        WebBase.textPrint("change frame By Id -->  " + frameId);
        WebBase.sleep(2);

    }

    public void switchFrameByindex(WebDriver Driver, int index) throws InterruptedException {
        Driver.switchTo().frame(index);
        WebBase.textPrint("change frame By Index -->  " + index);
        WebBase.sleep(2);

    }

    public void switchFrameByelement(WebDriver Driver, WebElement element) throws InterruptedException {
        Driver.switchTo().frame(element);
        WebBase.textPrint("change frame By element -->  " + element);
        WebBase.sleep(2);


    }
/*
    public static void switchFrameByXpath(WebDriver Driver, String Xpath) throws InterruptedException {
        try {
            Driver.switchTo().frame(Driver.findElement(By.xpath(Xpath)));
            WebBase.textPrint("change frame By Xpath -->  " + Xpath);
            WebBase.sleep(2);

        } catch (Error e) {
            WebBase.textPrint("chage frame By Xpath to -->  " + Xpath + "  --> is Fail !");
            e.printStackTrace();
            Assert.assertTrue(false);

            Driver.quit();
        }
    }
*/
    public void switchFrameForXpath(WebDriver Driver, String frameXpath) throws InterruptedException {
        try {
            WebElement frame = Driver.findElement(By.xpath(frameXpath));
            Driver.switchTo().frame(frame);
            WebBase.textPrint("Find Frame For Xpath And change to  ->" + frameXpath + "  -->is scuesses!");
            WebBase.sleep(2);

        } catch (Error e) {
            WebBase.textPrint("Find Frame For Xpath And change to  ->" + frameXpath + "  -->is Fial!");
            e.printStackTrace();
            Assert.assertTrue(false);

            Driver.quit();
        }
    }

    public static void switchToWindowByTitle(WebDriver Driver, String windowTitle) {

        Set<String> windowHandles = Driver.getWindowHandles();
        System.out.println("all handle is -->" + windowHandles);
        try {
            for (String handler : windowHandles) {
                Driver.switchTo().window(handler);
                System.out.println("switch to handle -->" + handler);
                String title = Driver.getTitle();
                System.out.println("switch window title -->" + title);
                WebBase.sleep(2);

                if (windowTitle.equals(title)) {
                    System.out.println("NO such window to switch");
                    break;
                    // or return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);

        }
    }

    public static void CloseWindowAndBack(WebDriver Driver) throws InterruptedException {

    }


    /*
        //使用相同方法 switchHandle()
        public static void switchToWindowByHandle(WebDriver Driver) {
            try {
                // 获取当前页面句柄
                String handle = Driver.getWindowHandle();
                // 获取所有页面的句柄，并循环判断不是当前的句柄
                for (String handles : Driver.getWindowHandles()) {
                    if (handles.equals(handle))
                        continue;
                    Driver.switchTo().window(handles);
                }
            } catch (Error e) {
                e.printStackTrace();
                screenSnap(Driver);
            }
        }
    */
    public static void selectById(WebDriver Driver, String Id, String Value) {

        try {
            Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            new Select(Driver.findElement(By.id(Id))).selectByVisibleText(Value);
            WebBase.textPrint(Id + "  <--Select Value-->  " + Value + "  :sucesses!");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            screenSnap(Driver);
            WebBase.textPrint(Id + "  <--Select Value-->  " + Value + "  :Fail!");

        }
    }

    public static void outOfFrame(WebDriver Driver) throws InterruptedException {
        Driver.switchTo().defaultContent();
        WebBase.textPrint("change frame By Default ");
        WebBase.sleep(2);

    }

    public void acceptPop(WebDriver Driver) throws InterruptedException {
        Driver.switchTo().alert().accept();
        WebBase.textPrint("accept Pop Window ");
        WebBase.sleep(2);

    }

    public static void launchAdress(WebDriver Driver, String Adress) {

        try {
            Driver.navigate().to(Adress);
            textPrint("Open Adress : " + Adress);
        } catch (Error e) {
            e.printStackTrace();
            screenSnap(Driver);
        }
    }


    public static void holdByXpath(WebDriver Driver, String Xpath) throws InterruptedException {
        /**
         * @param: [Driver, Xpath]
         *
         * @auther: luopeng
         *
         * @date: 11:47 2018/8/8
         *
         * @description : 在xpath定位的元素上悬停
         */
        try {

            Actions builder = new Actions(Driver);
            builder.moveToElement(Driver.findElement(By.xpath(Xpath))).perform();
            textPrint("hold on Xpath ->" + Xpath + " success!");
            Thread.sleep(1000);
        } catch (Error e) {
            e.printStackTrace();
            WebBase.screenSnap(Driver);
            textPrint("hold on Xpath ->" + Xpath + " Fail!");
        }
    }

    public static void holdByLinkText(WebDriver Driver, String LinkText) throws InterruptedException {

        try {

            Actions builder = new Actions(Driver);
            builder.moveToElement(Driver.findElement(By.linkText(LinkText))).perform();
            textPrint("hold on LinkText ->" + LinkText + " success!");

        } catch (Error e) {
            e.printStackTrace();
            screenSnap(Driver);
            textPrint("hold on LinkText ->" + LinkText + " Fail!");
        }
    }

    public static void holdByKeyAndWord(WebDriver Driver, String ActionType, String Key, String Word) {

        try {

        } catch (Error e) {
            e.printStackTrace();
            screenSnap(Driver);
        }
    }

    public static void methodLaunch(String MethodName) {
        System.out.println("method -->" + MethodName + " launch");
    }

    public static void methodEnd(String MethodName) {
        System.out.println("method ==>" + MethodName + "  end");
    }

    public static void getTextByXpath(WebDriver Driver, String XpathName) throws InterruptedException {
        /**
         * @param: [Driver, XpathName]
         *
         * @auther: luopeng
         *
         * @date: 16:48 2018/9/19
         *
         * @description : 获取 元素 信息 并打印
         */
        try {
            Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            String ElementText;

            ElementText = Driver.findElement(By.xpath(XpathName)).getText();

            textPrint("获取 Xpath元素 -->" + XpathName + " 的Text值为 --> :" + ElementText);
        } catch (Error e) {
            e.printStackTrace();
            screenSnap(Driver);
        }

    }

    public static void refreshPage(WebDriver Driver) throws InterruptedException {
        Driver.navigate().refresh();

        System.out.println("刷新页面");

        Thread.sleep(5000);

    }

}
