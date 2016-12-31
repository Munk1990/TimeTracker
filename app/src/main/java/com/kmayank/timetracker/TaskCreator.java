package com.kmayank.timetracker;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;

import java.util.Calendar;
import java.util.GregorianCalendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.kmayank.timetracker.dao.ITaskDao;
import com.kmayank.timetracker.dao.TaskDaoListImpl;
import com.kmayank.timetracker.models.Task;

import org.w3c.dom.Text;

import java.util.Date;

public class TaskCreator extends AppCompatActivity {
    ITaskDao taskDao = TaskDaoListImpl.getTaskDao();


    int countermins = 0;

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

    public void selectTimer(View view){
        CheckBox timeCheckbox = (CheckBox)findViewById(R.id.repeat_timer_check);
        timeCheckbox.setChecked(true);
        timeCheckbox.setClickable(false);
    }

    public void selectCounter(View view){
        CheckBox timeCheckbox = (CheckBox)findViewById(R.id.repeat_timer_check);
        timeCheckbox.setChecked(true);
        timeCheckbox.setClickable(true);
    }

    public void selectCounterTime(View view){
        invokeCounterDialogMinutes();
    }

    public void createTask(View view){
        EditText editText = (EditText)findViewById(R.id.task_title);
        Task task = new Task(editText.getText().toString());
        TextView hourtext = (TextView)TaskCreator.this.findViewById(R.id.counter_hour);
        TextView minuteText= (TextView)TaskCreator.this.findViewById(R.id.counter_minute);
        task.setCounterMinutes(Integer.parseInt(hourtext.getText().toString())*60
                + Integer.parseInt(minuteText.getText().toString()));
        CheckBox alarmCheckBox = (CheckBox)findViewById(R.id.repeat_timer_check);
        if (alarmCheckBox.isChecked()) {
            TextView timeText = (TextView) findViewById(R.id.repeat_timer_label);
            String[] alarmString = timeText.getText().toString().split(":");
            task.setAlarmMinutes(Integer.parseInt(alarmString[0]) * 60 + Integer.parseInt(alarmString[1]));
        }
        taskDao.saveTask(task);
    }

    private void invokeCounterDialogMinutes() {
        final Dialog counterDialog = new Dialog(TaskCreator.this);
        counterDialog.setTitle("Counter");
        counterDialog.setContentView(R.layout.counter_selector);
        Button okButton = (Button)counterDialog.findViewById(R.id.accept);
        final NumberPicker hourPick = (NumberPicker)counterDialog.findViewById(R.id.hour);
        final NumberPicker minutePick = (NumberPicker)counterDialog.findViewById(R.id.minute);
        hourPick.setMaxValue(100);
        hourPick.setMinValue(0);
        hourPick.setValue(20);
        minutePick.setMaxValue(59);
        minutePick.setMinValue(0);
        minutePick.setValue(0);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView hourtext = (TextView)TaskCreator.this.findViewById(R.id.counter_hour);
                hourtext.setText(Integer.toString(hourPick.getValue()));
                TextView minuteText= (TextView)TaskCreator.this.findViewById(R.id.counter_minute);
                minuteText.setText(Integer.toString(minutePick.getValue()));
                counterDialog.dismiss();
            }
        });
        counterDialog.show();
    }
}
