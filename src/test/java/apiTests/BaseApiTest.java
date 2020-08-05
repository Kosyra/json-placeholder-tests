package apiTests;

import apiTests.utils.Constants;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;

public class BaseApiTest {
    @BeforeTest
    public void SetUp(){
        RestAssured.baseURI = Constants.URL;
    }
}
