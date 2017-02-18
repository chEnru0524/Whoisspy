package com.example.asus.whoisspy;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;

import static android.provider.BaseColumns._ID;
import static com.example.asus.whoisspy.Mysql_Database.QUESTION1;
import static com.example.asus.whoisspy.Mysql_Database.QUESTION2;
import static com.example.asus.whoisspy.Mysql_Database.TABLE_NAME;

/**
 * Created by ASUS on 2017/2/2.
 */

public final class Database extends AppCompatActivity {
    private static Context context;
    private static Mysql_Database mysql_database=null;
    public static ArrayList<QuestionType> questions= new ArrayList<QuestionType>();
    public static ArrayList<Integer> nothing_positions=new ArrayList<Integer>();
    public static ArrayList<Integer> spy_positions=new ArrayList<Integer>();
    public static int which_question;
    public static int spy_question_num;
    public static int spynum;
    public static int allnum;
    public static int nothing;
    public static int now_join_num = 1;
    public static ArrayList<QuestionType> getQuestions() {
        return questions;
    }
    public static ArrayList<Integer> getNothing_positions()
    {
        return nothing_positions;
    }
    public static ArrayList<Integer>getSpy_positions()
    {
        return spy_positions;
    }
    public void load_datas(Context load_context)
    {
        mysql_database=new Mysql_Database(load_context);
        System.out.print("已讀取!!!!!!!!!!!!!!!!!!!!!!!!!!");
        SQLiteDatabase db = mysql_database.getReadableDatabase();
        mysql_database=new Mysql_Database(this);
        Cursor cursor=db.query(TABLE_NAME, null, null, null, null, null, null, null);


        while (cursor.moveToNext())
        {
            QuestionType temp=new QuestionType();
            temp.setQuestion1(cursor.getString(1));
            temp.setQuestion2(cursor.getString(2));
            //Toast.makeText(load_context,cursor.getString(1)+" "+cursor.getString(2), Toast.LENGTH_SHORT).show();

            questions.add(temp);
        }
        /*temp.setQuestion1(cursor.getString(0));
        temp.setQuestion2(cursor.getString(1));
        questions.add(temp);*/
    }

    public static void Random_Values()
    {
        Database.now_join_num = 1;
        Database.spy_positions.clear();
        Database.nothing_positions.clear();
        int [] ran=new int[Database.spynum+Database.nothing];
        for (int a = 0; a < ran.length; ++a) {
            int i = 0;
            pick:
            while (i == 0) {
                i = (int) (Math.random() * (Database.allnum)+1);
                for (int b = 0; b < a; ++b) {
                    if (ran[b] == i) {
                        i = 0;
                        continue pick;
                    }
                }
                ran[a] = i;
            }
        }
        //將random出的值放入database
        for(int i = 0;i < Database.spynum + Database.nothing;i++)
        {
            if(i < Database.spynum)
            {
                //Toast.makeText(setting.this,Integer.toString(ran[i]),Toast.LENGTH_SHORT).show();
                Database.spy_positions.add(ran[i]);
            }
            else
            {
                Database.nothing_positions.add(ran[i]);
            }
        }
        Database.which_question=(int)(Math.random()*(Database.questions.size()-1));
        Database.spy_question_num=(int)(Math.random()*2+1);
    }
}
