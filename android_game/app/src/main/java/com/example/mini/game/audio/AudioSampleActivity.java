package com.example.mini.game.audio;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.mini.game.R;




public class AudioSampleActivity extends ActionBarActivity {
    // path to file
    final String FILE = "/sdcard/external_sd/Music/Billy_Talent/Billy Talent - Diamond on a Landmine with Lyrics.mp3";
    //final String FILE = "/sdcard/red.mp3";
    //final String FILE = "/storage/sdcard0/1hFileTest.mp3";
    //final String FILE = "/sdcard/external_sd/Music/Billy_Talent/explosivo.mp3";
    //final String FILE = "/sdcard/external_sd/Music/samples/jazz.mp3";
    //final String FILE = "/sdcard/external_sd/Music/samples/drum_beat.mp3";
    //final String FILE = "/sdcard/external_sd/Music/samples/snare.mp3";
    //final String FILE = "/sdcard/external_sd/Music/samples/kick.mp3";
    //final String FILE = "/sdcard/external_sd/Music/samples/guitar.mp3";
    //final String FILE = "/sdcard/external_sd/Music/samples/tests/trance1.mp3";
    //final String FILE = "/sdcard/external_sd/Music/samples/tests/queen1.mp3";
    //final String FILE = "/sdcard/external_sd/Music/samples/tests/limit.mp3";
    //final String FILE = "/sdcard/external_sd/Music/samples/tests/madeon1.mp3";

    AudioAnalyser audioAnalyser;
    AudioPlayer audioPlayer;
    boolean isPaused = false;
    final int bufferSize = 1024;

    public static float FLUX_LENGTH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_sample);

        NativeMP3Decoder.initLib();
        audioAnalyser = new AudioAnalyser(FILE, bufferSize, 44100,400);
        FLUX_LENGTH = audioAnalyser.FLUX_LENGTH_MS;
        audioPlayer = new AudioPlayer(FILE, bufferSize, 44100);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_audio_sample, menu);
        return true;
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

    public void pauseMusic(View view)  {
        if(!isPaused) {
            audioPlayer.pauseAudio();
            isPaused = true;
        } else {
            audioPlayer.playAudio();
            isPaused = false;
        }
    }

    public void rewindMusic(View view)  {
        audioPlayer.rewindAudio(2000);
    }

    public void drawGraph(View view)  {
        audioAnalyser.startAnalyzing();
/*
        try {
            audioAnalyser.analyzerThread.join();
        }catch(InterruptedException e){e.printStackTrace();}

        int size = audioAnalyser.getCurrentSpectralFluxSize();

        GraphView.GraphViewData[] data = new GraphView.GraphViewData[audioAnalyser.getCurrentSpectralFluxSize()];
        for(int i = 0;i < data.length; i++) {
            long time = audioAnalyser.getTimeOfFlux(i);
            float flux = audioAnalyser.getFluxAt(i);
            float bump = audioAnalyser.getBumper().getNextBump();
            //Log.i("Graph","Bump[" + i +"]: " + bump);
            data[i] = new GraphView.GraphViewData(time, bump);
            //data[i] = new GraphView.GraphViewData(audioAnalyser.getTimeOfFlux(i), flux);
        }
        GraphViewSeries exampleSeries = new GraphViewSeries(data);

        GraphView graphView = new LineGraphView(
                this // context
                , "Flux" // heading
        );
        graphView.addSeries(exampleSeries); // data

        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);
        layout.addView(graphView);
        */
    }

    public void startMusic(View view) throws Exception {
        audioPlayer.startDecoding();
        audioPlayer.playAudio();
    }
}
