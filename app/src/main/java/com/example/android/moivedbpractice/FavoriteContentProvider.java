package com.example.android.moivedbpractice;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.android.moivedbpractice.FavoriteMovieContract;
import com.example.android.moivedbpractice.FavoriteMovieContract.FavoriteMovieEntry;
public class FavoriteContentProvider extends ContentProvider {
    private FavoriteDbHelper mFavoriteDbHelper;
    public static final int MOVIES = 100;
    private static final UriMatcher mUriMatch = buildUriMatcher();
    public static UriMatcher buildUriMatcher(){
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(FavoriteMovieContract.AUTHORITY,
                FavoriteMovieContract.PATH_TASKS, MOVIES);
        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        mFavoriteDbHelper = new FavoriteDbHelper(context);
        return true;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final SQLiteDatabase db = mFavoriteDbHelper.getWritableDatabase();
        int match = mUriMatch.match(uri);
        Uri returnUri;
        switch (match){
            case MOVIES:
                long id = db.insert(FavoriteMovieEntry.TABLE_NAME,
                        null,contentValues);
                returnUri = ContentUris.withAppendedId(FavoriteMovieEntry.CONTENT_URI, id);
                break;
            default:
                throw new UnsupportedOperationException(("unknown uri"));

        }
        return null;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        final SQLiteDatabase db = mFavoriteDbHelper.getReadableDatabase();
        int match = mUriMatch.match(uri);
        Cursor favCursor;
        switch (match){
            case MOVIES:
                favCursor = db.query(FavoriteMovieEntry.TABLE_NAME,null,
                        null,null,
                        null,null, null );
                return favCursor;
                default:
                    return null;

        }


    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
