package com.stabstudio.userlayout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;


public class SurveyListFragment extends Fragment {

    private ListView listView;
    private EditText searchField;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View vi = inflater.inflate(R.layout.fragment_form_list, container, false);

        searchField = (EditText)vi.findViewById(R.id.searchfield);
        listView = (ListView)vi.findViewById(R.id.formslist);

        final SurveyListAdapter adapter = new SurveyListAdapter(HomeScreenActivity.SWIPEACT);
        listView.setAdapter(adapter);
        //listView.setNestedScrollingEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                
            }
        });

        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return vi;

    }

}
