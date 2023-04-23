package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification request; //Making it static so we are clearly telling that don't create another instance or use single instance entire your execution
	public RequestSpecification requestSpecification() throws IOException
	{
		if(request==null)
		{
			PrintStream log= new PrintStream(new FileOutputStream("logging.txt"));
			//Return type of RequestSpecBuilder() object is, RequestSpecification-->
			request=new RequestSpecBuilder().setContentType(ContentType.JSON).addQueryParam("key", "qaclick123")
			.addFilter(RequestLoggingFilter.logRequestTo(log))
			.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setBaseUri(getGlobalProperty("baseUrl")).build();
			return request;
		}
		return request;
	}
	
	
	public static String getGlobalProperty(String key) throws IOException
	{
		Properties property=new Properties();
		FileInputStream fileInput=new FileInputStream("D:\\Workspace\\restAssured\\APIFramework\\src\\test\\java\\resources\\global.properties");
		property.load(fileInput);
		return property.getProperty(key);
		
	}
	
	public String getJsonPath(Response response, String key)
	{
		String responseString=response.asString();
		JsonPath js=new JsonPath(responseString);
		return js.get(key).toString();
	}
}
