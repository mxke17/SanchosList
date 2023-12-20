package com.example.sanchoslists;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StringListAdapter extends RecyclerView.Adapter<StringListAdapter.StringViewHolder> {
    List<String> arrayList;

    public StringListAdapter(List<String> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public StringViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main, parent, false);

        return new StringViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull final StringViewHolder holder, final int position) {
        holder.tagText.setText(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class StringViewHolder extends RecyclerView.ViewHolder {
        public TextView tagText;

        public StringViewHolder(View view) {
            super(view);

            tagText = view.findViewById(R.id.listNamesList);
        }
    }
}
