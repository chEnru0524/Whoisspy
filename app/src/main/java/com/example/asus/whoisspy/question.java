package com.example.asus.whoisspy;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class question extends AppCompatActivity {
    //看題目
    private TextView question_output;
    private Button rem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        question_output=(TextView)findViewById(R.id.question_output);
        rem=(Button)findViewById(R.id.rem);
        //透過比較要顯示哪個
        int i=Database.now_join_num-1;
        Integer tp = Integer.valueOf(i);
        //int temp=i;
        int check_spy=0;
        int check_nothing=0;
        //System.out.println(Database.getSpy_positions().get(0));
            /*if(Integer.compare(tp,Database.getSpy_positions().get(0))==0)
            joiner_view.get(i).setText("Spy");*/
        for(int j=0;j<Database.getSpy_positions().size();j++)
        {
            if(Integer.compare(tp,Database.getSpy_positions().get(j))==0)
            {
                if(Database.spy_question_num==1)
                    question_output.setText(Database.questions.get(Database.which_question).getQuestion1());
                else if(Database.spy_question_num==2)
                    question_output.setText(Database.questions.get(Database.which_question).getQuestion2());
                //Toast.makeText(end.this,String.valueOf(i),Toast.LENGTH_SHORT).show();
                check_spy=1;
            }
        }
        for(int j=0;j<Database.getNothing_positions().size();j++)
        {
            if(Integer.compare(tp,Database.getNothing_positions().get(j))==0)
            {
                question_output.setText(" ");
                check_nothing=1;
            }
        }
        if(check_nothing==0&&check_spy==0)
        {
            if(Database.spy_question_num==2)
                question_output.setText(Database.questions.get(Database.which_question).getQuestion1());
            else if(Database.spy_question_num==1)
                question_output.setText(Database.questions.get(Database.which_question).getQuestion2());
            //question_output.setText("平民");
        }



        //question_output.setText(Database.questions.get(Database.which_question).getQuestion1());
        //question_output.setText("題目");




        //Database.nothing_positions.get(0);
        //當到Database.allnum時得跳到end.class
        if(Database.now_join_num==Database.allnum+1)
        {
            rem.setOnClickListener(new Button.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Intent intent=new Intent();
                    intent.setClass(question.this,end.class);
                    startActivity(intent);
                    question.this.finish();
                }

            });
        }
        else{
            rem.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(question.this,joiner_saw.class);
                startActivity(intent);
               question.this.finish();
            }
            });
        }
    }
    //返回鍵處理
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Database.now_join_num = 1;
            Intent myIntent ;
            myIntent = new Intent(question.this, setting.class);
            startActivity(myIntent);
            question.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
