package e2e;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData {
    public static void main(String[] args) throws Exception {
        // File path of the Excel
        String filePath = "/Users/kondurikiran/Desktop/userdata.xlsx";

        // Open the file
        FileInputStream fis = new FileInputStream(filePath);

        // Create Workbook instance
        Workbook workbook = new XSSFWorkbook(fis);

        // Get the first sheet
        Sheet sheet = workbook.getSheetAt(0);

        // Iterate through rows
        for (Row row : sheet) {
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case STRING:
                        System.out.print(cell.getStringCellValue() + "\t");
                        break;
                    case NUMERIC:
                        System.out.print(cell.getNumericCellValue() + "\t");
                        break;
                    case BOOLEAN:
                        System.out.print(cell.getBooleanCellValue() + "\t");
                        break;
                    default:
                        System.out.print(" \t");
                }
            }
            System.out.println(); // new line after each row
        }

        // Close workbook
        workbook.close();
        fis.close();
    }
}






