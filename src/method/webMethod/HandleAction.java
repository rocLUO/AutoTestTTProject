package method.webMethod;

import baseFile.WebBase;
import config.WebInfo;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class HandleAction extends WebInfo {

    public static void switchHandle(WebDriver Driver) throws InterruptedException {
        /**
         * @param: [Driver]
         *
         * @auther: luopeng
         *
         * @date: 14:53 2018/9/29
         *
         * @description : 通过切换 handle 来切换浏览器窗口
        */
        WebBase.methodLaunch("切换 handle");

        try {

            //获取当前 handle
            String now = Driver.getWindowHandle();
            System.out.println("当前" +now);

            //获取当前所有 handle
            Set<String> all = Driver.getWindowHandles();
            System.out.println("所有" +all);

            //切换至当前 页面 handle
            WebBase.switchHandle(Driver);

            //答应当前handle
            String now2 = Driver.getWindowHandle();
            System.out.println("当前" +now2);

            Thread.sleep(2000);
        } catch (Error e ) {
            e.printStackTrace();
            WebBase.screenSnap(Driver);
        }

        WebBase.methodEnd("切换 handle");
    }
}
