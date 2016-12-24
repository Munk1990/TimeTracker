package com.kmayank.timetracker;

import android.app.TimePickerDialog;

import java.util.Calendar;
import java.util.GregorianCalendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.util.Date;

public class TaskCreator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_creator);
        TextView timeField = (TextView)findViewById(R.id.repeat_timer_label);
        timeField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (view.hasFocus()){
                    showTimePickerDialog(view);
                }
            }
        });
    }

    public void showTimePickerDialog(View view){
        Date now = new GregorianCalendar().getTime();
        TimePickerDialog dialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int min) {
                TextView timeText = (TextView)findViewById(R.id.repeat_timer_label);
                timeText.setText(hour+":"+min);
                ((CheckBox)findViewById(R.id.repeat_timer_check)).setChecked(true);
            }
        }, Calendar.getInstance().get(Calendar.HOUR), Calendar.getInstance().get(Calendar.MINUTE),true);
        dialog.show();
    }

    public void enableDailyReminder(View view){
        findViewById(R.id.repeat_timer_label).setEnabled(((CheckBox) view).isChecked());
    }

}
