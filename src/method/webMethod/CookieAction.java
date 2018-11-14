package method.webMethod;


import baseFile.WebBase;
import config.WebInfo;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.util.Date;
import java.util.Set;
import java.util.StringTokenizer;

import static baseFile.WebBase.textPrint;


public class CookieAction extends WebInfo {

//    public static void main(String[] args) throws InterruptedException {
//
//        WebDriver dr = new ChromeDriver();
//        String url = "http://www.baidu.com";
//
//        System.out.printf("now accesss %s \n", url);
//        dr.get(url);
//
//        Thread.sleep(2000);
//
//        System.out.println(dr.manage().getCookies());
//
//        dr.manage().deleteAllCookies();
//
//        Cookie c1 = new Cookie("BAIDUID", "xxxxxxxxxx");
//        Cookie c2 = new Cookie("BDUSS", "xxxxxxxxxx");
//
//        dr.manage().addCookie(c1);
//        dr.manage().addCookie(c2);
//
//        System.out.println("browser will be close");
//        dr.quit();
//    }

    public static void getCookies(WebDriver Driver) {
        /**
         * @param: [Driver]
         *
         * @auther: luopeng
         *
         * @date: 17:00 2018/8/6
         *
         * @description : 获取 浏览器 cookies
         */
        WebBase.methodLaunch("获取cookie信息");
        try {

            Set<Cookie> info = Driver.manage().getCookies();
            textPrint("catch cookies info is sussess !");
            System.out.println(info);

        } catch (Error e) {
            e.printStackTrace();
            textPrint("catch cookies info is Fail !");
        }
        WebBase.methodEnd("获取cookie信息");
    }

    public static void addCookies(WebDriver Driver, Cookie cookie) {
        /**
         * @param: [Driver, cookie]
         *
         * @auther: luopeng
         *
         * @date: 17:01 2018/8/6
         *
         * @description :  添加特定 name 和 value 的 cookie
         */
        WebBase.methodLaunch("编辑获取cookie的name信息");
        try {

            Driver.manage().addCookie(cookie);
            textPrint("add cookies info is success !");
            System.out.println(cookie);
        } catch (Error e) {
            e.printStackTrace();
            textPrint("add cookies info is Fail !");
        }
        WebBase.methodEnd("编辑获取cookie的name信息");
    }

    public static void setCookies(WebDriver Driver, String Name, String Value) {
        /**
         * @param: [Driver, Name, Value]
         *
         * @auther: luopeng
         *
         * @date: 17:01 2018/8/6
         *
         * @description : 写入 特定 name 和 value 的 cookie
         */
        WebBase.methodLaunch("编辑获取cookie的value信息");
        try {

            Cookie c = new Cookie(Name, Value);

            addCookies(Driver, c);

        } catch (Error e) {
            e.printStackTrace();
        }
        WebBase.methodEnd("编辑获取cookie的value信息");
    }

    public static void deleteAllCookies(WebDriver Driver) {
        /**
         * @param: [Driver]
         *
         * @auther: luopeng
         *
         * @date: 17:09 2018/8/6
         *
         * @description : 删除所有 cookkies
         */
        WebBase.methodLaunch("删除所有获取cookie");
        try {
            Driver.manage().deleteAllCookies();
            textPrint("delete cookies info is success !");
        } catch (Error e) {
            e.printStackTrace();
        }
        WebBase.methodEnd("删除所有获取cookie");
    }

    public static void saveCookie(WebDriver Driver) throws IOException {
        /**
         * @param: [Driver]
         *
         * @auther: luopeng
         *
         * @date: 15:02 2018/8/13
         *
         * @description : 获取 cookie 并且 保存至本地 testCookie.data中
         */
        WebBase.methodLaunch("获取cookie并保存至本地");
        try {
            File file = new File("testCookie.data");

            file.delete();
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Cookie ck : Driver.manage().getCookies()) {
                bw.write(ck.getName() + ";" + ck.getValue() + ";"
                        + ck.getDomain() + ";" + ck.getPath() + ";"
                        + ck.getExpiry() + ";" + ck.isSecure());
                bw.newLine();
            }
            bw.flush();
            bw.close();
            fw.close();

        } catch (Error e) {
            e.printStackTrace();
        } finally {
            textPrint("cookie write to file -->testCookie.data");
        }
        WebBase.methodEnd("获取cookie并保存至本地");
    }

    public static void readCookieFile(WebDriver Driver) throws FileNotFoundException {
        /**
         * @param: [Driver]
         *
         * @auther: luopeng
         *
         * @date: 15:03 2018/8/13
         *
         * @description : 读取 本地 cookie 文件
         */
        WebBase.methodLaunch("读取本地 cookie");
        try {
            File file = new File("testCookie.data");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer str = new StringTokenizer(line, ";");
                while (str.hasMoreTokens()) {
                    String name = str.nextToken();
                    String value = str.nextToken();
                    String domain = str.nextToken();
                    String path = str.nextToken();
                    Date expiry = null;
                    String dt;
                    if (!(dt = str.nextToken()).equals(null)) {
                        //expiry=new Date(dt);
                        System.out.println();
                    }
                    boolean isSecure = new Boolean(str.nextToken()).booleanValue();
                    Cookie ck = new Cookie(name, value, domain, path, expiry, isSecure);
                    Driver.manage().addCookie(ck);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            textPrint("read local cookie File success ");
        }
        WebBase.methodEnd("读取本地 cookie");

    }
}
