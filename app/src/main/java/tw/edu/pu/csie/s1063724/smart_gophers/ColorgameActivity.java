package tw.edu.pu.csie.s1063724.smart_gophers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.Random;

public class ColorgameActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    public static final int CODE = 999;
    public static final int RANDOM_NUMBER = 500;
    private TextView mTextView;
    private Button mButton;
    private ImageView mImageView;

    /**
     * 定义地鼠的位置
     */
    public int[][] mPosition = new int[][]{
            {32, 180}, {432, 456}, {521, 256}, {429, 503},
            {456, 222}, {145, 321}, {123, 556}, {342, 200},
    };

    private int mTotalCount, mSuccessCount = 0;
    public static final int MAX_COUNT = 20;

    private MyHandler mMyHandler = new MyHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colorgame);
        initView();
    }

    private void initView() {
        mTextView = findViewById(R.id.textView);
        mButton = findViewById(R.id.button);
        mImageView = findViewById(R.id.imageView);
        mButton.setOnClickListener(this);
        mImageView.setOnTouchListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                start();
                break;
        }
    }

    private void start() {
        mTextView.setText("遊戲開始!");
        mButton.setText("遊戲中...");
        mButton.setEnabled(false);
        //发送消息
        next(RANDOM_NUMBER);
    }

    private void next(int delayTime) {
        //产生一个0——数组长度的随机数
        int positon = new Random().nextInt(mPosition.length);
        Message message = Message.obtain();
        message.what = CODE;
        message.arg1 = positon;
        mMyHandler.sendMessageDelayed(message, delayTime);
        //每发送一次消息，总数就加一
        mTotalCount++;
    }

    /**
     * 图片点击事件
     *
     * @param v
     * @param event
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //每次触碰到地鼠，则地鼠消失，打到地鼠的数量加一
        v.setVisibility(View.GONE);
        mSuccessCount++;
        mTextView.setText("打到了" + mSuccessCount + "隻，共" + MAX_COUNT + "隻");
        return false;
    }

    public static class MyHandler extends Handler {
        private final WeakReference<ColorgameActivity> mWeakReference;

        public MyHandler(ColorgameActivity activity) {
            this.mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            ColorgameActivity activity = mWeakReference.get();
            super.handleMessage(msg);
            switch (msg.what) {
                case CODE:
                    if (activity.mTotalCount > MAX_COUNT) {
                        //游戏结束，初始化游戏
                        activity.clear();
                        Toast.makeText(activity, "地鼠打完了!!", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        int position = msg.arg1;
                        activity.mImageView.setX(activity.mPosition[position][0]);
                        activity.mImageView.setY(activity.mPosition[position][1]);
                        activity.mImageView.setVisibility(View.VISIBLE);
                        //在随机位置上显示地鼠之后，再次发送消息
                        int randomTime = new Random().nextInt(RANDOM_NUMBER) + RANDOM_NUMBER;
                        activity.next(randomTime);
                    }
                    break;
            }
        }
    }

    /**
     * 游戏结束，初始化游戏
     */
    private void clear() {
        mTotalCount = 0;
        mSuccessCount = 0;
        mImageView.setVisibility(View.GONE);
        mButton.setText("點擊開始");
        mButton.setEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //實現清單監聽
        switch (item.getItemId()) {
            case R.id.about:
                Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show();
                Intent main2ActivityIntent = new Intent(ColorgameActivity.this, menuActivity.class);
                startActivity(main2ActivityIntent);
                break;

            default:
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
            builder.setPositiveButton("Wait", null);
            //显示提示框
            builder.show();
        }
        return super.onKeyDown(keyCode, event);
    }

    //返回遊戲清單
    protected void onDestroy() {
        super.onDestroy();
        System.exit(0);
    }
}