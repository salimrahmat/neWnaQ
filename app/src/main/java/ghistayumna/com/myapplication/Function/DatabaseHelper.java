package ghistayumna.com.myapplication.Function;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "newnaq.db";
    private static final int DATABASE_VERSION = 1;

    private static final String USER = "user";
    private static final String KEY_USER_ID = "userid";
    private static final String KEY_NAME="name";
    private static final String KEY_EMAIL="email";
    private static final String KEY_MOBILE_PHONE="phone_number";
    private static final String KEY_PASSWORD="password";

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_USER = "CREATE TABLE "+USER+"("+
                KEY_USER_ID+"STRING PRIMARY KEY," +
                KEY_NAME+" TEXT," +
                KEY_EMAIL+" TEXT," +
                KEY_MOBILE_PHONE+" TEXT," +
                KEY_PASSWORD+" TEXT" +
        ")";
        db.execSQL(CREATE_TABLE_USER);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
               onCreate(db);
    }
}
