package com.tutungis.assignment2parta;

/**
 * Model class containing user details
 *
 * @class           User
 * @author          Tristan S. Tutungis
 * @date_created    8/10/2022
 * @last_modified   9/10/2022 0:01
 */
public class User
{
    private final int id;
    private final String name;
    private final String username;
    private final String email;
    private final Address address;
    private final String phone;
    private final String website;
    private final Company company;
    
    public User(int id, String name, String username, String email,
                Address address, String phone, String website, Company company)
    {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.company = company;
    }
    
    public int getId()
    {
        return this.id;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public String getUsername()
    {
        return this.username;
    }
    
    public String getEmail()
    {
        return this.email;
    }
    
    public Address getAddress()
    {
        return this.address;
    }
    
    public String getPhone()
    {
        return this.phone;
    }
    
    public String getWebsite()
    {
        return this.website;
    }
    
    public Company getCompany()
    {
        return this.company;
    }
}
