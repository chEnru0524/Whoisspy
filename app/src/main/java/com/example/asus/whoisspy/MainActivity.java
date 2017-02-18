package com.example.asus.whoisspy;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.provider.BaseColumns._ID;
import static com.example.asus.whoisspy.Mysql_Database.QUESTION1;
import static com.example.asus.whoisspy.Mysql_Database.QUESTION2;
import static com.example.asus.whoisspy.Mysql_Database.TABLE_NAME;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //初始介面
    private Button start;
    private Button append;
    private Button exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button)findViewById(R.id.start);
        append = (Button)findViewById(R.id.append);
        exit = (Button)findViewById(R.id.exit);
        start.setOnClickListener(this);
        append.setOnClickListener(this);
        exit.setOnClickListener(this);
        /*start.setOnClickListener(new Button.OnClickListener(){

            @Override

            public void onClick(View v) {
                //設定人數
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,setting.class);
                startActivity(intent);
                MainActivity.this.finish();


            }

        });
        append.setOnClickListener(new Button.OnClickListener(){

            @Override

            public void onClick(View v) {

            }

        });
        exit.setOnClickListener(new Button.OnClickListener(){

            @Override

            public void onClick(View v) {
                finish();
            }

        });*/

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            // v這個參數讀出Button的id
            case R.id.start:{
                //設定人數
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,setting.class);
                startActivity(intent);
                MainActivity.this.finish();
                break;
            }
            case R.id.append:{
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,append.class);
                startActivity(intent);
                MainActivity.this.finish();
                break;
            }
            case R.id.exit: {
                android.os.Process.killProcess(android.os.Process.myPid());
                //System.exit(0);
                break;
            }
        }
    }

}
