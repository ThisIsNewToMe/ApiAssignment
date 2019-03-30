package com.api.ApiAssessment;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import java.util.List;

import com.google.gson.JsonParser;


public class HelperMethods 
{
	
	public static boolean CreateNewChannel (String Auth_Token,String Channel_Name) 
	{
		
		Response response = given()
							.accept(ContentType.JSON)
							.body("{'name':'"+Channel_Name+"'}")
							.header("Content-type","application/json")
							.header("Authorization","Bearer " + Auth_Token)
							.when()
							.post("https://slack.com/api/channels.create");
		String responseBody =  response.print();
		//JsonPath jsonPathEvaluator = response.jsonPath();
		//String ChannelID= jsonPathEvaluator.get("channel.id");
		boolean isChannelCreated = (new JsonParser()).parse(responseBody).getAsJsonObject().get("ok").getAsBoolean();
		return isChannelCreated;

    }
	
	public static List<String> GetListOfAllChannels(String Auth_Token)
	{
		
		Response response = given().
							contentType(ContentType.JSON)
							.header("Authorization","Bearer " + Auth_Token)
							.when()
							.get("https://slack.com/api/channels.list")
							;
		List<String> jsonResponse = response.jsonPath().getJsonObject("channels.name");//getList("channels.name");

		return jsonResponse;
	
	}
	
	public static boolean JoinAndArchiveChannel(String Auth_Token,String Channel_Name)
	{
		if(HelperMethods.isChannelArchived(Auth_Token, Channel_Name))
		{	
			String channelId = (new JsonParser()).parse(HelperMethods.Join(Auth_Token,Channel_Name)).getAsJsonObject().get("channel").getAsJsonObject().get("id").getAsString();

			Response response1 = given().
					
	    			contentType(ContentType.JSON)
	    			.accept(ContentType.JSON)
	    			.body("{'channel':'"+channelId+"'}")
	    			.header("Content-type","application/json")
	    			.header("Authorization","Bearer " + Auth_Token)
	    			.when()
	    			.post("https://slack.com/api/channels.archive");
	
			JsonPath jsonPathEvaluator1 = response1.jsonPath();
			boolean ok= jsonPathEvaluator1.get("ok");
			return ok;
		}
			
		return false;

	}
	
	public static boolean JoinAndRenameList(String Auth_Token,String Channel_Name,String NewChannel_Name) 
	{
		String channelId = (new JsonParser()).parse(HelperMethods.Join(Auth_Token,Channel_Name)).getAsJsonObject().get("channel").getAsJsonObject().get("id").getAsString();
		Response response1 = given()
		    	.contentType(ContentType.JSON)
		    	.accept(ContentType.JSON)
		    	.body("{'channel':'"+channelId+"', 'name':'"+NewChannel_Name+"'}")
		    	.header("Content-type","application/json")
		    	.header("Authorization","Bearer " + Auth_Token)
		    	.when()
		    	.post("https://slack.com/api/channels.rename");
				JsonPath jsonPathEvaluator1 = response1.jsonPath();
				boolean ok = jsonPathEvaluator1.get("ok");
				return ok;
	
	}
	
	public static String Join(String Auth_Token,String Channel_Name)
	{
		Response response = given().
				
    			contentType(ContentType.JSON)
    			.accept(ContentType.JSON)
    			.body("{'name':'"+Channel_Name+"'}")
    			.header("Content-type","application/json")
    			.header("Authorization","Bearer " + Auth_Token)
    			.when()
    			.post("https://slack.com/api/channels.join");
		
		return response.asString();
	}
	
	public static boolean isChannelArchived(String Auth_Token,String Channel_Name)
	{
		boolean channel = (new JsonParser()).parse(HelperMethods.Join(Auth_Token,Channel_Name)).getAsJsonObject().get("ok").getAsBoolean();
		return channel;
	}
}
