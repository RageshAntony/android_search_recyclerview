package com.example.admin.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> implements Filterable {

    public Context context;
    public List<Model> modelList;
    public List<Model> myfilterList;


    public RecyclerViewAdapter(Context context, List<Model> modelList) {
        this.context = context;
        this.modelList = modelList;
        this.myfilterList = modelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recyclerview_items, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Model model = myfilterList.get(i);
        myViewHolder.textView.setText(model.getName());
    }

    @Override
    public int getItemCount() {
        return myfilterList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String text = constraint.toString();
                if (text.isEmpty()) {
                    myfilterList = modelList;
                } else {
                    ArrayList<Model> filterable = new ArrayList<>();
                    for (Model model : modelList) {
                        System.out.println("check equal " + model.getName() + "   " + text);
                        if (model.getName().toLowerCase().contains(text)) {
                            filterable.add(model);
                        }
                    }
                    myfilterList = filterable;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = myfilterList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                myfilterList = (ArrayList<Model>) results.values;
                notifyDataSetChanged();

            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvName);
        }
    }
}
