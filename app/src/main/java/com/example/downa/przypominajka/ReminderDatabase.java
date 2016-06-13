package com.example.downa.przypominajka;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by downa on 13.06.2016.
 */
public class ReminderDatabase {

    static final public String TABLE_NAME = "Reminders";
    private ReminderDbHelper DbHelper;

    public ReminderDatabase(Context context){
        this.DbHelper= new ReminderDbHelper(context);
    }

    public void addReminder(Reminder reminder){
        SQLiteDatabase db = DbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", reminder.getName());
        values.put("place", reminder.getPlace());


        db.insert(TABLE_NAME, null, values);
    }

    public Reminder getReminder(int position) {
        SQLiteDatabase db = DbHelper.getReadableDatabase();

        String[] projection = {
                "name", "place"
        };

        Cursor cursor = db.query(
                TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        cursor.moveToPosition(position);

        String name = cursor.getString(0);
        String place = cursor.getString(1);

        return new Reminder(name, place);
    }

    public int getReminderNumber() {
        SQLiteDatabase db =  DbHelper.getReadableDatabase();

        return (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME, null, null);
    }

}
