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
	       	//Assert statement to check if the channel is archived
	       	Assert.assertEquals(true,HelperMethods.JoinAndArchiveChannel(prop.getProperty("Auth_Token"), ChannelToArchive));
	        //Assert statement to check if error message comes up when channel is joined
	        Assert.assertEquals(false,HelperMethods.Join(prop.getProperty("Auth_Token"), ChannelToArchive));

       }
               
       catch(Exception e)
       {
       	System.out.println(e);
       }
   }
}