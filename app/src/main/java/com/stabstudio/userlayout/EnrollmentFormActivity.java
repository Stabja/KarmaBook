package com.stabstudio.userlayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EnrollmentFormActivity extends AppCompatActivity {

    private ListView listView;
    private String[] forms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment_form);

        initializeforms();

        listView = (ListView)findViewById(R.id.enrolllist);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, forms);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(EnrollmentFormActivity.this, SurveyActivity.class);
                startActivity(in);
            }
        });

    }

    public void initializeforms(){
        forms = new String[20];
        for(int i = 0; i < 20; i++){
            forms[i] = "Enrollment Form " + i;
        }
    }

}
