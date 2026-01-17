package testAPI;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetAPTTest {
    @Test
    public void testAPI() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
        Response response =  RestAssured
                .given()
                .when()
                .get("/users/1");
        System.out.println(response.asString());
        Assert.assertEquals(response.statusCode(),200,"laays du lieu k thanh cong");
        Assert.assertEquals(response.jsonPath().getInt("id"),1,"id sai");
        Assert.assertEquals(response.jsonPath().getInt("name"),"Leanne Graham","name sai");
        Assert.assertEquals(response.jsonPath().getInt("username"),"Bret","user sai");

    }
}
