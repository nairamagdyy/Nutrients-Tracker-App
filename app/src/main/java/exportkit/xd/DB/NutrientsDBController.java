package exportkit.xd.DB;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import exportkit.xd.View.Recipe.Item;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class NutrientsDBController {

    Context context;
    public NutrientsDBController(Context cntx) {
        this.context= cntx;
    }

    public HashMap<String, ArrayList<Item>> getNutrientsInfo(ArrayList<Item> ingredients)
    {
        HashMap<String, ArrayList<Item>> Map = new HashMap<>();

        try{
            AssetManager assetManager= context.getAssets();
            InputStream inputStreams= assetManager.open("NutritionFacts.xls");
            Workbook workbook= Workbook.getWorkbook(inputStreams);
            Sheet sheet= workbook.getSheet(0);
            int numberOfRows= sheet.getRows(),
                    numberOfColumns=sheet.getColumns();

            Cell[] headerRow= sheet.getRow(0);

            for(int k=0; k<ingredients.size(); k++) { //loop on my ingredients list
                for (int i=1; i<numberOfRows; i++) { //loop on excel rows, start with 1 ; row 0 has headers
                    Cell[] curRow = sheet.getRow(i);
                    String ingredientName = curRow[0].getContents().trim();
                    if(ingredients.get(k).name.equals(ingredientName)){ // if this row = curr ingredient
                        ArrayList<Item> Facts = new ArrayList<>();
                        for(int c=1; c<numberOfColumns; c++) { //get nutrients information
                            Item fact = new Item();
                            fact.name = headerRow[c].getContents().replaceAll("\\s","");//title
                            fact.amount = Double.parseDouble(curRow[c].getContents().replaceAll("\\s",""));
                            Facts.add(fact);
                        }

                        Map.put(ingredientName, Facts);
                        break;
                    }
                }
            }
        }
        catch (Exception e){ }


        return Map ;
    }
}
