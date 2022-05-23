package com.example.howmanydaysmanagerver2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.myapplicatiohowmanydaysmanagerver2.R;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class Activity_SetData extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    Button Button_set_date;
    Button Button_save;
    private TextView textView_show_date;
    private EditText editText_title;
    Common c;

    private boolean DataSaveOK = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_data);

        getWindow().setStatusBarColor(Color.DKGRAY);

        findViews();
        if(savedInstanceState == null) {
            setListeners();
        }
    }

    protected void findViews(){
        Button_set_date = findViewById(R.id.button_set_date);
        Button_save = findViewById(R.id.button_save);
        textView_show_date = findViewById(R.id.texView_show_date);
        editText_title = findViewById(R.id.editText_title);
    }

    protected void setListeners(){
        Button_save.setOnClickListener( v -> {

            Intent intent = new Intent(this, MainActivity.class);

            //値をセット
            c = (Common)getApplication();

            String title = editText_title.getText().toString();
            String startDate = textView_show_date.getText().toString();
            int difference_of_days = DateCalculator(startDate);

            //保存のためのvalidation
            if(startDate.isEmpty()){
                noInputsChecker();
            }else if(difference_of_days == 0){
                todayChecker();
            }else{
                DataSaveOK = true;
            }

            //最終的な保存
            if(DataSaveOK) {

                if(title.isEmpty()) {
                    ++c.elem_num;
                    title = "無題" + c.elem_num;
                }
                c.titleList.add(title);

                if(difference_of_days > 0) {
                    String Date = String.valueOf(difference_of_days);
                    c.DateList.add("今日で" + startDate + "から" + Date + "日目");
                }else if(difference_of_days < 0){
                    String Date = String.valueOf(-difference_of_days);
                    c.DateList.add("今日で" + startDate + "まであと" + Date + "日");
                }

                startActivity(intent);
            }


        });
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        String str = String.format(Locale.US, "%d/%d/%d",year,month+1,day);
        textView_show_date.setText(str);
    }

    private int DateCalculator(String startDate){
        //開始日から、表示する日数の算出
        //現在の日付の取得
        Calendar cal_startDate = Calendar.getInstance();
        Calendar cal_today = Calendar.getInstance();
        long diff ;
        int difference_of_days = 0 ;

        startDate += " 00:00:00:000";

        //String→Calendar 変換
        try{
            cal_startDate.setTime(Objects.requireNonNull(c.sdf.parse(startDate)));
            cal_today.setTime(Objects.requireNonNull(c.sdf.parse(c.today_for_Activity_SetData)));
        }catch (ParseException e){
            cal_startDate = null;
        }

        //計算
        if( cal_startDate != null) {
            diff = cal_today.getTimeInMillis() - cal_startDate.getTimeInMillis();

            //日単位に変換
            int MILLIS_OF_DAY = 1000*60*60*24;
            difference_of_days = (int)(diff / MILLIS_OF_DAY);
        }

        return difference_of_days;
    }

    private void todayChecker(){
        String toastMessage = "開始日が本日と同じです";
        Toast toast = Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT);
        toast.show();
    }
    private void noInputsChecker(){
        String toastMessage = "開始日が設定されていません";
        Toast toast = Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT);
        toast.show();
    }

}