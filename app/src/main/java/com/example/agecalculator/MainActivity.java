package com.example.agecalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private DatePickerDialog DPD;
    private Button btn;
    private TextView Day_Answer;
    private TextView Month_Answer;
    private TextView Year_Answer;
    private String SYear;
    private String SMonth;
    private String SDay;
    private String CDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitiateDatePicker();
        btn = findViewById(R.id.ChooseYourAge_btn);
        Day_Answer = findViewById(R.id.Day_ans);
        Month_Answer = findViewById(R.id.Month_ans);
        Year_Answer = findViewById(R.id.Year_ans);
        if(savedInstanceState != null){
            Day_Answer.setText((String) savedInstanceState.get("Day"));
            SDay = (String) Day_Answer.getText();
            Month_Answer.setText((String) savedInstanceState.get("Month"));
            SMonth = (String) Month_Answer.getText();
            Year_Answer.setText((String) savedInstanceState.get("Year"));
            SYear = (String) Year_Answer.getText();
            CDate = (String) savedInstanceState.get("CDate");
            if(CDate != null) {
                btn.setText(CDate);
                CDate = (String) btn.getText();
            }
        }
    }
    // initiate the age dialog
    public void InitiateDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            //that what happen when press ok
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                CDate = String_Date(year,month,dayOfMonth);
                btn.setText(CDate);
                Day_Answer.setText(Calculate_Day(dayOfMonth));
                SDay = (String) Day_Answer.getText();
                Month_Answer.setText(Calculate_Month(month));
                SMonth = (String) Month_Answer.getText();
                Year_Answer.setText(Calculate_Year(year,month));
                SYear = (String) Year_Answer.getText();
            }
        };
        //cal of the current day
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_DARK;
        //send this,style of the calender,action what happen when press enter,year and month and day to start
        DPD = new DatePickerDialog(this,style,dateSetListener,year,month,day);
    }

    private String Calculate_Year(int year,int month) {
        Calendar NowCal = Calendar.getInstance();
        int NowYear = NowCal.get(Calendar.YEAR);
        int NowMonth = NowCal.get(Calendar.MONTH);
        if(year > NowYear)
            return "invalid";
        else
            if(month>NowMonth){
                NowYear--;
                return Math.abs(year-NowYear) + " " ;
            }
            else
                return Math.abs(year-NowYear) + " " ;
    }

    private String Calculate_Month(int month) {
        Calendar NowCal = Calendar.getInstance();
        int NowMonth = NowCal.get(Calendar.MONTH);
        return Math.abs(month-NowMonth) + " " ;
    }

    private String Calculate_Day(int day) {
        Calendar NowCal = Calendar.getInstance();
        int NowDay = NowCal.get(Calendar.DAY_OF_MONTH);
        return Math.abs(day-NowDay) + " " ;
    }

    private String String_Date(int year, int month, int dayOfMonth) {

        return dayOfMonth + " / " + (month+1) + " / " + year ;
    }

    public void openCalPicker(View view) {
        DPD.show();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Day",SDay);
        outState.putString("Month",SMonth);
        outState.putString("Year",SYear);
        outState.putString("CDate",CDate);

    }
}