package ghistayumna.com.myapplication.SetterGetter;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import ghistayumna.com.myapplication.Model.User.ModelUser;
import ghistayumna.com.myapplication.R;
public class UserSignUp {
    private final ProgressDialog progressDialog;
    private View view;
    private ViewGroup container;
    private LayoutInflater inflater;
    private Context context;
    private ModelUser modelUser;
    private boolean valid=true;
    private EditText nameUser,email,mobilePhone,password,repassword;


    public UserSignUp(View view, Context context, ProgressDialog progressDialog) {
        this.view = view;
        this.context = context;
        this.progressDialog = progressDialog;
    }

    public ModelUser signUp(){
        modelUser = new ModelUser();
        nameUser = (EditText)view.findViewById(R.id.input_name);
        email = (EditText)view.findViewById(R.id.signup_email);
        mobilePhone = (EditText)view.findViewById(R.id.signup_mobile);
        password =(EditText)view.findViewById(R.id.signup_password);
        repassword = (EditText)view.findViewById(R.id.signup_reEnterPassword);
        modelUser.setCompleted(true);

        if(nameUser.getText().length()==0){
            nameUser.setError("nama is required");
            modelUser.setCompleted(false);
        }else{
            nameUser.setError(null);
            modelUser.setName(nameUser.getText().toString());
        }

        if(email.getText().length()==0){
            email.setError("email is required");
            modelUser.setCompleted(false);
        }else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                 email.setError("enter a valid email address ");
                 modelUser.setCompleted(false);
        }else {
            email.setError(null);
            modelUser.setEmail(email.getText().toString());
        }

        if(mobilePhone.getText().length()==0){
            mobilePhone.setError("phone number id required");
            modelUser.setCompleted(false);
        }else{
            mobilePhone.setError(null);
            modelUser.setMobilePhone(mobilePhone.getText().toString());
        }

        if(password.getText().length()==0){
            password.setError("password is required");
            modelUser.setCompleted(false);
        }else{
            password.setError(null);
        }

        if(repassword.getText().length()==0){
            repassword.setError("re password is riquired");
        }

        if(password.getText().toString().equals(repassword.getText().toString())){
            modelUser.setPassWord(password.getText().toString());
            password.setError(null);
            repassword.setError(null);
        }else{
            repassword.setError("password not same");
        }

        return modelUser;
    }


}
