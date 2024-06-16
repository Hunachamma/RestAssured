package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTest {

	@Test(priority=1,dataProvider="data",dataProviderClass=DataProviders.class)
	public void testPostTest(String userid, String username,String firstName,String lastName,String email,String password,String phone) {
		User userPayLoad=new User();
		
		userPayLoad.setId(Integer.parseInt(userid));
		userPayLoad.setUsername(username);
		userPayLoad.setFirstName(firstName);
		userPayLoad.setLastName(lastName);
		userPayLoad.setEmail(email);
		userPayLoad.setPhone(phone);
		
		Response response =UserEndpoints.createUser(userPayLoad);
		//response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String userName)
	{
			Response response=UserEndpoints.deleteUser(userName);
			Assert.assertEquals(response.getStatusCode(),200);	
	
	}
	
}
