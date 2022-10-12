package com.tutungis.assignment2parta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder
{
    UserViewHolder(LayoutInflater li, ViewGroup parent)
    {
        super(li.inflate(R.layout.user_list_item, parent, false));
    }

    void bind(User user)
    {
        TextView name = itemView.findViewById(R.id.name);
        TextView username = itemView.findViewById(R.id.username);

        name.setText(user.getName());
        username.setText(user.getUsername());
        Context context = itemView.getContext();
        itemView.setOnClickListener(view -> context.startActivity(UserActivity.getIntent(context, user)));
    }
}