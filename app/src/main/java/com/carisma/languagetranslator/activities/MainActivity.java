package com.carisma.languagetranslator.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.carisma.languagetranslator.R;
import com.carisma.languagetranslator.models.Word;
import com.carisma.languagetranslator.utilities.LanguageFetcher;
import com.carisma.languagetranslator.utilities.LanguageProcessor;

public class MainActivity extends AppCompatActivity {
    LanguageFetcher fetcher;
    LanguageProcessor languageProcessor;

    public LanguageFetcher getFetcher() {
        return fetcher;
    }

    public void setFetcher(LanguageFetcher fetcher) {
        this.fetcher = fetcher;
    }

    public LanguageProcessor getLanguageProcessor() {
        return languageProcessor;
    }

    public void setLanguageProcessor(LanguageProcessor languageProcessor) {
        this.languageProcessor = languageProcessor;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fetcher = new LanguageFetcher(this);

        final Spinner sourceSpinner = (Spinner) findViewById(R.id.spinner_source);
        final Spinner destinationSpinner = (Spinner) findViewById(R.id.spinner_destination);
        setupSpinner1(sourceSpinner);
        setupSpinner2(destinationSpinner);

        convert(sourceSpinner, destinationSpinner);
    }

    private void convert(final Spinner one, final Spinner two) {
        Button convert = (Button) findViewById(R.id.convert_language_button);
        final TextView result = (TextView) findViewById(R.id.result);

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String firstString = one.getSelectedItem().toString();
                final String second = two.getSelectedItem().toString();

                String firstFormat = new LanguageProcessor(MainActivity.this).extractFormat(firstString);
                String secondFormat = new LanguageProcessor(MainActivity.this).extractFormat(second);
                final EditText enteredWordEditText = (EditText) findViewById(R.id.word_input_edit_text);
                final String textInput = enteredWordEditText.getText().toString();

                if (firstString != null && second != null && textInput != null) {

                    fetcher.getWord(textInput, firstFormat, secondFormat, new LanguageFetcher.OnWordObtainedListener() {
                        @Override
                        public void onWordObtained(Word word) {
                            if (word.getText() != null) {
                                Toast.makeText(getApplicationContext(), "converting " + textInput + firstString + " to " + second, Toast.LENGTH_LONG).show();
                                Toast.makeText(getApplicationContext(), word.getText(), Toast.LENGTH_LONG).show();
                                result.setText("Result: " + word.getText());
                            }
                        }
                    });
                } else {
                    enteredWordEditText.setError("Error validating field");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void setupSpinner1(Spinner spinnerFirst) {

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(MainActivity.this,
                R.array.languages, android.R.layout.simple_spinner_dropdown_item);

        spinnerFirst.setAdapter(spinnerAdapter);
    }

    private void setupSpinner2(Spinner spinnerSecond) {

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(MainActivity.this,
                R.array.languages, android.R.layout.simple_spinner_dropdown_item);

        spinnerSecond.setAdapter(spinnerAdapter);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
