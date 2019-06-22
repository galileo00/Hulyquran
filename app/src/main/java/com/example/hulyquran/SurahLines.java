package com.example.hulyquran;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hulyquran.API.ApiManager;
import com.example.hulyquran.API.RecietersVoice.RadiosItem;
import com.example.hulyquran.API.RecietersVoice.RecietersResponse;
import com.example.hulyquran.Adapters.RecietersRecyclerAdapter;
import com.example.hulyquran.Adapters.SurahLinesAdapter;
import com.example.hulyquran.Base.BaseActivity;
import com.example.hulyquran.Fragments.QuranFragment;
import com.example.hulyquran.Models.SurahLineModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SurahLines extends BaseActivity {


    private List<SurahLineModel> list;
    private RecyclerView.LayoutManager layoutManager;
    private SurahLinesAdapter adapter;
    private RecyclerView recyclerView;
    private String surahName;
    private TextView surahNameText;
    private int sorahIndex;
    private int ayalineIndex = -1;
    private RecyclerView recyclerViewOfRecieters;
    RecietersRecyclerAdapter recietersRecyclerAdapter;
    RecyclerView.LayoutManager reclayoutManager;
    MediaPlayer mediaPlayer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah_lines);


        surahNameText = findViewById(R.id.suraNameTextView);



        ayaRecyclerView();
        recietrsRecyclerView();


    }

    private String creatReadyURL() {

        String surahIndexString = sorahIndex + "";
        while (surahIndexString.length() < 3) {
            surahIndexString = "0" + surahIndexString;
        }

        String ayalineIndexString = ayalineIndex + "";
        while (ayalineIndexString.length() < 3) {
            ayalineIndexString = "0" + ayalineIndexString;
        }


        String theReadyUrl = surahIndexString + ayalineIndexString + ".mp3";

        return theReadyUrl;
    }

    private void creatYasList() {

        list = new ArrayList<>();
        int count = 0;


        BufferedReader reader;
        String filename = QuranFragment.getFileName();
        surahName = "سورة" + QuranFragment.getSurahName();
        surahNameText.setText(surahName);

        try {
            final InputStream file = getAssets().open(filename);
            reader = new BufferedReader(new InputStreamReader(file));
            String line = reader.readLine();
            while (line != null) {
                count++;
                list.add(new SurahLineModel(line + "(" + count + ")"));
                line = reader.readLine();


            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }


    }


    public void ayaRecyclerView() {
        creatYasList();
        Typeface secondfont = Typeface.createFromAsset(getAssets(), "fonts/khara.otf");
        surahNameText.setTypeface(secondfont);
        adapter = new SurahLinesAdapter(list, secondfont);
        sorahIndex = QuranFragment.getSurahIndex();
        adapter.setOnItemViewClickListener(new SurahLinesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SurahLineModel lineModel, int position) {
                ayalineIndex = position + 1;
                recyclerViewOfRecieters.setVisibility(View.VISIBLE);
                creatReadyURL();
            }
        });
        layoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


    }


    public void recietrsRecyclerView() {


        recyclerViewOfRecieters = findViewById(R.id.recyclerViewOfRecieters);
        recietersRecyclerAdapter = new RecietersRecyclerAdapter(null);
        reclayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewOfRecieters.setAdapter(recietersRecyclerAdapter);
        recyclerViewOfRecieters.setLayoutManager(reclayoutManager);
        recietersRecyclerAdapter.setOnplayViewClickListener(new RecietersRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(RadiosItem surah, int position) {
                playreciter(surah.getURL()+creatReadyURL());
            }
        });
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerViewOfRecieters);
        creatRecietersList();


    }
    private void playreciter(String s) {

        if (mediaPlayer!=null&& mediaPlayer.isPlaying())return;

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(s);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void creatRecietersList() {
        showProgresBar();

        ApiManager.getAPIs().getAllRecietersResponse().enqueue(new Callback<RecietersResponse>() {
            @Override
            public void onResponse(Call<RecietersResponse> call, Response<RecietersResponse> response) {

                hideDialog();
                if (response.isSuccessful()) {
                    recietersRecyclerAdapter.changeList(response.body().getRadios());
                }
            }

            @Override
            public void onFailure(Call<RecietersResponse> call, Throwable t) {

                hideDialog();
                Toast.makeText(activity, "Faild", Toast.LENGTH_SHORT).show();

            }
        });
    }


}
