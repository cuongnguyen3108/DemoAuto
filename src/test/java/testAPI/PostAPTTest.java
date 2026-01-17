package testAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PostAPTTest {
    @Test
    public void testPostAPI(){
        String requestBody="{\n" +
                "    \"title\": \"foo\",\n" +
                "    \"body\": \"bar\",\n" +
                "    \"userId\": 1\n" +
                "}";
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response =  RestAssured.given().basePath("/posts").contentType(ContentType.JSON)
                .body(requestBody).post();
        System.out.println(response.asString());
        Assert.assertEquals(response.statusCode(),201,"laays du lieu k thanh cong");
        Assert.assertEquals(response.jsonPath().getInt("id"),101,"id sai");
        Assert.assertEquals(response.jsonPath().getString("title"),"foo","title sai");
        Assert.assertEquals(response.jsonPath().getString("body"),"bar","body sai");
        Assert.assertEquals(response.jsonPath().getInt("userId"),1,"userId sai");

    }
}
