package com.example.downa.przypominajka;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by downa on 13.06.2016.
 */
public class ReminderDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION= 1;

    static final String DATABASE_NAME="Reminder";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + "Reminders" + " (" +
                    "Name" + " TEXT," +
                    "Place" + " TEXT," +
                    "Pion"  + " REAL," +
                    "Poziom" + " REAL);";


    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + "Reminders";

    public ReminderDbHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
    }

}
