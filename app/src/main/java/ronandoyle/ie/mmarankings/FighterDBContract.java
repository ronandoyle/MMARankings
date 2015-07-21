package ronandoyle.ie.mmarankings;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * This class will define the table and column for the database.
 *
 * @author Ronan Doyle <ronan@donedeal.ie>
 */
public class FighterDBContract {

    public static final String CONTENT_AUTHORITY = "ronandoyle.ie.mmarankings";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    // Provides paths to the data.
    public static final String PATH_FIGHTER = "fighter";

    /* Inner class that defines the table contents of the fighter table.*/
    public static final class FighterEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_FIGHTER).build();
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FIGHTER;

        public static final String TABLE_NAME = "fighter_table";

        // Database columns
        public static final String COLUMN_ID = "fighter_id";
        public static final String COLUMN_FIRST_NAME = "first_name";
        public static final String COLUMN_LAST_NAME = "last_name";
        public static final String COLUMN_NICKNAME = "nickname";
        public static final String COLUMN_STAT_ID = "stat_id";
        public static final String COLUMN_WINS = "wins";
        public static final String COLUMN_DRAWS = "draws";
        public static final String COLUMN_LOSSES = "losses";
        public static final String COLUMN_WEIGHT_CLASS = "weight_class";
        public static final String COLUMN_TITLE_HOLDER = "title_holder";
        public static final String COLUMN_STATUS = "fighter_status";
        public static final String COLUMN_RANK = "rank";
        public static final String COLUMN_POUND_FOR_POUND = "pound_for_pound";
        public static final String COLUMN_THUMBNAIL = "thumbnail";
        public static final String COLUMN_BELT_THUMBNAIL = "belt_thumbnail";
        public static final String COLUMN_LEFT_BODY_IMAGE = "left_body_image";
        public static final String COLUMN_RIGHT_BODY_IMAGE = "right_body_image";
        public static final String COLUMN_PROFILE_IMAGE = "profile_image";

        public static Uri buildFighterUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}