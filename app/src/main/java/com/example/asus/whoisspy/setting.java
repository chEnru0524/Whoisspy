package com.example.asus.whoisspy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Random;

public class setting extends AppCompatActivity {
    //設定人數
    private Spinner joinernum;
    private Spinner spynum;
    private Spinner nonum;
    private Button start_game;
    private Integer[] select_spy_num={1,2,3};
    private Integer[] select_nothing_num={0,1,2};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        //傳資料用
        //Bundle bundle=new Bundle();
        //Toast.makeText(setting.this,"請設定",Toast.LENGTH_SHORT).show();
        /*Database.now_join_num = 1;
        Database.spy_positions.clear();
        Database.nothing_positions.clear();*/
        joinernum=(Spinner)findViewById(R.id.joinernum);
        spynum=(Spinner)findViewById(R.id.spynum);
        nonum=(Spinner)findViewById(R.id.nonum);
        start_game=(Button)findViewById(R.id.start_game);
        final Integer[] select_num={4,5,6,7,8,9,10,11,12,13,14};

        //有id、question1、question2
        /*for(int i=0;i<Database.questions.size();i++) {
            System.out.print(Database.questions.get(i).getQuestion1()+" "+Database.questions.get(i).getQuestion2());
           Toast.makeText(setting.this,Database.questions.size()+" "+ Database.questions.get(i).getQuestion1()+" "+Database.questions.get(i).getQuestion2(), Toast.LENGTH_SHORT).show();

        }*/
        final ArrayAdapter<Integer> all_num=new ArrayAdapter<Integer>(setting.this,android.R.layout.simple_spinner_dropdown_item,select_num);
        ArrayAdapter<Integer> spy_num=new ArrayAdapter<Integer>(setting.this,android.R.layout.simple_spinner_dropdown_item,select_spy_num);
        ArrayAdapter<Integer> nothing_num=new ArrayAdapter<Integer>(setting.this,android.R.layout.simple_spinner_dropdown_item,select_nothing_num);

        joinernum.setAdapter(all_num);

        spynum.setAdapter(spy_num);
        nonum.setAdapter(nothing_num);
        //選擇總人數
        joinernum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Database.allnum = select_num[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //選擇臥底人數
        spynum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Database.spynum = select_spy_num[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //選擇白板人數
        nonum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Database.nothing = select_nothing_num[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        start_game.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {


                //Random random_which_question=new Random();
                //random 0-14 spy position and nothing position
                Database.Random_Values();
                //random nothing position
                /*for(int i=0;i<Database.nothing;i++)
                {
                    Database.nothing_positions.add((int)(Math.random()*(9-3+1)+3));
                }*/
                //Toast.makeText(setting.this,String.valueOf(ran[0]),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.setClass(setting.this,joiner_saw.class);
                startActivity(intent);
                setting.this.finish();
            }

        });


    }
    //返回鍵處理
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent myIntent ;
            myIntent = new Intent(setting.this, MainActivity.class);
            startActivity(myIntent);
            setting.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
