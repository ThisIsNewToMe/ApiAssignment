package com.api.ApiAssessment;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.util.List;


public class HelperMethods 
{
	public static void CreateNewChannel (String Auth_Token,String Channel_Name) 
	{

		Response response = given()
							.accept(ContentType.JSON)
							.body("{'name':'"+Channel_Name+"'}")
							.header("Content-type","application/json")
							.header("Authorization","Bearer " + Auth_Token)
							.when()
							.post("https://slack.com/api/channels.create");

		JsonPath jsonPathEvaluator = response.jsonPath();
		String ChannelID= jsonPathEvaluator.get("channel.id");

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
		Response response = given().
				
    			contentType(ContentType.JSON)
    			.accept(ContentType.JSON)
    			.body("{'name':'"+Channel_Name+"'}")
    			.header("Content-type","application/json")
    			.header("Authorization","Bearer " + Auth_Token)
    			.when()
    			.post("https://slack.com/api/channels.join");
		JsonPath jsonPathEvaluator = response.jsonPath();
		String ChannelId= jsonPathEvaluator.get("channel.id");

		
		Response response1 = given().
				
    			contentType(ContentType.JSON)
    			.accept(ContentType.JSON)
    			.body("{'channel':'"+ChannelId+"'}")
    			.header("Content-type","application/json")
    			.header("Authorization","Bearer " + Auth_Token)
    			.when()
    			.post("https://slack.com/api/channels.archive");

		JsonPath jsonPathEvaluator1 = response1.jsonPath();
		boolean ok= jsonPathEvaluator1.get("ok");

		return ok;

	}
	
	public static boolean JoinAndRenameList(String Auth_Token,String Channel_Name,String NewChannel_Name)
	{
		Response response = given().
		
    			contentType(ContentType.JSON)
    			.accept(ContentType.JSON)
    			.body("{'name':'"+Channel_Name+"'}")
    			.header("Content-type","application/json")
    			.header("Authorization","Bearer " + Auth_Token)
    			.when()
    			.post("https://slack.com/api/channels.join");
		JsonPath jsonPathEvaluator = response.jsonPath();
		String ChannelId= jsonPathEvaluator.get("channel.id");

		
		Response response1 = given()
		    	.contentType(ContentType.JSON)
		    	.accept(ContentType.JSON)
		    	.body("{'channel':'"+ChannelId+"', 'name':'"+NewChannel_Name+"'}")
		    	.header("Content-type","application/json")
		    	.header("Authorization","Bearer " + Auth_Token)
		    	.when()
		    	.post("https://slack.com/api/channels.rename");
				JsonPath jsonPathEvaluator1 = response1.jsonPath();
				boolean ok= jsonPathEvaluator1.get("ok");
				return ok;
		
	
	}
	
	public static boolean Join(String Auth_Token,String Channel_Name)
	{
		Response response = given().
				
    			contentType(ContentType.JSON)
    			.accept(ContentType.JSON)
    			.body("{'name':'"+Channel_Name+"'}")
    			.header("Content-type","application/json")
    			.header("Authorization","Bearer " + Auth_Token)
    			.when()
    			.post("https://slack.com/api/channels.join");
		JsonPath jsonPathEvaluator = response.jsonPath();
		boolean ok= jsonPathEvaluator.get("ok");
		return ok;
	}
}
