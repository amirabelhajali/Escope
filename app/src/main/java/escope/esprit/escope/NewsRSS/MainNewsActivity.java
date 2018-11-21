package escope.esprit.escope.NewsRSS;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import escope.esprit.escope.R;


public class MainNewsActivity  extends AppCompatActivity {
        RecyclerView recyclerView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_news);
            recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            Feed feed = new Feed(this, recyclerView);
            feed.execute();
        }
    }
