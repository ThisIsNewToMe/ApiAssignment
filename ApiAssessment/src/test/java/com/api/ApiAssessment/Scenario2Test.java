package com.api.ApiAssessment;
import java.util.Properties;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.api.ApiAssessment.HelperMethods;
import com.api.ApiAssessment.RestUtils;


public class Scenario2Test
{
	@Test
	@Parameters({"ChannelToArchive"})
   public static void ArchivingTest(String ChannelToArchive) //String ChannelToArchive
   {
	   	Properties prop = RestUtils.filename();	
       try
       {
    	   boolean archiveChannel =  HelperMethods.JoinAndArchiveChannel(prop.getProperty("Auth_Token"),ChannelToArchive);
	       if(archiveChannel)	
	       {
	    	   //Assert statement to check if the channel is archived
		       	Assert.assertTrue(archiveChannel);
		       	
		       	boolean isChannelArchivingSuccessful = HelperMethods.isChannelArchived(prop.getProperty("Auth_Token"),ChannelToArchive);
		       	
		        //validation to check if the channel is archived
		       	//Assert statement to check if error message comes up when channel is joined - meaning the channel is either already archived or it is not created
		        Assert.assertFalse(isChannelArchivingSuccessful);
	       }
	       else
	       {
	    	   Assert.fail("unable to archive channel,please check the channel name");
	       }
       }
               
       catch(Exception e)
       {
       	System.out.println(e);
       }
   }
}