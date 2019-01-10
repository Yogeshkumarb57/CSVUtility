package com.utilities;

import java.io.InputStream;

/**
 * Hello world!
 *
 */
public class CSVUtilityTestApp
{
    private static InputStream inputStream = null;
    private static ClassLoader classLoader = null;

    static {
        classLoader = Thread.currentThread().getContextClassLoader();
        inputStream = classLoader.getResourceAsStream("/PersonData.csv");
    }

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
