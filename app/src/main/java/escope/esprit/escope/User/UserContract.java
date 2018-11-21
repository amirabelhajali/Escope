package escope.esprit.escope.User;

import android.provider.BaseColumns;


public final class UserContract {

    public UserContract() {
    }

    public static class UserEntry implements BaseColumns {
        public static final String FIRSTNAME_COLUMN = "firstName";
        public static final String LASTNAME_COLUMN = "lastName";
        public static final String EMAIL_COLUMN = "email";
        public static final String IMAGE_COLUMN = "image";
        public static final String ROLE_COLUMN = "role";
        public static final String BIRTHDATE_COLUMN = "birthDate";
        public static final String TABLE_NAME = "user";
    }

}
