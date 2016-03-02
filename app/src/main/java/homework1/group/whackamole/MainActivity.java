package homework1.group.whackamole;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textView_Time,textView_Score;

    private ImageButton imgBtn_mole1,imgBtn_mole2,imgBtn_mole3;
    private ImageButton imgBtn_mole4,imgBtn_mole5,imgBtn_mole6;
    private ImageButton imgBtn_mole7,imgBtn_mole8,imgBtn_mole9;

    private Button btn_start;

    private int score = 0;
    private int shimao = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_Time = (TextView) findViewById(R.id.textView);
        textView_Score = (TextView) findViewById(R.id.textView2);

        imgBtn_mole1 = (ImageButton) findViewById(R.id.imageButton1);
        imgBtn_mole2 = (ImageButton) findViewById(R.id.imageButton2);
        imgBtn_mole3 = (ImageButton) findViewById(R.id.imageButton3);
        imgBtn_mole4 = (ImageButton) findViewById(R.id.imageButton4);
        imgBtn_mole5 = (ImageButton) findViewById(R.id.imageButton5);
        imgBtn_mole6 = (ImageButton) findViewById(R.id.imageButton6);
        imgBtn_mole7 = (ImageButton) findViewById(R.id.imageButton7);
        imgBtn_mole8 = (ImageButton) findViewById(R.id.imageButton8);
        imgBtn_mole9 = (ImageButton) findViewById(R.id.imageButton9);

        btn_start = (Button) findViewById(R.id.button);

        btn_start.setOnClickListener(startBtnLst);
        imgBtn_mole1.setOnClickListener(imgBtnLst);
        imgBtn_mole2.setOnClickListener(imgBtnLst);
        imgBtn_mole3.setOnClickListener(imgBtnLst);
        imgBtn_mole4.setOnClickListener(imgBtnLst);
        imgBtn_mole5.setOnClickListener(imgBtnLst);
        imgBtn_mole6.setOnClickListener(imgBtnLst);
        imgBtn_mole7.setOnClickListener(imgBtnLst);
        imgBtn_mole8.setOnClickListener(imgBtnLst);
        imgBtn_mole9.setOnClickListener(imgBtnLst);

        setImgBtnEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_description){
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle(R.string.menu_description)
                    .setMessage(R.string.menu_description_msg)
                    .setCancelable(false)
                    .setPositiveButton(R.string.dialog_positiveBtn, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
        }
        else if(item.getItemId()==R.id.menu_level){
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle(R.string.menu_level)
                    .setMessage(R.string.menu_level_msg)
                    .setCancelable(false)
                    .setPositiveButton(R.string.dialog_easy, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            shimao = 1;
                        }
                    })
                    .setNegativeButton(R.string.dialog_hard, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            shimao = 5;
                        }

                    }).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private View.OnClickListener startBtnLst = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            btn_start.setVisibility(View.INVISIBLE);

            new CountDownTimer(30000,1000){
                @Override
                public void onTick(long millisUntilFinished) {
                    textView_Time.setText("Time: "+(millisUntilFinished/1000)+" s");
                    setImgBtnEnabled(false);

                    Random random = new Random();
                    int num = random.nextInt(shimao)+1;

                    HashSet<Integer> randomSet = new HashSet<Integer>();
                    for(int i=0; i<num; ++i){
                        int n = 0;
                        do {
                            n = random.nextInt(9)+1;
                        }while (!randomSet.add(n));
                    }

                    for(int i:randomSet){
                        setImgBtnEnabled(i,true);
                    }
                }

                @Override
                public void onFinish() {
                    textView_Time.setText("Time: 0 s");
                    shimao = 1;
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle(R.string.dialog_title)
                            .setMessage("獲得分數: "+score+" 分")
                            .setCancelable(false)
                            .setPositiveButton(R.string.dialog_positiveBtn, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    btn_start.setVisibility(View.VISIBLE);
                                    textView_Time.setText(R.string.timer_init);
                                    textView_Score.setText(R.string.score_init);
                                    setImgBtnEnabled(false);

                                    score = 0;
                                }
                            }).show();
                }
            }.start();
        }
    };

    private View.OnClickListener imgBtnLst = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            score += 5;
            textView_Score.setText("Score: "+score+" 分");
            ImageButton clickImgBtn = (ImageButton) v;
            clickImgBtn.setEnabled(false);
        }
    };

    public void setImgBtnEnabled(boolean b){
        imgBtn_mole1.setEnabled(b);
        imgBtn_mole2.setEnabled(b);
        imgBtn_mole3.setEnabled(b);
        imgBtn_mole4.setEnabled(b);
        imgBtn_mole5.setEnabled(b);
        imgBtn_mole6.setEnabled(b);
        imgBtn_mole7.setEnabled(b);
        imgBtn_mole8.setEnabled(b);
        imgBtn_mole9.setEnabled(b);
    }

    public void setImgBtnEnabled(int num, boolean b){
        switch (num)
        {
            case 1:imgBtn_mole1.setEnabled(b);
                break;
            case 2:imgBtn_mole2.setEnabled(b);
                break;
            case 3:imgBtn_mole3.setEnabled(b);
                break;
            case 4:imgBtn_mole4.setEnabled(b);
                break;
            case 5:imgBtn_mole5.setEnabled(b);
                break;
            case 6:imgBtn_mole6.setEnabled(b);
                break;
            case 7:imgBtn_mole7.setEnabled(b);
                break;
            case 8:imgBtn_mole8.setEnabled(b);
                break;
            case 9:imgBtn_mole9.setEnabled(b);
                break;
        }
    }
}
