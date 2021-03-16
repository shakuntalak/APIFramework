package com.book.ResponseModel;
import com.fasterxml.jackson.annotation.JsonProperty;

import static io.restassured.RestAssured.*;

import static io.restassured.RestAssured.given;
public class AddBookResponse
{
    String Msg;
    String ID;
    String msg;

//    @JsonProperty("msg")
//    public void setMsgError(String msg) {
//        msg = msg;
//    }
//
//
//    public String getMsgError() {
//        return msg;
//    }

    @JsonProperty("Msg")
    public void setMsg(String msg) {
        Msg = msg;
    }
    @JsonProperty("ID")
    public void setID(String ID) {
        this.ID = ID;
    }

    public String getMsg() {
        return Msg;
    }

    public String getID() {
        return ID;
    }
}
