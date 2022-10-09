package com.tutungis.assignment2parta;

import android.app.Activity;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Class containing methods for connections to remote REST services
 *
 * @class           RemoteUtils
 * @author          Tristan S. Tutungis
 * @date_created    9/10/2022
 * @last_modified   9/10/2022 0:05
 */
public class RemoteUtils {
    public static HttpURLConnection openConnection(String urlStr, Activity uiActivity)
    {
        HttpURLConnection connection = null;

        try
        {
            connection = (HttpURLConnection) new URL(urlStr).openConnection();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        if(connection == null)
        {
            uiActivity.runOnUiThread(new Runnable()
            {
                @Override
                public void run() {
                    Toast.makeText(uiActivity,"Check Internet and Restart Application",
                            Toast.LENGTH_LONG).show();
                }
            });
        }

        return connection;
    }
}
