package com.example.android.moivedbpractice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.moivedbpractice.FavoriteMovieContract.FavoriteMovieEntry;
public class MainActivity extends AppCompatActivity {
    public SQLiteDatabase mDb;
    private Button addButton, queryButton,addSecondMovie, deleteButton;
    String[] movieTwo = {"random url", "inception", "2010", "8.9", "matrix inside a matrix","4568465"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FavoriteDbHelper dbHelper = new FavoriteDbHelper(getApplicationContext());
        mDb = dbHelper.getWritableDatabase();
        addButton = findViewById(R.id.add_button);
        queryButton = findViewById(R.id.query_button);
        addSecondMovie = findViewById(R.id.add_second_movie);
        deleteButton = findViewById(R.id.delete_button);
        Log.d("onCreate", "onCreate");
        Context context = getApplicationContext();
        AddValuesToDB addValues = new AddValuesToDB(mDb, context);
       /*
        if(mDb!=null){
            mDb.delete(FavoriteMovieEntry.TABLE_NAME, null, null);
            Log.d("delete"," in delete if statement");
        }
*/
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO add generic static text to query
                ContentValues cv = new ContentValues();
                cv.put(FavoriteMovieEntry.POSTER_PATH, "random url");
                cv.put(FavoriteMovieEntry.ORIGINAL_TITLE, "The Matrix");
                cv.put(FavoriteMovieEntry.RELEASE_DATE, "1999");
                cv.put(FavoriteMovieEntry.VOTE_AVERAGE, "9.2");
                cv.put(FavoriteMovieEntry.OVERVIEW, "The world is a VR simulation");
                cv.put(FavoriteMovieEntry.ID, "3141592657");
                Log.d("query click", "added all content values");
                mDb.insert(FavoriteMovieEntry.TABLE_NAME, null, cv);
                //mDb.close();

            }
        });
        addSecondMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentValues cv = new ContentValues();
                cv.put(FavoriteMovieEntry.POSTER_PATH, movieTwo[0]);
                cv.put(FavoriteMovieEntry.ORIGINAL_TITLE, movieTwo[1]);
                cv.put(FavoriteMovieEntry.RELEASE_DATE, movieTwo[2]);
                cv.put(FavoriteMovieEntry.VOTE_AVERAGE, movieTwo[3]);
                cv.put(FavoriteMovieEntry.OVERVIEW, movieTwo[4]);
                cv.put(FavoriteMovieEntry.ID, movieTwo[5]);
                Log.d("query click", "added all second content values");
                mDb.insert(FavoriteMovieEntry.TABLE_NAME, null, cv);
               // mDb.close();

            }
        });
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO override text view from query
                //FavoriteDbHelper dbHelper = new FavoriteDbHelper(getApplicationContext());
                //dbHelper.getReadableDatabase();
                FavoriteDbHelper dbHelper = new FavoriteDbHelper(getApplicationContext());
                mDb = dbHelper.getReadableDatabase();
                Cursor cursor = getAllMovies();
                //recyclerViewAdapter = new adapter(context, cursor.getCount());    //only add as many elements as are in the cursor/ db
                if (cursor.moveToFirst()) {
                    for (int i = 0; i < cursor.getCount(); i++) {
                        Log.d("cursor", cursor.getCount() + "");
                        String movieTitle = cursor.getString(cursor.getColumnIndex(FavoriteMovieEntry.ORIGINAL_TITLE));
                        Log.d("query", movieTitle);
                    }

                }
                Log.d("query", "end of onClick");

            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDb.delete(FavoriteMovieEntry.TABLE_NAME,null, null);
                /*
                Uri uri = getContentResolver().insert(FavoriteMovieEntry.CONTENT_URI, cv);
                if(uri!=null){
                    Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_SHORT).show();
                }
                */
            }
        });
    }

    private Cursor getAllMovies(){
        return mDb.query(
                FavoriteMovieEntry.TABLE_NAME,
                null,
                null, //only query movie based on year release, matrix is 1999
                null,
                null,
                null,
                null);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
