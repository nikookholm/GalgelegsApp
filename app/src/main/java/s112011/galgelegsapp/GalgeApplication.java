package s112011.galgelegsapp;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.firebase.client.Firebase;

/**
 * Created by KimDrewes on 11-01-2016.
 */
public class GalgeApplication extends Application{
    SharedPreferences myPrefs;
    SharedPreferences.Editor editor;


    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);


        myPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = myPrefs.edit();


    }

}
