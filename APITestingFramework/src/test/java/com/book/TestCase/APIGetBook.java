package com.book.TestCase;

import com.book.ResponseModel.AddBookResponse;
import com.book.ResponseModel.GetBookResponse;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class APIGetBook extends APIBookBase{

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


    public GetBookResponse[] getBookByAuthorName(String authorName)
    {
        Response getBookResponse=given().queryParam("AuthorName",authorName).
                header("Content-Type","application/json").
                when().
                get("Library/GetBook.php").
                then().
                statusCode(200).
                extract().
                response();
        GetBookResponse[] getBookResponseAuthor=getBookResponse.as(GetBookResponse[].class);

//        List<String> jsonResponse = getBookResponse.jsonPath().getList("$");
//
//        List<String> bookNames = getBookResponse.jsonPath().getList("book_name");
//        System.out.println("Book Names created by the author "+authorName+" are:");
//        for(int i=0;i< jsonResponse.size();i++)
//            System.out.println(bookNames.get(i));

        return getBookResponseAuthor;


    }

//    public String getBookError(String id)
//    {
//        RestAssured.baseURI = "http://216.10.245.166";
//        Response getBookResponseError=given().queryParam("ID",id).
//                header("Content-Type","application/json").
//                when().
//                get("Library/GetBook.php").
//                then().
//                statusCode(404).
//                extract().
//                response();
//        GetBookResponseError[] getBookResponse=getBookResponseError.as(GetBookResponseError[].class);
//
//        return getBookResponse[0].getMsg();
//    }
}
