package hr.martin.mirenic.recepies123;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class RecepieDetailActivity extends AppCompatActivity {

    TextView titleA ;
    TextView descriptionA;
    TextView key_wordsA;
    TextView dificultyA;
    TextView createdByA;
    TextView creationDateA;
    TextView textA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recepie_detail);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String id = extras.getString("id");
        String title = extras.getString("title");
        String description = extras.getString("description");
        String keyWords = extras.getString("keyWords");
        String dificulty = extras.getString("dificulty");
        String createdBy = extras.getString("createdBy");
        String creationDate = extras.getString("creationDate");
        String text = extras.getString("text");


        titleA = findViewById(R.id.title);
        descriptionA = findViewById(R.id.description);
        key_wordsA = findViewById(R.id.key_words);
        dificultyA = findViewById(R.id.dificulty);
        createdByA = findViewById(R.id.CreaBy);
        creationDateA = findViewById(R.id.creadate);
        textA = findViewById(R.id.text);

        titleA.setText(title);
        descriptionA.setText(description);
        key_wordsA.setText(keyWords);
        dificultyA.setText(dificulty);
        createdByA.setText(createdBy);
        creationDateA.setText(creationDate);
        textA.setText(text);

    }
}
