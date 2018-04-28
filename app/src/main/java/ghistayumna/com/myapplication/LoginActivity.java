package ghistayumna.com.myapplication;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import butterknife.BindView;
import butterknife.ButterKnife;
import ghistayumna.com.myapplication.Dao.UserDao;
import ghistayumna.com.myapplication.Function.ConnectivityReceiver;
import ghistayumna.com.myapplication.Function.InternetConnection;
import ghistayumna.com.myapplication.Function.MyApplication;
import ghistayumna.com.myapplication.Model.User.ModelUser;
import ghistayumna.com.myapplication.SetterGetter.UserLoginSetGet;

import static android.content.Context.MODE_PRIVATE;

public class LoginActivity extends AppCompatActivity {

    private UserDao userDao ;
    private UserLoginSetGet userLoginSetGet;
    private ModelUser modelUser = new ModelUser();
    private View view;
    private Button buttonLogin;
    private Context context;
    private ProgressDialog progressDialog;
    SharedPreferences preferences;
    public static final String Email = "Key Email";
    @BindView(R.id.link_signup) TextView signup;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final View currentiew  = this.findViewById(R.id.loginactivity);
        ButterKnife.bind(this);
        context = getApplicationContext();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading");
        progressDialog.setCancelable(false);
        preferences = getSharedPreferences(Email,Context.MODE_PRIVATE);

       // userDao = new UserDao(currentiew,context,progressDialog);
        userLoginSetGet = new UserLoginSetGet(currentiew,context,progressDialog);
        buttonLogin = (Button)findViewById(R.id.btn_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(InternetConnection.getInstance(context).isOnline()){
                    Toast.makeText(context,"WiFi/Mobile Networks Connected!",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context,"Ooops! No WiFi/Mobile Networks Connected!",Toast.LENGTH_SHORT).show();
                }
                //checkConnection();
                //doLogin();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignActivity.class);
                startActivity(intent);
            }
        });
    }

//    private void checkConnection() {
//        boolean isConnected = ConnectivityReceiver.isConnected();
//        showSnack(isConnected);
//    }
//
//    private void showSnack(boolean isConnected) {
//        String message;
//        int color;
//        if (isConnected) {
//            message = "Good! Connected to Internet";
//            color = getColor(R.color.primary_dark);
//        } else {
//            message = "Sorry! Not connected to internet";
//            color = getColor(R.color.primary);
//        }
//
//        Snackbar snackbar = Snackbar
//                .make(findViewById(R.id.loginactivity), message, Snackbar.LENGTH_LONG);
//        snackbar.show();
//    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        // register connection status listener
//        MyApplication.getInstance().setConnectivityListener(this);
//    }


    private void doLogin() {
        modelUser = userLoginSetGet.getDataLogin();
        Log.d("etamah","kumaha  "+modelUser.getCompleted());

        if(!modelUser.getCompleted()){
            Log.d("return","method login");
            SharedPreferences.Editor edit = preferences.edit();
            edit.putString("key",modelUser.getEmail());
            edit.commit();

            Snackbar.make(findViewById(R.id.loginactivity),"Kemungkinan ada kesalahan  "+preferences.getString("key","") ,Snackbar.LENGTH_LONG).show();
            //progressDialog.dismiss();
            return;
        }
//        else{
//            if(userDao.validate(modelUser)){
//                Snackbar.make(findViewById(R.id.loginactivity),"Login succes",Snackbar.LENGTH_LONG).show();
//            }
//        }
    }

//    @Override
//    public void onNetworkConnectionChanged(boolean isConnected) {
//
//    }
}