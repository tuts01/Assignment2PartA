package com.tutungis.assignment2parta;

/**
 * Model class containing address details
 *
 * @class           Address
 * @author          Tristan S. Tutungis
 * @date_created    8/10/2022
 * @last_modified   9/10/2022 11:03
 */
public class Address
{
    
    private final String street;
    private final String suite;
    private final String city;
    private final String zipcode;
    private final String latitude;
    private final String longitude;
    
    public Address(String street, String suite, String city, String zipcode,
                   String latitude, String longitude)
    {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    public String getStreet()
    {
        return this.street;
    }
    
    public String getSuite()
    {
        return this.suite;
    }
    
    public String getCity()
    {
        return this.city;
    }
    
    public String getZipcode()
    {
        return this.zipcode;
    }
    
    public String getLatitude()
    {
        return this.latitude;
    }
    
    public String getLongitude()
    {
        return this.longitude;
    }
}
