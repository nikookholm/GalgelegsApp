package s112011.galgelegsapp;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by KimDrewes on 11-01-2016.
 */
public class GalgeApplication extends Application{


    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);

    }
}
