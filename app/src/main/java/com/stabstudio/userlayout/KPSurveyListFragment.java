package com.stabstudio.userlayout;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class KPSurveyListFragment extends Fragment {

    private Button registeredUser;
    private Button enroll;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View vi = inflater.inflate(R.layout.fragment_kp_form_list, container, false);

        registeredUser = (Button)vi.findViewById(R.id.regUser);
        enroll = (Button)vi.findViewById(R.id.enroll);

        registeredUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity().getBaseContext(), UserListActivity.class);
                startActivity(in);
            }
        });

        enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity().getBaseContext(), EnrollmentFormActivity.class);
                startActivity(in);
            }
        });
        return vi;
    }

}
