package com.book.TestCase;

import com.book.ResponseModel.AddBookResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteBookTest {

    @Test
    public void verifyDeleteBook()
    {

        APIBookBase book = new APIBookBase();
        AddBookResponse bookResponse = book.addBook();
        String bookID=bookResponse.getID();
        String actualDeleteMessage=book.deleteBook(bookID);
        String expectedDeleteMessage="book is successfully deleted";
        Assert.assertEquals(actualDeleteMessage,expectedDeleteMessage);
    }
}
