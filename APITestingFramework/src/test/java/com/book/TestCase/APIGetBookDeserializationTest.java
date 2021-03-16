package com.book.TestCase;

import com.book.ResponseModel.GetBookResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class APIGetBookDeserializationTest extends APIBookBase{

    @Test
    public void verifyGetBookByIdDeserializationTest() {
        //RestAssured.baseURI = "http://216.10.245.166";
        Response response = given().queryParam("ID", "8976587654")
                .header("Content-Type", "text/plain")
                .when()
                .get("/Library/GetBook.php")
                .then()
                .statusCode(200)
                .extract()
                .response();

        GetBookResponse[] bookResponses = response.as(GetBookResponse[].class);
        Assert.assertEquals(bookResponses[0].getAuthorName(), "Priya");
        System.out.println(bookResponses[0].getBook_name());
        System.out.println(bookResponses[0].getAisle());
        //Assert.assertEquals(bookResponses[0].getBook_name(), "Test API Test Selenium1");
    }

    @Test
    public void validateGetBookDeserializationTest() {
        Response response = given().queryParam("ID", "9123675456456")
                .header("Content-Type", "text/plain")
                .when()
                .get("/Library/GetBook.php")
                .then()
                .statusCode(200)
                .extract()
                .response();
        GetBookResponse[] book = response.as(GetBookResponse[].class);
        Assert.assertEquals(book[0].getBook_name(), "Test API Test Selenium1", "Test failed because the book name is incorrect");
    }


    }
