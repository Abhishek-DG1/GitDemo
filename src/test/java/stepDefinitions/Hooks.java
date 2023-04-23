package stepDefinitions;


import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		Stepdefinition sd=new Stepdefinition();	
		if(Stepdefinition.placeId==null) 
		{
			sd.add_place_payload_with("Shetty", "French", "Asia");
			sd.user_calls_with_http_request("addPlaceApi", "POST");
			sd.verify_place_id_created_maps_to_using("Shetty", "getPlaceApi");
		}
	}
}
