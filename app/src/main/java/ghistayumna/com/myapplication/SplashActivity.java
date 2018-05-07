package ghistayumna.com.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import ghistayumna.com.myapplication.Function.InternetConnection;

public class SplashActivity extends AppCompatActivity {

    TextView textView;
    ImageView imageView;
    ProgressBar progressBar;
    private Context context;
    private String checked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        progressBar = (ProgressBar)findViewById(R.id.progresBar1);
        imageView = (ImageView)findViewById(R.id.image);
        context = getApplicationContext();



        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                new checkConnection().execute();
            }
        },5000L);
    }
    class checkConnection extends AsyncTask<String,Integer,String>{

        @Override
        protected String doInBackground(String... strings) {
            Log.d("doInback","check connection");
            if(InternetConnection.getInstance(context).isOnline()){
                checked="Connected";
                Log.d("doInback1","check connection");
            }else{
                checked="This Connected";
                Log.d("doInback2","check connection");
            }
            return checked;
        }

        @Override
        protected void onPostExecute(String Result) {
            Log.d("hasil",Result);
            progressBar.setVisibility(View.GONE);
            if(Result.equals("Connected")){
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }else{
                Toast.makeText(context,"Ooops! No WiFi/Mobile Networks Connected!",Toast.LENGTH_SHORT).show();
            }
        }


    }
}
