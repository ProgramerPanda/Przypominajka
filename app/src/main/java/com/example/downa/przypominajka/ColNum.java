package com.example.downa.przypominajka;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by downa on 13.06.2016.
 */
public class ColNum {

    private static final String PREFS_NAME = "columns_number";
    private static final String KEY_NUM_COLUMNS = "numColumns";

    private SharedPreferences preferences;

    public ColNum(Context context){
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void saveSelectedNumberOfColumns(int numberOfColumns){
        preferences.edit().putInt(KEY_NUM_COLUMNS, numberOfColumns).commit();
    }

    public int getSelectedNumbersOfColumns(){
        return preferences.getInt(KEY_NUM_COLUMNS,1);
    }
}
