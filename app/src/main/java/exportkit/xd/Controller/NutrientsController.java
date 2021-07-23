package exportkit.xd.Controller;

import android.content.Context;

import java.util.ArrayList;

import exportkit.xd.DB.NutrientsDBController;
import exportkit.xd.View.Recipe.Ingredient;

public class NutrientsController {
    NutrientsDBController db;

    public NutrientsController(Context cntx) {
        db= new NutrientsDBController(cntx);
    }

    public ArrayList<Ingredient> calculateNutrients(ArrayList<Ingredient> ingredients){
        ArrayList<Ingredient> facts= new ArrayList<>();

        // initialize array
        Ingredient itm= new Ingredient();
        itm.name="Calories"; itm.amount=0;  facts.add(0, itm);  itm= new Ingredient();
        itm.name="Protein";  itm.amount=0;  facts.add(1, itm);  itm= new Ingredient();
        itm.name="Fats";     itm.amount=0;  facts.add(2, itm);  itm= new Ingredient();
        itm.name="SatFats";  itm.amount=0;  facts.add(3, itm);  itm= new Ingredient();
        itm.name="Fiber";    itm.amount=0;  facts.add(4, itm);  itm= new Ingredient();
        itm.name="Carbs";    itm.amount=0;  facts.add(5, itm);

        db.getNutrientsInfo(ingredients);
/*
        //1. get nutrition info foreach ingredient
        HashMap<String, ArrayList<Ingredient>> ingredientsInfo= db.getNutrientsInfo(ingredients);

        //2. loop on map and start to calculate based on amount
        for(Map.Entry<String, ArrayList<Ingredient>> idx: ingredientsInfo.entrySet()){
            //1. get recipe amount for current ingredient
            double amount= ingredients.get(indexOf(ingredients, idx.getKey())).amount;
            //2. read facts for current ingredient
            ArrayList<Ingredient> info= idx.getValue();
            //3. based on amount and facts start to calculate
            for(int i=0; i<info.size(); i++){
                double temp= amount * info.get(i).amount / db.servingSize;
                int index= indexOf(facts, info.get(i).name); //which fact, carbs? fats? ans so on
                Ingredient item= facts.get(index);
                item.amount+= temp; //contain facts for whole recipe not foreach ingredient
                facts.set(index, item);
            }
        }
        /*
        System.out.println("--------------------------------------------------------");
        for(int i=0; i<facts.size(); i++)
            System.out.println(facts.get(i).name+" "+facts.get(i).amount);
        */

        return null;
    }

    private int indexOf(ArrayList<Ingredient> list, String name){
        int idx=-1;
        for (int i=0; i<list.size(); i++){
            if(list.get(i).name.equals(name)){
                idx=i;
                break;
            }
        }

        return idx;
    }
}