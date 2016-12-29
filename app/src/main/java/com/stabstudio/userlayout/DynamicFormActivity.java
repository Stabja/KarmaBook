package com.stabstudio.userlayout;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class DynamicFormActivity extends AppCompatActivity {

    private String jsonStr = "{\"inputboxes\":[\n" +
            "{\"qId\":1,\"dataType\":\"Text\",\"label\":\"Name\",\"helpText\":\"Official Name\",\"MaxCharsAllowed\":100,\"helpImageUrl\":\"na\",\"settings\":\"AR\",\"condition\":\"NA\"},\n" +
            "{\"qId\":2,\"dataType\":\"Number\",\"label\":\"Age\",\"helpText\":\"How old are you?\",\"MaxCharsAllowed\":2,\"helpImageUrl\":\"na\",\"settings\":\"PoT\",\"condition\":\"NA\"},\n" +
            "{\"qId\":3,\"dataType\":\"Date\",\"label\":\"Address\",\"helpText\":\"Official Address\",\"MaxCharsAllowed\":100,\"helpImageUrl\":\"na\",\"settings\":\"AR\",\"condition\":\"NA\"},\n" +
            "{\"qId\":4,\"dataType\":\"Multiple Select\",\"label\":\"Places\",\"helpText\":\"Places u have visited?\",\"MaxCharsAllowed\":20,\"helpImageUrl\":\"na\",\"settings\":\"AR\",\"condition\":\"NA\", \"enum\":\"delhi,mumbai,pune,hyderabad\"},\n" +
            "{\"qId\":5,\"dataType\":\"Single Select\",\"label\":\"Gender\",\"helpText\":\"What are u?\",\"MaxCharsAllowed\":100,\"helpImageUrl\":\"na\",\"settings\":\"AR\",\"condition\":\"NA\", \"enum\":\"male,female\"},\n" +
            "{\"qId\":6,\"dataType\":\"Phone\",\"label\":\"Mobile no\",\"helpText\":\"Enter ur phone no\",\"MaxCharsAllowed\":100,\"helpImageUrl\":\"na\",\"settings\":\"AR\",\"condition\":\"NA\"},\n" +
            "{\"qId\":7,\"dataType\":\"Email\",\"label\":\"Email Address\",\"helpText\":\"Enter ur email?\",\"MaxCharsAllowed\":100,\"helpImageUrl\":\"na\",\"settings\":\"AR\",\"condition\":\"NA\"},\n" +
            "{\"qId\":8,\"dataType\":\"Image\",\"label\":\"Profile Photo\",\"helpText\":\"Take a snap\",\"MaxCharsAllowed\":100,\"helpImageUrl\":\"na\",\"settings\":\"AR\",\"condition\":\"NA\"},\n" +
            "{\"qId\":9,\"dataType\":\"Signature\",\"label\":\"Signature\",\"helpText\":\"Tap to put signature\",\"MaxCharsAllowed\":100,\"helpImageUrl\":\"na\",\"settings\":\"AR\",\"condition\":\"NA\"},\n" +
            "{\"qId\":10,\"dataType\":\"Geo Location\",\"label\":\"GPS Coordinates\",\"helpText\":\"Get GPS coordinates\",\"MaxCharsAllowed\":100,\"helpImageUrl\":\"na\",\"settings\":\"AR\",\"condition\":\"NA\"}\n" +
            "]}";

    private  String jsonStr2 = "{\"inputboxes\":[\n" +
            "{\"qId\":1,\"dataType\":\"Text\",\"label\":\"Name\",\"helpText\":\"Official Name\",\"MaxCharsAllowed\":100,\"helpImageUrl\":\"na\",\"settings\":\"AR\",\"condition\":\"NA\"},\n" +
            "{\"qId\":2,\"dataType\":\"Number\",\"label\":\"Age\",\"helpText\":\"How old are you?\",\"MaxCharsAllowed\":2,\"helpImageUrl\":\"na\",\"settings\":\"PoT\",\"condition\":\"NA\"},\n" +
            "{\"qId\":3,\"dataType\":\"Date\",\"label\":\"Address\",\"helpText\":\"Official Address\",\"MaxCharsAllowed\":100,\"helpImageUrl\":\"na\",\"settings\":\"AR\",\"condition\":\"NA\"},\n" +
            "{\"qId\":4,\"dataType\":\"Multiple Select\",\"label\":\"Places\",\"helpText\":\"Places u have visited?\",\"MaxCharsAllowed\":20,\"helpImageUrl\":\"na\",\"settings\":\"AR\",\"condition\":\"NA\", \"enum\":\"delhi,mumbai,pune,hyderabad\"},\n" +
            "{\"qId\":5,\"dataType\":\"Single Select\",\"label\":\"Gender\",\"helpText\":\"What are u?\",\"MaxCharsAllowed\":100,\"helpImageUrl\":\"na\",\"settings\":\"AR\",\"condition\":\"NA\", \"enum\":\"male,female\"},\n" +
            "{\"qId\":6,\"dataType\":\"Signature\",\"label\":\"Signature\",\"helpText\":\"Tap to put signature\",\"MaxCharsAllowed\":100,\"helpImageUrl\":\"na\",\"settings\":\"AR\",\"condition\":\"NA\"},\n" +
            "{\"qId\":1,\"dataType\":\"Text\",\"label\":\"Name\",\"helpText\":\"Official Name\",\"MaxCharsAllowed\":100,\"helpImageUrl\":\"na\",\"settings\":\"AR\",\"condition\":\"NA\"},\n" +
            "{\"qId\":2,\"dataType\":\"Number\",\"label\":\"Age\",\"helpText\":\"How old are you?\",\"MaxCharsAllowed\":2,\"helpImageUrl\":\"na\",\"settings\":\"PoT\",\"condition\":\"NA\"},\n" +
            "{\"qId\":3,\"dataType\":\"Date\",\"label\":\"Address\",\"helpText\":\"Official Address\",\"MaxCharsAllowed\":100,\"helpImageUrl\":\"na\",\"settings\":\"AR\",\"condition\":\"NA\"},\n" +
            "{\"qId\":4,\"dataType\":\"Multiple Select\",\"label\":\"Places\",\"helpText\":\"Places u have visited?\",\"MaxCharsAllowed\":20,\"helpImageUrl\":\"na\",\"settings\":\"AR\",\"condition\":\"NA\", \"enum\":\"delhi,mumbai,pune,hyderabad\"},\n" +
            "{\"qId\":5,\"dataType\":\"Single Select\",\"label\":\"Gender\",\"helpText\":\"What are u?\",\"MaxCharsAllowed\":100,\"helpImageUrl\":\"na\",\"settings\":\"AR\",\"condition\":\"NA\", \"enum\":\"male,female\"},\n" +
            "{\"qId\":6,\"dataType\":\"Signature\",\"label\":\"Signature\",\"helpText\":\"Tap to put signature\",\"MaxCharsAllowed\":100,\"helpImageUrl\":\"na\",\"settings\":\"AR\",\"condition\":\"NA\"}\n" +
            "]}";

    private ArrayList<JsonField> fields = new ArrayList<JsonField>();

    private LinearLayout ll;
    private ScrollView sv;

    private int numTextViews;
    private int numEditTexts;
    private int numButtons;
    private int numCheckBox;
    private int numRadioGroup;
    private int numRadioButtons;

    private ArrayList<OutputJSON> viewInfos = new ArrayList<OutputJSON>();
    private String imagePath = null;
    private String signaturePath = null;
    private String gpsCoord = null;

    private String ResponseJSONFileName = null;
    private String InputJSONFileName = null;
    private String InputJSONFileString = null;

    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;
    private int hour, minute;


    private EditText dateEt;
    private EditText timeEt;

    public String JsonArrayName;
    public String from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

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
        JsonArrayName = responseIntent.getStringExtra("jsonName");
        InputJSONFileName = JsonArrayName + ".json";
        ResponseJSONFileName = "RESPONSE" + InputJSONFileName;
        readJSONFile(getExternalFilesDir("InputJSON") + "/" + InputJSONFileName);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);


        try {
            JSONObject root = new JSONObject(InputJSONFileString);
            JSONArray array = root.getJSONArray(JsonArrayName);
            len = array.length();
            for(int i = 0; i < array.length(); i++){
                JSONObject jo = array.getJSONObject(i);
                int l1 = jo.getInt("qId");
                String l2 = jo.getString("dataType");
                String l3 = jo.getString("label");
                String l4 = jo.getString("helpText");
                int l5 = jo.getInt("MaxCharsAllowed");

                String isCondNull = jo.getString("condition");

                if(l2.equals("Multiple Select")){           //CheckBox
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
                else if(l2.equals("Single Select")){        //RadioGroup
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
                    for(int j = 0; j < l6.length; j++){
                        RadioButton bt = new RadioButton(this);
                        bt.setLayoutParams(boxDimensions);
                        bt.setText(l6[j]);
                        rg.addView(bt);
                    }
                    ll.addView(rg);
                    OutputJSON opj = new OutputJSON("b", tv, rg);
                    viewInfos.add(opj);
                }
                else if(l2.equals("Image") || l2.equals("Signature") || l2.equals("Geo Location")){  //Button
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
                    Button bt = new Button(this);
                    bt.setLayoutParams(vDimensions);
                    bt.setPadding(15, 15, 15, 30);
                    bt.setText(l4);
                    ll.addView(bt);
                    OutputJSON opj = new OutputJSON("c", tv, bt);
                    viewInfos.add(opj);

                    if(l2.equals("Image")){
                        bt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent in = new Intent();
                                //in.setType("image/*");
                                in.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(Intent.createChooser(in, "Select Picture"), 100);
                                //startActivityForResult(in, 100);
                            }
                        });
                    }
                    else if(l2.equals("Signature")){
                        bt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent in = new Intent(getApplicationContext(), SignatureActivity.class);
                                in.putExtra("target", "DFA");
                                startActivityForResult(in, 200);
                            }
                        });
                    }
                    else if(l2.equals("Geo Location")){
                        bt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent in = new Intent(getApplicationContext(), GetCurrentLocation.class);
                                in.putExtra("target", "DFA");
                                startActivityForResult(in, 300);
                            }
                        });
                    }

                }
                else{                                                       //EditText
                    JsonField jf = new JsonField(l1, l2, l3, l4, l5);
                    fields.add(jf);
                    numTextViews++;
                    numEditTexts++;
                    final TextView tv = new TextView(this);
                    tv.setLayoutParams(vDimensions);
                    tv.setPadding(15, 30, 15, 15);
                    tv.setText(l3);
                    tv.setTextSize(20);
                    ll.addView(tv);

                    final EditText et = new EditText(this);
                    et.setFilters(new InputFilter[] {new InputFilter.LengthFilter(l5)});
                    et.setLayoutParams(vDimensions);
                    et.setPadding(15, 15, 15, 30);
                    et.setHint(l4);

                    if(l2.equals("Text")){
                        et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                    }
                    else if(l2.equals("Number")){
                        et.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    }
                    else if(l2.equals("Date")){
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
                    else if(l2.equals("Phone")){
                        et.setInputType(InputType.TYPE_CLASS_PHONE | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                        Toast.makeText(getApplicationContext(), "Phone Working", Toast.LENGTH_SHORT).show();
                    }
                    else if(l2.equals("Email")){
                        et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                        Toast.makeText(getApplicationContext(), "Email Working", Toast.LENGTH_SHORT).show();
                    }
                    ll.addView(et);
                    OutputJSON opj = new OutputJSON("d", tv, et);
                    viewInfos.add(opj);

                    if(!isCondNull.equals("NA")){
                        tv.setVisibility(View.INVISIBLE);
                        et.setVisibility(View.INVISIBLE);
                        JSONObject condition = jo.getJSONObject("condition");
                        int condId = condition.getInt("qId");
                        final int condVal = condition.getInt("conditionalValue");
                        EditText condnalEt = (EditText) viewInfos.get(condId-1).v2;
                        condnalEt.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            }
                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if(!s.toString().equals("")){
                                    if(Integer.parseInt(s.toString()) > condVal){
                                        tv.setVisibility(View.VISIBLE);
                                        et.setVisibility(View.VISIBLE);
                                    }
                                    else{
                                        tv.setVisibility(View.INVISIBLE);
                                        et.setVisibility(View.INVISIBLE);
                                    }
                                }
                            }
                            @Override
                            public void afterTextChanged(Editable s) {
                            }
                        });
                    }

                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Button submit = new Button(this);
        submit.setLayoutParams(vDimensions);
        submit.setText("Submit");
        submit.setPadding(15, 50, 15, 15);

        ll.addView(submit);
        sv.addView(ll);
        setContentView(sv);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValues();
            }
        });
    }


    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == RESULT_OK && data != null && data.getData() != null){
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                Toast.makeText(getApplicationContext(), uri.toString(), Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            imagePath = uri.toString();
        }
        if(resultCode == 200){
            signaturePath = data.getStringExtra("sigpath");
        }
        if(resultCode == 300){
            gpsCoord = data.getStringExtra("location");
        }
    }

    public void readJSONFile(String path){
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
            InputJSONFileString = buffer.toString();
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
    }

    public void getValues(){
        JSONArray jsonArray = new JSONArray();
        for(int i = 0; i < viewInfos.size(); i++){
            OutputJSON opj = viewInfos.get(i);
            int qid = i;
            TextView tv = (TextView)opj.v1;
            String label = tv.getText().toString();
            String value = null;
            if(opj.type.equals("a")){                                             //CheckBox
                value = "";
                for(int j = 0; j < opj.v3.length; j++){
                    CheckBox cb = (CheckBox)opj.v3[j];
                    if(cb.isChecked()){
                            value += cb.getText().toString() + ",";
                    }
                }
                if(value.length() > 1){
                    value = value.substring(0, value.length()-1);
                }
            }
            else if(opj.type.equals("b")){                                        //RadioGroup
                RadioGroup rg = (RadioGroup)opj.v2;
                if(rg.getCheckedRadioButtonId() == -1){
                    Toast.makeText(DynamicFormActivity.this, "Select a radiobutton", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    int idd = rg.getCheckedRadioButtonId();
                    RadioButton rb = (RadioButton)findViewById(idd);
                    value = rb.getText().toString();
                }
            }
            else if(opj.type.equals("c")){                                        //Button
                Button bt = (Button)opj.v2;
                if(fields.get(i).l2.equals("Image")){
                    value = imagePath;
                }
                else if(fields.get(i).l2.equals("Signature")){
                    value = signaturePath;
                }
                else if(fields.get(i).l2.equals("Geo Location")){
                    value = gpsCoord;
                }
            }
            else if(opj.type.equals("d")){                                        //EditText
                EditText et = (EditText)opj.v2;
                value = et.getText().toString();
                if(fields.get(i).l2.equals("Email")){
                    if(!isValidEmail(value)){
                        Toast.makeText(getApplicationContext(), "Not valid email format", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
            JSONObject temp = new JSONObject();
            try {
                temp.put("qid", qid);
                temp.put("value", value);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonArray.put(temp);
        }
        JSONObject finalOutput = new JSONObject();
        try {
            finalOutput.put("JSONOutput", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String content = finalOutput.toString();
        String path = getExternalFilesDir("ResponseJSON").getPath() + "/" + JsonArrayName + "/";
        File file = new File(path + ResponseJSONFileName);
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(file);
            fout.write(content.getBytes());
            Toast.makeText(getApplicationContext(), "JSON Saved Successfully", Toast.LENGTH_SHORT).show();
            finish();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
