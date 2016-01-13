package s112011.galgelegsapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.io.FileNotFoundException;


public class Spil extends AppCompatActivity {

    static GalgeLogik logik = new GalgeLogik();
    FragmentTransaction transaction;
    ImageView frame;
    Bundle bundle = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spil);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Fragment fragment = new Level_fragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentKeyboard, fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void fragmentFrame(Fragment frag){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentKeyboard, frag).commit();
}
    public void startSpil(){
       logik.nulstil();
//        Intent intent = new Intent(Intent.ACTION_PICK,
//                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(intent, 0);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            Uri targetUri = data.getData();

            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
               bitmap = Bitmap.createBitmap(bitmap,100,300,200,300);


                frame = (ImageView) findViewById(R.id.image);
                frame.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
