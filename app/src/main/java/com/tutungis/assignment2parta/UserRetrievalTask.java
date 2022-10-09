package com.tutungis.assignment2parta;

import android.app.Activity;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class UserRetrievalTask implements Callable<ArrayList<User>>, Urls
{
    private Activity uiActivity;

    public UserRetrievalTask(Activity uiActivity)
    {
        this.uiActivity = uiActivity;
    }
    public ArrayList<User> call() throws IOException {
        ArrayList<User> users = new ArrayList<User>();

        HttpURLConnection connection = RemoteUtils.openConnection(Urls.USERS_URL, uiActivity);

        if(connection != null)
        {
            try
            {
                RemoteUtils.checkConnection(connection);
            }
            catch(HttpResponseException e)
            {

            }
        }
    }
}
