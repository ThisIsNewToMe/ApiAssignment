package com.api.ApiAssessment;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;
import java.util.List;
import java.util.Properties;


public class Scenario1Test 
{
   @Test
   //@Parameters({"ChannelName","NewChannelName"})
    public static void CreateAndRenameTest() //String ChannelName,String NewChannelName
    {
	   	Properties prop = RestUtils.filename();	
	   	
	   	
        try
        {
        	boolean isChannelCreated = HelperMethods.CreateNewChannel(prop.getProperty("Auth_Token"), "sample55");
        	if(isChannelCreated)
        	{
        		boolean response = HelperMethods.JoinAndRenameList(prop.getProperty("Auth_Token"),"sample55" ,"sample56" );
        		List<String> channelNames = HelperMethods.GetListOfAllChannels(prop.getProperty("Auth_Token"));
 
            	if(response)
            	{
            		Assert.assertEquals(true, channelNames.contains("sample56"));
            	}
            	
            	else
            	{
            		Assert.fail("Unable to rename channel as the name is already taken");
            	}
            	
        	}
        	
        	else
        	{
        		Assert.fail("New channel is not created , please check the channel name");
        	}
        		
        }
                
        catch(Exception e)
        {
        	Assert.fail("unable tp create and rename channel,try with another name");
        }
    }
   
  
}
