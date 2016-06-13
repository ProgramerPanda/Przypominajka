package com.example.downa.przypominajka;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AlertDialog.Builder dialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getActionBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.info){
            Dialog();
            return true;
        }
        if (id == R.id.exit){
            finish();
            System.exit(0);
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    public void Dialog(){
        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Information");
        dialogBuilder.setMessage("Projet made by Jakub Downarowicz");
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Closed Info", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog InfoDialog = dialogBuilder.create();
        InfoDialog.show();
    }

    public void listReminder(View v){
        startActivity(new Intent(this, ListReminders.class));
    }

    public void addReminder(View v){
        startActivity(new Intent(this, AddReminder.class));
    }

    public void locationReminder(View v){
        startActivity(new Intent(this, LocationReminder.class));
    }
}
