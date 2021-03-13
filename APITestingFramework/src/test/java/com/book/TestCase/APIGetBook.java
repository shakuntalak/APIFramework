package com.book.TestCase;

import com.book.ResponseModel.AddBookResponse;
import com.book.ResponseModel.GetBookResponse;
import com.book.ResponseModel.GetBookResponseError;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
public class APIGetBook {

    @Test
    public void verifyGetBookById() {
        RestAssured.baseURI = "http://216.10.245.166";
        Response response=given().queryParam("ID", "8976587654")
                                 .header("Content-Type","text/plain")
                                 .when()
                                 .get("/Library/GetBook.php")
                                 .then()
                                 .statusCode(200)
                                 .extract()
                                 .response();

        //BookResponse[] bookResponses = response.as(BookResponse[].class);
        Assert.assertEquals(response.asString(),"[{\"book_name\":\"Test API Test Selenium1\",\"isbn\":\"9123675\",\"aisle\":\"456456\",\"author\":\"test123\"}]");
    }

    public GetBookResponse getBook(AddBookResponse book)
    {
        Response getBookResponse=given().queryParam("ID",book.getID()).
                header("Content-Type","application/json").
                when().
                get("Library/GetBook.php").
                then().
                statusCode(200).
                extract().
                response();
        GetBookResponse[] getbookresponse=getBookResponse.as(GetBookResponse[].class);

        return getbookresponse[0];
    }

    public String getBookError(String id)
    {
        RestAssured.baseURI = "http://216.10.245.166";
        Response getBookResponseError=given().queryParam("ID",id).
                header("Content-Type","application/json").
                when().
                get("Library/GetBook.php").
                then().
                statusCode(404).
                extract().
                response();
        GetBookResponseError[] getBookResponse=getBookResponseError.as(GetBookResponseError[].class);

        return getBookResponse[0].getMsg();
    }
}
