package tw.edu.pu.csie.s1063724.smart_gophers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class menuActivity<mPlayer> extends AppCompatActivity {
    Button return1;
    MediaPlayer mPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

//        try{
//            mPlayer = MediaPlayer.create(this, R.raw.music);
//            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//            mPlayer.setLooping(true);
//        }catch (IllegalStateException e) {
//            e.printStackTrace();
//        }

        return1 = (Button) findViewById(R.id.return1);
        return1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
//    @Override
//    protected void onResume()
//    {
//        super.onResume();
//        mPlayer.start();
//    }
//    @Override
//    protected void onPause()
//    {
//        super.onPause();
//        mPlayer.pause();
//    }
//    @Override
//    protected void onDestroy()
//    {
//        super.onDestroy();
//        mPlayer.release();
//    }
}