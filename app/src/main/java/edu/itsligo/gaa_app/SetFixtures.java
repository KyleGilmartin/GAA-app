package edu.itsligo.gaa_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;


public class SetFixtures extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_fixtures);

        DatabaseHandler db = new DatabaseHandler(this);

        db.emptyFixtures();     // empty table if required

        // Inserting fixtures
        Log.i("Insert: ", "Inserting ..");
        db.addFixture(new Fixtures("Mayo", "Dublin", "19 DEC 2020", "Croke Park"));
        db.addFixture(new Fixtures("Sligo", "Leitrim", "15 SEP 2020", "Sligo"));
        db.addFixture(new Fixtures("Galway", "Roscommon", "23 NOV 2020", "Galway"));
        db.addFixture(new Fixtures("Dublin", "Meath", "11 OCT 2020", "Croke Park"));
        db.addFixture(new Fixtures("Kerry", "Cork", "02 OCT 2020", "Kerry"));
        db.addFixture(new Fixtures("Tipperary", "Clare", "12 DEC 2020", "Tipperary"));
        db.addFixture(new Fixtures("Donegal", "Tyrone", "25 SEP 2020", "Donegal"));
        db.addFixture(new Fixtures("Cavan", "Monaghan", "06 SEP 2020", "Cavan"));


        // Reading all fixtures
        //Log.i("Reading: ", "Reading all fixtures..");
        List<Fixtures> fixtures = db.getAllFixtures();


        for (Fixtures f : fixtures) {
            String log =
                    "Id: " + f.getFixture_id() +
                            ", Home Team: " + f.getHome_team() +
                            " , Away Team: " + f.getAway_team() +
                            " , Date: " + f.getGame_date() +
                            " , Venue: " + f.getGame_venue();

            // Writing Fixtures to log
            Log.i("Fixture: ", log);
        }

        Log.i("divider", "====================");

        Fixtures singleFixture = db.getFixture(3);
        Log.i("Fixture 3 is ", singleFixture.getHome_team() + " Vs " +
                singleFixture.getAway_team());

        Log.i("divider", "====================");

        // Calling SQL statement
        List<Fixtures> upcoming3Fixtures = db.getUpcomingThreeFixtures();

        for (Fixtures f : upcoming3Fixtures) {
            String log =
                    "Id: " + f.getFixture_id() +
                            ", Home Team: " + f.getHome_team() +
                            " , Away Team: " + f.getAway_team() +
                            " , Date: " + f.getGame_date() +
                            " , Venue: " + f.getGame_venue();

            // Writing Fixture to log
            Log.i("Fixture: ", log);
        }
    }

}
