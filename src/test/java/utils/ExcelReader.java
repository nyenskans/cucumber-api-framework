package utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {

   // XSSFWorkbook for .xlsx file
    static Workbook book;
    static Sheet sheet;

    /**
     * This method opens the Excel file
     * @param filePath
     */
    public static void openExcel(String filePath){
        try {
            FileInputStream fis = new FileInputStream(filePath);
            book = new XSSFWorkbook(fis);
        } catch (FileNotFoundException e) { // --> for fileinputstream
            // to get exception if file is not available
            e.printStackTrace();
        } catch (IOException e) {
            // to get exception if input/output is not upro mark --> for xssfworkbook
        throw new RuntimeException(e);
        }
    }

    /**
     * This method gets the sheet which we are working with
     * @param sheetName
     */
    public static void getSheet(String sheetName){
        // This way, we identify the sheet we should read from excel file:
         sheet = book.getSheet(sheetName);
        // Now we read the data from the
    }


    /**
     * This method returns the count of rows which contain data
     * @return
     */
    public static int getRowCount(){
        return sheet.getPhysicalNumberOfRows();
    }


    /**
     * This method returns the count of columns which contain data
     * @return
     */
    public static int getColsCount(int rowIndex){
        // the only way to determine the num of columns is to go into each row and get the last cell
        // It will get the total number of cells in each row --> which is the number of columns
        return sheet.getRow(rowIndex).getPhysicalNumberOfCells();
    }


    /**
     * This method retrieves data from a specific cell (according to the coordinates-> row index, column index)
     * @param rowIndex
     * @param colIndex
     * @return
     */
    public static String getCellData(int rowIndex, int colIndex){
        return sheet.getRow(rowIndex).getCell(colIndex).toString();
    }


    /**
     * This method will read the complete data from an excel file and store it into a list of map
     * @param filePath
     * @param sheetName
     * @return
     */
    public static List<Map<String, String>> excelListIntoMap(String filePath, String sheetName){

        openExcel(filePath);

        getSheet(sheetName);

        List<Map<String, String>> listData = new ArrayList<>();

        // outer loop takes care of total rows in excel file:
        // i starts from 1 because 0 stores keys not values
        for(int row=1; row< getRowCount();row++){
            // create a map to store each row
            // we use LinkedHashMap for data synchronization (to get it in order one by one)
            Map<String, String> map = new LinkedHashMap<>();
            // inner loop takes care of columns in excel file - looping through all values of the cells
            for(int col = 0; col<getColsCount(row); col++){

                // now we add key-value pairs into the map from above using .put()
                map.put(getCellData(0,col), getCellData(row, col)); // this line gets header
                // getCellData(0,col) gives us keys
                // getCellData(row, col)) gives us values to the corresponding keys
            }
            // now we add the maps (key-value pairs) into the list
            listData.add(map);
        }
        return listData;
    }


}
