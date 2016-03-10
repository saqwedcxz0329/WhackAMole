package homework1.group.whackamole;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Louis on 2016/3/10.
 */
public class RankActivity extends AppCompatActivity{

    private TextView rank_1, rank_2, rank_3, rank_4, rank_5;

    private final static int DBVersion = 1;
    private final static String DBName = "Rank.db";
    private RankDBHelper dbHelper;
    private ArrayList<String> recSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        ViewInit();
    }

    private void ViewInit(){
        rank_1 = (TextView)findViewById(R.id.rank_1);
        rank_2 = (TextView)findViewById(R.id.rank_2);
        rank_3 = (TextView)findViewById(R.id.rank_3);
        rank_4 = (TextView)findViewById(R.id.rank_4);
        rank_5 = (TextView)findViewById(R.id.rank_5);
    }

    @Override
    public void onResume(){
        super.onResume();
        if(dbHelper == null){
            dbHelper = new RankDBHelper(this, DBName, null, DBVersion);
        }
        recSet = dbHelper.getRecSet();
        showRec();
    }

    @Override
    public void onPause(){
        super.onPause();
        if(dbHelper != null){
            dbHelper.close();
            dbHelper = null;
        }
        recSet.clear();
    }

    private void showRec(){
        if(recSet.size() != 0){
            for(int i=0; i<recSet.size(); i++){
                String[] fld = recSet.get(i).split("#");
                switch (i){
                    case 0:
                        rank_1.setTextColor(Color.RED);
                        rank_1.setText(fld[0] + " " + fld[1]);
                        break;
                    case 1:
                        rank_2.setTextColor(Color.GREEN);
                        rank_2.setText(fld[0] + " " + fld[1]);
                        break;
                    case 2:
                        rank_3.setTextColor(Color.BLUE);
                        rank_3.setText(fld[0] + " " + fld[1]);
                        break;
                    case 3:
                        rank_4.setText(fld[0] + " " + fld[1]);
                        break;
                    case 4:
                        rank_5.setText(fld[0] + " " + fld[1]);
                        break;
                }
            }
        }
        else{
            rank_1.setText("");
            rank_2.setText("");
            rank_3.setText("");
            rank_4.setText("");
            rank_5.setText("");
        }
    }
}
