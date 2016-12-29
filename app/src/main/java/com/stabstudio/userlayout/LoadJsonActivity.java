package com.stabstudio.userlayout;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class LoadJsonActivity extends AppCompatActivity {

    private LinearLayout ll;
    private ScrollView sv;

    private int numTextViews;
    private int numEditTexts;
    private int numButtons;
    private int numCheckBox;
    private int numRadioGroup;
    private int numRadioButtons;

    private ArrayList<JsonField> fields = new ArrayList<JsonField>();
    private ArrayList<OutputJSON> viewInfos = new ArrayList<OutputJSON>();

    private String ResponseJSONFileName = null;
    private String InputJSONFileName = null;
    private String InputJSONFileString = null;
    private String ResponseJSONFileString = null;

    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;
    private int hour, minute;


    private EditText dateEt;
    private EditText timeEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sv = new ScrollView(this);
        ll = new LinearLayout(this);

        LinearLayout.LayoutParams llDimensions = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        sv.setLayoutParams(llDimensions);
        ll.setLayoutParams(llDimensions);
        ll.setPadding(20, 20, 20, 20);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setVerticalScrollBarEnabled(true);
        ViewGroup.LayoutParams vDimensions = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams boxDimensions = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        boxDimensions.setMargins(50, 0, 0, 0);

        int len = 0;

        Intent responseIntent = getIntent();
        String temporary = responseIntent.getStringExtra("responseJsonName");    //RESPONSEAllFields
        InputJSONFileName = temporary.replace("RESPONSE", "") + ".json";                                 //AllFields.json
        ResponseJSONFileName = temporary + ".json";                              //RESPONSEAllFields.json
        InputJSONFileString = readJSONFile(getExternalFilesDir("InputJSON") + "/" + InputJSONFileName);
        String inputJsonArray = temporary.replace("RESPONSE", "");
        ResponseJSONFileString = readJSONFile(getExternalFilesDir("ResponseJSON") + "/" + inputJsonArray + "/" + ResponseJSONFileName);


        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        try {
            JSONObject root = new JSONObject(InputJSONFileString);
            JSONArray array = root.getJSONArray(inputJsonArray);
            len = array.length();
            for(int i = 0; i < array.length(); i++){
                JSONObject jo = array.getJSONObject(i);
                int l1 = jo.getInt("qId");
                String l2 = jo.getString("dataType");
                String l3 = jo.getString("label");
                String l4 = jo.getString("helpText");
                int l5 = jo.getInt("MaxCharsAllowed");
                if(l2.equals("Multiple Select")){                //CheckBox
                    String temp = jo.getString("enum");
                    String l6[] = temp.split(",");
                    JsonField jf = new JsonField(l1, l2, l3, l4, l5, l6);
                    fields.add(jf);
                    numTextViews++;
                    numCheckBox += l6.length;
                    TextView tv = new TextView(this);
                    tv.setLayoutParams(vDimensions);
                    tv.setPadding(15, 30, 15, 15);
                    tv.setText(l3);
                    tv.setTextSize(20);
                    ll.addView(tv);
                    View[] chkArr = new View[l6.length];
                    for(int j = 0; j < l6.length; j++){
                        CheckBox cb = new CheckBox(this);
                        cb.setLayoutParams(boxDimensions);
                        cb.setText(l6[j]);
                        ll.addView(cb);
                        chkArr[j] = cb;
                    }
                    OutputJSON opj = new OutputJSON("a", tv, chkArr);
                    viewInfos.add(opj);
                }
                else if(l2.equals("Single Select")){             //RadioButton
                    String temp = jo.getString("enum");
                    String l6[] = temp.split(",");
                    JsonField jf = new JsonField(l1, l2, l3, l4, l5, l6);
                    fields.add(jf);
                    numTextViews++;
                    numRadioGroup++;
                    numRadioButtons += l6.length;
                    TextView tv = new TextView(this);
                    tv.setLayoutParams(vDimensions);
                    tv.setPadding(15, 30, 15, 15);
                    tv.setText(l3);
                    tv.setTextSize(20);
                    ll.addView(tv);
                    RadioGroup rg = new RadioGroup(this);
                    rg.setLayoutParams(boxDimensions);
                    RadioButton[] rbArray = new RadioButton[l6.length];
                    for(int j = 0; j < l6.length; j++){
                        RadioButton bt = new RadioButton(this);
                        bt.setLayoutParams(boxDimensions);
                        bt.setText(l6[j]);
                        rbArray[j] = bt;
                        rg.addView(bt);
                    }
                    ll.addView(rg);
                    OutputJSON opj = new OutputJSON("b", tv, rbArray);
                    viewInfos.add(opj);
                }
                else if(l2.equals("Signature")){         //Button
                    JsonField jf = new JsonField(l1, l2, l3, l4, l5);
                    fields.add(jf);
                    numTextViews++;
                    numButtons++;
                    TextView tv = new TextView(this);
                    tv.setLayoutParams(vDimensions);
                    tv.setPadding(15, 30, 15, 15);
                    tv.setText(l3);
                    tv.setTextSize(20);
                    ll.addView(tv);
                    ImageView imageView = new ImageView(this);
                    imageView.setLayoutParams(vDimensions);
                    imageView.setMaxWidth(240);
                    imageView.setMaxHeight(240);
                    imageView.setPadding(15, 15, 15, 30);
                    ll.addView(imageView);
                    OutputJSON opj = new OutputJSON("c", tv, imageView);
                    viewInfos.add(opj);
                }
                else{
                    JsonField jf = new JsonField(l1, l2, l3, l4, l5);
                    fields.add(jf);
                    numTextViews++;
                    numEditTexts++;
                    TextView tv = new TextView(this);
                    tv.setLayoutParams(vDimensions);
                    tv.setPadding(15, 30, 15, 15);
                    tv.setText(l3);
                    tv.setTextSize(20);
                    ll.addView(tv);
                    EditText et = new EditText(this);
                    et.setLayoutParams(vDimensions);
                    et.setPadding(15, 15, 15, 30);
                    et.setHint(l4);
                    if(l2.equals("Date")){
                        dateEt = et;
                        et.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showDialog(50);
                            }
                        });
                    }
                    else if(l2.equals("Time")){
                        timeEt = et;
                        et.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showDialog(100);
                            }
                        });
                    }
                    ll.addView(et);
                    OutputJSON opj = new OutputJSON("d", tv, et);
                    viewInfos.add(opj);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sv.addView(ll);
        setContentView(sv);
        loadValues();
    }


    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 50) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        if(id == 100){
            return new TimePickerDialog(this, myTimeListener, hour, minute, false);
        }
        return null;
    }

    private TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener(){
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            StringBuilder str = new StringBuilder().append(hourOfDay).append(":").append(minute);
            timeEt.setText(str);
            Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
        }
    };

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker arg0, int year, int month, int day) {
            StringBuilder str = new StringBuilder().append(day).append("/").append(month).append("/").append(year);
            dateEt.setText(str);
            Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
        }
    };


    public String readJSONFile(String path){
        File file = new File(path);
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(file);
            int data = fin.read();
            StringBuffer buffer = new StringBuffer();
            while (data != -1){
                buffer.append((char)data);
                data = fin.read();
            }
            return buffer.toString();
            //Toast.makeText(getApplicationContext(), "Data Loaded Successfully", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void loadValues(){
        try {
            JSONObject root = new JSONObject(ResponseJSONFileString);
            JSONArray array = root.getJSONArray("JSONOutput");
            for(int i = 0; i < array.length(); i++){
                OutputJSON opj = viewInfos.get(i);
                JSONObject jo = array.getJSONObject(i);
                String str = jo.getString("value");
                if(opj.type.equals("a")){                                 //CheckBox
                    String[] strArr = str.split(",");
                    View[] chkView = opj.v3;
                    CheckBox[] chkArray = new CheckBox[chkView.length];
                    for(int j = 0; j < chkView.length; j++){
                        chkArray[j] = (CheckBox) chkView[j];
                    }
                    for(int k = 0; k < strArr.length; k++){
                        for(int j = 0; j < chkArray.length; j++){
                            if(strArr[k].equals(chkArray[j].getText())){
                                chkArray[j].setChecked(true);
                                break;
                            }
                        }
                    }
                }
                else if(opj.type.equals("b")){                            //RadioGroup
                    View[] radioView = opj.v3;
                    RadioButton[] rbArr = new RadioButton[radioView.length];
                    for(int j = 0; j < radioView.length; j++){
                        rbArr[j] = (RadioButton)radioView[j];
                    }
                    for(int j = 0; j < rbArr.length; j++){
                        if(rbArr[j].getText().toString().equals(str)){
                            rbArr[j].setChecked(true);
                            break;
                        }
                    }
                }
                else if(opj.type.equals("c")){                            //Button
                    ImageView imgView = (ImageView)opj.v2;
                    File imgFile = new File(getExternalFilesDir("Images") + "/mysignature.png");
                    if(imgFile.exists()){
                        Bitmap bmp = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                        imgView.setImageBitmap(bmp);
                    }
                }
                else if(opj.type.equals("d")){                            //EditText
                    TextView tv = (TextView)opj.v1;
                    EditText et = (EditText)opj.v2;
                    et.setText(str);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public class JsonField{
        int l1;
        String l2;
        String l3;
        String l4;
        int l5;
        String l6[];
        public JsonField(int l1, String l2, String l3, String l4, int l5){
            this.l1 = l1;
            this.l2 = l2;
            this.l3 = l3;
            this.l4 = l4;
            this.l5 = l5;
        }
        public JsonField(int l1, String l2, String l3, String l4, int l5, String[] l6){
            this.l1 = l1;
            this.l2 = l2;
            this.l3 = l3;
            this.l4 = l4;
            this.l5 = l5;
            this.l6 = l6;
        }
    }

    public class OutputJSON{
        String type;
        View v1;
        View v2;
        View v3[];
        public OutputJSON(String type, View v1, View v2){
            this.type = type;
            this.v1 = v1;
            this.v2 = v2;
        }
        public OutputJSON(String type, View v1, View[] v3){
            this.type = type;
            this.v1 = v1;
            this.v3 = v3;
        }
    }

}
