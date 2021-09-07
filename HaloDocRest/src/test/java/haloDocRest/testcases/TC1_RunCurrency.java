package haloDocRest.testcases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import haloDocRest.Base.BaseTest;
import haloDocRest.utils.UtilsFiles;
import io.restassured.response.Response;

public class TC1_RunCurrency extends BaseTest {

	static String code = null;
	static UtilsFiles utils= new UtilsFiles(); ;

	@Test
	public void getID() {

		try {

			Response response = rest.given().contentType("application/json").when().get("/v2/currencies");
			System.out.println(response.then().assertThat().body(matchesJsonSchemaInClasspath("currencyschema.json"))
					.statusCode(200).log().all());

			ArrayList<String> list = response.jsonPath().getJsonObject("data.name");
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).equalsIgnoreCase(prop.getProperty("country"))) {
					code = response.jsonPath().getJsonObject("data[" + i + "].id");
					break;
				}
			}
			if (code == null) {
				System.out.println("Country not Available");
			} else {
				System.out.println(code);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(dependsOnMethods = "getID")
	public void getCurrencyVal() {
		try {
			Response response = rest.given().contentType("application/json").when()
					.get("/v2/exchange-rates?currency=" + code);

			/*
			 * System.out.println(response.then() .assertThat()
			 * .body(matchesJsonSchemaInClasspath("currencyschema.json")) .statusCode(200)
			 * .log().all());
			 */
			
			Map<String,Double> newJson = new HashMap<>();
			LinkedHashMap jsonArray = response.jsonPath().getJsonObject("data.rates");
			System.out.println(utils.findtheValues(jsonArray));
			
		} catch (Exception e) {
			e.printStackTrace();

		}

	}
}
