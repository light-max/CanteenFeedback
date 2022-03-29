package com.pky.canteen;

import android.app.Application;

import com.pky.canteen.utils.FileManager;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FileManager.applicationContext = getApplicationContext();
    }
}
