package com.tutungis.assignment2parta;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class UserActivity extends AppCompatActivity {

    private static final String USER = "user";
    private static final String POSTS = "posts";
    
    private ArrayList<Post> posts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        
        TextView name = findViewById(R.id.name2),
                username = findViewById(R.id.userName),
                email = findViewById(R.id.email),
                address = findViewById(R.id.address),
                phone = findViewById(R.id.phone),
                website = findViewById(R.id.website),
                companyName = findViewById(R.id.companyName),
                catchphrase = findViewById(R.id.catchphrase),
                bs = findViewById(R.id.bs);
    
        Button button = findViewById(R.id.button);
        
        User user = (User) getIntent().getSerializableExtra(USER);
        
        name.setText(user.getName());
        username.setText(user.getUsername());
        email.setText(user.getEmail());
        address.setText(user.getAddress().toString());
        phone.setText(user.getPhone());
        website.setText(user.getWebsite());
        companyName.setText(user.getCompany().getName());
        catchphrase.setText(user.getCompany().getCatchphrase());
        bs.setText(user.getCompany().getBs());
        
        button.setOnClickListener(view ->
        {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            PostRetrievalTask task = new PostRetrievalTask(UserActivity.this, user.getId());
            Future<ArrayList<Post>> future = executorService.submit(task);
            
            RecyclerView rv = findViewById(R.id.postRecyclerView);
            rv.setLayoutManager(new LinearLayoutManager(
                    UserActivity.this, LinearLayoutManager.VERTICAL, false));
            posts = getPosts(future);
            rv.setAdapter(new PostAdapter(UserActivity.this, posts));
        });
    }
    
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState)
    {
        outState.putSerializable(POSTS, posts);
        super.onSaveInstanceState(outState);
    }
    
    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState)
    {
        if(savedInstanceState != null)
        {
            RecyclerView rv = findViewById(R.id.postRecyclerView);
            rv.setLayoutManager(new LinearLayoutManager(
                    UserActivity.this, LinearLayoutManager.VERTICAL, false));
            posts = (ArrayList<Post>) savedInstanceState.get(POSTS);
            rv.setAdapter(new PostAdapter(UserActivity.this, posts));
            super.onRestoreInstanceState(savedInstanceState);
        }
    }
    
    public static Intent getIntent(Context context, User user)
    {
        return new Intent(context, UserActivity.class).putExtra(USER, user);
    }
    
    private ArrayList<Post> getPosts(Future<ArrayList<Post>> future)
    {
        ArrayList<Post> posts = null;
        
        try
        {
            posts = future.get(10000, TimeUnit.MILLISECONDS);
        }
        catch(ExecutionException e)
        {
            e.printStackTrace();
            runOnUiThread(() -> Toast.makeText(UserActivity.this, "Error Retrieving Posts",
                    Toast.LENGTH_LONG).show());
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
            runOnUiThread(() -> Toast.makeText(UserActivity.this,
                    "Interrupted while Retrieving Posts", Toast.LENGTH_LONG).show());
        }
        catch(TimeoutException e)
        {
            e.printStackTrace();
            runOnUiThread(() -> Toast.makeText(UserActivity.this,
                    "Timeout while Retrieving Posts", Toast.LENGTH_LONG).show());
        }
        
        return posts;
    }
    
    
}