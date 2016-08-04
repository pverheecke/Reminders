package net.verheecke.peter.reminders.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import net.verheecke.peter.reminders.data.RemindersContract.ReminderEntry;
import net.verheecke.peter.reminders.data.RemindersContract.UserEntry;


/**
 * Created by Peter on 7/28/2016.
 */
public class ReminderDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    static final String DATABASE_NAME = "reminders.db";

    public ReminderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_REMINDER_TABLE = "CREATE TABLE " + ReminderEntry.TABLE_NAME + " (" +
                ReminderEntry._ID + " INTEGER PRIMARY KEY, " +
                ReminderEntry.COLUMN_USER_ID + " INTEGER NOT NULL, " +
                ReminderEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                ReminderEntry.COLUMN_DESCRIPTION + " TEXT, " +
                ReminderEntry.COLUMN_DATE_CREATED + " INTEGER NOT NULL, " +
                ReminderEntry.COLUMN_TAGS + " TEXT NOT NULL, " +

                " FOREIGN KEY (" + ReminderEntry.COLUMN_USER_ID + ") REFERENCES" +
                UserEntry.TABLE_NAME + " (" + UserEntry._ID + "), " +
                " UNIQUE (" + ReminderEntry.COLUMN_TITLE + ", " +
                ReminderEntry.COLUMN_USER_ID + ") ON CONFLICT REPLACE);";

        final String SQL_CREATE_USER_TABLE = "CREATE TABLE " + UserEntry.TABLE_NAME + " (" +
                UserEntry._ID + " INTEGER PRIMARY KEY, " +
                UserEntry.COLUMN_DISPLAY_NAME + " TEXT NOT NULL, " +
                UserEntry.COLUMN_EMAIL + " TEXT NOT NULL)";
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
