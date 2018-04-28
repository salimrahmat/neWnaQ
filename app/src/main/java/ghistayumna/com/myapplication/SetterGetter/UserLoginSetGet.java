package ghistayumna.com.myapplication.SetterGetter;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import ghistayumna.com.myapplication.Model.User.ModelUser;
import ghistayumna.com.myapplication.R;

/**
 * Created by 0414831216 on 4/8/2018.
 */

public class UserLoginSetGet {
    private final ProgressDialog progressDialog;
    private View view;
    private ViewGroup container;
    private LayoutInflater inflater;
    private Context context;
    private ModelUser modelUser;
    private boolean valid=true;

    private EditText userEmail,userPassword;

    public UserLoginSetGet(View view, Context context, ProgressDialog progressDialog
    ){
        this.view = view;
        this.context = context;
        this.progressDialog = progressDialog;
    }

    public ModelUser getDataLogin(){
        //progressDialog.show();
        modelUser = new ModelUser();
        userEmail = (EditText)view.findViewById(R.id.input_email);
        userPassword = (EditText)view.findViewById(R.id.input_password);
        modelUser.setCompleted(true);
        if(userEmail.getText().length()==0){
            userEmail.setError("Email is required ");
            modelUser.setCompleted(false);
        }else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(userEmail.getText().toString()).matches()){
            userEmail.setError("enter a valid email address ");
            modelUser.setCompleted(false);
        }
        else{
            userEmail.setError(null);
            modelUser.setEmail(userEmail.getText().toString());
        }

        if(userPassword.getText().length()==0){
            userPassword.setError("Password is required");
            modelUser.setCompleted(false);
        }else if(userPassword.getText().length() < 4 || userPassword.getText().length() > 10){
                 userPassword.setError("pass between 4 and 10 alphanumeric");
            modelUser.setCompleted(false);
        }else{
            userPassword.setError(null);
            modelUser.setPassWord(userPassword.getText().toString());
        }
        return modelUser;
    }
}
