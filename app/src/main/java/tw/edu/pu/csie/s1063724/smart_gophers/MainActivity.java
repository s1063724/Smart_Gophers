package tw.edu.pu.csie.s1063724.smart_gophers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity<mPlayer> extends AppCompatActivity {
    MediaPlayer mPlayer;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try{
            mPlayer = MediaPlayer.create(this, R.raw.music);
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mPlayer.setLooping(true);
        }catch (IllegalStateException e)
        {
            e.printStackTrace();
        }
    }


    @Override
    protected void onResume()
    {
        super.onResume();
        mPlayer.start();
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        mPlayer.pause();
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mPlayer.release();
    }
}