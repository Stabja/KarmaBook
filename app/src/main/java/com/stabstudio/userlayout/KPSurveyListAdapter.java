package com.stabstudio.userlayout;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class KPSurveyListAdapter extends BaseAdapter{

    private ArrayList<String> list = new ArrayList<String>();
    private static LayoutInflater inflater = null;
    private Context context;

    public KPSurveyListAdapter(Context c){
        context = c;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for(int i = 0; i < 20; i++){
            list.add("User " + i);
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if(convertView == null){
            vi = inflater.inflate(R.layout.kp_survey_list_row, null);
        }
        TextView title = (TextView)vi.findViewById(R.id.form2id);
        Button button = (Button)vi.findViewById(R.id.misc);

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, SurveyActivity.class);
                context.startActivity(in);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, SurveyReportActivity.class);
                context.startActivity(in);
            }
        });


        String str = list.get(position);
        title.setText(str);
        return vi;
    }

}
