package edu.itsligo.gaa_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import edu.itsligo.gaa_app.GamesFragment;
import edu.itsligo.gaa_app.Login;
import edu.itsligo.gaa_app.ProfileFragment;
import edu.itsligo.gaa_app.R;

public class User extends AppCompatActivity {

    private DrawerLayout draw;
    TextView email,username;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        draw = findViewById(R.id.drawer_layout);
        // framnet for profile
        NavigationView navigationView = findViewById(R.id.nav_view);



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_Account:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new ProfileFragment()).commit();
                        break;
                    case  R.id.nav_Games:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new GamesFragment()).commit();

                        break;
                    default:

                }
                draw.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, draw, toolbar,
                R.string.nav_app_bar_open_drawer_description, R.string.navigation_drawer_close);
        draw.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ProfileFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_Account);
        }
    }




    @Override
    public void onBackPressed() {
        if (draw.isDrawerOpen(GravityCompat.START)) {
            draw.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void logoutUser(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }
}

