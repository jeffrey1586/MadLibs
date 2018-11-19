package com.example.mini_.jeffreychong_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.InputStream;

public class Activity3 extends AppCompatActivity {

    InputStream plainStory;
    String name;
    Story story;
    String wordType;
    TextView explainWord;
    int count;
    TextView wordCount;
    EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        // making new story with the chosen text file
        Intent intent = getIntent();
        int plainText = intent.getIntExtra("plainText", 10);
        switch (plainText) {
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

        // getting and setting the type of word
        wordType = story.getNextPlaceholder();
        explainWord = findViewById(R.id.explainWord);
        explainWord.setText(wordType);

        // getting and setting the amount of words that still have to be prompt
        count = story.getPlaceholderRemainingCount();
        wordCount = findViewById(R.id.wordCount);
        wordCount.setText(String.valueOf(count));

        // setting the word type as a hint
        inputText = findViewById(R.id.fillText);
        inputText.setHint(wordType);

        // when the confirm button is clicked
        Button confirmButton = findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking if a word is typed
                String newWord = inputText.getText().toString();
                if (!newWord.isEmpty()) {
                    // filling the input word in the placeholder
                    story.fillInPlaceholder(newWord);
                    inputText.getText().clear();

                    // give the next word type and amount of words left
                    wordType = story.getNextPlaceholder();
                    explainWord.setText(wordType);
                    inputText.setHint(wordType);
                    count = story.getPlaceholderRemainingCount();
                    wordCount.setText(String.valueOf(count));

                    // if all words are filled open next activity
                    if (story.isFilledIn()) {
                        openActivity_last();
                    }
                }
            }
        });
    }

    // this method is prompt when all words are given by user
    public void openActivity_last() {
        Intent intent = new Intent(Activity3.this, Activity4.class);
        intent.putExtra("fullText", story.toString());
        intent.putExtra("name", name);
        startActivity(intent);
    }
}
