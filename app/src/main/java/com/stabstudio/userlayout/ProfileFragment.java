package com.stabstudio.userlayout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import android.graphics.Color;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    private ImageView imageView;
    private CheckBox cb1;
    private CheckBox cb2;
    private CheckBox cb3;
    private CheckBox cb4;

    private HorizontalBarChart barChart;
    private BarDataSet barDataSet1;
    private BarDataSet barDataSet2;
    private BarDataSet barDataSet3;
    private BarDataSet barDataSet4;

    ArrayList<BarDataSet> dataseth = new ArrayList<BarDataSet>();
    ArrayList<String> labels = new ArrayList<String>();

    public BarData data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View vi = inflater.inflate(R.layout.fragment_profile, container, false);

        imageView = (ImageView) vi.findViewById(R.id.profileimg);
        barChart= (HorizontalBarChart) vi.findViewById(R.id.charth);
        cb1 = (CheckBox)vi.findViewById(R.id.gcheckbox1);
        cb2 = (CheckBox)vi.findViewById(R.id.gcheckbox2);
        cb3 = (CheckBox)vi.findViewById(R.id.gcheckbox3);
        cb4 = (CheckBox)vi.findViewById(R.id.gcheckbox4);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(4f, 0));
        entries.add(new BarEntry(8f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(12f, 3));
        entries.add(new BarEntry(18f, 4));
        entries.add(new BarEntry(9f, 5));

        final BarDataSet dataset = new BarDataSet(entries, "# of Calls");


        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");

        ArrayList<BarEntry> group1 = new ArrayList<>();
        group1.add(new BarEntry(4f, 0));
        group1.add(new BarEntry(8f, 1));
        group1.add(new BarEntry(6f, 2));
        group1.add(new BarEntry(12f, 3));
        group1.add(new BarEntry(18f, 4));
        group1.add(new BarEntry(9f, 5));

        ArrayList<BarEntry> group2 = new ArrayList<>();
        group2.add(new BarEntry(6f, 0));
        group2.add(new BarEntry(7f, 1));
        group2.add(new BarEntry(8f, 2));
        group2.add(new BarEntry(12f, 3));
        group2.add(new BarEntry(15f, 4));
        group2.add(new BarEntry(10f, 5));

        ArrayList<BarEntry> group3 = new ArrayList<>();
        group3.add(new BarEntry(2f, 0));
        group3.add(new BarEntry(9f, 1));
        group3.add(new BarEntry(4f, 2));
        group3.add(new BarEntry(6f, 3));
        group3.add(new BarEntry(11f, 4));
        group3.add(new BarEntry(5f, 5));

        ArrayList<BarEntry> group4 = new ArrayList<>();
        group4.add(new BarEntry(16f, 0));
        group4.add(new BarEntry(9f, 1));
        group4.add(new BarEntry(3f, 2));
        group4.add(new BarEntry(2f, 3));
        group4.add(new BarEntry(11f, 4));
        group4.add(new BarEntry(14f, 5));

        barDataSet1 = new BarDataSet(group1, "Group 1");
        barDataSet1.setColor(Color.rgb(235, 0, 0));

        barDataSet2 = new BarDataSet(group2, "Group 2");
        barDataSet2.setColor(Color.rgb(149, 149, 149));

        barDataSet3 = new BarDataSet(group3, "Group 3");
        barDataSet3.setColor(Color.rgb(242, 114, 39));

        barDataSet4 = new BarDataSet(group4, "Group 4");
        barDataSet4.setColor(Color.rgb(0, 0, 0));

        cb1.setChecked(true);
        cb2.setChecked(true);
        cb3.setChecked(true);
        cb4.setChecked(true);

        dataseth.add(barDataSet4);
        dataseth.add(barDataSet3);
        dataseth.add(barDataSet2);
        dataseth.add(barDataSet1);

        data = new BarData(labels, dataseth);
        barChart.setData(data);
        barChart.animateY(5000);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent();
                in.setType("image/*");
                in.setAction(Intent.ACTION_PICK);
                //in.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(in, "Select Picture"), 100);
            }
        });

        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkChecked();
            }
        });

        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkChecked();
            }
        });

        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkChecked();
            }
        });

        cb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkChecked();
            }
        });
        return vi;
    }

    public void checkChecked(){
        dataseth.clear();
        barChart.clear();
        if(cb4.isChecked()){
            dataseth.add(barDataSet4);
        }
        if(cb3.isChecked()){
            dataseth.add(barDataSet3);
        }
        if(cb2.isChecked()){
            dataseth.add(barDataSet2);
        }
        if(cb1.isChecked()){
            dataseth.add(barDataSet1);
        }
        data = new BarData(labels, dataseth);
        barChart.setData(data);
    }

	//Some Commenting
	
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == Activity.RESULT_OK && data != null && data.getData() != null){
            Uri uri = data.getData();
            File file = new File(getActivity().getBaseContext().getExternalFilesDir("Images").getPath() + "/profilepic.png");
            FileOutputStream fout = null;
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getBaseContext().getContentResolver(), uri);
                imageView.setImageBitmap(bitmap);
                fout = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 80, fout);
                //Toast.makeText(getActivity().getBaseContext(), uri.toString(), Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
