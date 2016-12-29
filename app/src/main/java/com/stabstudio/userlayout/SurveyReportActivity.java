package com.stabstudio.userlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SurveyReportActivity extends AppCompatActivity {

    private ListView listView;
    private String[] forms = {"Nutrition Report", "Referral", "Disease", "Medicines"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_report);

        listView = (ListView) findViewById(R.id.reportlist);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.simple_list_row, forms);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }
}
