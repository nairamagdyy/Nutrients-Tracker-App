package exportkit.xd.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import exportkit.xd.DB.NutrientsDBController;
import exportkit.xd.View.Recipe.Item;

public class NutrientsController {
    NutrientsDBController db;

    public NutrientsController() {
        db= new NutrientsDBController();
    }

    public ArrayList<Item> calculateNutrients(ArrayList<Item> ingredients){
        ArrayList<Item> facts= new ArrayList<>();
        // initialize array
        Item item= new Item();
        item.amount=0;
        item.name="Calories"; facts.add(0, item);
        item.name="Protein"; facts.add(1, item);
        item.name="Fats"; facts.add(2, item);
        item.name="SatFats"; facts.add(3, item);
        item.name="Fiber"; facts.add(4, item);
        item.name="Carbs"; facts.add(5, item);

        //1. get nutrition info foreach ingredient
        HashMap<String, ArrayList<Item>> ingredientsInfo= db.getNutrientsInfo(ingredients);

        //2. loop on map and start to calculate based on amount
        for(Map.Entry<String, ArrayList<Item>> idx: ingredientsInfo.entrySet()){
            //1. get amount
            double amount= ingredients.get(ingredients.indexOf(idx.getKey())).amount;
            //2. read facts for each ingredient
            ArrayList<Item> info= idx.getValue();
            //3. based on amount and facts start to calculate
            double standardMeasure=0;
            for(int i=0; i<info.size(); i++){
                if(info.get(i).name.equals("Grams"))
                    standardMeasure= info.get(i).amount;
                else{
                    double temp= amount * info.get(i).amount / standardMeasure;
                    int index= facts.indexOf(info.get(i).name);
                    item= facts.get(index);
                    item.amount+= temp;
                    facts.set(index, item);
                }
            }
        }
        return facts;
    }
}
