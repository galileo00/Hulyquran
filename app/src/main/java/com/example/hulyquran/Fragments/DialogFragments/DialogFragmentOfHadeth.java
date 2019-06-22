package com.example.hulyquran.Fragments.DialogFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hulyquran.Adapters.HadethLinesAdapter;
import com.example.hulyquran.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DialogFragmentOfHadeth extends DialogFragment {


    public DialogFragmentOfHadeth() {
    }

    View view;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    HadethLinesAdapter adapter;
    List<String> list;
    String hadethName ;

    public void setHadethName(String hadethName) {
        this.hadethName = hadethName;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_fragmen_hadeth_content, container, false);


        creatList();
        recyclerView = view.findViewById(R.id.linesRecyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        adapter = new HadethLinesAdapter(list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);


        return view;


    }

    private void creatList() {


        BufferedReader reader;
        list = new ArrayList<>();
        try {
            final InputStream file = getContext().getAssets().open("ahadeth.txt");
            reader = new BufferedReader(new InputStreamReader(file));
            String line = reader.readLine();
            while (line != null) {
                if (Objects.equals(line, hadethName)) {
                    while (!Objects.equals(line, "#")) {
                        list.add(line);
                        line = reader.readLine();
                    }
                }
                line = reader.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
