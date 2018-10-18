package com.example.chaplin;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ai.api.AIListener;
import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import ai.api.model.AIError;
import ai.api.model.AIResponse;
import ai.api.model.Result;

public class MainActivity extends AppCompatActivity implements AIListener {


    Button SpeakB = (Button) findViewById(R.id.idSpeak);
    private AIService mAIService;
    private TextToSpeech mTextToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final AIConfiguration config = new AIConfiguration("cd2036a87c8843b69c7970d00f912b1e",
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);
        mAIService = AIService.getService(
                this, config);
        mAIService.setListener(this);
        mTextToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

            }
        });

        SpeakB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAIService.startListening();
            }
        });
    }

    @Override
    public void onResult(AIResponse result) {
        Result respuesta = result.getResult();

        mTextToSpeech.speak(respuesta.getFulfillment().getSpeech(), TextToSpeech.QUEUE_FLUSH, null ,null);
    }

    @Override
    public void onError(AIError error) {

    }

    @Override
    public void onAudioLevel(float level) {

    }

    @Override
    public void onListeningStarted() {

    }

    @Override
    public void onListeningCanceled() {

    }

    @Override
    public void onListeningFinished() {

    }
}
