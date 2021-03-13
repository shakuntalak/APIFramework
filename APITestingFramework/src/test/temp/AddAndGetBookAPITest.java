import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class AddAndGetBookAPITest {
    @Test
    public void verifyAddAndGetBookAPITest()
    {
        RestAssured.baseURI="http://216.10.245.166";
        AddBookRequest request=new AddBookRequest();
        request.setBook_name("Selenium Automation testing");
        request.setAuthor_name("Amar");
        request.setIsbn("89jd372281");
        request.setAisle("5609776");
        Response response=given().header("Content-Type","application/json").
                          body(request).
                          when().
                          post("Library/Addbook.php").
                          then().
                          assertThat().
                          statusCode(200).
                          extract().response();
        AddBookResponse addbookresponse=response.body().as(AddBookResponse.class);
       Response getBookResponse=given().queryParam("ID",addbookresponse.getID()).
                                header("Content-Type","application/json").
                                when().
                                get("Library/GetBook.php").
                                then().
                                statusCode(200).
                                extract().
                                response();
       BookResponse[] book=getBookResponse.as(BookResponse[].class);
        Assert.assertEquals(book[0].author_name,"Amar","Test failed ,Author name is not correct");
    }
    @Test
    public void validateGetBookUsingIdTest()
    {
        RestAssured.baseURI="http://216.10.245.166";
        Response response=given().queryParam("ID","Prammu12345").
                header("Content-Type","application/json").
                when().get("Library/GetBook.php").
                then().statusCode(200).extract().response();
        Assert.assertEquals(response.asString(),"[{\"book_name\":\"Learn selenium Automation with Java\",\"isbn\":\"Prammu12\",\"aisle\":\"345\",\"author\":\"John foe2\"}]");

    }
    @Test
    public void validateGetBookUsingIdWithDeserializationTest()
    {
        RestAssured.baseURI="http://216.10.245.166";
        Response response=given().queryParam("ID","Prammu12345").
                header("Content-Type","application/json").
                when().get("Library/GetBook.php").
                then().statusCode(200).extract().response();
        BookResponse[] book=response.as(BookResponse[].class);
        Assert.assertEquals(book[0].getAuthorname(),"John foe2");
    }

    @Test
    public void validateGetBookUsingAuthorNameWithDeserializationTest()
    {
        RestAssured.baseURI="http://216.10.245.166";
        Response response=given().queryParam("AuthorName","Prithu").
                header("Content-Type","application/json").
                when().get("Library/GetBook.php").
                then().statusCode(200).extract().response();
        BookResponse[] book=response.as(BookResponse[].class);
        Assert.assertEquals(book[0].getBook_name(),"Learn testing","Test failed because the author name is incorrect");
    }
    @Test
    public void verifyAddBookTest()
    {
        RestAssured.baseURI="http://216.10.245.166/";
        AddBookRequest request=new AddBookRequest();
        request.setBook_name("Learning RestAPI");
        request.setIsbn("007557");
        request.setAisle("abcd");
        request.setAuthor_name("Piyush");
        Response responseAdd=given().header("Content-Type","application/json").
                body(request).
                when().
                post("Library/Addbook.php").
                then().assertThat().statusCode(200).extract().response();
        AddBookResponse res=responseAdd.body().as(AddBookResponse.class);
        Assert.assertEquals(res.getMsg(),"successfully added");
        Assert.assertEquals(res.getID(),"007557abcd");

    }
}