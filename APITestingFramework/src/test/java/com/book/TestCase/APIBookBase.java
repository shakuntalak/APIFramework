package com.book.TestCase;

import com.book.RequestModel.AddBookRequest;
import com.book.RequestModel.DeleteBookRequest;
import com.book.ResponseModel.AddBookResponse;
import com.book.ResponseModel.AddDuplicateBookResponse;
import com.book.ResponseModel.DeleteBookResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class APIBookBase {

    public AddBookResponse addBook()
    {
        String aisleGenerate,bookName="RestAPI Testing Test123",isbnGenerate;
        String authorName="John Martin";
        Random random=new Random();
        aisleGenerate = String.format("%04d", random.nextInt(10000));
        isbnGenerate = String.format("%04d", random.nextInt(10000));
        RestAssured.baseURI = "http://216.10.245.166";
        AddBookRequest request = new AddBookRequest();
        request.setBook_name(bookName);
        request.setAuthor_name(authorName);
        request.setIsbn(isbnGenerate);
        request.setAisle(aisleGenerate);
        Response response = given().header("Content-Type", "application/json").
                body(request).
                when().
                post("Library/Addbook.php").
                then().
                assertThat().
                statusCode(200).
                extract().response();


        AddBookResponse book = response.as(AddBookResponse.class);
        return book;
    }

    public AddDuplicateBookResponse addBookDuplicate(String bookName, String isbn, String aisle, String authorName)
    {

        RestAssured.baseURI = "http://216.10.245.166";
        //String bookName="Testing-REST API Automation",isbn="56789",aisle="234665",authorName="Shakuntala";
        AddBookRequest request = new AddBookRequest();
        request.setBook_name(bookName);
        request.setAuthor_name(authorName);
        request.setIsbn(isbn);
        request.setAisle(aisle);
        Response response = given().header("Content-Type", "application/json").
                body(request).
                when().
                post("Library/Addbook.php").
                then().
                assertThat().
                statusCode(404).
                extract().response();

        AddDuplicateBookResponse book = response.as(AddDuplicateBookResponse.class);
        return book;
    }


    public String deleteBook(String id)
    {
        RestAssured.baseURI = "http://216.10.245.166";

        DeleteBookRequest request=new DeleteBookRequest();
        request.setDeleteBookID(id);
        Response response = given().header("Content-Type", "application/json").
                body(request).
                when().
                post("Library/DeleteBook.php").
                then().
                assertThat().
                statusCode(200).
                extract().response();


        DeleteBookResponse book = response.as(DeleteBookResponse.class);
        return book.getDeleteMsg();
    }

}