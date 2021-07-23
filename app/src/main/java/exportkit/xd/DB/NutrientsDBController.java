package exportkit.xd.DB;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.InputStream;
import java.util.ArrayList;

import exportkit.xd.DB.Constants.RecipeNutrientsTableConstants;
import exportkit.xd.Model.NutrientsFactsRecord;
import exportkit.xd.View.Recipe.Ingredient;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class NutrientsDBController {

    Context context;
    public RecipeNutrientsTableConstants columns= new RecipeNutrientsTableConstants();

    public NutrientsDBController(Context cntx) {
        this.context= cntx;
    }

    public ArrayList<NutrientsFactsRecord> getNutrientsInfo(ArrayList<Ingredient> ingredients)
    {
        ArrayList<NutrientsFactsRecord> list= new ArrayList<>();

        try{
            //open excel file and read it
            AssetManager assetManager= context.getAssets();
            InputStream inputStreams= assetManager.open("NutritionFacts.xls");
            Workbook workbook= Workbook.getWorkbook(inputStreams);
            Sheet sheet= workbook.getSheet(0);

            //get header titles-> first row in sheet
            Cell[] headerRow= sheet.getRow(0);

            //loop on ingredients List
            for(int k=0; k<ingredients.size(); k++) { //loop on my ingredients list
                //convert all letters to lowercase and remove all spaces -> excel format
                String name= (ingredients.get(k).name).toLowerCase().replaceAll("\\s","");
                Cell cellFood = sheet.findCell(name); //search on ingredient
                int row = cellFood.getRow(); //get number of row
                Cell[] factsRow = sheet.getRow(row); //read all columns
                list.add(storeFacts(headerRow, factsRow)); //create instance of NutrientsFactsRecord
            }
        }
        catch (Exception e){ }

        return list;
    }

    private NutrientsFactsRecord storeFacts(Cell[] headerRow, Cell[] factsRow){
        NutrientsFactsRecord facts= new NutrientsFactsRecord();
        for(int i=0; i<headerRow.length; i++){
           if(!factsRow[i].getContents().equals("null")) {

               if (headerRow[i].getContents().trim().equals(columns.col_FoodName)) {
                   facts.setFoodName(factsRow[i].getContents().trim());

               } else if (headerRow[i].getContents().trim().equals(columns.col_Category)) {
                   facts.setCategory(factsRow[i].getContents().trim());

               } else if (headerRow[i].getContents().trim().equals(columns.col_Calories)) {
                   facts.setCalories(Double.parseDouble(factsRow[i].getContents().trim()));

               } else if (headerRow[i].getContents().trim().equals(columns.col_Fats)) {
                   facts.setFats(Double.parseDouble(factsRow[i].getContents().trim()));

               } else if (headerRow[i].getContents().trim().equals(columns.col_SaFats)) {
                   facts.setSaFats(Double.parseDouble(factsRow[i].getContents().trim()));

               } else if (headerRow[i].getContents().trim().equals(columns.col_Protein)) {
                   facts.setProtein(Double.parseDouble(factsRow[i].getContents().trim()));

               } else if (headerRow[i].getContents().trim().equals(columns.col_Carbs)) {
                   facts.setCarbs(Double.parseDouble(factsRow[i].getContents().trim()));

               } else if (headerRow[i].getContents().trim().equals(columns.col_Sugars)) {
                   facts.setSugars(Double.parseDouble(factsRow[i].getContents().trim()));

               } else if (headerRow[i].getContents().trim().equals(columns.col_Cholesterol)) {
                   facts.setCholesterol(Double.parseDouble(factsRow[i].getContents().trim()));

               } else if (headerRow[i].getContents().trim().equals(columns.col_Calcium)) {
                   facts.setCalcium(Double.parseDouble(factsRow[i].getContents().trim()));

               } else if (headerRow[i].getContents().trim().equals(columns.col_Vitamin_A)) {
                   facts.setVitamin_A(Double.parseDouble(factsRow[i].getContents().trim()));

               } else if (headerRow[i].getContents().trim().equals(columns.col_Vitamin_C)) {
                   facts.setVitamin_C(Double.parseDouble(factsRow[i].getContents().trim()));

               } else if (headerRow[i].getContents().trim().equals(columns.col_Vitamin_B6)) {
                   facts.setVitamin_B6(Double.parseDouble(factsRow[i].getContents().trim()));

               } else if (headerRow[i].getContents().trim().equals(columns.col_Vitamin_B12)) {
                   facts.setVitamin_B12(Double.parseDouble(factsRow[i].getContents().trim()));

               } else if (headerRow[i].getContents().trim().equals(columns.col_Vitamin_D)) {
                   facts.setVitamin_D(Double.parseDouble(factsRow[i].getContents().trim()));
               }
           }
        }

        return facts;
    }
}
