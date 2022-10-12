package com.tutungis.assignment2parta;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MenuActivity extends AppCompatActivity
{
    private static final String USERS = "users";
    
    private ArrayList<User> users;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        if(savedInstanceState == null)
        {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            UserRetrievalTask task = new UserRetrievalTask(MenuActivity.this);
            Future<ArrayList<User>> future = executorService.submit(task);
    
            RecyclerView rv = findViewById(R.id.userRecyclerView);
            rv.setLayoutManager(new LinearLayoutManager(
                    MenuActivity.this, LinearLayoutManager.VERTICAL, false));
            users = getUsers(future);
            rv.setAdapter(new UserAdapter(MenuActivity.this, users));
        }
    }
    
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState)
    {
        outState.putSerializable(USERS, users);
        super.onSaveInstanceState(outState);
    }
    
    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState)
    {
        if(savedInstanceState != null)
        {
            RecyclerView rv = findViewById(R.id.userRecyclerView);
            rv.setLayoutManager(new LinearLayoutManager(
                    MenuActivity.this, LinearLayoutManager.VERTICAL, false));
            users = (ArrayList<User>) savedInstanceState.get(USERS);
            rv.setAdapter(new UserAdapter(MenuActivity.this, users));
            super.onRestoreInstanceState(savedInstanceState);
        }
    }

    private ArrayList<User> getUsers(Future<ArrayList<User>> future)
    {
        ArrayList<User> users = null;

        try {
            users = future.get(10000, TimeUnit.MILLISECONDS);
        }
        catch(ExecutionException e)
        {
            e.printStackTrace();
            runOnUiThread(() -> Toast.makeText(MenuActivity.this, "Error Retrieving User Details",
                    Toast.LENGTH_LONG).show());
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
            runOnUiThread(() -> Toast.makeText(MenuActivity.this,
                    "Interrupted while Retrieving User Details", Toast.LENGTH_LONG).show());
        }
        catch(TimeoutException e)
        {
            e.printStackTrace();
            runOnUiThread(() -> Toast.makeText(MenuActivity.this,
                    "Timeout while Retrieving User Details", Toast.LENGTH_LONG).show());
        }
        return users;
    }
}