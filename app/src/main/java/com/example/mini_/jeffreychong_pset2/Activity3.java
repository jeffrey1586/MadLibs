package com.example.mini_.jeffreychong_pset2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.InputStream;

public class Activity3 extends AppCompatActivity {

    String wordType;
    TextView explainWord;
    Story chosenStory;
    int count;
    TextView wordCount;
    EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        Button confirmButton = findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new buttonClickListener());

        // making new story with the chosen text file
        Intent intent = getIntent();
        chosenStory = (Story) intent.getSerializableExtra("plainText");

        // getting and setting the type of word
        wordType = chosenStory.getNextPlaceholder();
        explainWord = findViewById(R.id.explainWord);
        explainWord.setText(wordType);

        // getting and setting the amount of words that still have to be prompt
        count = chosenStory.getPlaceholderRemainingCount();
        wordCount = findViewById(R.id.wordCount);
        wordCount.setText(String.valueOf(count));

        // setting the word type as a hint
        inputText = findViewById(R.id.fillText);
        inputText.setHint(wordType);
    }

    private class buttonClickListener implements Button.OnClickListener {
        // when the confirm button is clicked
        SharedPreferences.Editor editor = getSharedPreferences("settings", MODE_PRIVATE).edit();
        Intent intent = getIntent();
        Story chosenStory = (Story) intent.getSerializableExtra("plainText");

        @Override
        public void onClick(View v) {
            // saving word in shared preference
            String newWord = inputText.getText().toString();

            if (!newWord.isEmpty()) {
                editor.putString("word", newWord);
                editor.apply();

                // filling the input word in the placeholder
                SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
                String storedWord = prefs.getString("word", "false");
                chosenStory.fillInPlaceholder(storedWord);
                inputText.getText().clear();

                // give the next word type and amount of words left
                wordType = chosenStory.getNextPlaceholder();
                explainWord.setText(wordType);
                inputText.setHint(wordType);
                count = chosenStory.getPlaceholderRemainingCount();
                wordCount.setText(String.valueOf(count));

                // if all words are filled open next activity
                if (chosenStory.isFilledIn()) {
                    openActivity_last();
                }
            }
        }
    }

    // this method is prompt when all words are given by user
    public void openActivity_last() {
        Intent intent = new Intent(Activity3.this, Activity4.class);
        intent.putExtra("fullText", chosenStory.toString());
        startActivity(intent);
    }
}
