package com.example.lab_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.HashSet;
import java.util.Set;

public class AddNoteActivity extends AppCompatActivity {

    EditText nameNote;
    EditText textNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        this.nameNote = findViewById(R.id.nameNote);
        this.textNote = findViewById(R.id.textNote);
    }

    public void saveNote(View view) {
        String noteNameToAdd = this.nameNote.getText().toString();
        String noteTextToAdd = this.textNote.getText().toString();
        if (TextUtils.isEmpty(noteNameToAdd) || TextUtils.isEmpty(noteTextToAdd)) {

            Toast.makeText(getApplicationContext(), "The EditText field is empty.", Toast.LENGTH_LONG).show();
        }
        else{
            SharedPreferences sharedPref = this.getSharedPreferences(Constants.NOTES_FILE, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();

            Set<String> savedSet = sharedPref.getStringSet(Constants.NOTES_ARRAY_KEY, null);
            Set<String> newSet = new HashSet<>();
            if(savedSet != null) {
                newSet.addAll(savedSet);
            }
            newSet.add(noteNameToAdd+" : "+noteTextToAdd);

            editor.putString(noteNameToAdd, noteTextToAdd);
            editor.putStringSet(Constants.NOTES_ARRAY_KEY, newSet);
            editor.apply();

            finish();
        }
    }
}