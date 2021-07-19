package exportkit.xd.DB;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import exportkit.xd.View.Recipe.Item;

public class NutrientsDBController {

    static String SheetPath= "./Files/NutritionFacts.csv";
    private static Row row ;

    public static HashMap<String, ArrayList<Item>> getNutrientsInfo(ArrayList<Item> ingredients)
    {
        HashMap<String, ArrayList<Item>> Map = new HashMap<>();
        ArrayList<Item> Facts = new ArrayList<>();

        try {
            FileInputStream fileInput = new FileInputStream(SheetPath);
            Workbook workbook = new XSSFWorkbook(fileInput);
            Sheet sheet = workbook.getSheetAt(0);

            row = sheet.getRow(0);
            int numberOfColumns = row.getLastCellNum();
            int numberOfRows = sheet.getLastRowNum() ; // number of rows in excel sheet

            for(int k=0; k<ingredients.size(); k++) { //loop on my ingredients list
                for (int i=0; i<numberOfRows; i++) { //loop on excel rows
                    row = sheet.getRow(i);

                    String ingredientName = row.getCell(0).getStringCellValue();
                    if(ingredients.get(k).name.equals(ingredientName)){ // if this row = curr ingredient

                        for(int j=1; j<numberOfColumns; j++) { //get nutrients information
                            Item fact = new Item();
                            fact.name = CellReference.convertNumToColString(i);
                            fact.amount = Double.parseDouble(row.getCell(j).getStringCellValue().trim());
                            Facts.add(fact);
                        }

                        Map.put(ingredientName, Facts);
                        break;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Map ;
    }



}
