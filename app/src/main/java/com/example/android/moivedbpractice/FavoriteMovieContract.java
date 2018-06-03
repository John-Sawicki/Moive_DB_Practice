package com.example.android.moivedbpractice;

import android.net.Uri;
import android.provider.BaseColumns;

public class FavoriteMovieContract  {
    private FavoriteMovieContract(){}
    public static final String AUTHORITY = "com.example.android.moivedbpractice";   //authority is the package name for the project
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+AUTHORITY);
    public static final String PATH_TASKS = "tasks";
    public static final String[] POSTER_PATH_ARRAY ={"posterpath"};
    public static final class FavoriteMovieEntry implements BaseColumns {   // includes _id and _count
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_TASKS).build();
        public static final String TABLE_NAME ="favorites";
        public static final String POSTER_PATH ="posterpath";
        public static final String ORIGINAL_TITLE ="original_title";
        public static final String RELEASE_DATE ="release_date";
        public static final String VOTE_AVERAGE ="vote_average";
        public static final String OVERVIEW ="overview";
        public static final String ID ="id";
    }
}
