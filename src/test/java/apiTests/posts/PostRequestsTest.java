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

public class PostRequestsTest extends BaseApiTest {

    @Test
    public void CreateNewPost() throws JsonProcessingException {
        //Arrange
        PostRequestBody requestBody = new PostRequestBody();
        PostRequestDto testData = requestBody.GetRandomPostData();
        ObjectMapper mapper = new ObjectMapper();
        String jsonData = mapper.writeValueAsString(testData);

        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        request.body(jsonData);

        //Act
        Response response = request.post(Constants.POSTS_ENDPOINT);
        var body = response.getBody();

        //Assert
        Assert.assertEquals(response.statusCode(), 201,
                "Status odpowiedzi jest inny niż oczekiwany. Treść odpowiedzi: " + body.asString());
        PostResponseDto responseBody = body.as(PostResponseDto.class);
        Assert.assertEquals(responseBody.getUserId(), testData.getUserId());
        Assert.assertEquals(responseBody.getBody(), testData.getBody());
        Assert.assertEquals(responseBody.getTitle(), testData.getTitle());
    }
}
