package com.example.hulyquran.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hulyquran.R;

import java.util.List;

public class HadethLinesAdapter  extends RecyclerView.Adapter<HadethLinesAdapter.ViewHolder> {

List<String> list;

    public HadethLinesAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item_hadeth_lines,viewGroup,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        String line = list.get(i);
        viewHolder.hadethLine.setText(line);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView hadethLine;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            hadethLine = itemView.findViewById(R.id.hadethLine);
        }
    }
}
