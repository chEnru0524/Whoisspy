package com.example.asus.whoisspy;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

//剩排版
public class end extends AppCompatActivity {
    //正式開始
    private static Toast toast;
    private TableLayout scene;
    //private List<TableRow> joiner_scene;
    private List<TextView> joiner_view;
    private Button return_main;
    private Button restart;
    private int spy_survive;
    private int nothing_survive;
    private int all_survive;
    private int check_game_over=0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        return_main = (Button) findViewById(R.id.re_main);
        restart = (Button) findViewById(R.id.restart);
        scene = (TableLayout) findViewById(R.id.join_Table);
        //TableLayout.LayoutParams row_layout = new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        //TableRow.LayoutParams view_layout = new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        //joiner_scene=new ArrayList<>();
        joiner_view = new ArrayList<>();
        spy_survive = Database.spynum;
        nothing_survive = Database.nothing;
        all_survive = Database.allnum;

        for (int i = 1; i <= Database.allnum; i++) {
            final TextView temp = new TextView(this);
            temp.setId(i);
            String tt = (i) + "號";
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
                    //檢查身分
                    //顯示誰死
                    //確認是否有一方獲勝
                    if(check_game_over==0)
                    {
                        if (Check_Kill_Who(v.getId()) == 1) {
                            temp.setText("臥底");
                            //makeTextAndShow(end.this, String.valueOf(spy_survive)+" "+String.valueOf(all_survive), Toast.LENGTH_SHORT);
                        } else if (Check_Kill_Who(v.getId()) == 2) {
                            temp.setText("白板");
                        }
                        else
                        {
                            temp.setText("平民");
                        }
                        all_survive--;
                        makeTextAndShow(end.this, "臥底:"+String.valueOf(spy_survive)+"\n"+"白板:"+String.valueOf(nothing_survive), Toast.LENGTH_SHORT);
                        //Check_Kill_Who(v.getId());
                        if (Check_Who_Win() == 1) {
                            makeTextAndShow(end.this, "平民獲勝", Toast.LENGTH_SHORT);
                            //Toast.makeText(end.this,"平民獲勝",Toast.LENGTH_SHORT).show();
                            PrintAll();
                            check_game_over=1;
                        } else if (Check_Who_Win() == 2) {
                            //makeTextAndShow(end.this, "間諜獲勝", Toast.LENGTH_SHORT);
                            //Toast.makeText(end.this,"間諜獲勝",Toast.LENGTH_SHORT).show();
                            PrintAll();
                            check_game_over=1;
                        }
                    }

                }

            });
            joiner_view.add(temp);


            /*TableRow tr=new TableRow(this);
            joiner_scene.add(tr);
            tr.addView(temp);
            scene.addView(tr);*/


            //toast.makeText(end.this,Database.getSpy_positions().get(0),Toast.LENGTH_SHORT).show();

        }
        int rows = Database.allnum / 3;
        if (Database.allnum % 3 != 0)
            rows++;
        int j = 0;
        int k = 0;
        for (int i = 1; i <= rows; i++) {
            TableRow tr = new TableRow(this);
            k = j + 3;
            for (; j < k; j++) {
                if (j == Database.allnum) {
                    break;
                }
                tr.addView(joiner_view.get(j));
            }
            scene.addView(tr);

        }

        //按鈕結束
        return_main.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(end.this, MainActivity.class);
                startActivity(intent);
                end.this.finish();
            }

        });
        //按鈕重玩
        restart.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                Database.Random_Values();
                Intent intent = new Intent();
                intent.setClass(end.this, joiner_saw.class);
                startActivity(intent);
                end.this.finish();
            }

        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    //全部顯示
    public void PrintAll() {
        for (int i = 0; i < Database.allnum; i++) {
            /*if(i==Database.getSpy_positions().get(i)&&Database.spy_question_num==1) {
                //joiner_view.get(i).setText(Database.questions.get(Database.which_question).getQuestion1());
                joiner_view.get(i).setText("spy");
            }else if(i==Database.getSpy_positions().get(i)&&Database.spy_question_num==2) {
                //joiner_view.get(i).setText(Database.questions.get(Database.which_question).getQuestion2());
                joiner_view.get(i).setText("spy");
            }
            else if(i==Database.getNothing_positions().get(i))
            {
                joiner_view.get(i).setText("");
            }
            else {
                if (Database.spy_question_num == 1) {
                    //joiner_view.get(i).setText(Database.questions.get(Database.which_question).getQuestion2());
                    joiner_view.get(i).setText("平民");
                } else if (Database.spy_question_num == 2) {
                    //joiner_view.get(i).setText(Database.questions.get(Database.which_question).getQuestion1());
                    joiner_view.get(i).setText("平民");
                }
            }*/
            if (i == Database.allnum)
                break;
            //位置是從1開始
            Integer tp = Integer.valueOf(i+1);
            //list是從0開始
            int temp=i+1;
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
                        joiner_view.get(i).setText(temp+"號"+"\n"+"間諜\n"+Database.questions.get(Database.which_question).getQuestion1());
                    else if(Database.spy_question_num==2)
                        joiner_view.get(i).setText(temp+"號"+"\n"+"間諜\n"+Database.questions.get(Database.which_question).getQuestion2());
                    //Toast.makeText(end.this,String.valueOf(i),Toast.LENGTH_SHORT).show();
                    check_spy=1;
                }
            }
            for(int j=0;j<Database.getNothing_positions().size();j++)
            {
                if(Integer.compare(tp,Database.getNothing_positions().get(j))==0)
                {
                    joiner_view.get(i).setText(temp+"號"+"\n"+"白板\n"+" ");
                    check_nothing=1;
                }
            }
            if(check_nothing==0&&check_spy==0)
            {
                if(Database.spy_question_num==2)
                    joiner_view.get(i).setText(temp+"號"+"\n"+"平民\n"+Database.questions.get(Database.which_question).getQuestion1());
                else if(Database.spy_question_num==1)
                    joiner_view.get(i).setText(temp+"號"+"\n"+"平民\n"+Database.questions.get(Database.which_question).getQuestion2());

            }

        }
    }

    public int Check_Kill_Who(int id) {
        for (int i = 0; i < spy_survive; i++) {
            if (id == Database.getSpy_positions().get(i)) {
                spy_survive--;
                makeTextAndShow(end.this, "臥底死亡", Toast.LENGTH_SHORT);
                //Toast.makeText(end.this,"臥底死亡",Toast.LENGTH_SHORT).show();
                return 1;
            }
        }
        for (int i = 0; i < nothing_survive; i++) {
            if (id == Database.getNothing_positions().get(i)) {
                nothing_survive--;
                makeTextAndShow(end.this, "白板死亡", Toast.LENGTH_SHORT);
                //Toast.makeText(end.this,"白板死亡",Toast.LENGTH_SHORT).show();
                return 2;
            }
        }
        makeTextAndShow(end.this, "平民死亡", Toast.LENGTH_SHORT);
        //Toast.makeText(end.this,"平民死亡",Toast.LENGTH_SHORT).show();
        //平民死亡
        return 3;
    }

    public int Check_Who_Win() {
        //玩家贏
        if (spy_survive == 0 && nothing_survive == 0) {
            //makeTextAndShow(end.this,"平民win",Toast.LENGTH_SHORT);
            return 1;
        }
        //spy win
        else if (spy_survive > all_survive / 2 ) {
               return 2;
        }
        //makeTextAndShow(end.this,"no win",Toast.LENGTH_SHORT);
        return 3;
    }

    //toast無延遲
    private static void makeTextAndShow(final Context context, final String text, final int duration) {
        if (toast == null) {
            //如果還沒有用過makeText方法，才使用
            toast = Toast.makeText(context, text, duration);
        } else {
            toast.setText(text);
            toast.setDuration(duration);
        }
        toast.show();
    }

    //返回鍵處理
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Database.now_join_num = 1;
            Intent myIntent;
            myIntent = new Intent(end.this, setting.class);
            startActivity(myIntent);
            end.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("end Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
