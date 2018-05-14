package ghistayumna.com.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
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
import ghistayumna.com.myapplication.Model.User.ModelUser;
import ghistayumna.com.myapplication.SetterGetter.UserSignUp;

public class SignActivity extends AppCompatActivity {
    @BindView(R.id.tool) Toolbar toolbar;
    @BindView(R.id.btn_signupuser) Button button;
    private Context context;
    private ProgressDialog progressDialog;
    private UserSignUp userSignUp;


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
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading");
        progressDialog.setCancelable(false);
        userSignUp = new UserSignUp(currentiew,context,progressDialog);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               createuser();
            }
        });

    }

    private void createuser() {
        ModelUser modelUser = new ModelUser();
        modelUser = userSignUp.signUp();
//        if(!modelUser.getCompleted()){
//            Snackbar.make(findViewById(R.id.signupUser),"Kemungkinan ada kesalahan  " ,Snackbar.LENGTH_LONG).show();
//        }
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
