package com.tutungis.assignment2parta;

/**
 * This exception is thrown when the HTTP response code indicates an error
 *
 * @class           HTTPResponseException
 * @extends         IOException
 * @author          Tristan S. Tutungis
 * @date_created    8/10/2022
 * @last_modified   9/10/2022 10:52
 *
 * @deprecated      This class is no longer needed
 */

@Deprecated
public class HttpResponseException extends java.io.IOException
{
    @Deprecated
    public HttpResponseException(String str)
    {
        super(str);
    }
}
