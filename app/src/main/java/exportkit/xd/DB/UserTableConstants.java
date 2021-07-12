package exportkit.xd.DB;

public class UserTableConstants {
    // User table name
    public static final String DB_User_Table = "user" ;
    // User Table Columns names
    public static final String DB_col_ID = "id" ;
    public static final String DB_col_name = "fullname" ;
    public static final String DB_col_username = "username" ;
    public static final String DB_col_email = "email" ;
    public static final String DB_col_password = "password" ;
    public static final String DB_col_gender = "gender" ;
    public static final String DB_col_phonenumber = "phonenumber" ;

    // create table sql query
    public String CREATE_USER_TABLE = "CREATE TABLE " + DB_User_Table + "("
            + DB_col_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DB_col_name + " TEXT, " + DB_col_username + " TEXT UNIQUE, "
            + DB_col_email + " TEXT UNIQUE, " + DB_col_password + " TEXT, " + DB_col_gender + " TEXT, " + DB_col_phonenumber + " INT "  +")";
    // drop table sql query
    public String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + DB_User_Table;
}
