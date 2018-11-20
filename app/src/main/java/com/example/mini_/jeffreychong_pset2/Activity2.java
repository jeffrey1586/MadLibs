package com.example.mini_.jeffreychong_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.io.InputStream;
import java.io.Serializable;

public class Activity2 extends AppCompatActivity {

    private Spinner spinner;
    int storyNumber;
    Story story;
    InputStream plainStory;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        // making list of story titles
        spinner = findViewById(R.id.spinner);
        String[] items = new String[]{"", "Simple", "Tarzan", "University", "Clothes", "Dance"};

        // making and setting the adapter for the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Activity2.this,
                android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // the click listener for the spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // checking if a legitimate title is chosen
                storyNumber = position;

                // when clicked on OK button
                Button storyButton = findViewById(R.id.storyButton);
                storyButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (storyNumber != 0) {
                            switch (storyNumber) {
                                case 1:
                                    plainStory = getResources().openRawResource(R.raw.madlib0_simple);
                                    name = "Simple";
                                    break;
                                case 2:
                                    plainStory = getResources().openRawResource(R.raw.madlib1_tarzan);
                                    name = "Tarzan";
                                    break;
                                case 3:
                                    plainStory = getResources().openRawResource(R.raw.madlib2_university);
                                    name = "University";
                                    break;
                                case 4:
                                    plainStory = getResources().openRawResource(R.raw.madlib3_clothes);
                                    name = "Clothes";
                                    break;
                                case 5:
                                    plainStory = getResources().openRawResource(R.raw.madlib4_dance);
                                    name = "Dance";
                                    break;
                            }
                            story = new Story(plainStory);
                            openActivity_next();
                        }
                    }
                });
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    // this method is prompt to convert the title and the user to the next activity
    public void openActivity_next() {
        Intent intent = new Intent(Activity2.this, Activity3.class);
        intent.putExtra("plainText", story);
        intent.putExtra("name", name);
        startActivity(intent);
    }
}
