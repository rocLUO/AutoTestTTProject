package dataEngine;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {
    /**
     * @param:
     *做别的
     * @auther: luopeng
     *
     * @date: 17:09 2018/11/5
     *
     * @description : 关键字驱动 - 获取excel信息
    */


    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;

    // 设置Excel文件路径，方便读取到文件
    public static void setExcelFile(String Path, String SheetName) throws IOException {

        FileInputStream ExcelFile = new FileInputStream(Path);
        ExcelWBook = new XSSFWorkbook(ExcelFile);
        ExcelWSheet = ExcelWBook.getSheet(SheetName);
    }

    // 读取Excel文件单元格数据
    public static String getCellData(int RowNum, int ColNum) throws Exception {
        Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
        String CellData = Cell.getStringCellValue();
        return CellData;

    }
}