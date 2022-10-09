package com.tutungis.assignment2parta;

/**
 * Model class containing a user post
 *
 * @class           Post
 * @author          Tristan S. Tutungis
 * @date_created    8/10/2022
 * @last_modified   9/10/2022 0:05
 */
public class Post
{
    private final int userId;
    private final int id;
    private final String title;
    private final String body;
    
    public Post(int userId, int id, String title, String body)
    {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }
    
    public int getUserId()
    {
        return this.userId;
    }
    
    public int getId()
    {
        return this.id;
    }
    
    public String getTitle()
    {
        return this.title;
    }
    
    public String getBody()
    {
        return this.body;
    }
}
