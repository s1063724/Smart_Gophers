package tw.edu.pu.csie.s1063724.smart_gophers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.SyncStateContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.media.AudioManager;
import android.media.MediaPlayer;
public class ColorActivity<mPlayer> extends AppCompatActivity {
    Button brown1;
    MediaPlayer mPlayer;
    //實踐返回鍵
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Toast.makeText(this, "RETURN", Toast.LENGTH_SHORT).show();
            finish();
        }

        //實現清單監聽
        switch (item.getItemId()) {
            case R.id.about:
                Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show();
                Intent main2ActivityIntent = new Intent(ColorActivity.this, menuActivity.class);
                startActivity(main2ActivityIntent);
                break;

            default:
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
        try{
            mPlayer = MediaPlayer.create(this, R.raw.music);
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mPlayer.setLooping(true);
        }catch (IllegalStateException e)
        {
            e.printStackTrace();
        }
        //建返回鍵
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        brown1 = (Button) findViewById(R.id.brown1);
        brown1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main2ActivityIntent = new Intent(ColorActivity.this, ColorgameActivity.class);
                startActivity(main2ActivityIntent);
            }
        });
    }

    //建立清單
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
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
