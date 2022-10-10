package com.tutungis.assignment2parta;

import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.concurrent.Callable;

/**
 * Callable class designed to retrieve user details from REST
 *
 * @class           UserRetrievalTask
 * @implements      Callable
 * @implements      Urls
 * @author          Tristan S. Tutungis
 * @date_created    9/10/2022
 * @last_modified   10/10/2022 10:43
 */
public class UserRetrievalTask implements Callable<ArrayList<User>>, Urls
{
    private final Activity uiActivity;

    public UserRetrievalTask(Activity uiActivity)
    {
        this.uiActivity = uiActivity;
    }
    
    public ArrayList<User> call(){
        ArrayList<User> users = new ArrayList<>();
        String dataStr;
        HttpURLConnection connection = RemoteUtils.openConnection(Urls.USERS_URL, uiActivity);

        if(connection != null)
        {
            if(RemoteUtils.checkConnection(connection, uiActivity))
            {
                dataStr = RemoteUtils.getJSONString(connection, this.uiActivity);
                parseUserData(dataStr, users);
            }
            
            connection.disconnect();
        }
        
        return users;
    }
    
    private void parseUserData(String data, ArrayList<User> users)
    {
        try
        {
            JSONArray jsonArray = new JSONArray(data);
            
            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                JSONObject jAddress = jsonObject.getJSONObject("address");
                JSONObject jGeo = jAddress.getJSONObject("geo");
                JSONObject jCompany = jsonObject.getJSONObject("company");
                
                users.add(new User(
                        jsonObject.getInt("id"),
                        jsonObject.getString("name"),
                        jsonObject.getString("username"),
                        jsonObject.getString("email"),
                        new Address(
                                jAddress.getString("street"),
                                jAddress.getString("suite"),
                                jAddress.getString("city"),
                                jAddress.getString("zipcode"),
                                jGeo.getString("lat"),
                                jGeo.getString("lng")
                        ),
                        jsonObject.getString("phone"),
                        jsonObject.getString("website"),
                        new Company(
                                jCompany.getString("name"),
                                jCompany.getString("catchPhrase"),
                                jCompany.getString("bs"))
                        ));
            }
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }
    }
}
