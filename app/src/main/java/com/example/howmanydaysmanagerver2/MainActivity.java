package com.example.howmanydaysmanagerver2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.myapplicatiohowmanydaysmanagerver2.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView listView;
    Button addButton;
    ImageButton button_sort;
    LinearLayout linearLayout;
    private TextView textView_today;
//    List dataset;

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
        button_sort = findViewById(R.id.imageButton_sort);
    }

    protected void setListeners(){
        addButton.setOnClickListener( v -> {
            Intent intent = new Intent(getApplication(), Activity_SetData.class);
            startActivity(intent);
        });
        listView.setOnItemClickListener(this);
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
        if (c.dataset != null) {
            // ListViewに表示するリスト項目をArrayListで準備する
            for (int i = 0 ; i < c.dataset.size(); i++) {
                CompareObjects d = (CompareObjects) c.dataset.get(i);

                Map<String, String> item = new HashMap<>();
                item.put("title", d.getTitle());

                if( d.getDifferenceOfDate() > 0) {
                    String Date = String.valueOf(d.getDifferenceOfDate());
                    item.put("startDate", "今日で" + d.getStartDate() + "から" + Date + "日目");
                }else if( d.getDifferenceOfDate() < 0){
                    String Date = String.valueOf(-d.getDifferenceOfDate());
                    item.put("startDate","今日で" + d.getStartDate() + "まであと" + Date + "日");
                }else{
                    item.put("startDate", "今日で" + d.getStartDate() + "から" + 0 + "日目");
                }
                data.add(item);
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id){
        //clickされた要素のtext
        c.editPosition = position;

        //SubActivityに遷移
        Intent intent = new Intent(this.getApplication(), ActivityEditListElem.class);
        startActivity(intent);
    }

    public void showDialog(View view){
        DialogFragment dialogFragment = new MyDialogFragment();
        dialogFragment.show(getSupportFragmentManager(),"my_dialog");
    }

}