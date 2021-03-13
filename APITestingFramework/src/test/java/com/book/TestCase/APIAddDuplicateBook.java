package com.book.TestCase;

import com.book.ResponseModel.AddBookResponse;
import com.book.ResponseModel.AddDuplicateBookResponse;
import com.book.ResponseModel.GetBookResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class APIAddDuplicateBook {

    @Test
    public void verifyAddDuplicateBook(){
        String bookName,isbn,aisle,authorName;
        String ExpectedAuthor="Rest API Test";
        APIBookBase addBook=new APIBookBase();
        AddBookResponse book = addBook.addBook();

        APIGetBook getBook=new APIGetBook();
        GetBookResponse getBookResponse=getBook.getBook(book);
        bookName=getBookResponse.getBook_name();
        isbn= getBookResponse.getIsbn();
        aisle= getBookResponse.getAisle();
        authorName= getBookResponse.getAuthorName();

        AddDuplicateBookResponse bookResponse=addBook.addBookDuplicate(bookName,isbn,aisle,authorName);

        Assert.assertEquals(bookResponse.getMsg(),"Add Book operation failed, looks like the book already exists");

    }
    }
