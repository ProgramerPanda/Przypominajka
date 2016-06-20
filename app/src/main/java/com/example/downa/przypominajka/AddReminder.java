package com.example.downa.przypominajka;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by downa on 13.06.2016.
 */
public class AddReminder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        creatReminder();
    }

    private boolean isEmpty(EditText etText){
        if(etText.getText().toString().trim().length()>0){
            return false;
        } else{
            return true;
        }
    }

    private void creatReminder(){
        final EditText n = (EditText) findViewById(R.id.name);
        final EditText p = (EditText) findViewById(R.id.place);

        Button save = (Button) findViewById(R.id.saveButt);

        save.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                if((isEmpty(n)==false)||(isEmpty(p)==false)){
                    String nameReminder = n.getText().toString();
                    String placeReminder = p.getText().toString();
                    saveReminder(new Reminder(nameReminder, placeReminder, Cords.getPion(), Cords.getPoziom()));
                } else {
                    Toast.makeText(getApplicationContext(), "Fill missing information", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void saveReminder(Reminder reminder){
        ReminderDatabase reminderDB = new ReminderDatabase(this);
        reminderDB.addReminder(reminder);

        Toast.makeText(this, "" + reminder.getName() + " saved", Toast.LENGTH_SHORT).show();

        finish();
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id== android.R.id.home){
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
