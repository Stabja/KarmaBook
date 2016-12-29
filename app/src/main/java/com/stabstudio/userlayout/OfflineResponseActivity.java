package com.stabstudio.userlayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;

public class OfflineResponseActivity extends AppCompatActivity {

    private ListView listView;
    private String[] ResponseJson;

    public String responseFolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_response);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);    //Sets up back button on Action Bar

        readResponseFiles();

        listView = (ListView)findViewById(R.id.offlinelist);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ResponseJson);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent in = new Intent(getApplicationContext(), LoadJsonActivity.class);
                Intent in = new Intent(getApplicationContext(), DynamicFormActivity2.class);
                in.putExtra("responseJsonName", ResponseJson[position]);
                in.putExtra("fromActivity", "OfflneRespActivity");
                in.putExtra("jsonName", responseFolder);
                startActivity(in);
            }
        });

    }

    public void readResponseFiles(){

        Intent intent = getIntent();
        responseFolder = intent.getStringExtra("responseFolderName");

        File home = getExternalFilesDir("ResponseJSON/" + responseFolder);
        File[] listFiles = home.listFiles();
        ResponseJson = new String[listFiles.length];
        int i = 0;
        for(File f : listFiles){
            String temp = f.getName();
            String name = temp.substring(0, temp.lastIndexOf('.'));
            ResponseJson[i] = name;
            i++;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            //Toast.makeText(getApplicationContext(), "Working", Toast.LENGTH_SHORT).show();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
