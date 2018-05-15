package ghistayumna.com.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ghistayumna.com.myapplication.Implement.IUserDao;
import ghistayumna.com.myapplication.Model.User.ModelUser;
import ghistayumna.com.myapplication.SetterGetter.UserSignUp;

public class SignActivity extends AppCompatActivity {
    @BindView(R.id.tool) Toolbar toolbar;
    @BindView(R.id.btn_signupuser) Button button;
    private Context context;
    private ProgressDialog progressDialog;
    private UserSignUp userSignUp;
    private IUserDao iUserDao;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setLogoDescription(getResources().getString(R.string.app_name));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = getApplicationContext();
        final View currentiew  = this.findViewById(R.id.signupUser);
        iUserDao = new IUserDao(context);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading");
        progressDialog.setCancelable(false);
        userSignUp = new UserSignUp(currentiew,context,progressDialog);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               progressDialog.show();
               createuser();
            }
        });

    }

    private void createuser() {
        ModelUser modelUser = new ModelUser();
        modelUser = userSignUp.signUp();
        modelUser.setUserId(iUserDao.getIdUser());
        if(modelUser.getCompleted()){
            result=iUserDao.insertUser(modelUser);
            Snackbar.make(findViewById(R.id.signupUser),"Success "+result ,Snackbar.LENGTH_LONG).show();
            Intent intent = new Intent(context,LoginActivity.class);
            startActivity(intent);
        }else{
            Snackbar.make(findViewById(R.id.signupUser),"Error silahkan hub It" ,Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Menerapkan menu terpilih di menu-> menu_sign.xml
        getMenuInflater().inflate(R.menu.menu_sign,menu);
        //return true;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
         if(id == R.id.close){
        super.onBackPressed();
//             System.exit(0);
//             finish();
         }
        return super.onOptionsItemSelected(item);
    }

}
