import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;

public class mainclass
{
   
    public static void main(String[] args)
    {
        try
        {
    	 Response response1 = given().
    			contentType(ContentType.JSON)
    			.accept(ContentType.JSON)
    			.header("Content-type","application/json")
    			.header("token","xoxp-589989990836-581301693873-591096348343-aaf4d1eeb3e9f2e09a987bf56bd86b8f")
    			.when()
    			.post("https://slack.com/api/channels.create");

    	  System.out.println(response1.asString());
        }
        
        catch(Exception e)
        {
        	System.out.println(e);
        }
    }
}





