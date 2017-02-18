package com.example.asus.whoisspy;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import static android.provider.BaseColumns._ID;
import static com.example.asus.whoisspy.Mysql_Database.QUESTION1;
import static com.example.asus.whoisspy.Mysql_Database.TABLE_NAME;

public class Delete extends AppCompatActivity {
    private List<TextView> question_view;
    private TableLayout scene;
    private Mysql_Database mysql_database=null;
    private int check_delete=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        question_view = new ArrayList<>();
        scene=(TableLayout) findViewById(R.id.question_Table);
        mysql_database=new Mysql_Database(Delete.this);
        mysql_database.close();
        for (; check_delete <Database.questions.size();check_delete++) {
            final TextView temp = new TextView(this);
            temp.setId(check_delete);
            String tt = Database.questions.get(check_delete).getQuestion1()+"\n"+Database.questions.get(check_delete).getQuestion2();
            temp.setText(tt);
            temp.setClickable(true);
            temp.setTextSize(25);
            temp.setSingleLine(false);
            //找到螢幕大小
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            //將每個view設為1/3螢幕
            temp.setHeight(metrics.heightPixels / 3);
            temp.setWidth(metrics.widthPixels / 3);
            //temp.getLayoutParams().height=50;
            temp.setGravity(Gravity.CENTER);
            temp.setOnClickListener(new TextView.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //點擊刪除題目
                    //int i_id=v.getId();
                    //String delete_question1=Database.questions.get(check_delete).getQuestion1();

                    //Toast.makeText(Delete.this,id,Toast.LENGTH_SHORT).show();
                    //System.out.print(id);
                    if(deleteTitle(check_delete+1))
                        Toast.makeText(Delete.this,"刪除題目",Toast.LENGTH_SHORT).show();
                    else
                    {
                        Toast.makeText(Delete.this,"刪除失敗",Toast.LENGTH_SHORT).show();
                        Delete_All();
                    }
                    Database.questions.clear();
                    Database.load_datas(Delete.this);

                }

            });
            question_view.add(temp);
        }
        check_delete=0;
        int rows = Database.questions.size() / 3;
        if (Database.questions.size() % 3 != 0)
            rows++;
        int j = 0;
        int k = 0;
        for (int i = 1; i <= rows; i++) {
            TableRow tr = new TableRow(this);
            k = j + 3;
            for (; j < k; j++) {
                if (j == Database.questions.size()) {
                    break;
                }
                tr.addView(question_view.get(j));
            }
            scene.addView(tr);

        }

    }
    public boolean deleteTitle(int id)
    {
        //String where="="+String.valueOf(id);
        mysql_database=new Mysql_Database(Delete.this);
        SQLiteDatabase db = mysql_database.getWritableDatabase();
        //db.delete(TABLE_NAME, null, null);
        return db.delete(TABLE_NAME,"_ID=?", new String[]{Integer.toString(id)})>0;
    }
    public boolean Delete_All()
    {
        Mysql_Database mysql_database=new Mysql_Database(Delete.this);
        SQLiteDatabase db = mysql_database.getWritableDatabase();
        return db.delete(TABLE_NAME, null, null)>0;
    }
    //返回鍵處理
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent myIntent;
            myIntent = new Intent(Delete.this, MainActivity.class);
            startActivity(myIntent);
            Delete.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}


