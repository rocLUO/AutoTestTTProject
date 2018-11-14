package config;

public class WebInfo {

    //测试驱动配置
    public static String Chrome = "webdriver.chrome.driver";
    public static String FireFox = "webdriver.firefox.bin";

    //驱动路径配置
    public static String ChromePath = "E:\\dev soft\\chromedriver_win32 v243\\chromedriver.exe";
    public String FireFoxPath = "";

    //通用等待事件
    public static int waitTime = 6000;

    //关键字驱动excel 路径
    public static final String ExcelPath = ".//src//dataEngine//dataEngine.xlsx";
    public static final String SheetNameA = "TestSteps";
    public static int SearchLine = 3;


    // OR(对象仓库)文件路径
    public static final String O_Path =".//src//config/O.txt";


}
