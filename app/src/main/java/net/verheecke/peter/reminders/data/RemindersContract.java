package net.verheecke.peter.reminders.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Peter on 7/28/2016.
 */
public class RemindersContract {

    // The "Content authority" is a name for the entire content provider, similar to the
    // relationship between a domain name and its website.  A convenient string to use for the
    // content authority is the package name for the app, which is guaranteed to be unique on the
    // device.
    public static final String CONTENT_AUTHORITY = "net.verheecke.peter.reminders";

    // Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
    // the content provider.
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    // Possible paths (appended to base content URI for possible URI's)
    // For instance, content://com.example.android.sunshine.app/weather/ is a valid path for
    // looking at weather data. content://com.example.android.sunshine.app/givemeroot/ will fail,
    // as the ContentProvider hasn't been given any information on what to do with "givemeroot".
    // At least, let's hope not.  Don't be that dev, reader.  Don't be that dev.
    public static final String PATH_REMINDER = "reminders";
    public static final String PATH_TAG = "tags";
    public static final String PATH_REMINDER_TAG = "reminder_tags";
    public static final String PATH_REMINDER_SCHEDULE = "reminder_schedules";


    public static final class ReminderEntry implements BaseColumns {
        public static final String TABLE_NAME = "reminder";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESCRIPTION = "description";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_REMINDER).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_REMINDER;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_REMINDER;

        public static Uri buildReminderUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class TagEntry implements BaseColumns {
        public static final String TABLE_NAME = "tag";
        public static final String COLUMN_NAME = "name";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_TAG).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TAG;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TAG;
    }

    public static final class ReminderTagEntry implements BaseColumns {
        public static final String TABLE_NAME = "reminder_tag";
        public static final String COLUMN_REMINDER_ID = "reminder_id";
        public static final String COLUMN_TAG_ID = "tag_id";

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" +
                        PATH_REMINDER_TAG;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" +
                        PATH_REMINDER_TAG;
    }

    public static final class ReminderScheduleEntry implements BaseColumns {
        public static final String TABLE_NAME = "reminder_schedule";
        public static final String COLUMN_REMINDER_ID = "reminder_id";
        public static final String COLUMN_REPEAT_START = "repeat_start";
        public static final String COLUMN_REPEAT_INTERVAL = "repeat_interval";

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" +
                        PATH_REMINDER_SCHEDULE;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" +
                        PATH_REMINDER_SCHEDULE;
    }

}
