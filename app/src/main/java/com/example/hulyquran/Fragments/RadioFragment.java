package com.example.hulyquran.Fragments;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hulyquran.API.ApiManager;
import com.example.hulyquran.API.ModelRadioChanels.RadioResponse;
import com.example.hulyquran.API.ModelRadioChanels.RadiosItem;
import com.example.hulyquran.Adapters.RadioRecyclerAdapter;
import com.example.hulyquran.Base.BaseFragment;
import com.example.hulyquran.R;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RadioFragment extends BaseFragment {


    private RecyclerView recyclerviewRadio;
    RecyclerView.LayoutManager layoutManager;
    RadioRecyclerAdapter adapter;


    public RadioFragment() {
        // Required empty public constructor
    }

    View view;
    int pastPosition = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_radio, container, false);

        showProgresBar();
        creatList();
        recyclerviewRadio = view.findViewById(R.id.recyclerviewRadio);
        layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
        adapter = new RadioRecyclerAdapter(null);
        adapter.changBoolean(false);
        SnapHelper snapHelper = new PagerSnapHelper();
        recyclerviewRadio.setLayoutManager(layoutManager);
        recyclerviewRadio.setAdapter(adapter);
        snapHelper.attachToRecyclerView(recyclerviewRadio);

        adapter.setOnPlayViewClickListener(new RadioRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(RadiosItem chanel, int position) {

                if (mediaPlayer != null && mediaPlayer.isPlaying() && position == pastPosition) {
                    stopMusic();
                    adapter.changBoolean(false);
                } else if (mediaPlayer != null && mediaPlayer.isPlaying()) {

                    stopMusic();
                    playMusic(chanel.getURL());
                    pastPosition = position;
                    adapter.changBoolean(true);
                } else {
                    playMusic(chanel.getURL());
                    pastPosition = position;
                    adapter.changBoolean(true);
                }

            }
        });
        adapter.setOnStopViewClickListener(new RadioRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(RadiosItem chanel, int position) {
                stopMusic();
                adapter.changBoolean(false);

            }
        });


        return view;
    }



    public MediaPlayer mediaPlayer;

    public void playMusic(String url) {
        stopMusic();
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(url);
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

    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

    }

    private void creatList() {

        ApiManager.getAPIs().getAllRAdioResponse().enqueue(new Callback<RadioResponse>() {
            @Override
            public void onResponse(Call<RadioResponse> call, Response<RadioResponse> response) {
                hideDialog();
                if (response.isSuccessful()) {
                    adapter.changeList(response.body().getRadios());
                }
            }

            @Override
            public void onFailure(Call<RadioResponse> call, Throwable t) {
                hideDialog();
                showMessage("error", t.getMessage(), "ok");
            }
        });
    }

}
