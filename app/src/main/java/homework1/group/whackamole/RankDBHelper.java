package homework1.group.whackamole;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class RankDBHelper extends SQLiteOpenHelper{

    private final static String TableName = "RankTable";
    private final static String User_col = "UserName";
    private final static String Score_col = "UserScore";
    private final static String CREATE_TABLE = "CREATE TABLE " + TableName + " ("  +
                                                User_col + " TEXT NOT NULL, " +
                                                Score_col + " TEXT NOT NULL);";
    public RankDBHelper(Context context, String name, CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TableName);
        onCreate(db);
    }

    public long insertRec(String UserName, String UserScore){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues rec = new ContentValues();
        rec.put("UserName", UserName);
        rec.put("UserScore", UserScore);
        long rowID = db.insert(TableName, null, rec);
        db.close();
        return rowID;
    }

    public ArrayList<String> getRecSet(){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM " + TableName + " ORDER BY " + Score_col + " DESC;";
        Cursor recSet = db.rawQuery(sql, null);
        ArrayList<String> recAry = new ArrayList<String>();
        int colCount = recSet.getColumnCount();
        while(recSet.moveToNext()){
            String fldSet = "";
            for(int i=0; i<colCount; i++){
                fldSet += recSet.getString(i) + "#";
            }
            recAry.add(fldSet);
        }
        recSet.close();
        db.close();
        return recAry;
    }
}
