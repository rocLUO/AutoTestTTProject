package config;

public class AppInfo {


    /*------------------------------手机公共参数----------------------------------*/
    public static String Platform = "Android";
    public static String PhoneCode = "MKJNW17C20005057";
    public static String ApkPackage = "com.android.ayplatform.release";
    public static String ApkActivity = "com.android.ayplatform.activity.WelcomeActivity";
    public static String Version = "8.0";

    //before start method act
    public static int waitTime = 5000;

    //test lunch environment
    public static String type = ".release";

    // login method use
    public static String Account = "admin_pretest";
    public static String Password = "11111111";

    // change account name
    public static String ForAccountName = "";
    public static String PhoneHost = "http://127.0.0.1:4723/wd/hub";

    public static final String ForceAction = "";
}
