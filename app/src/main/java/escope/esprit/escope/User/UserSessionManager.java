package escope.esprit.escope.User;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class UserSessionManager {
    // Shared Preferences reference
    SharedPreferences pref;

    // Editor reference for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREFER_NAME = "AndroidExamplePref";

    // All Shared Preferences Keys
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_FIRSTNAME = "firstname";
    public static final String KEY_LASTNAME = "lastname";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_ROLE= "role";
    public static final String KEY_USERTOKEN= "usertoken";
    public static final String KEY_BIRTHDATE = "birthdate";

    // Email address (make variable public to access from outside)

    // Constructor
    public UserSessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    //Create login session
    public void createUserLoginSession(String token,String firstname, String lastname,String email,String role,String birthdate){
        // Storing login value as TRUE
        editor.putBoolean(IS_USER_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_FIRSTNAME, firstname);
        editor.putString(KEY_LASTNAME, lastname);
        editor.putString(KEY_EMAIL, email);
      //  editor.putString(KEY_IMAGE, image);
        editor.putString(KEY_ROLE,role);
        editor.putString(KEY_USERTOKEN,token);

        // Storing email in pref
        editor.putString(KEY_BIRTHDATE, birthdate);

        // commit changes
        editor.commit();
    }

    /**
     * Check login method will check user login status
     * If false it will redirect user to login page
     * Else do anything
     * */
    public boolean checkLogin(){
        // Check login status
        if(!this.isUserLoggedIn()){

            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);

            // Closing all the Activities from stack
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);

            return true;
        }
        return false;
    }



    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){

        //Use hashmap to store user credentials
        HashMap<String, String> user = new HashMap<String, String>();

        // user name
        user.put(KEY_FIRSTNAME, pref.getString(KEY_FIRSTNAME, null));
        user.put(KEY_LASTNAME, pref.getString(KEY_LASTNAME, null));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(KEY_IMAGE, pref.getString(KEY_IMAGE, null));
        user.put(KEY_ROLE, pref.getString(KEY_ROLE, null));
        user.put(KEY_USERTOKEN, pref.getString(KEY_USERTOKEN, null));

        // user email id
        user.put(KEY_BIRTHDATE, pref.getString(KEY_BIRTHDATE, null));

        // return user
        return user;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){

        // Clearing all user data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Login Activity
        Intent i = new Intent(_context, LoginActivity.class);

        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }


    // Check for login
    public boolean isUserLoggedIn(){
        return pref.getBoolean(IS_USER_LOGIN, false);
    }
}
