package ronandoyle.ie.mmarankings;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Modifies data in the database.
 *
 * @author Ronan Doyle <ronan@donedeal.ie>
 */
public class FighterContentProvider extends ContentProvider {

    private FighterDBHelper mFighterDBHelper;
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    static final int FIGHTER = 100;

    static UriMatcher buildUriMatcher() {
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = FighterDBContract.CONTENT_AUTHORITY;

        uriMatcher.addURI(authority, FighterDBContract.PATH_FIGHTER, FIGHTER);
        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        mFighterDBHelper = new FighterDBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selectedEntry, String[] arguments, String sortOrder) {
        Cursor returnCursor;
        switch (sUriMatcher.match(uri)) {
            case FIGHTER:
                returnCursor = mFighterDBHelper.getReadableDatabase().query(
                        FighterDBContract.FighterEntry.TABLE_NAME,
                        projection,
                        selectedEntry,
                        arguments,
                        null,
                        null,
                        sortOrder);
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        returnCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return returnCursor;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case FIGHTER:
                return FighterDBContract.FighterEntry.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        final SQLiteDatabase sqLiteDatabase = mFighterDBHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case FIGHTER:
                sqLiteDatabase.beginTransaction();
                int returnCount = 0;
                try {
                    for (ContentValues contentValue : values) {
                        long _id = sqLiteDatabase.
                                insert(FighterDBContract.FighterEntry.TABLE_NAME, null,
                                        contentValue);
                        if (_id != -1) {
                            returnCount++;
                        }
                    }
                    sqLiteDatabase.setTransactionSuccessful();
                } finally {
                    sqLiteDatabase.endTransaction();
                }
                getContext().getContentResolver().notifyChange(uri, null);
                return returnCount;
            default:
                return super.bulkInsert(uri, values);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final SQLiteDatabase sqLiteDatabase = mFighterDBHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case FIGHTER: {
                long _id = sqLiteDatabase.
                        insert(FighterDBContract.FighterEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    returnUri = FighterDBContract.FighterEntry.buildFighterUri(_id);
                } else {
                    throw new SQLException("failed to insert row into " + uri);
                }
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selectedEntry, String[] arguments) {
        final SQLiteDatabase sqLiteDatabase = mFighterDBHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int numRowsDeleted;

        if (null == selectedEntry) {
            selectedEntry = "1";
        }
        switch (match) {
            case FIGHTER:
                numRowsDeleted = sqLiteDatabase.delete(FighterDBContract.FighterEntry.TABLE_NAME,
                        selectedEntry, arguments);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (numRowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return numRowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selectedEntry, String[] arguments) {
        final SQLiteDatabase sqLiteDatabase = mFighterDBHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int numRowsUpdated;

        switch (match) {
            case FIGHTER:
                numRowsUpdated = sqLiteDatabase.update(FighterDBContract.FighterEntry.TABLE_NAME,
                        contentValues, selectedEntry, arguments);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (numRowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return numRowsUpdated;
    }
}
