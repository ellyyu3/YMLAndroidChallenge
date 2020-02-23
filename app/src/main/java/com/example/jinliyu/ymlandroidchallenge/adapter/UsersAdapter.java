package com.example.jinliyu.ymlandroidchallenge.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.jinliyu.ymlandroidchallenge.R;
import com.example.jinliyu.ymlandroidchallenge.model.User;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<User> userList;
    private final Context context;
    private final RecyclerViewClickListener listener;

    /**
     * @param context        context
     * @param userList       followers data from service
     * @param listener       listener for passing data
     */
    public UsersAdapter(Context context, List<User> userList, RecyclerViewClickListener listener) {
        this.context = context;
        this.userList = userList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_user_layout, parent, false);
            return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        User user =  userList.get(position);
        final String iconUrl = user.avatar_url;
        GridViewHolder gridViewHolder = (GridViewHolder) holder;
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);
        Glide.with(context)
                .load(iconUrl)
                .apply(options)
                .into(gridViewHolder.imageView);

        holder.itemView.setTag(position);
        gridViewHolder.userNameTv.setText(user.login);
}

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public interface RecyclerViewClickListener {
        void recyclerViewListClicked(View view, int position);
    }

    class GridViewHolder extends RecyclerView.ViewHolder {

        final ImageView imageView;
        final TextView userNameTv;

        GridViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.user_grid_image);
            userNameTv = view.findViewById(R.id.user_username);

            view.setOnClickListener(v -> listener.recyclerViewListClicked(v, (int) v.getTag()));
        }

    }



}
