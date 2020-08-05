package apiTests.posts;

import apiTests.utils.Constants;
import io.restassured.http.ContentType;
import org.hamcrest.core.Is;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

public class GetRequestsTest {
    //        Response response = RestAssured.get(URL + "/posts");
    //        System.out.println();
    @Test
    public void GetAllPostsOfSpecificUser(){
        given()
                .contentType(ContentType.JSON)
                .and()
                .queryParam("userId", 1)
                .when().get(Constants.URL + "/posts")
                .then().statusCode(200)
                .and().body("userId[0]", Is.is(1));
    }

    @Test
    public void GetASpecificPost(){
        given()
                .contentType(ContentType.JSON)
                .and().pathParam("postId", 1)
                .when().get(Constants.URL + Constants.POSTS_ENDPOINT+ "/{postId}")
                .then().statusCode(200)
                .and().body("id", Is.is(1));
    }
    @Test
    public void GetAllPosts(){
        given()
                .when().get(Constants.URL + "/posts")
                .then().statusCode(200)
                .and().body("body", hasSize(100));
    }
}
