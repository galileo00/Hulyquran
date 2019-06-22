package com.example.hulyquran.Adapters;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hulyquran.Models.Surah;
import com.example.hulyquran.R;

import java.util.List;

public class SurahRecyclerAdapter extends RecyclerView.Adapter<SurahRecyclerAdapter.ViewHolder> {


    private OnItemClickListener onItemViewClickListener;

    public void setOnItemViewClickListener(OnItemClickListener onItemViewClickListener) {
        this.onItemViewClickListener = onItemViewClickListener;
    }

    private List<Surah> list;
    private Typeface surah;

    public SurahRecyclerAdapter(List<Surah> list, Typeface surah) {
        this.list = list;
        this.surah = surah;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.surah_item, viewGroup, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {


        final Surah surah = list.get(position);
        viewHolder.surahName.setText(surah.getSurahName());
        if (onItemViewClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemViewClickListener.onItemClicked(surah, position);
                }
            });

        }

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView surahName;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            surahName = itemView.findViewById(R.id.surahNam);
            surahName.setTypeface(surah);
        }
    }


   public interface OnItemClickListener {



        void onItemClicked(Surah surah, int position);


    }

}

