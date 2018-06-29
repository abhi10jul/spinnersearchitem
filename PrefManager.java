package com.example.lenovo.languagechange.SharedPreferenceManager;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by lenovo on 15-12-2017.
 */

public class PrefManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "language";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    private static final String IS_LOGIN = "IsLoggedIn";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

    public String getLang(){
        return pref.getString("KeyLang","");
    }
    public void setLang(String Lang){
        editor.putString("KeyLang",Lang);
        editor.commit();
    }

}
