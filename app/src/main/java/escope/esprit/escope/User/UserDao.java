package escope.esprit.escope.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;




public class UserDao {

    UserHelper mDbHelper;

    public UserDao(Context context) {
        mDbHelper = new UserHelper(context);
    }

    public long insertCigarette(User user){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(UserContract.UserEntry.FIRSTNAME_COLUMN, user.getFirstname());
        cv.put(UserContract.UserEntry.LASTNAME_COLUMN, user.getLastname());
        cv.put(UserContract.UserEntry.ROLE_COLUMN, user.getRole());
        cv.put(UserContract.UserEntry.EMAIL_COLUMN, user.getEmail());
        cv.put(UserContract.UserEntry.BIRTHDATE_COLUMN, user.getBirthdate());
        return  db.insert(UserContract.UserEntry.TABLE_NAME, null, cv);
    }

    public List<User> findAllCigarettes(){
        List<User> UserList = new ArrayList<>();
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.query(UserContract.UserEntry.TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            User user = new User();
            //ON RECUPERE L'INDEX DE LA COLONNNE SUIVANT SON NOM
            user.setFirstname(cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.FIRSTNAME_COLUMN)));
            user.setLastname(cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.LASTNAME_COLUMN)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.EMAIL_COLUMN)));
            UserList.add(user);
            cursor.moveToNext();
        }
        cursor.close();
        return UserList;
    }
}
