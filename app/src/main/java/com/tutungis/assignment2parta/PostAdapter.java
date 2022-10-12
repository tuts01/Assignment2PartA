package com.tutungis.assignment2parta;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder>
{
    private ArrayList<Post> data;
    private Activity activity;
    
    public PostAdapter(Activity activity, ArrayList<Post> data)
    {
        this.activity = activity;
        this.data = data;
    }
    
    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new PostViewHolder(LayoutInflater.from(activity), parent);
    }
    
    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position)
    {
        holder.bind(data.get(position));
    }
    
    @Override
    public int getItemCount()
    {
        return data.size();
    }
}
