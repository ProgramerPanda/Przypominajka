package com.example.downa.przypominajka;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by downa on 13.06.2016.
 */
public class ListReminders extends AppCompatActivity {

    private ColNum columnNumberPreferences;

    private GridView grid;

    private ReminderAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        columnNumberPreferences = new ColNum(this);


        initializeGrid();
    }

    @Override
    protected void onResume(){
        super.onResume();

        adapter.notifyDataSetChanged();
    }

    private void initializeGrid(){

        grid = (GridView) findViewById(R.id.reminder_grid);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Reminder temp = adapter.getItem(position);
                //Toast.makeText(getApplicationContext(), temp.getName()+temp.getPlace(), Toast.LENGTH_LONG).show();
                Cords.finger= temp;
                //startActivity(new Intent(this, MapsActivity.class));
                Intent appInfo = new Intent(ListReminders.this, MapsActivity.class);
                startActivity(appInfo);

            }
        });
        adapter = new ReminderAdapter(this);
        grid.setNumColumns(columnNumberPreferences.getSelectedNumbersOfColumns());
        grid.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.exit){
            finish();
            System.exit(0);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setNumberOfGridColumns(int numberOfColumns){
        grid.setNumColumns(numberOfColumns);
        columnNumberPreferences.saveSelectedNumberOfColumns(numberOfColumns);
    }

}
