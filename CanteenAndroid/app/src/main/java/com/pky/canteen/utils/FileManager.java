package com.pky.canteen.utils;

import android.content.Context;

import java.io.File;

public class FileManager {
    public static Context applicationContext = null;

    public static File getFiles() {
        return applicationContext.getExternalFilesDir(null);
    }
}