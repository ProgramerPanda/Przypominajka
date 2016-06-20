package com.example.downa.przypominajka;

/**
 * Created by downa on 13.06.2016.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;


public class ReminderAdapter extends BaseAdapter{

    private ReminderDatabase reminderProvider;
    private Context context;

    public ReminderAdapter(Context context){
        this.context=context;
        this.reminderProvider = new ReminderDatabase(context);
    }

    @Override
    public int getCount(){
        return reminderProvider.getReminderNumber();
    }

    @Override
    public Reminder getItem(int position){
        return reminderProvider.getReminder(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View ReminderView;

        if(convertView == null){
            ReminderView = LayoutInflater.from(context).inflate(R.layout.reminder_row, parent, false);
        } else{
            ReminderView = convertView;
        }

        bindReminderToView(getItem(position), ReminderView, position);

        return ReminderView;
    }

    private void bindReminderToView(Reminder reminder, View ReminderView, int position){


        TextView ReminderName = (TextView) ReminderView.findViewById(R.id.reminder_name);
        ReminderName.setText(reminder.getName());

        TextView ReminderPlace = (TextView) ReminderView.findViewById(R.id.reminder_place);
        ReminderPlace.setText(reminder.getPlace());

        TextView Pion = (TextView) ReminderView.findViewById(R.id.Pion);
        Double temp = reminder.getPion();
        Pion.setText(Double.toString(temp));

        TextView Poziom = (TextView) ReminderView.findViewById(R.id.Poziom);
        temp = reminder.getPoziom();
        Poziom.setText(Double.toString(temp));
    }
}
