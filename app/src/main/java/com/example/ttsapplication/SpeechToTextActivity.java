package com.example.ttsapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SpeechToTextActivity extends AppCompatActivity {
    EditText editText;
    private static final int RECOGNIZER = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_to_text);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editText = findViewById(R.id.sptext);
        ImageButton imageButton = findViewById(R.id.speechbutton);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speach to text");
                startActivityForResult(intent,RECOGNIZER);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == RECOGNIZER){
            ArrayList<String> match = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            editText.setText(match.get(0).toString());
            String demo = editText.getText().toString();
            if(demo.equals("open youtube") || demo.equals("open Youtube") || demo.equals("open YouTube")){
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
                startActivity(intent);
            }
            else if(demo.equals("open whatsapp") || demo.equals("open Whatsapp") || demo.equals("open WhatsApp")){
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.whatsapp");
                startActivity(intent);
            }
            else if(demo.equals("open Facebook")|| demo.equals("open facebook") || demo.equals("open FaceBook")){
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.facebook.katana");
                startActivity(intent);
            }
            else if(demo.equals("open Instagram") || demo.equals("open instagram")){
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.instagram.android");
                startActivity(intent);
            }
            else if(demo.equals("open Messenger") || demo.equals("open messenger")){
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.facebook.orca");
                startActivity(intent);
            }
            else if(demo.equals("open snapchat") || demo.equals("open Snapchat")){
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.snapchat.android");
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "App not installed or voice not reconized", Toast.LENGTH_LONG).show();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}