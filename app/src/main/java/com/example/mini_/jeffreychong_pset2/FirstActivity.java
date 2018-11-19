package com.example.mini_.jeffreychong_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // open next activity when start button is clicked
        button = findViewById(R.id.startButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity_second();
            }
        });
    }

    // method that is prompt when start button is clicked
    public void openActivity_second() {
        Intent intent = new Intent(FirstActivity.this, Activity2.class);
        startActivity(intent);
    }
}
