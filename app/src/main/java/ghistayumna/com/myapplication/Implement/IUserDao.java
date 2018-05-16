package ghistayumna.com.myapplication.Implement;

import android.content.ContentValues;
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
import ghistayumna.com.myapplication.Function.FinalConstant;
public class IUserDao implements UserDao {
    private DatabaseHelper   databaseHelper;
    private Integer numBer;
    private Integer userLogin;
    private String userId;
    private String result;
    private String userMail;
    public IUserDao(Context context){
        databaseHelper = new DatabaseHelper(context);
    }


    @Override
    public int login(ModelUser modelUser) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("select count(*) from user " +
                "where email="+"'"+modelUser.getEmail()+"'"+" " +
                "and password="+"'"+modelUser.getPassWord()+"'",null);
        try{
            cursor.moveToFirst();
            userLogin = cursor.getInt(0);
        }catch (Exception e){
            e.getMessage();
        }finally {
            db.close();
            cursor.close();
        }

        if(userLogin==FinalConstant.login_error){
            return userLogin;
        }else{
            return userLogin=FinalConstant.login_succes;
        }

    }

    @Override
    public String getUserEmail(String email) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("select email from user " +
                "where email="+"'"+email+"'",null);
        try{
            if(cursor.moveToFirst()){
                userMail=cursor.getString(cursor.getColumnIndex("email"));
            }
        }catch (Exception e){
            e.getMessage();
        }finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
                db.close();
            }
        }
        return userMail;
    }

    @Override
    public String insertUser(ModelUser modelUser) {
        result="succes";
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();

            contentValues.put("name",modelUser.getName());
            contentValues.put("email",modelUser.getEmail());
            contentValues.put("password",modelUser.getPassWord());
            contentValues.put("userid",modelUser.getUserId());
            contentValues.put("phone_number",modelUser.getMobilePhone());

            db.insertOrThrow("user",null,contentValues);
            db.setTransactionSuccessful();
        }catch (Exception e){
            result=e.getMessage();
            Log.d("error ",result);
        } finally{
            db.endTransaction();
            db.close();
        }

        return result;
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
            userId=dateFormat.format(date)+Integer.toString(numBer+1);

        }else{
            userId="tidak ditemukan";
        }
        return userId;
    }


}
