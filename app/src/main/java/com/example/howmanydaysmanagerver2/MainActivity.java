package com.example.howmanydaysmanagerver2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplicatiohowmanydaysmanagerver2.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button addButton;
    LinearLayout linearLayout;
    private TextView textView_today;
    private GestureDetector gestureDetector;
    private static final int SWIPE_MIN_DISTANCE = -100;

    Common c;

    List<Map<String, String>> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c = (Common) getApplication();


        //ステータスバーのカスタマイズ
        getWindow().setStatusBarColor(Color.DKGRAY);

        findViews();

        //今日の日付の表示
        String text_today = "今日: " + c.today + " ";
        textView_today.setText(text_today);


        if(savedInstanceState == null) {
            setListeners();
        }
        setAdapters();
        dataReceiver();

    }

    protected void findViews(){
        listView = findViewById(R.id.listview);
        addButton = findViewById(R.id.button1);
        textView_today = findViewById(R.id.textView_today);
        linearLayout = findViewById(R.id.swipe);
    }

    protected void setListeners(){
        addButton.setOnClickListener( v -> {
            Intent intent = new Intent(getApplication(), Activity_SetData.class);
            startActivity(intent);
        });
    }

    protected void setAdapters(){
        //第4引数は設定するデータリストの中のマップのキーを設定
        SimpleAdapter adapter = new SimpleAdapter(this,
                data,
                android.R.layout.simple_list_item_2,
                new String[] {"title", "startDate"},
                new int[] {android.R.id.text1, android.R.id.text2});
        listView.setAdapter(adapter);
    }

    protected  void dataReceiver(){
        // 受け取りの記述
        if (c.titleList != null) {
            // ListViewに表示するリスト項目をArrayListで準備する
            for (int i = 0, j = 0; i < c.titleList.size(); i++, j++) {
                Map<String, String> item = new HashMap<>();
                item.put("title", c.titleList.get(i));
                item.put("startDate", c.DateList.get(j));
                data.add(item);
            }
        }
    }

}