package com.example.hulyquran.Fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hulyquran.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CountingFragment extends Fragment {


    public CountingFragment() {
    }


    // Required empty public constructor


    private Button tasbeha1view;
    private Button tasbehaAllview;
    private TextView tasbeha1;
    private TextView tasbehaAll;


    private View view;

    int count;
    ImageView imagefoto;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_counting, container, false);


        Typeface secondfont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/khara.otf");


        tasbeha1 = view.findViewById(R.id.tasbeha1);
        tasbeha1.setTypeface(secondfont);
        tasbeha1view = view.findViewById(R.id.tasbehaview);
        tasbehaAll = view.findViewById(R.id.tasbehaAll);
        tasbehaAll.setTypeface(secondfont);
        tasbehaAllview = view.findViewById(R.id.tasbehaAllview);
        imagefoto = view.findViewById(R.id.sebhafoto);
        count = 0;
        imagefoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count < 33) {
                    count++;
                    tasbeha1view.setText(count + "");
                    tasbehaAllview.setText(count + "");
                } else {
                    Toast.makeText(getContext(), "اضغط على نوغ التسبيح لتغييره", Toast.LENGTH_SHORT).show();
                }

            }
        });


        Spinner spinner3 = view.findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.spiner_items, R.layout.text);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                count = 0;
                tasbeha1view.setText(count + "");
                tasbehaAllview.setText(count + "");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;
    }

}
