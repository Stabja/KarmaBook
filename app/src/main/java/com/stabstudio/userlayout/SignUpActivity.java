package com.stabstudio.userlayout;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class SignUpActivity extends AppCompatActivity {

    private Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().hide();

        Intent in = getIntent();
        String lang = in.getStringExtra("target");
        if(lang.equals("en")){
            setLanguage("en");
        }
        else if(lang.equals("hi")){
            setLanguage("hi");
        }

        signup = (Button)findViewById(R.id.signupbutton);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void setLanguage(String str) {
        Locale locale = new Locale(str);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
    }

}
