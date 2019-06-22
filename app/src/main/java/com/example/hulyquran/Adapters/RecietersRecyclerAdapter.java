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

import com.example.hulyquran.API.RecietersVoice.RadiosItem;
import com.example.hulyquran.R;

import java.util.List;

public class RecietersRecyclerAdapter extends RecyclerView.Adapter<RecietersRecyclerAdapter.ViewHolder> {


    private OnItemClickListener onplayViewClickListener;

    public void setOnplayViewClickListener(OnItemClickListener onplayViewClickListener) {
        this.onplayViewClickListener = onplayViewClickListener;
    }

    private List<RadiosItem> list;
    private Typeface surah;

    public RecietersRecyclerAdapter(List<RadiosItem> list) {
        this.list = list;
    }


    public void changeList( List<RadiosItem> list ){
        this.list=list;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recieters, viewGroup, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {


        final RadiosItem recieter = list.get(position);
        viewHolder.name.setText(recieter.getName());
        viewHolder.rwaya.setText(recieter.getRewaya());
        viewHolder.moratal.setText(recieter.getMusshafType());
        if (onplayViewClickListener != null) {
            viewHolder.playButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onplayViewClickListener.onItemClicked(recieter, position);
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
        TextView name;
        TextView rwaya;
        TextView moratal;
        ImageView playButton;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.recitersName);
            rwaya = itemView.findViewById(R.id.rewaya);
            moratal = itemView.findViewById(R.id.moratal);
            playButton = itemView.findViewById(R.id.playAyaButton);
        }
    }


   public interface OnItemClickListener {



        void onItemClicked(RadiosItem surah, int position);


    }

}

