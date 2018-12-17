package com.example.mini_.jeffreychong_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        // getting the intent with the full story and story name
        Intent intent = getIntent();
        String text = intent.getStringExtra("fullText");
        String name = intent.getStringExtra("name");

        // setting the full, filled text on the screen
        TextView nameStory = findViewById(R.id.storyName);
        nameStory.setText(name);
        TextView storyText = findViewById(R.id.storyText);
        storyText.setMovementMethod(new ScrollingMovementMethod());
        storyText.setText(text);

        // when reset button is clicked
        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity_second();
            }
        });
    }

    // brings the user to the second activity, to start new story
    public void openActivity_second(){
        Intent intent_second = new Intent(Activity4.this, Activity2.class);
        startActivity(intent_second);
    }

    @Override
    public void onBackPressed() {
        Intent toHomeScreen = new Intent(this, FirstActivity.class);
        startActivity(toHomeScreen);
    }
}
