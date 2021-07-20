package exportkit.xd.DB.Constatnts;

public class RecipeNutrientsTableConstants {
    //Table name
    public static final String DB_Table = "RecipeNutrientsFacts" ;
    //Table Columns names
    public static final String DB_col_ID = "ID" ;
    public static final String DB_col_CALORIES = "Calories" ;
    public static final String DB_col_PROTEIN = "Protein" ;
    public static final String DB_col_FATS = "Fats" ;
    public static final String DB_col_SatFATS = "SatFats" ;
    public static final String DB_col_FIBER = "Fiber" ;
    public static final String DB_col_CARBS = "Carbs" ;

    public String CREATE_TABLE = "CREATE TABLE "
            + DB_Table + "("
            + DB_col_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DB_col_CALORIES + " INTEGER, "
            + DB_col_PROTEIN + " INTEGER, "
            + DB_col_FATS + " INTEGER, "
            + DB_col_SatFATS + " INTEGER, "
            + DB_col_FIBER + " INTEGER, "
            + DB_col_CARBS + " INTEGER)";

    // drop table sql query
    public String DROP_TABLE = "DROP TABLE IF EXISTS " + DB_Table;
}
