package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.runner.RunWith;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIresources;
import resources.TestDataBuild;
import resources.Utils;

@RunWith(Cucumber.class)
public class Stepdefinition extends Utils {

	 
	RequestSpecification res;
	ResponseSpecification resSpec;
	Response response;
	static TestDataBuild data;
	static String placeId;


	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {

		
		
		data=new TestDataBuild();
		res=given().spec(requestSpecification()).body(data.addPlacePayload(name,language,address));
	}
	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpMethod) {
		APIresources resourceApi=APIresources.valueOf(resource); //When you call this method automatically constructor is executed
		System.out.println(resourceApi.getResource());
		
		resSpec= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(httpMethod.equalsIgnoreCase("POST"))
			response= res.when().post(resourceApi.getResource());
				//.then().spec(resSpec).extract().response();
		
		else if(httpMethod.equalsIgnoreCase("GET"))
			response= res.when().get(resourceApi.getResource());
		
		else if(httpMethod.equalsIgnoreCase("DELETE"))
			response= res.when().delete(resourceApi.getResource());
		
		else if(httpMethod.equalsIgnoreCase("UPDATE"))
			response= res.when().put(resourceApi.getResource());
	}
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(),200);
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
		
		//String newKeyValue=js.get(keyValue).toString();
		
		assertEquals(getJsonPath(response,keyValue),expectedValue);
		
		
	} 

	@Then("verify placeID created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
	   
		//Prepare Request Spec-->
		placeId=getJsonPath(response,"place_id");
		res=given().spec(requestSpecification()).queryParam("place_id", placeId);
		//Hit Get API Call-->
		user_calls_with_http_request(resource,"GET");
		String actualName=getJsonPath(response,"name");
		assertEquals(actualName,expectedName);
		System.out.println(actualName);
		
		
		
	}
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {

		res=given().spec(requestSpecification()).body(data.deletePlacePayload(placeId));
	}





}
