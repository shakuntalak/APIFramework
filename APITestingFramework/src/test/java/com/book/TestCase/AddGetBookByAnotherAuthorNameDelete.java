package com.book.TestCase;

import com.book.ResponseModel.AddBookResponse;
import com.book.ResponseModel.GetBookResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Random;


public class AddGetBookByAnotherAuthorNameDelete extends APIBookBase{

    

    @Test
    public void verifyAddBookGetDelete() {

        int bookCountByAuthorName,expectedBookCount=3;
        Random random=new Random();
        String authorName = "BOXOM"+String.format("%02d", random.nextInt(1000));;

        APIBookBase bookBase = new APIBookBase();
        AddBookResponse book = bookBase.addBook(authorName);
        bookBase.addBook(authorName);
        bookBase.addBook(authorName);
        APIGetBook getBookByID = new APIGetBook();
        GetBookResponse[] bookResponses=getBookByID.getBookByAuthorName(authorName);
        bookCountByAuthorName=bookResponses.length;

        String authorNameForDelete="BOXOMDelete";
        bookBase.addBook(authorNameForDelete);
        String bookID=book.getID();
        String actualDeleteMessage=bookBase.deleteBook(bookID);
        String expectedDeleteMessage="book is successfully deleted";
        Assert.assertEquals(actualDeleteMessage,expectedDeleteMessage);

        System.out.println("Number of books created by Author "+authorName+" is: "+bookCountByAuthorName);

        Assert.assertEquals(bookCountByAuthorName,expectedBookCount);

        System.out.println("Book Names created by the author "+authorName+" are:");
        for(int i=0;i<bookCountByAuthorName;i++)
            System.out.println(bookResponses[i].getBook_name());


        }

    }


