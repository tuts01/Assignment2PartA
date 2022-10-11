package com.tutungis.assignment2parta;

import android.app.Activity;
import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.concurrent.Callable;

/**
 * Callable class designed to retrieve post details for a given user from REST
 *
 * @class           PostRetrievalTask
 * @implements      Callable
 * @implements      Urls
 * @author          Tristan S. Tutungis
 * @date_created    10/10/2022
 * @last_modified   10/10/2022 13:38
 */
public class PostRetrievalTask implements Callable<ArrayList<Post>>, Urls
{
    private final Activity uiActivity;
    private final int userId;

    public PostRetrievalTask(Activity uiActivity, int userId)
    {
        this.uiActivity = uiActivity;
        this.userId = userId;
    }

    public ArrayList<Post> call()
    {
        ArrayList<Post> posts = new ArrayList<>();
        String dataStr;

        HttpURLConnection connection = RemoteUtils.openConnection(
                Urls.POSTS_URL + "?userId=" + this.userId, uiActivity);

        if(connection != null) {
            if (RemoteUtils.checkConnection(connection, uiActivity)) {
                dataStr = RemoteUtils.getJSONString(connection, this.uiActivity);
                parsePostData(dataStr, posts);
            }

            connection.disconnect();
        }

        return posts;
    }

    private void parsePostData(String data, ArrayList<Post> posts)
    {
        try
        {
            JSONArray jsonArray = new JSONArray(data);

            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                posts.add(new Post(this.userId, jsonObject.getInt("id"),
                        jsonObject.getString("title"), jsonObject.getString("body")));
            }
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }
    }
}
