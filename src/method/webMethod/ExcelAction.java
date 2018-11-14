package method.webMethod;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ExcelAction {

    public static HSSFSheet ExcelSheet;
    public static HSSFWorkbook ExcelBook;
    public static HSSFRow Row;
    public static HSSFCell Cell;

    public static void setExcelFile(String Path, String SheetName) throws Exception {
        /**
         * @param: [Path, SheetName]
         *
         * @auther: luopeng
         *
         * @date: 16:41 2018/8/13
         *
         * @description : 加载 excel 文件
        */
        FileInputStream ExcelFile = new FileInputStream(Path);
        ExcelBook = new HSSFWorkbook(ExcelFile);
        ExcelSheet = ExcelBook.getSheet(SheetName);
    }

    public static void setCellData(String Result, int RowNum, int ColNum, String Path) throws Exception {
        /**
         * @param: [Result, RowNum, ColNum, Path]
         *
         * @auther: luopeng
         *
         * @date: 16:41 2018/8/13
         *
         * @description : 数据写入
        */
        Row = ExcelSheet.getRow(RowNum);
        Cell = Row.getCell(ColNum);
//        Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
        if (Cell == null){
            Cell = Row.createCell(ColNum);
            Cell.setCellValue(Result);
        } else {
            Cell.setCellValue(Result);
        }

        FileOutputStream fileOut = new FileOutputStream(Path);
        ExcelBook.write(fileOut);
        fileOut.flush();
        fileOut.close();

    }

    public static String getCellData(int RowNum, int CloNum) {
        /**
         * @param: [RowNum, CloNum]
         *
         * @auther: luopeng
         *
         * @date: 16:46 2018/8/13
         *
         * @description : 获取 对应单元格 数据
        */
        Cell = ExcelSheet.getRow(RowNum).getCell(CloNum);
        String cellData = Cell.getStringCellValue();
        return cellData;
    }

    public static int getLastRowNums(String SheetName) {
        /**
         * @param: [SheetName]
         *
         * @auther: luopeng
         *
         * @date: 16:46 2018/8/13
         *
         * @description : 获取直到sheet最后一行数据
        */
        try {
            ExcelSheet=ExcelBook.getSheet(SheetName);
            int rowCount = ExcelSheet.getLastRowNum();
            return rowCount;
        }catch (Exception e){
            throw(e);
        }
    }
}
