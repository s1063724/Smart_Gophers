package tw.edu.pu.csie.s1063724.smart_gophers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class GamelistActivity extends AppCompatActivity {
    Button color1, fruit1;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //實現返回鍵監聽
        if (item.getItemId() == android.R.id.home) {
            Toast.makeText(this, "RETURN", Toast.LENGTH_SHORT).show();
            finish();
        }
        //實現清單監聽
        switch (item.getItemId()) {
            case R.id.about:
                Toast.makeText(this, "About us", Toast.LENGTH_SHORT).show();

                Intent main2ActivityIntent = new Intent(GamelistActivity.this, menuActivity.class);
                startActivity(main2ActivityIntent);
                break;

            default:
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_gamelist);

        //建返回鍵
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        color1 = (Button) findViewById(R.id.color1);
        color1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main2ActivityIntent = new Intent(GamelistActivity.this, ColorActivity.class);
                startActivity(main2ActivityIntent);
            }
        });

        fruit1 = (Button) findViewById(R.id.fruit1);
        fruit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main2ActivityIntent = new Intent(GamelistActivity.this, DrawActivity.class);
                startActivity(main2ActivityIntent);
            }
        });



    }

    //建立清單
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

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

}