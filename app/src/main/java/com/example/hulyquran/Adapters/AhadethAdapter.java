package com.example.hulyquran.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hulyquran.R;

import java.util.List;

public class AhadethAdapter extends RecyclerView.Adapter<AhadethAdapter.ViewHolder> {

    List<String> list;

    OnItemClickListener onItemViewClickListener;

    public void setOnItemViewClickListener(OnItemClickListener onItemViewClickListener) {
        this.onItemViewClickListener = onItemViewClickListener;
    }

    public AhadethAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item_ahadeth_names,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        final String hadethName = list.get(position);
        viewHolder.hadethName.setText(hadethName);

        if (onItemViewClickListener!=null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemViewClickListener.onClick(position ,hadethName);
                }
            });
        }




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView hadethName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hadethName = itemView.findViewById(R.id.hadethName);
        }
    }



    public interface OnItemClickListener {

         void onClick( int pos, String hadeth);

    }



}
