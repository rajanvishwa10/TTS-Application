package com.example.ttsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private TextToSpeech textToSpeech;
    private EditText editText;
    private SeekBar seekBar,seekBar2;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                 if(status ==  TextToSpeech.SUCCESS){
                     int result = textToSpeech.setLanguage(Locale.US);
                     if((result == TextToSpeech.LANG_MISSING_DATA) || (result == TextToSpeech.LANG_NOT_SUPPORTED) ){
                         Toast.makeText(MainActivity.this, "Language not supported", Toast.LENGTH_SHORT).show();
                         Set<String> a=new HashSet<>();
                         a.add("male");
                         Voice v=new Voice("en-us-x-sfg#male_2-local",new Locale("en","US"),400,200,true, a);
                         textToSpeech.setVoice(v);
                     }
                     else {
                         button.setEnabled(true);
                     }
                 }else {
                     Toast.makeText(MainActivity.this, "Initialization failed", Toast.LENGTH_SHORT).show();
                 }
            }
        });
        editText = findViewById(R.id.edittext);
        seekBar = findViewById(R.id.seekbaar);
        seekBar2 = findViewById(R.id.seekbaar2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                if(text.isEmpty()){
                    editText.setError("Enter Text");
                    editText.requestFocus();
                }
                float pitch = (float) seekBar.getProgress() / 50;
                if(pitch < 0.1){
                    pitch = 0.1f;
                }
                float speed = (float) seekBar2.getProgress() / 50;
                if(speed < 0.1){
                    speed = 0.1f;
                }
                textToSpeech.setPitch(pitch);
                textToSpeech.setSpeechRate(speed);
                textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }

    @Override
    protected void onDestroy() {
        if(textToSpeech != null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }

    public void spt(View view) {
        Intent intent = new Intent(this, SpeechToTextActivity.class);
        startActivity(intent);
    }
}