package ghistayumna.com.myapplication.Implement;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ghistayumna.com.myapplication.Dao.UserDao;
import ghistayumna.com.myapplication.Function.DatabaseHelper;
import ghistayumna.com.myapplication.Model.User.ModelUser;

public class IUserDao implements UserDao {
    private DatabaseHelper   databaseHelper;
    private Integer numBer;
    private String userId;
    public IUserDao(Context context){
        databaseHelper = new DatabaseHelper(context);
    }


    @Override
    public int login(ModelUser modelUser) {
        return 0;
    }

    @Override
    public String getUserEmail(String email) {

        return null;
    }

    @Override
    public String insertUser(ModelUser modelUser) {
        return null;
    }

    @Override
    public String getIdUser() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT count(*) from user",null);
        try{
            cursor.moveToFirst();
            numBer = cursor.getInt(0);
//           if(cursor.moveToFirst()){
//              do{
//                  numBer = cursor.getCount();
//              }while (cursor.moveToFirst());
//           }

        }catch (Exception e){
            Log.d("error", "Error while trying to get posts from database"+" : "+e.getMessage());
        }finally {
            if(cursor != null && !cursor.isClosed()){
                cursor.close();
                db.close();
            }
        }

        if(numBer >= 0 ){
            SimpleDateFormat dateFormat = new SimpleDateFormat("ddmmyyyy");
            Date date = new Date();
            dateFormat.format(date);
            userId=dateFormat.format(date)+Integer.toString(numBer);

        }else{
            userId="tidak ditemukan";
        }
        return userId;
    }


}
