package haloDocRest.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;

import io.restassured.RestAssured;

public class BaseTest {
	
	public static RestAssured rest;
	public static Properties prop;
	
	
	@BeforeTest
	public void baseFunction() throws IOException {
	
		FileInputStream fis = new FileInputStream(new File("./src/test/resources/config.properties"));
		prop = new Properties();
		prop.load(fis);
		
		rest.baseURI = prop.getProperty("baseurl");
		
	}
	
	

}
