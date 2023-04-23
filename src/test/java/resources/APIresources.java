package resources;
//enum is a special class in java which has collection of Constants & Methods
public enum APIresources {

	addPlaceApi("/maps/api/place/add/json"),
	getPlaceApi("/maps/api/place/get/json"),
	deletePlaceApi("/maps/api/place/delete/json"),
	updatePlaceApi("/maps/api/place/update/json");
	
	public String resource;
	APIresources(String resource)
	{
		this.resource=resource;
	}
	public String getResource()
	{
		return resource;
	}
}
