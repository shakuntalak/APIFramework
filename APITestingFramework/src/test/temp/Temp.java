package com.book.TestCase;
import com.book.RequestModel.AddBookRequest;
import com.book.ResponseModel.BookResponse;
import com.book.ResponseModel.ResponseAddBook;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class Temp {

    @Test
    public void test1() {
        RestAssured.baseURI = "http://216.10.245.166";
        AddBookRequest request = new AddBookRequest();
        request.setBookName("Rest APIAutomation testing");
        request.setAuthor("Priya");
        request.setIsbn("56678");
        request.setAisle("8967456");
        Response response = given().header("Content-Type", "application/json").
                body(request).
                when().
                post("Library/Addbook.php").
                then().
                assertThat().
                statusCode(200).
                extract().response();
        ResponseAddBook addbookresponse = response.body().as(ResponseAddBook.class);

        Response getBookResponse=given().queryParam("ID", addbookresponse.getResponseId())
                .header("Content-Type","text/plain")
                .when()
                .get("/Library/GetBook.php")
                .then()
                .statusCode(200)
                .extract()
                .response();
        BookResponse[] book=response.as(BookResponse[].class);
        Assert.assertEquals(book[0].getBook_name(),"Test API Test Selenium1","Test failed because the book name is incorrect");

//        Response getBookResponse = given().queryParam("ID", addbookresponse.getResponseId()).
//                header("Content-Type", "application/json").
//                when().
//                get("Library/GetBook.php").
//                then().
//                statusCode(200).
//                extract().
//                response();
//        ResponseAddBook[] book = getBookResponse.as(ResponseAddBook[].class);
//        Assert.assertEquals(book[0].getResponseId(), "Priya", "Test failed ,Author name is not correct");
    }
}
