package com.example.brainvireinfotechinclogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Welcome extends AppCompatActivity {
    ListView dataInListView;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        dataInListView = (ListView) findViewById(R.id.list_item);
        dbHelper = new DBHelper(this,"MyDB.db",null,1);

        //Here i am fetching all the data from database..
        Cursor getDataFromDatabase = dbHelper.showData();

        if(getDataFromDatabase.getCount() == 0){
            Toast.makeText(this,"No Data Available",Toast.LENGTH_LONG).show();
        }
        StringBuffer buffer = new StringBuffer();
        while (getDataFromDatabase.moveToNext()){
            buffer.append("NAME:" + getDataFromDatabase.getString(0)+"\n");
            buffer.append("EMAIL :" + getDataFromDatabase.getString(1)+"\n");
        }
        String[] Data = {buffer.toString()};
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Data);
        dataInListView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
