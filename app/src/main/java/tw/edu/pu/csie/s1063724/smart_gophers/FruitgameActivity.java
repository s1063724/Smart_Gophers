package tw.edu.pu.csie.s1063724.smart_gophers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class FruitgameActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //實現清單監聽
        switch (item.getItemId()) {
            case R.id.about:
                Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show();
                Intent main2ActivityIntent = new Intent(FruitgameActivity.this, menuActivity.class);
                startActivity(main2ActivityIntent);
                break;

            default:
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruitgame);
    }

    //建立清單
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("Reminder");
            builder.setMessage("Don't you wanna play?");

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

    //返回遊戲清單
    protected void onDestroy(){
        super.onDestroy();
        System.exit(0);
    }
}
