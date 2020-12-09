package edu.itsligo.gaa_app;

public class Fixtures {

    int  fixture_id;          // primary score
    String home_team;       // date in 02 DEC 2020
    String away_team;     // JOE
    String game_date;              // score - should be even
    String game_venue;

    /*
     * Constructors
     */
    public Fixtures(int fixture_id, String home_team, String away_team, String game_date, String game_venue) {
        this.fixture_id = fixture_id;
        this.home_team = home_team;
        this.away_team = away_team;
        this.game_date = game_date;
        this.game_venue = game_venue;
    }

    public Fixtures() {
    }

    public int getFixture_id() {
        return fixture_id;
    }

    public void setFixture_id(int fixture_id) {
        this.fixture_id = fixture_id;
    }

    public String getHome_team() {
        return home_team;
    }

    public void setHome_team(String home_team) {
        this.home_team = home_team;
    }

    public String getAway_team() {
        return away_team;
    }

    public void setAway_team(String away_team) {
        this.away_team = away_team;
    }

    public String getGame_date() {
        return game_date;
    }

    public void setGame_date(String game_date) {
        this.game_date = game_date;
    }

    public String getGame_venue() {
        return game_venue;
    }

    public void setGame_venue(String game_venue) {
        this.game_venue = game_venue;
    }

    /*
     * Getter and setter methods
     */
    public Fixtures(String home_team, String away_team, String game_date, String game_venue) {
        this.home_team = home_team;
        this.away_team = away_team;
        this.game_date = game_date;
        this.game_venue = game_venue;
    }


}


