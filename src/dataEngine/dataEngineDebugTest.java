package dataEngine;

import org.seleniumhq.jetty9.server.handler.ScopedHandler;

import static dataEngine.ExcelUtils. * ;

public class dataEngineDebugTest {
    /**
     * @param:
     *
     * @auther: luopeng
     *
     * @date: 17:10 2018/11/5
     *
     * @description : 关键字驱动 - 脚本调试文件
    */
    public static void main(String[] args) throws Exception {
        String excel_path = ".//src//dataEngine//dataEngine.xlsx";

        // 加载读取excel文件
        setExcelFile(excel_path, "TestSteps");

        for (int iRow = 1; iRow <= 9; iRow++) {

            String sActionKeyword = getCellData(iRow, 3);

            // 和excel文件中关键字进行对比
            if (sActionKeyword.equals("openBrowser")) {

                // 如果Excel文件中存在openBrowser的关键字就会调用openBrowser()方法，进行相关操作；下面其他关键字同理。
                ActionsKeywords.openBrowser();
            } else if (sActionKeyword.equals("openUrl")) {
                ActionsKeywords.openUrl();
            } else if (sActionKeyword.equals("input_KeyWord")) {
                ActionsKeywords.input(O.input_KeyBox_id);
            } else if (sActionKeyword.equals("click_search")) {
                ActionsKeywords.click(O.click_search_id);
            } else if (sActionKeyword.equals("closeBrowser")) {
                ActionsKeywords.closeBrowser();
            }

        }

    }

}