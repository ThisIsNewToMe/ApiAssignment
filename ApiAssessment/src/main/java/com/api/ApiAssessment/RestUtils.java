package com.api.ApiAssessment;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class RestUtils
{
	public static String path;
	
	
    public static void setContentType (ContentType Type)
    
    {
    	given().contentType(Type);
    }
    
    public static Properties filename()
    {
    	File file = new File("C:\\Users\\paro\\git\\repository\\ApiAssessment\\Configurations.properties");
		  
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		
		//load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
    }
    
}
