//package com.example.myapplicatiohowmanydaysmanagerver2;
//
//import android.app.DatePickerDialog;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.DatePicker;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.fragment.app.DialogFragment;
//import androidx.fragment.app.Fragment;
//
//import java.util.Locale;
//
//public class Fragment1 extends Fragment implements DatePickerDialog.OnDateSetListener{
//
//    private TextView textView_showDate;
//
//    public static Fragment1 newInstance(String str){
//        //Fragment1のインスタンス生成
//        Fragment1 fragment1 = new Fragment1();
//        // Bundle にパラメータを設定
//        Bundle barg = new Bundle();
//        barg.putString("Message",str);
//        fragment1.setArguments(barg);
//
//        return fragment1;
//    }
//
//    // FragmentのViewを生成して返す
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
//
//        //(https://tkm0on.hatenablog.com/entry/2015/05/19/223117)
//        View view = inflater.inflate(R.layout.fragment_main, container, false);
//        return  view;
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view,Bundle savedInstanceState){
//        super.onViewCreated(view, savedInstanceState);
//
//        Bundle args = getArguments();
//        if(args != null){
//            String str = args.getString("Message");
//            textView = view.findViewById(R.id.text_fragment);
//            textView.setText(str);
//        }
//
//    }
//
//    public void showDatePickerDialog(View v) {
//        DialogFragment newFragment = new DatePickerFragment();
//        newFragment.show(getSupportFragmentManager(), "datePicker");
//    }
//
//    @Override
//    public void onDateSet(DatePicker view, int year, int month, int day) {
//        textView_showDate.setText(String.format(Locale.JAPAN, "%d/%d/%d",year,month+1,day));
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//
//        Button button = (Button)getActivity().findViewById(R.id.button_setDate);
//        button.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                showDatePickerDialog(v);
//            }
//        });
//
//    }
//
//    public void showDatePickerDialog(View v) {
//        DialogFragment newFragment = new DatePickerFragment();
//        newFragment.show(getSupportFragmentManager(), "datePicker");
//    }
//}
