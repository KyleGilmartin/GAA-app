package edu.itsligo.gaa_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/*
 * Class has create / drop table and CRUD ops for table
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "GaaAppFixtures";
    private static final String TABLE_FIXTURES = "fixtures";
    private static final String KEY_FIXTURE_ID = "fixture_id";
    private static final String KEY_HOME_TEAM = "home_team";
    private static final String KEY_AWAY_TEAM = "away_team";
    private static final String KEY_GAME_DATE = "game_date";
    private static final String KEY_GAME_VENUE = "game_venue";


    /*
     * Constructor
     */
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FIXTURES_TABLE = "CREATE TABLE " + TABLE_FIXTURES + "(" +
                KEY_FIXTURE_ID + " INTEGER PRIMARY KEY," +
                KEY_AWAY_TEAM + " TEXT NOT NULL," +
                KEY_HOME_TEAM + " TEXT NOT NULL," +
                KEY_GAME_DATE + " TEXT NOT NULL," +
                KEY_GAME_VENUE + " TEXT NOT NULL" +
                ")";
        db.execSQL(CREATE_FIXTURES_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FIXTURES);

        // Create tables again
        onCreate(db);
    }

    /*
     * CRUD Helper methods
     */
    public void emptyFixtures() {
        // Drop older table if existed
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FIXTURES);

        // Create tables again
        onCreate(db);
    }

    // code to add the new hiScore
    void addFixture(Fixtures fixture) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_AWAY_TEAM, fixture.getAway_team());
        values.put(KEY_HOME_TEAM, fixture.getHome_team());
        values.put(KEY_GAME_DATE, fixture.getGame_date());
        values.put(KEY_GAME_VENUE, fixture.getGame_venue());

        // Inserting Row
        db.insert(TABLE_FIXTURES, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single hiScore
    Fixtures getFixture(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FIXTURES, new String[]{
                        KEY_FIXTURE_ID,
                        KEY_AWAY_TEAM,
                        KEY_HOME_TEAM,
                        KEY_GAME_DATE,
                        KEY_GAME_VENUE},
                KEY_FIXTURE_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Fixtures fixtures = new Fixtures((
                cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3));
        // return hi score
        return fixtures;
    }

    // code to get all hiScores in a list view
    public List<Fixtures> getAllFixtures() {
        List<Fixtures> fixturesList = new ArrayList<Fixtures>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_FIXTURES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Fixtures fixtures = new Fixtures();
                fixtures.setFixture_id(Integer.parseInt(cursor.getString(0)));
                fixtures.setHome_team(cursor.getString(1));
                fixtures.setAway_team(cursor.getString(2));
                fixtures.setGame_date(cursor.getString(3));
                fixtures.setGame_venue(cursor.getString(4));
                // Adding hi score to list
                fixturesList.add(fixtures);
            } while (cursor.moveToNext());
        }

        // return hiScore list
        return fixturesList;
    }

    // code to update the single hiScore
    public int updateFixture(Fixtures fixtures) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_HOME_TEAM, fixtures.getHome_team());
        values.put(KEY_AWAY_TEAM, fixtures.getAway_team());
        values.put(KEY_GAME_DATE, fixtures.getGame_date());
        values.put(KEY_GAME_VENUE, fixtures.getGame_venue());

        // updating row
        return db.update(TABLE_FIXTURES, values, KEY_FIXTURE_ID + " = ?",
                new String[]{String.valueOf(fixtures.getFixture_id())});
    }

    // Deleting single hiScore
    public void deleteHiScore(Fixtures fixtures) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FIXTURES, KEY_FIXTURE_ID + " = ?",
                new String[]{String.valueOf(fixtures.getFixture_id())});
        db.close();
    }

    // Getting top 5 scores
    public List<Fixtures> getUpcomingThreeFixtures() {
        List<Fixtures> fixtureList = new ArrayList<Fixtures>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_FIXTURES +
                " ORDER BY SCORE DESC " +
                " LIMIT 3";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Fixtures fixtures = new Fixtures();
                fixtures.setFixture_id(Integer.parseInt(cursor.getString(0)));
                fixtures.setHome_team(cursor.getString(1));
                fixtures.setAway_team(cursor.getString(2));
                fixtures.setGame_date(cursor.getString(3));
                fixtures.setGame_venue(cursor.getString(4));

                // Adding fixture to list
                fixtureList.add(fixtures);
            } while (cursor.moveToNext());
        }

        // return hi score list
        return fixtureList;
    }

}


