package com.example.irobot.voicerecognizer;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ImageButton openMic;
    private TextView showVoiceText;
    private final int Req_Code_Speech_Output = 143;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openMic = (ImageButton) findViewById(R.id.imageButton3);
        showVoiceText= (TextView) findViewById(R.id.showVoiceOutput);

        openMic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonToOpenMic();
                //showNotification();
            }
        });
    }


    /*private void showNotification(){
         NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
                    builder.setContentTitle("Search");
                    builder.setContentText("Completed!");
                    builder.setDefaults(Notification.DEFAULT_ALL);
        builder.setSmallIcon(R.drawable.music);


        /*Intent intent = new Intent();

        PendingIntent pi = PendingIntent.getActivity(this,0, intent,0);
        builder.setContentIntent(pi);

                 Notification notification = builder.build();
                    NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                    notificationManager.notify(101,notification);
    }*/

    private void buttonToOpenMic(){

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Speak Now");
       // showNotification();

        try{
            startActivityForResult(intent,Req_Code_Speech_Output);


        }catch(ActivityNotFoundException tim){

        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case Req_Code_Speech_Output : {
                if (resultCode == RESULT_OK && null!= data){



                    ArrayList<String> voiceInText = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    showVoiceText.setText(voiceInText.get(0));
                    String serachContent = voiceInText.get(0);
                    Intent search = new Intent(Intent.ACTION_WEB_SEARCH);
                    search.putExtra(SearchManager.QUERY,serachContent);
                    startActivity(search);
                }

            }
        }
    }
}

