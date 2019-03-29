import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.api.ApiAssessment.*;
import java.util.Properties;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class Scenario1Test 
{
   @Test
   @Parameters({"ChannelName","NewChannelName"})
    public static void CreateAndRenameTest(String ChannelName,String NewChannelName) //String ChannelName,String NewChannelName
    {
	   	Properties prop = RestUtils.filename();	
        try
        {
        	//HelperMethods.JoinAndRenameList(prop.getProperty("Auth_Token"), ChannelName, NewChannelName);
        	if(HelperMethods.JoinAndRenameList(prop.getProperty("Auth_Token"), ChannelName, NewChannelName))
        	{
        		// using hamcrest assert statement to checks if the renamed channel is available in the list of channel
        		assertThat(HelperMethods.GetListOfAllChannels(prop.getProperty("Auth_Token")),hasItem(NewChannelName));
        	}
        	else
        		Assert.fail("please check the channel name");
        	
        }
                
        catch(Exception e)
        {
        	System.out.println(e);
        }
    }
   
  
}
