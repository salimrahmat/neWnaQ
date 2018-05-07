package ghistayumna.com.myapplication.Function;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "newnaq.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table user( id_user text primary_key, nama text, email text," +
                "mobile_phone text,alamat text, status integer)";
        Log.d("Jurig kuris", "onCreate: " + sql);
        db.execSQL(sql);

        String sql1="create table user_login(id_user text ," +
                "email text," +
                "password  text," +
                "last_login date)";
        db.execSQL(sql1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
               onCreate(db);
    }
}
