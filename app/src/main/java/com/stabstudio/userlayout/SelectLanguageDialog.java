package com.stabstudio.userlayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SelectLanguageDialog extends AppCompatActivity {

    private ListView listView;
    private String[] languages = {"English", "Hindi", "Bengali", "Marathi"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_select_language);

        listView = (ListView)findViewById(R.id.langlist);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, languages);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                String item = parent.getItemAtPosition(position).toString();
                Intent in = new Intent(getApplicationContext(), HomeScreenActivity.class);
                if(item.equals("English")){
                    in.putExtra("target", "en");
                }
                else if(item.equals("Hindi")){
                    in.putExtra("target", "hi");
                }
                finish();
                startActivity(in);
            }
        });

    }
}
