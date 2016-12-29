package com.stabstudio.userlayout;

import android.app.Application;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private Button button;
    private EditText editText;
    private EditText editText2;
    private String[] languages = {"English", "Hindi", "Spanish", "Bengali", "French", "Marathi"};
    public Intent in;
    public Intent in2;
    public Intent sin;

    private TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FontsOverride.setDefaultFont(this, "DEFAULT", "AvenirLTStd-Roman.otf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "AvenirLTStd-Roman.otf");
        FontsOverride.setDefaultFont(this, "SERIF", "AvenirLTStd-Roman.otf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "AvenirLTStd-Roman.otf");

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.username);
        editText2 = (EditText)findViewById(R.id.password);

        signup = (TextView)findViewById(R.id.signup);
        sin = new Intent(getApplicationContext(), SignUpActivity.class);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(sin);
            }
        });

        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, languages);
        spinner.setAdapter(adapter);

        in = new Intent(getApplicationContext(), HomeScreenActivity.class);
        in2 = new Intent(getApplicationContext(), KPHomeScreenActivity.class);
        in.putExtra("from", "NKP");
        in2.putExtra("from", "KP");
        button = (Button) findViewById(R.id.signin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().equals("Kp")) {
                    startActivity(in2);
                }
                else{
                    startActivity(in);
                }
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                if(item.equals("English")){
                    in.putExtra("target", "en");
                    in2.putExtra("target", "en");
                    sin.putExtra("target", "en");
                }
                if(item.equals("Hindi")){
                    in.putExtra("target", "hi");
                    in2.putExtra("target", "hi");
                    sin.putExtra("target", "hi");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setLanguage(String str) {
        Locale locale = new Locale(str);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

}
