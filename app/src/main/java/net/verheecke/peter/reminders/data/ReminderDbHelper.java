package net.verheecke.peter.reminders.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import net.verheecke.peter.reminders.data.RemindersContract.ReminderEntry;
import net.verheecke.peter.reminders.data.RemindersContract.TagEntry;
import net.verheecke.peter.reminders.data.RemindersContract.ReminderTagEntry;
import net.verheecke.peter.reminders.data.RemindersContract.ReminderScheduleEntry;


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
                ReminderEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                ReminderEntry.COLUMN_DESCRIPTION + " TEXT, " +
                " UNIQUE (" + ReminderEntry.COLUMN_TITLE +  ") ON CONFLICT REPLACE);";

        final String SQL_CREATE_TAG_TABLE = "CREATE TABLE " + TagEntry.TABLE_NAME + " (" +
                TagEntry._ID + " INTEGER PRIMARY KEY, " +
                TagEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                " UNIQUE (" + TagEntry.COLUMN_NAME + ") ON CONFLICT REPLACE);";

        final String SQL_CREATE_REMINDER_TAG_TABLE = "CREATE TABLE " + ReminderTagEntry.TABLE_NAME
                + " (" + ReminderTagEntry._ID + " INTEGER PRIMARY KEY, " +
                ReminderTagEntry.COLUMN_REMINDER_ID + " INTEGER NOT NULL, " +
                ReminderTagEntry.COLUMN_TAG_ID + " INTEGER NOT NULL, " +
                " FOREIGN KEY (" + ReminderTagEntry.COLUMN_REMINDER_ID + ") REFERENCES " +
                ReminderEntry.TABLE_NAME + " (" + ReminderEntry._ID + "), " +
                " FOREIGN KEY (" + ReminderTagEntry.COLUMN_TAG_ID + ") REFERENCES " +
                TagEntry.TABLE_NAME + " (" + TagEntry._ID + "), " +
                " UNIQUE (" + ReminderTagEntry.COLUMN_REMINDER_ID + ", " +
                ReminderTagEntry.COLUMN_TAG_ID + ") ON CONFLICT REPLACE)";

        final String SQL_CREATE_REMINDER_SCHEDULE_TABLE = "CREATE TABLE " +
                ReminderScheduleEntry.TABLE_NAME + " (" +
                ReminderScheduleEntry._ID + " INTEGER PRIMARY KEY, " +
                ReminderScheduleEntry.COLUMN_REMINDER_ID + " INTEGER NOT NULL, " +
                ReminderScheduleEntry.COLUMN_REPEAT_START + " INTEGER NOT NULL, " +
                ReminderScheduleEntry.COLUMN_REPEAT_INTERVAL + " INTEGER NOT NULL, " +
                " FOREIGN KEY (" + ReminderScheduleEntry.COLUMN_REMINDER_ID + ") REFERENCES " +
                ReminderEntry.TABLE_NAME + " (" + ReminderEntry._ID + "))";


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
