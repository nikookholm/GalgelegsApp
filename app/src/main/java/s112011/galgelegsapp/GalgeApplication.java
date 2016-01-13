package s112011.galgelegsapp;

import android.app.Application;
import android.content.SharedPreferences;

import com.firebase.client.Firebase;

/**
 * Created by KimDrewes on 11-01-2016.
 */
public class GalgeApplication extends Application{

    public static final String PREFS_NAME = "pref";

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);


    }
}
