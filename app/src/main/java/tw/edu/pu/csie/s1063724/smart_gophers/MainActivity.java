package tw.edu.pu.csie.s1063724.smart_gophers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity<mPlayer> extends AppCompatActivity {
    Button btn;
    MediaPlayer mPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, GamelistActivity.class);
                startActivity(it);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("Reminder");
            builder.setMessage("Do you wanna exit?");

            //设置确定按钮
            builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            //设置取消按钮
            builder.setPositiveButton("Wait",null);
            //显示提示框
            builder.show();
        }
        return super.onKeyDown(keyCode, event);
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
    //正常退出
    @Override
    protected void onDestroy(){
        super.onDestroy();
        mPlayer.release();
        System.exit(0);
    }
}
