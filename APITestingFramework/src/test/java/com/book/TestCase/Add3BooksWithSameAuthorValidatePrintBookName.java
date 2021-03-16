package com.book.TestCase;

import com.book.ResponseModel.AddBookResponse;
import com.book.ResponseModel.GetBookResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;


public class Add3BooksWithSameAuthorValidatePrintBookName extends APIBookBase {


    @Test
    public void verifyAdd3BooksPrintNames() {
        int bookCountByAuthorName,expectedBookCount=3;
        Random random=new Random();

        String authorName = "BOXOM"+String.format("%02d", random.nextInt(10000));;

        APIBookBase bookBase = new APIBookBase();
        AddBookResponse book = bookBase.addBook(authorName);
        bookBase.addBook(authorName);
        bookBase.addBook(authorName);
        APIGetBook getBookByID = new APIGetBook();
        GetBookResponse[] bookResponses=getBookByID.getBookByAuthorName(authorName);
        bookCountByAuthorName=bookResponses.length;
        System.out.println("Number of books created by Author "+authorName+" is: "+bookCountByAuthorName);

        Assert.assertEquals(bookCountByAuthorName,expectedBookCount);

        System.out.println("Book Names created by the author "+authorName+" are:");
        for(int i=0;i<bookCountByAuthorName;i++)
            System.out.println(bookResponses[i].getBook_name());

        }

    }


