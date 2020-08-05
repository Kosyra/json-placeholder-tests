package apiTests.posts;

import apiTests.BaseApiTest;
import apiTests.testData.PostRequestBody;
import apiTests.utils.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.PostRequestDto;
import dto.PostResponseDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PutRequestsTest extends BaseApiTest {

    @Test
    public void ChangeExistingPost() throws JsonProcessingException {
        //Arrange
        PostRequestBody requestBody = new PostRequestBody();
        PostRequestDto testData = requestBody.GetExistingPostData();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(testData);

        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.pathParam("id",1);
        requestSpecification.body(jsonData);

        //Act
        Response response = requestSpecification.put(Constants.POSTS_ENDPOINT + "/{id}");
        var body = response.getBody();

        //Assert
        Assert.assertEquals(response.statusCode(), 200,
                "Status odpowiedzi jest inny niż oczekiwany. Treść odpowiedzi: " + body.asString());
        PostResponseDto responseBody = body.as(PostResponseDto.class);
        Assert.assertEquals(responseBody.getUserId(), testData.getUserId());
        Assert.assertEquals(responseBody.getBody(), testData.getBody());
        Assert.assertEquals(responseBody.getTitle(), testData.getTitle());

    }
}
