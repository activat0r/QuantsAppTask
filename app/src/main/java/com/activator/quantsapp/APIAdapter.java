package com.activator.quantsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;

public class APIAdapter extends RecyclerView.Adapter<APIAdapter.APIViewHolder> {
    private Context mContext;
    private List<FeedModel> feedModelList;

    APIAdapter(List<FeedModel> listitems, Context context){
        this.mContext = context;
        this.feedModelList = listitems;
    }

    @NonNull
    @Override
    public APIViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holder = LayoutInflater.from(mContext).inflate(R.layout.feed_item,parent,false);
        return new APIViewHolder(holder);

    }

    @Override
    public void onBindViewHolder(@NonNull APIViewHolder holder, int position) {

        FeedModel item = feedModelList.get(position);
        Picasso.get().load(item.getProfileURL()).fit().into(holder.image);
        holder.name.setText(item.getName());
        holder.status.setText(item.getStatus());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YY hh:mm");
        String dateString = sdf.format(item.getTime());
        holder.date.setText(dateString);

    }

    @Override
    public int getItemCount() {
        return feedModelList.size();
    }

    public class APIViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView status;
        private TextView date;
        private ImageView image;

        public APIViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.feedList_name);
            status = itemView.findViewById(R.id.feedList_status);
            date = itemView.findViewById(R.id.feedList_time);
            image = itemView.findViewById(R.id.feedList_image);
        }
    }
}
