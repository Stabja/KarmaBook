package com.stabstudio.userlayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;


public class SurveyListAdapter extends BaseAdapter implements Filterable{

    private static LayoutInflater inflater = null;
    private Context context;
    private ArrayList<String> list = new ArrayList<String>();
    private ArrayList<String> originalData = new ArrayList<String>();
    private ArrayList<String> filteredData = new ArrayList<String>();

    public SurveyListAdapter(Context c){
        context = c;
        String form = context.getResources().getString(R.string.form);
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        /*File home = context.getExternalFilesDir("InputJSON");
        File[] listFiles = home.listFiles();
        for(File f : listFiles){
            String temp = f.getName();
            String name = temp.substring(0, temp.lastIndexOf('.'));
            list.add(name);
        }*/

        readFromAssets();
        copyAssets();
        CreateResponseFolders();

        this.originalData = list;
        this.filteredData = list;

    }

    private void readFromAssets(){
        String[] names;
        try {
            names = context.getAssets().list("InputJSON");
            for(String f1 : names){
                //Toast.makeText(context, f1, Toast.LENGTH_SHORT).show();
                String name = f1.substring(0, f1.lastIndexOf('.'));
                list.add(name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFromSDCard(){
        File home = context.getExternalFilesDir("InputJSON");
        File[] listFiles = home.listFiles();
        for(File f : listFiles){
            String temp = f.getName();
            String name = temp.substring(0, temp.lastIndexOf('.'));
            list.add(name);
        }
    }

    private void copyAssets() {
        String[] files;
        try {
            files = context.getAssets().list("InputJSON");
            if (files != null) {
                for (String filename : files) {
                    InputStream in = null;
                    OutputStream out = null;
                    try {
                        in = context.getAssets().open("InputJSON/" + filename);
                        File outFile = new File(context.getExternalFilesDir("InputJSON").getPath() + "/" + filename);
                        out = new FileOutputStream(outFile);
                        copyFile(in, out);
                    } catch (IOException e) {
                        Log.e("tag", "Failed to copy asset file: " + filename, e);
                    } finally {
                        if (in != null) {
                            try {
                                in.close();
                            } catch (IOException e) {
                                // NOOP
                            }
                        }
                        if (out != null) {
                            try {
                                out.close();
                            } catch (IOException e) {
                                // NOOP
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            Log.e("tag", "Failed to get asset file list.", e);
        }
    }

    public void CreateResponseFolders(){
        for(String name : list){
            File home = context.getExternalFilesDir("ResponseJSON/" + name);
        }
    }

    public void createResponseCount(){
        String data = "";
        for(int i = 0; i < list.size(); i++){
            data += "0,";
        }
        data = data.substring(0, data.length()-1);
        File responseCount = context.getExternalFilesDir("ResponseCount.txt");
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(responseCount);
            fout.write(data.getBytes());
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


    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }

    @Override
    public int getCount() {
        return filteredData.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if(convertView == null){
            vi = inflater.inflate(R.layout.survey_list_row, null);
        }
        LinearLayout form = (LinearLayout) vi.findViewById(R.id.formid);
        LinearLayout onlineres = (LinearLayout)vi.findViewById(R.id.onlineresp);
        LinearLayout offlineres = (LinearLayout)vi.findViewById(R.id.offlineresp);
        TextView title = (TextView)vi.findViewById(R.id.formname);

        final int i = position;

        title.setText(filteredData.get(position));
        form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, DynamicFormActivity2.class);
                in.putExtra("jsonName", filteredData.get(position));
                in.putExtra("fromActivity", "SurveyListFragment");
                context.startActivity(in);
            }
        });

        onlineres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, OnlineResponseActivity.class);
                context.startActivity(in);
            }
        });

        offlineres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, OfflineResponseActivity.class);
                in.putExtra("responseFolderName", filteredData.get(position));
                context.startActivity(in);
            }
        });

        return vi;
    }

    @Override
    public Filter getFilter() {
        return new Filter(){

            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                charSequence = charSequence.toString().toLowerCase();
                FilterResults results = new FilterResults();
                //If there is nothing to filter on, return the original data for your list
                if(charSequence == null || charSequence.toString().length() == 0){
                    results.values = originalData;
                    results.count = originalData.size();
                }
                else{
                    ArrayList<String> founded = new ArrayList<String>();
                    //Iterate through originalData and put those in filteredData which matches the string
                    for(String data : originalData){
                        if(data.toLowerCase().contains(charSequence)){
                            founded.add(data);
                        }
                    }
                    results.values = founded;
                    results.count = founded.size();
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredData = (ArrayList<String>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
