package com.example.lab_4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.Set;

public class DeleteNoteActivity extends AppCompatActivity {

    Spinner spinner;
    ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        this.spinner = findViewById(R.id.spinner);

        SharedPreferences sharedPref = this.getSharedPreferences(Constants.NOTES_FILE, Context.MODE_PRIVATE);
        Set<String> set = sharedPref.getStringSet(Constants.NOTES_ARRAY_KEY, null);
        arrayList.addAll(set);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    @SuppressLint("MutatingSharedPrefs")
    public void deleteNote(View view) {
        SharedPreferences sharedPref = this.getSharedPreferences(Constants.NOTES_FILE, Context.MODE_PRIVATE);
        String noteToDelete = spinner.getSelectedItem().toString();
        Set<String> set = sharedPref.getStringSet(Constants.NOTES_ARRAY_KEY, null);
        if(set != null) {
            set.remove(noteToDelete);
        }
        String[] parts = noteToDelete.split(" :");
        sharedPref.edit().remove(parts[0]).apply();

        finish();
    }

    public void deleteAll(View view) {
        SharedPreferences sharedPref = this.getSharedPreferences(Constants.NOTES_FILE, Context.MODE_PRIVATE);
        sharedPref.edit().clear().apply();
        finish();
    }
}