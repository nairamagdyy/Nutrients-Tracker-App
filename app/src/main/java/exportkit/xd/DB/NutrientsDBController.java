package exportkit.xd.DB;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class NutrientsDBController {
    String Path  = "C:\\Users\\ALAA\\OneDrive\\Documents\\GitHub\\Graduation__Project\\nutrients_csvfile.csv" ;
    String line = "" ;
    private static Row row ;

    public static HashMap<String, ArrayList<String>> getMapData(String SheetPath)
    {
        HashMap<String, ArrayList<String>> Map = new HashMap<String,ArrayList<String>>();
        ArrayList<String> Facts = new ArrayList<String>();
        try {
            FileInputStream fileInput = new FileInputStream(SheetPath) ;
            Workbook workbook = new XSSFWorkbook(fileInput)  ;
            Sheet sheet = workbook.getSheetAt(0) ;
            row = sheet.getRow(0);
            int numberofColumns = row.getLastCellNum();
            int numberofRows = sheet.getLastRowNum() ; // number of rows in excel sheet
            for (int i = 0 ; i < numberofRows ; i++ )
            {
                row = sheet.getRow(i) ;
                String IngredientName  = row.getCell(0).getStringCellValue() ;
                for (int j = 1 ; j <numberofColumns ; j++)
                {
                    String facts= row.getCell(j).getStringCellValue();
                    Facts.add(facts) ;

                }

                Map.put(IngredientName,Facts) ;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Map ;

    }



}
