package ghistayumna.com.myapplication.Dao;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.Editable;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import ghistayumna.com.myapplication.Model.User.ModelUser;

/**
 * Created by 0414831216 on 4/7/2018.
 */

public interface UserDao {

   public int login(ModelUser modelUser);
   public String getUserEmail(String email);
   public String insertUser(ModelUser modelUser);

//    public UserDao(View view, Context context, ProgressDialog progressDialog){
//        this.view = view;
//        this.context = context;
//        this.progressDialog = progressDialog;
//
//    }
//
//    public boolean validate(ModelUser modelUser){
//        valid=true;
//        if(modelUser.getEmail().length()==0 ||
//                   !android.util.Patterns.EMAIL_ADDRESS.matcher(modelUser.getEmail()).matches()){
//               userEmail.setError("Email harus diisi or format email salah");
//               valid=false;
//           }
//
//           if(modelUser.getPassWord().length()==0 ||
//                   modelUser.getPassWord().length() < 4 || modelUser.getPassWord().length() > 10 ){
//               userPassword.setError("between 4 and 10 alphanumeric characters");
//               valid=false;
//           }
//
//        progressDialog.dismiss();
//        return valid;
//    }

}
