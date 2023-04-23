Feature: Validating Place APIs

@AddPlace @Regression
Scenario Outline: Verify if Place is being Successfully Added using AddPlaceAPI
		
		Given Add Place Payload with "<name>" "<language>" "<address>"
		When user calls "addPlaceApi" with "Post" http request
		Then the API call got success with status code 200
		And "status" in response body is "OK"
		And "scope" in response body is "APP"
		And verify placeID created maps to "<name>" using "getPlaceApi"
		
		Examples:
		|name     |language |address 	 |
		|Aindrila |Bengali  |Barrackpore |
#		|Avik     |English  |Jadavpur	 |
#		|Vishal   |Hindi    |Howrah  	 |
#		|Riyaz    |Urdhu    |Barrackpore  |
#		|Abhishek |Bengali  |Barrackpore  |

@DeletePlace @Regression
Scenario: Verify if Delete Place Functionality is working
		
		Given DeletePlace Payload
		When user calls "deletePlaceApi" with "Post" http request
		Then the API call got success with status code 200
		And "status" in response body is "OK"
		