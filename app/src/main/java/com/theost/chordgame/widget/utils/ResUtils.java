package com.theost.chordgame.widget.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.lang.reflect.Field;
import java.util.Locale;

public class ResUtils {

    public static Bitmap getBitmap(Context context, int resId) {
        return BitmapFactory.decodeResource(context.getResources(), resId);
    }

    public static String getStringLocale(Context context, Locale requestedLocale, int resourceId) {
        Resources resources = context.getResources();
        Configuration conf = resources.getConfiguration();
        Locale savedLocale = conf.locale;
        conf.locale = requestedLocale;
        resources.updateConfiguration(conf, null);
        String result = resources.getString(resourceId);
        conf.locale = savedLocale;
        resources.updateConfiguration(conf, null);

        return result;
    }

    public static int getResId(String resName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}