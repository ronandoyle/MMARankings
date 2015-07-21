package ronandoyle.ie.mmarankings;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import ronandoyle.ie.mmarankings.FighterDBContract.FighterEntry;

/**
 * This class will manage a local database for fighter data.
 *
 * @author Ronan Doyle <ronan@donedeal.ie>
 */
public class FighterDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "mmarankings.db";

    public FighterDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Creating the table to hold the fighter data.
        final String SQL_CREATE_FIGHTER_TABLE =
                "CREATE TABLE " + FighterEntry.TABLE_NAME + " (" +
                FighterEntry._ID + " INTEGER PRIMARY KEY," +
                FighterEntry.COLUMN_ID + " INTEGER UNIQUE NOT NULL, " +
                FighterEntry.COLUMN_FIRST_NAME + " TEXT NOT NULL, " +
                FighterEntry.COLUMN_LAST_NAME + " TEXT NOT NULL, " +
                FighterEntry.COLUMN_NICKNAME + " TEXT, " +
                FighterEntry.COLUMN_STAT_ID + " INTEGER UNIQUE NOT NULL, " +
                FighterEntry.COLUMN_WINS + " INTEGER NOT NULL, " +
                FighterEntry.COLUMN_LOSSES + " INTEGER NOT NULL, " +
                FighterEntry.COLUMN_DRAWS + " INTEGER NOT NULL, " +
                FighterEntry.COLUMN_WEIGHT_CLASS + " TEXT NOT NULL, " +
                FighterEntry.COLUMN_TITLE_HOLDER + " INTEGER NOT NULL, " +
                FighterEntry.COLUMN_STATUS + " TEXT, " +
                FighterEntry.COLUMN_RANK + " TEXT, " +
                FighterEntry.COLUMN_POUND_FOR_POUND + " TEXT, " +
                FighterEntry.COLUMN_THUMBNAIL + " TEXT, " +
                FighterEntry.COLUMN_BELT_THUMBNAIL + " TEXT, " +
                FighterEntry.COLUMN_LEFT_BODY_IMAGE + " TEXT, " +
                FighterEntry.COLUMN_RIGHT_BODY_IMAGE + " TEXT, " +
                FighterEntry.COLUMN_PROFILE_IMAGE + " TEXT " + " );";

        sqLiteDatabase.execSQL(SQL_CREATE_FIGHTER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FighterEntry.TABLE_NAME);
    }
}
