package escope.esprit.escope.User;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class UserHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "user.db";
    public static final String COMMA_SEP = ",";
    public static final String TEXT_TYPE = " TEXT";

    public static final String CREATE_USER_TABLE_SQL =
            "CREATE TABLE "+ UserContract.UserEntry.TABLE_NAME+" ("
            +UserContract.UserEntry._ID+" INTEGER PRIMARY KEY"+COMMA_SEP
            +UserContract.UserEntry.FIRSTNAME_COLUMN+TEXT_TYPE+COMMA_SEP
                    +UserContract.UserEntry.EMAIL_COLUMN+TEXT_TYPE+COMMA_SEP
                    +UserContract.UserEntry.ROLE_COLUMN+TEXT_TYPE+COMMA_SEP
                    +UserContract.UserEntry.BIRTHDATE_COLUMN+TEXT_TYPE+COMMA_SEP
                    +UserContract.UserEntry.IMAGE_COLUMN+TEXT_TYPE+")";

    public static final String DROP_CIGARETTE_TABLE_SQL =
            "DROP TABLE IF EXISTS "+UserContract.UserEntry.TABLE_NAME;

    public UserHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_CIGARETTE_TABLE_SQL);
        onCreate(sqLiteDatabase);
    }
}
