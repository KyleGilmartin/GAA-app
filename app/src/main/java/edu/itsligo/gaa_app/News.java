package edu.itsligo.gaa_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.AdapterView.*;

public class News extends AppCompatActivity {

    ListView listView;
    String mTitle[] = {"Munster SFC Final: Terrific Tipp end 85 year wait for Munster title", "Ulster SFC Final: Cavan's remarkable 2020 adventure continues"};
    String mDescription[] = {"Munster SFC Final Description", "Ulster SFC Final Description"};
    int images[] = {R.drawable.tipperaryfinal, R.drawable.cavanfinal};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        listView = findViewById(R.id.listView);

        NewsAdapter adapter = new NewsAdapter(this, mTitle, mDescription, images);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0)
                {
                    Toast.makeText(News.this, "Munster SFC Final Description", Toast.LENGTH_SHORT).show();
                }

                if (position == 0)
                {
                    Toast.makeText(News.this, "Ulster SFC Final Description", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    class NewsAdapter extends ArrayAdapter<String> {

        Context context;
        String rTitle[], rDescription[];
        int rImages[];

        NewsAdapter (Context c, String title [], String description [], int images[])
        {
            super(c, R.layout.row, R.id.textView1, title);
            this.context = c;
            this.rTitle = title;
            this.rDescription = description;
            this.rImages = images;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView images = row.findViewById(R.id.image);
            TextView newsTitle = row.findViewById(R.id.textView1);
            TextView newsDescription = row.findViewById(R.id.textView2);

            images.setImageResource(rImages[position]);
            newsTitle.setText(rTitle[position]);
            newsDescription.setText(rDescription[position]);


            return row;
        }
    }
}