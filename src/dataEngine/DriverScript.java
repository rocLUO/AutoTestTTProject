package dataEngine;

import com.sun.org.apache.xml.internal.utils.Constants;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import config.WebInfo;
import dataEngine.ActionsKeywords;
import dataEngine.ExcelUtils;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;

public class DriverScript extends WebInfo {
    /**
     * @param:
     * @auther: luopeng
     * @date: 17:09 2018/11/5
     * @description : 使用Java反射类 -关键字 驱动 运行文件
     */

        // 声明一个public static的类对象，所以我们可以在main方法范围之外去使用。
        public static ActionsKeywords actionsKeywords;
        public static String sActionKeyword;
        // 下面是返回类型是方法，这里用到反射类
        public static Method method[];
        // 新建一个Properties对象
        public static Properties OR;
        public static String sPageObject;

        // 这里我们初始化'ActionsKeywords'类的一个对象
        public DriverScript() throws NoSuchMethodException, SecurityException{
            actionsKeywords = new ActionsKeywords();
            method = actionsKeywords.getClass().getMethods();
        }

        public static void main(String[] args) throws Exception {
            // 初始化一下DriverScript类
            DriverScript ds = new DriverScript();
            String excel_path = ExcelPath;
            // 申明一个对象仓库文件字符串的对象
            String Path_OR = O_Path;
            // 创建一个文件输入流对象
            FileInputStream fs = new FileInputStream(Path_OR);
            // 创建一个Properties对象
            OR = new Properties(System.getProperties());
            // 加载全部对象仓库文件
            OR.load(fs);

            // 加载读取excel文件
            ExcelUtils.setExcelFile(excel_path, "TestStep2");

            for (int iRow = 1; iRow <= 9; iRow++) {
                //3表示excel中keyword对应的列的索引，也就是左边数起第4列
                sActionKeyword = ExcelUtils.getCellData(iRow, 3);
                // 获取对应excel页面对象的名称，返回是一个string对象
                sPageObject = ExcelUtils.getCellData(iRow, 4);
                //调用'execute_Actions'方法
                execute_Actions();
            }
        }

        private static void execute_Actions() throws Exception {
            //循环遍历每一个关键字驱动方法（在actionskeywords.java中）
            //method variable contain all the method and method.length returns the total number of methods
            // 下面methid.length表示方法个数，method变量表示任何一个关键字方法，例如openBrowser()
            for(int i = 0;i < method.length;i++){
                //开始对比代码中关键字方法名称和excel中关键字这列值是否匹配
                if(method[i].getName().equals(sActionKeyword)){
                    //一点匹配到关键字，并传递页面对象参数和动作关键字参数
                    method[i].invoke(actionsKeywords,sPageObject);
                    //一旦任何关键字被执行，利用break语句去跳出for循环。
                    break;
                }
            }
        }

    }