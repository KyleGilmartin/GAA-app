package edu.itsligo.gaa_app;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class Admin extends AppCompatActivity {
    private DrawerLayout draw;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    TextView email,username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        email = (TextView) findViewById(R.id.emailPlacehold);
        username = (TextView) findViewById(R.id.usernamePlacehold);

        // firebase info to header
        DocumentReference docRef = fStore.collection("Users").document(fAuth.getCurrentUser().getUid());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
//
                    NavigationView navigationView = findViewById(R.id.nav_view);
                    View headerView = navigationView.getHeaderView(0);
                    username = (TextView) headerView.findViewById(R.id.usernamePlacehold);
                    username.setText(documentSnapshot.getString("FullName"));
                    email = (TextView) headerView.findViewById(R.id.emailPlacehold);
                    email.setText(documentSnapshot.getString("UserEmail"));
                }
            }
        });



//



        draw = findViewById(R.id.drawer_layout);
        // framnet for profile
        NavigationView navigationView = findViewById(R.id.nav_view);



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_Account:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new ProfileAdminFragment()).commit();
                        break;
                    case  R.id.nav_Games:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new GamesAdminFragment()).commit();

                        break;
                    case  R.id.nav_Users:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new AllUsersAdminFragment()).commit();

                        break;
                    case  R.id.nav_Contact:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new ContactAdminFragment()).commit();

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





    public void logoutAdmin(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }





    public void setTraining(View view) {
     {
}
}

    public void doLogoutAdmin(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }


}

