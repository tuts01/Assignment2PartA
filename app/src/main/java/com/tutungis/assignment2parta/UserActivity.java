package com.tutungis.assignment2parta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class UserActivity extends AppCompatActivity {

    private static final String USER = "user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
    }

    public static Intent getIntent(Context context, User user)
    {
        return new Intent(context, UserActivity.class).putExtra(USER, user);
    }
}