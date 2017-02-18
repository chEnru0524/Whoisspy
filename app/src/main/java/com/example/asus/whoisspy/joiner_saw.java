package com.example.asus.whoisspy;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class joiner_saw extends AppCompatActivity {
    //看幾號
    private TextView number;
    private TextView re;
    private Button me;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joiner_saw);

        number=(TextView)findViewById(R.id.number);
        re=(TextView)findViewById(R.id.re);
        me=(Button)findViewById(R.id.me);

        number.setText(Database.now_join_num+"號");
        re.setText("請交給"+Database.now_join_num+"號");
        Database.now_join_num++;
        me.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(joiner_saw.this,question.class);
                startActivity(intent);
                joiner_saw.this.finish();
            }

        });

    }

    //返回鍵處理
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Database.now_join_num = 1;
            Intent myIntent ;
            myIntent = new Intent(joiner_saw.this, setting.class);
            startActivity(myIntent);
            joiner_saw.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
