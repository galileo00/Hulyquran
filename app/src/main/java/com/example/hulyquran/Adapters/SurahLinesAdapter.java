package com.example.hulyquran.Adapters;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hulyquran.Models.SurahLineModel;
import com.example.hulyquran.R;

import java.util.List;

public class SurahLinesAdapter extends RecyclerView.Adapter<SurahLinesAdapter.ViewHolder> {


    private List<SurahLineModel> list;
    private Typeface aya;
    OnItemClickListener onItemViewClickListener;
    int itemIndex = -1;



    public void setOnItemViewClickListener(OnItemClickListener onItemViewClickListener) {
        this.onItemViewClickListener = onItemViewClickListener;
    }

    public SurahLinesAdapter(List<SurahLineModel> list, Typeface ayat) {
        this.list = list;
        this.aya = ayat;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.aya_line, viewGroup, false);
        return new ViewHolder(view);

    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {

        final SurahLineModel surahLine = list.get(position);
        viewHolder.surahLines.setText(surahLine.getSurahLine());
        viewHolder.itemView.setBackgroundResource(R.color.transperant);

        if (onItemViewClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemViewClickListener.onItemClick(surahLine, position);
                    itemIndex = position;
                    notifyDataSetChanged();

                }
            });
        }
        if (itemIndex == position) {
            viewHolder.itemView.setBackgroundResource(R.color.darktransperant);
        } else {
            viewHolder.itemView.setBackgroundResource(R.color.transperant);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView surahLines;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            surahLines = itemView.findViewById(R.id.suraLine);
            surahLines.setTypeface(aya);

        }
    }


    public interface OnItemClickListener {

        public void onItemClick(SurahLineModel lineModel, int position);


    }
}
