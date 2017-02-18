package com.example.asus.whoisspy;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.asus.whoisspy.Mysql_Database.QUESTION1;
import static com.example.asus.whoisspy.Mysql_Database.QUESTION2;
import static com.example.asus.whoisspy.Mysql_Database.TABLE_NAME;

public class append extends AppCompatActivity {
    private Button set_new;
    private EditText qt1;
    private EditText qt2;
    private Mysql_Database dbhelper=new Mysql_Database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_append);
        set_new=(Button)findViewById(R.id.set);
        qt1=(EditText) findViewById(R.id.input1);
        qt2=(EditText) findViewById(R.id.input2);
        //String question1=qt1.getText().toString();
        //String question2=qt2.getText().toString();

        set_new.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                //加入sqlite
                if("".equals(qt1.getText().toString().trim())||"".equals(qt2.getText().toString().trim()))
                {
                    Toast.makeText(append.this,"輸入不可為空值",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Add_New_Data();
                    Toast.makeText(append.this,"成功加入",Toast.LENGTH_SHORT).show();
                }
                Intent myIntent;
                myIntent = new Intent(append.this, MainActivity.class);
                startActivity(myIntent);
                append.this.finish();

            }

        });
    }
    //新增資料
    public void Add_New_Data()
    {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(QUESTION1,qt1.getText().toString());
        values.put(QUESTION2,qt2.getText().toString());
        Toast.makeText(append.this,qt1.getText().toString()+" "+qt2.getText().toString(), Toast.LENGTH_SHORT).show();

        db.insert(TABLE_NAME,null,values);

    }
    //返回鍵處理
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent myIntent;
            myIntent = new Intent(append.this, MainActivity.class);
            startActivity(myIntent);
            append.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
