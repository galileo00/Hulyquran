package com.example.hulyquran.Adapters;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hulyquran.API.ModelRadioChanels.RadiosItem;
import com.example.hulyquran.Models.Surah;
import com.example.hulyquran.R;

import java.util.List;

public class RadioRecyclerAdapter extends RecyclerView.Adapter<RadioRecyclerAdapter.ViewHolder> {


    private OnItemClickListener onPlayViewClickListener;
    private OnItemClickListener onStopViewClickListener;

    public void setOnPlayViewClickListener(OnItemClickListener onPlayViewClickListener) {
        this.onPlayViewClickListener = onPlayViewClickListener;
    }

    public void setOnStopViewClickListener(OnItemClickListener onStopViewClickListener) {
        this.onStopViewClickListener = onStopViewClickListener;
    }

    private List<RadiosItem> list;
    boolean isPlaying;

    public RadioRecyclerAdapter(List<RadiosItem> list) {
        this.list = list;


    }


    public void changeList(List<RadiosItem> list) {

        this.list = list;
        notifyDataSetChanged();
    }

    public void changBoolean(boolean isPlaying) {
        this.isPlaying = isPlaying;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item_radio_chanels, viewGroup, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {

        final RadiosItem chanel = list.get(position);
        viewHolder.chanelName.setText(chanel.getName());





        if (onPlayViewClickListener != null) {
            viewHolder.play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onPlayViewClickListener.onItemClicked(chanel, position);
                    if (isPlaying) {
                        viewHolder.play.setImageResource(R.drawable.ic_pause);
                    } else {
                        viewHolder.play.setImageResource(R.drawable.ic_play);
                    }



                }
            });
        }
        if (onStopViewClickListener != null) {
            viewHolder.stop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onStopViewClickListener.onItemClicked(chanel, position);
                    if (isPlaying) {
                        viewHolder.play.setImageResource(R.drawable.ic_pause);
                    } else {
                        viewHolder.play.setImageResource(R.drawable.ic_play);
                    }


                }
            });
        }
    }


    @Override
    public int getItemCount() {
        if (list == null) return 0;
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView chanelName;
        ImageView play;
        ImageView stop;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            chanelName = itemView.findViewById(R.id.chanellName);
            play = itemView.findViewById(R.id.play);
            stop = itemView.findViewById(R.id.stop);

        }


    }


    public interface OnItemClickListener {


        void onItemClicked(RadiosItem chanel, int position);


    }

}

