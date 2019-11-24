package co.id.stepdefinations;

import java.util.ArrayList;

import junit.framework.Assert;

import org.json.JSONArray;
import org.json.JSONObject;
import org.assertj.core.api.SoftAssertions;

import io.restassured.response.Response;
import co.id.stepdefinations.hooks.Hooks;
import co.id.utils.ConfigurationFunction;
import co.id.utils.GoJekFunctionUtility;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GojekAPI2 {

	Response response1;
	Response response2;
	String strapi1G;
	String strapi2G;
	SoftAssertions softAssert = new SoftAssertions();
	@Given("^User want to execute API Request as from fileA \"([^\"]*)\" and from fileB \"([^\"]*)\"$")
	public void user_want_to_execute_API_Request_as_from_fileA_and_from_fileB(String strapi1, String strapi2) throws Throwable {
		response1 = GoJekFunctionUtility.executeApiRequest(strapi1);
		strapi1G=strapi1;
		response2 = GoJekFunctionUtility.executeApiRequest(strapi2);
		strapi2G=strapi2;
	}


	@Then("^User validate Status Code as \"([^\"]*)\"$")
	public void user_validate_Status_Code_as(String arg1) throws Throwable {
		int intStatusCode1 = GoJekFunctionUtility.getstatusCode(response1);
		
		int intStatusCode2 = GoJekFunctionUtility.getstatusCode(response2);
		
		Hooks.scenario.write("Status Code API1: "+intStatusCode1+"; Status Code API2: "+intStatusCode2);
	}

	@Then("^User validate APIs response into JSON format$")
	public void user_validate_APIs_response_into_JSON_format() throws Throwable {
		String strResponse1 = GoJekFunctionUtility.getResponseData(response1);
		String strResponse2 = GoJekFunctionUtility.getResponseData(response2);
		
		ArrayList arrJsonResponse1 = getAPIResponseInJsonKeyValue(strResponse1);
		ArrayList arrJsonResponse2 = getAPIResponseInJsonKeyValue(strResponse2);

		for (int j = 0; j < arrJsonResponse1.size(); j++) {
			String file1key = arrJsonResponse1.get(j).toString();
			int m = j + 1;
			String file1value = arrJsonResponse1.get(m).toString();
			String file2key = arrJsonResponse2.get(j).toString();
			String file2value = arrJsonResponse2.get(m).toString();
			j = m;

			Hooks.scenario.write("<b>API from File 1: " + strapi1G
					+ "</b><p></p>" + file1key+" = " +file1value +";<p></p><b>API from File 2: "
					+ strapi2G + "</b><p></p>" + file2key+" = "+file2value);

			softAssert.assertThat(file1value).isEqualToIgnoringCase(file2value);

		}
		softAssert.assertAll();
	}

	
	
	ArrayList arrString;
	private ArrayList getAPIResponseInJsonKeyValue(String strResponse1) {
		JSONObject obj_JSONObject = new JSONObject(strResponse1);

		try {
			JSONObject obj_JSONObject2 = obj_JSONObject.getJSONObject("data");

			arrString = getJsonKeyValue(obj_JSONObject2);

		} catch (Exception e) {

			JSONArray objJsonArray = obj_JSONObject.getJSONArray("data");
			for (int j = 0; j < objJsonArray.length(); j++) {

				JSONObject obj_JSONObjectData = objJsonArray.getJSONObject(j);

				arrString = getJsonKeyValue(obj_JSONObjectData);

			}
		}
		// System.out.println(arrayList);
		return arrString;
	}

	private ArrayList getJsonKeyValue(JSONObject obj_JSONObject2) {
		ArrayList<String> arrString = new ArrayList<String>();
		for (String keyStr : obj_JSONObject2.keySet()) {
			String keyvalue = obj_JSONObject2.get(keyStr).toString();

			// System.out.println(keyStr+": "+keyvalue);
			arrString.add(keyStr);
			arrString.add(keyvalue);

		}
		return arrString;
	}
	
	
	
}
