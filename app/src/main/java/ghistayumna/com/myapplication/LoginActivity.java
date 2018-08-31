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


import bacis.ghistayumna.com.mylibrary.mapsGoogle.MapsActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import ghistayumna.com.myapplication.Dao.UserDao;
import ghistayumna.com.myapplication.Function.ConnectivityReceiver;
import ghistayumna.com.myapplication.Function.DatabaseHelper;
import ghistayumna.com.myapplication.Function.FinalConstant;
import ghistayumna.com.myapplication.Function.InternetConnection;
import ghistayumna.com.myapplication.Function.MyApplication;
import ghistayumna.com.myapplication.Implement.IUserDao;
import ghistayumna.com.myapplication.Model.User.ModelUser;
import ghistayumna.com.myapplication.SetterGetter.UserLoginSetGet;

import static android.content.Context.MODE_PRIVATE;

public class LoginActivity extends AppCompatActivity {

    private UserDao userDao ;
    private UserLoginSetGet userLoginSetGet;
    private ModelUser modelUser = new ModelUser();
    private View view;
    private Button buttonLogin,fotoTake;
    private Context context;
    private ProgressDialog progressDialog;
    private DatabaseHelper databaseHelper;
    private IUserDao iUserDao;
    SharedPreferences preferences;
    public static final String Email = "Key Email";
    @BindView(R.id.link_signup) TextView signup;
    @BindView(R.id.takefoto) TextView foto;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = getApplicationContext();
        databaseHelper = new DatabaseHelper(context);
        final View currentiew  = this.findViewById(R.id.loginactivity);
        ButterKnife.bind(this);
        iUserDao = new IUserDao(context);
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("loading");
//        progressDialog.setCancelable(false);
        preferences = getSharedPreferences(Email,Context.MODE_PRIVATE);

        userLoginSetGet = new UserLoginSetGet(currentiew,context,progressDialog);
        buttonLogin = (Button)findViewById(R.id.btn_login);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(InternetConnection.getInstance(context).isOnline()){
//                    Toast.makeText(context,"WiFi/Mobile Networks Connected!",Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(context,"Ooops! No WiFi/Mobile Networks Connected!",Toast.LENGTH_SHORT).show();
//                }
                doLogin();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
 //               Intent intent = new Intent(getApplicationContext(),SignActivity.class);
               Intent intent = new Intent(getApplicationContext(),AdminUserActivity.class);
               startActivity(intent);
            }
        });

        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent);
//                startActivityvity(intent);
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

        if(modelUser.getCompleted()){
            if(iUserDao.login(modelUser)== FinalConstant.login_succes){
                SharedPreferences.Editor edit = preferences.edit();
                edit.putString("key",iUserDao.getUserEmail(modelUser.getEmail()));
                edit.commit();
                Snackbar.make(findViewById(R.id.loginactivity),"Login Succes  "+preferences.getString("key",""),Snackbar.LENGTH_LONG).show();
            }else{
                Snackbar.make(findViewById(R.id.loginactivity),"Login failed  ",Snackbar.LENGTH_LONG).show();
            }
        }
//        if(!modelUser.getCompleted()){
//            Log.d("return","method login");
//            SharedPreferences.Editor edit = preferences.edit();
//            edit.putString("key",modelUser.getEmail());
//            edit.commit();
//
//            Snackbar.make(findViewById(R.id.loginactivity),"Kemungkinan ada kesalahan  "+preferences.getString("key","") ,Snackbar.LENGTH_LONG).show();
//            //progressDialog.dismiss();
//            return;
//        }
    }
}