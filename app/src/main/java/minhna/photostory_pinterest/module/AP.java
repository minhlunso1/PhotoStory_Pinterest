package minhna.photostory_pinterest.module;

import android.content.Context;
import android.content.SharedPreferences;

public class AP {
    private static SharedPreferences mAppPreferences;
    private static SharedPreferences.Editor mEditor;

    private AP() {

    }

    public static void initPrefs(Context context) {
        if (mAppPreferences == null) {
            mAppPreferences = context.getSharedPreferences("Session",
                    Context.MODE_PRIVATE);
        }
    }

    public static void clearPrefs(Context context) {
        SharedPreferences settings = context.getSharedPreferences("Session", Context.MODE_PRIVATE);
        settings.edit().clear().apply();
    }

    public static void saveData(Context context, String key, boolean value) {
        initPrefs(context);
        mEditor = mAppPreferences.edit();
        mEditor.putBoolean(key, value);
        mEditor.commit();
    }

    public static boolean getData(Context context, String key) {
        initPrefs(context);
        return mAppPreferences.getBoolean(key, false);
    }

    public static boolean getBooleanData(Context context, String key, boolean def) {
        initPrefs(context);
        return mAppPreferences.getBoolean(key, def);
    }

    public static void saveData(Context context, String key, String value) {
        initPrefs(context);
        mEditor = mAppPreferences.edit();
        mEditor.putString(key, value);
        mEditor.commit();
    }

    public static String getStringData(Context context, String key) {
        initPrefs(context);
        return mAppPreferences.getString(key, null);
    }


    public static void saveData(Context context, String key, long value) {
        initPrefs(context);
        mEditor = mAppPreferences.edit();
        mEditor.putLong(key, value);
        mEditor.commit();
    }

    public static long getLongData(Context context, String key) {
        initPrefs(context);
        return mAppPreferences.getLong(key, -1);
    }

    public static long getIntData(Context context, String key) {
        initPrefs(context);
        return mAppPreferences.getInt(key, -1);
    }
}
