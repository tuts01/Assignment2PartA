package com.tutungis.assignment2parta;

/**
 * Model class containing company details
 *
 * @class           Company
 * @author          Tristan S. Tutungis
 * @date_created    8/10/2022
 * @last_modified   9/10/2022 10:52
 */
public class Company
{
    private final String name;
    private final String catchphrase;
    private final String bs;
    
    public Company(String name, String catchphrase, String bs)
    {
        this.name = name;
        this.catchphrase = catchphrase;
        this.bs = bs;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public String getCatchphrase()
    {
        return this.catchphrase;
    }
    
    public String getBs()
    {
        return this.bs;
    }
}
