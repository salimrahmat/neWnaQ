package ghistayumna.com.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TakeFotoActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST_CODE = 7777;
    private static final String TAG = TakeFotoActivity.class.getSimpleName();

    @BindView(R.id.iv_camera)ImageView ivcamera;
    @BindView(R.id.bt_camera)Button btcamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_foto);
        ButterKnife.bind(this);


        btcamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode){
            case(CAMERA_REQUEST_CODE):
                if(resultCode == Activity.RESULT_OK)
                {
                    File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                    // result code sama, save gambar ke bitmap

                    Log.d("result",storageDir.getAbsolutePath());
                    Bitmap bitmap;
                    bitmap = (Bitmap) data.getExtras().get("data");
                    ivcamera.setImageBitmap(bitmap);

                    return;
                }
                break;
        }
    }
}
