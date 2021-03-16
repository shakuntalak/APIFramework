package com.book.TestCase;


import com.book.ResponseModel.GetBookResponse;
import com.book.ResponseModel.AddBookResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class EndToEndAddBookAPITest extends APIBookBase{

    @Test
    public void verifyAddAndGetBookAPITest()
    {
        String ExpectedAuthor="John Martin";
        APIBookBase addBook=new APIBookBase();
        AddBookResponse book = addBook.addBook(ExpectedAuthor);
        APIGetBook getBook=new APIGetBook();
        GetBookResponse getBookResponse =getBook.getBook(book);
        Assert.assertEquals(getBookResponse.getAuthorName(),ExpectedAuthor,"Test failed ,Author name is not correct");
    }

    @Test
    public void verifyAddGetDeleteAddBookTest() {
        String ExpectedAuthor = "John Martin";
        APIBookBase baseBook = new APIBookBase();
        AddBookResponse book = baseBook.addBook(ExpectedAuthor);

        APIGetBook getBook = new APIGetBook();
        GetBookResponse getBookResponse = getBook.getBook(book);
        Assert.assertEquals(getBookResponse.getAuthorName(), ExpectedAuthor, "Test failed ,Author name is not correct");

        String bookID = book.getID();
        String actualDeleteMessage = baseBook.deleteBook(bookID);
        String expectedDeleteMessage = "book is successfully deleted";
        Assert.assertEquals(actualDeleteMessage, expectedDeleteMessage);


        AddBookResponse book1 = baseBook.addBook(ExpectedAuthor);
        Assert.assertEquals(book1.getMsg(), "successfully added");


    }

}





//    String errorMessage=getBook.getBookError(bookID);
//        Assert.assertEquals(errorMessage,"The book by requested bookid / author name does not exists!");
