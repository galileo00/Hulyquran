package com.example.hulyquran.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hulyquran.Adapters.AhadethAdapter;
import com.example.hulyquran.Fragments.DialogFragments.DialogFragmentOfHadeth;
import com.example.hulyquran.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AhadethFragment extends Fragment {


    public AhadethFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    AhadethAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<String> list;
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ahadeth, container, false);

        creatList();
        recyclerView = view.findViewById(R.id.ahadethRecyclerView);
        adapter = new AhadethAdapter(list);
        adapter.setOnItemViewClickListener(new AhadethAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos, String hadeth) {

                DialogFragmentOfHadeth dialogFragment = new DialogFragmentOfHadeth();
                dialogFragment.setHadethName(hadeth);
                dialogFragment.show(getChildFragmentManager(), "anything");



            }
        });
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        return view;
    }

    private void creatList() {

        BufferedReader reader;
        list = new ArrayList<>();
        try {
            final InputStream file = getContext().getAssets().open("ahadeth.txt");
            reader = new BufferedReader(new InputStreamReader(file));
            String line = reader.readLine();
            list.add(line);

            while (line != null) {
                line = reader.readLine();

                if (line.equals("#")) {
                    line = reader.readLine();
                    list.add(line);

                }


            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }


    }


}
