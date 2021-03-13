package com.book.TestCase;

import com.book.ResponseModel.AddBookResponse;
import com.book.ResponseModel.GetBookResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddBookTest {

    @Test
    public void verifyAddBook() {
        String ExpectedAuthor = "John Martin";
        APIBookBase addBook = new APIBookBase();
        AddBookResponse book = addBook.addBook();
        System.out.println(book.getID());
        APIGetBook getBook = new APIGetBook();
        GetBookResponse getBookResponse = getBook.getBook(book);
        Assert.assertEquals(getBookResponse.getAuthorName(), ExpectedAuthor, "Test failed ,Author name is not correct");
    }

}