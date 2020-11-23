package edu.itsligo.gaa_app;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Admin extends AppCompatActivity {
    EditText title, description;
    Button setTraining;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        title = findViewById(R.id.etTitle);
        description = findViewById(R.id.etDescription);
        setTraining = findViewById(R.id.btnSetTraining);




        setTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!title.getText().toString().isEmpty() &&
                        !description.getText().toString().isEmpty()) {
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE,
                            title.getText().toString());
                    intent.putExtra(CalendarContract.Events.DESCRIPTION,
                            description.getText().toString());
                    intent.putExtra(Intent.EXTRA_EMAIL,"oisinlynch2010@gmail.com, oisin.lynch2@mail.itsligo.ie");
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    } else{
                        Toast.makeText(Admin.this,"There is no app that ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Admin.this,"Please fill out the entore form", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void logoutAdmin(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }


    public void setTraining(View view) {
    } {
}
}
