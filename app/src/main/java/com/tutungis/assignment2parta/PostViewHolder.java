package com.tutungis.assignment2parta;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class PostViewHolder extends RecyclerView.ViewHolder
{
    PostViewHolder(LayoutInflater li, ViewGroup parent)
    {
        super(li.inflate(R.layout.post_list_item, parent, false));
    }
    
    void bind(Post post)
    {
        TextView title = itemView.findViewById(R.id.postTitle);
        TextView body = itemView.findViewById(R.id.postBody);
        
        title.setText(post.getTitle());
        body.setText(post.getBody());
    }
}
